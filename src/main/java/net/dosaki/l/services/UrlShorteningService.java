package net.dosaki.l.services;

import net.dosaki.l.domain.ShortUrl;

import java.util.List;

public interface UrlShorteningService {
    ShortUrl shortenUrl(String url);
    ShortUrl getUrl(String shortUrl);
    ShortUrl getUrlForRedirection(String shortUrl);
    List<ShortUrl> getUrls();
}
