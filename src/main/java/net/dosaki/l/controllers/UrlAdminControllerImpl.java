package net.dosaki.l.controllers;

import net.dosaki.l.controllers.dto.ShortUrlDTO;
import net.dosaki.l.controllers.dto.TokenDTO;
import net.dosaki.l.domain.ShortUrl;
import net.dosaki.l.services.UrlShorteningService;
import net.dosaki.l.utils.aspects.Secured;
import net.dosaki.l.utils.mocked.PersistentAuthHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class UrlAdminControllerImpl implements UrlAdminController {
    @Autowired
    private UrlShorteningService urlShorteningService;

    @RequestMapping(value = "/token", method = RequestMethod.GET)
    @ResponseBody
    @Secured("Basic")
    @CrossOrigin(origins = "*")
    public TokenDTO token() {
        return new TokenDTO(PersistentAuthHandler.fetchToken());
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    @Secured("Bearer")
    @CrossOrigin(origins = "*")
    public List<ShortUrlDTO> list() {
        List<ShortUrl> urls = urlShorteningService.getUrls();
        return urls.stream().map(ShortUrlDTO::new).collect(Collectors.toList());
    }
}
