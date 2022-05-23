package net.dosaki.l.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "urls")
public class ShortUrl {
    @Id
    @Column(name = "identifier", updatable = false)
    private String identifier;

    @Column(name = "original_url", updatable = false)
    private String originalUrl;

    @Column(name = "visits")
    private int visits;

    @Column(name = "created_at", insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public int getVisits() {
        return visits;
    }

    public void setVisits(int visits) {
        this.visits = visits;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void incrementVisits() {
        this.visits++;
    }
}
