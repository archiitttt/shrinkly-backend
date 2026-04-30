package com.shrinkly.shrinkly.Repository;

import com.shrinkly.shrinkly.Entity.UrlEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface URLRepository extends JpaRepository<UrlEntity, Long>{

    Optional<UrlEntity> findByOriginalUrlAndActiveTrue(String originalUrl);

    Optional<UrlEntity> findByShortCodeAndActiveTrue(String shortCode);

}
