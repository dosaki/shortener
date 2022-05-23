package net.dosaki.l.services;

import net.dosaki.l.domain.ShortUrl;
import net.dosaki.l.repository.ShortUrlRepository;
import net.dosaki.l.utils.numbers.RandomNumberGenerator;
import net.dosaki.l.utils.numbers.URLSafeBase64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Optional;

@Service
public class UrlShorteningServiceImpl implements UrlShorteningService {
    private final ShortUrlRepository shortUrlRepository;

    @Autowired
    public UrlShorteningServiceImpl(ShortUrlRepository shortUrlRepository) {
        this.shortUrlRepository = shortUrlRepository;
    }

    private String generateShortUrlString() {
        return URLSafeBase64.fromBase10(RandomNumberGenerator.newLong());
    }

    @Override
    public ShortUrl shortenUrl(String url) {
        if (isValidUrl(url)) {
            Optional<ShortUrl> existingShortUrl = shortUrlRepository.findByOriginalUrl(url);

            if(existingShortUrl.isPresent()) {
                return existingShortUrl.get();
            } else {
                ShortUrl shortUrl = new ShortUrl();
                shortUrl.setOriginalUrl(url);
                shortUrl.setIdentifier(generateShortUrlString());
                shortUrl.setVisits(0);
                return shortUrlRepository.save(shortUrl);
            }
        }
        return null;
    }

    @Override
    public ShortUrl getUrl(String shortUrl) {
        Optional<ShortUrl> existingShortUrl = shortUrlRepository.get(shortUrl);
        return existingShortUrl.orElse(null);
    }

    @Override
    public ShortUrl getUrlForRedirection(String shortUrl) {
        ShortUrl existingShortUrl = this.getUrl(shortUrl);
        if(existingShortUrl != null){
            existingShortUrl.incrementVisits();
            return shortUrlRepository.saveAndFlush(existingShortUrl);
        }
        return null;
    }

    @Override
    public List<ShortUrl> getUrls() {
        return shortUrlRepository.findAll();
    }

    private Boolean isValidUrl(String url) {
        try {
            new URL(url);
        } catch (MalformedURLException malformedURLException) {
            return false;
        }
        return true;
    }
}
