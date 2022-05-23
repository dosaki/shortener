package net.dosaki.l.utils.aspects;

import net.dosaki.l.utils.exceptions.UnauthorizedException;
import net.dosaki.l.utils.mocked.PersistentAuthHandler;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

@Component
@Aspect
@Order(2)
public class SecuredAspect {

    private static Boolean credentialsAreCorrect(String authHeaderValue) {
        String credentials = authHeaderValue.split(" ")[1];
        return PersistentAuthHandler.verifyCredentials(credentials);
    }

    private Boolean isExistingToken(String authHeaderValue) {
        String token = authHeaderValue.split(" ")[1];
        return PersistentAuthHandler.verifyToken(token);
    }

    private Boolean shouldGenerateToken(String type) {
        return type.equalsIgnoreCase("basic");
    }

    private Boolean passesSecurity(String authHeaderValue, String type) {
        return switch (type) {
            case "bearer" -> isExistingToken(authHeaderValue);
            case "basic" -> credentialsAreCorrect(authHeaderValue);
            default -> false;
        };
    }

    @Around("@annotation(Secured)")
    public Object hasCorrectAuthorizationType(ProceedingJoinPoint joinPoint) throws Throwable {
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        Secured annotation = method.getAnnotation(Secured.class);
        String securityType = annotation.value();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String value = request.getHeader("Authorization");
        String authorizationType = value.split(" ")[0].toLowerCase();
        if (!authorizationType.equalsIgnoreCase(securityType)) {
            throw new UnauthorizedException(String.format("Unauthorized: Incorrect authorization type: %s", value));
        }
        if (!passesSecurity(value, authorizationType)) {
            throw new UnauthorizedException("Unauthorized: Invalid credentials");
        }
        if (shouldGenerateToken(authorizationType)) {
            joinPoint.getArgs();
        }
        return joinPoint.proceed();
    }
}
