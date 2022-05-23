package net.dosaki.l.controllers.dto;

import net.dosaki.l.domain.ShortUrl;

public class ShortUrlDTO {
    private final String identifier;
    private final String originalURL;
    private final String createdAt;
    private final Integer visits;

    public ShortUrlDTO() {
        this.identifier = null;
        this.originalURL = null;
        this.createdAt = null;
        this.visits = null;
    }

    public ShortUrlDTO(ShortUrl shortUrl) {
        this.identifier = shortUrl.getIdentifier();
        this.originalURL = shortUrl.getOriginalUrl();
        if(shortUrl.getCreatedAt() != null){
            this.createdAt = shortUrl.getCreatedAt().toString();
        } else {
            this.createdAt = null;
        }
        this.visits = shortUrl.getVisits();
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getOriginalURL() {
        return originalURL;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public Integer getVisits() {
        return visits;
    }

    public String getShortenedURL() {
        return String.format("/%s", this.identifier);
    }
}
