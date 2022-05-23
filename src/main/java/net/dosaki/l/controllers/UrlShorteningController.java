package net.dosaki.l.controllers;

import net.dosaki.l.controllers.dto.OriginalUrlDTO;
import net.dosaki.l.controllers.dto.ShortUrlDTO;
import org.springframework.web.servlet.view.RedirectView;

public interface UrlShorteningController {
    public RedirectView get(String shortUrl);
    public ShortUrlDTO post(OriginalUrlDTO originalUrl);
}
