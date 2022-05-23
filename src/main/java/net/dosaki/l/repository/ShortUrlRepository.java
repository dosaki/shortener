package net.dosaki.l.repository;

import net.dosaki.l.domain.ShortUrl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShortUrlRepository extends JpaRepository<ShortUrl, String> {
    @Query(value = "from ShortUrl u where u.identifier = :identifier")
    Optional<ShortUrl> get(@Param(value = "identifier") String identifier);

    @Query(value = "from ShortUrl u where u.originalUrl = :originalUrl")
    Optional<ShortUrl> findByOriginalUrl(@Param(value = "originalUrl") String originalUrl);
}
