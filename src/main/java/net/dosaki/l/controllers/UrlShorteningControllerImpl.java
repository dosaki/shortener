package net.dosaki.l.controllers;

import net.dosaki.l.controllers.dto.OriginalUrlDTO;
import net.dosaki.l.controllers.dto.ShortUrlDTO;
import net.dosaki.l.domain.ShortUrl;
import net.dosaki.l.services.UrlShorteningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/")
public class UrlShorteningControllerImpl implements UrlShorteningController {
    @Autowired
    private UrlShorteningService urlShorteningService;

    @RequestMapping(value = "/{shortUrl}", method = RequestMethod.GET)
    @ResponseBody
    public RedirectView get(@PathVariable(value = "shortUrl") String shortUrl) {
        ShortUrl url = urlShorteningService.getUrlForRedirection(shortUrl);
        RedirectView redirectView = new RedirectView();
        try {
            redirectView.setUrl(url.getOriginalUrl());
        } catch (Exception e) {
            // If anything goes wrong, just go to my page... free traffic right?
            String frontEndUrl = System.getenv("FRONTEND_URL");
            redirectView.setUrl(frontEndUrl == null ? "https://dosaki.net" : frontEndUrl);
        }
        return redirectView;
    }


    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ShortUrlDTO post(@RequestBody OriginalUrlDTO requestBody) {
        try {
            ShortUrl shortUrl = urlShorteningService.shortenUrl(requestBody.getOriginalUrl());
            return new ShortUrlDTO(shortUrl);
        } catch (Exception e) {
            return new ShortUrlDTO();
        }
    }
}
