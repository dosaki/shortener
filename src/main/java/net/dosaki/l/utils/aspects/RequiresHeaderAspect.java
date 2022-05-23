package net.dosaki.l.utils.aspects;

import net.dosaki.l.utils.exceptions.MissingHeaderException;
import net.dosaki.l.utils.exceptions.UnauthorizedException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Component
@Aspect
@Order(1)
public class RequiresHeaderAspect {
    @Around("@annotation(RequiresHeader)")
    public Object headerCheck(ProceedingJoinPoint joinPoint) throws Throwable {
        RequiresHeader annotation = ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(RequiresHeader.class);
        String requiredHeader = annotation.value();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String value = request.getHeader(requiredHeader);
        if(value == null || value.equals("")){
            throw new MissingHeaderException(requiredHeader);
        }
        return joinPoint.proceed();
    }
}
