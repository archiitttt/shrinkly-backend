package com.shrinkly.shrinkly.Service;

import com.shrinkly.shrinkly.Config.AppProperties;
import com.shrinkly.shrinkly.DTO.Response.CreateUrlResponse;
import com.shrinkly.shrinkly.Entity.UrlEntity;
import com.shrinkly.shrinkly.Exceptions.IdInvalidException;
import com.shrinkly.shrinkly.Exceptions.ShortUrlNotFoundException;
import com.shrinkly.shrinkly.Repository.URLRepository;
import com.shrinkly.shrinkly.Service.ShortCodeService.ShortCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class URLService {

    private final URLRepository urlRepo;
    private final ShortCodeService shortCodeService;
    private final SnowflakeService snowflakeService;
    private final AppProperties appProperties;

    public String test(int id){
        if(id > 5) throw new IdInvalidException("ID: " + id + " is invalid.");
        return "Valid id.";
    }

    public CreateUrlResponse saveUrl(String originalUrl){

        Optional<UrlEntity> existing = urlRepo.findByOriginalUrl(originalUrl);
        if(existing.isPresent()){
            UrlEntity urlEntity = existing.get();
            String shortUrl = appProperties.getDomain() + "/" + urlEntity.getShortCode();
            return new CreateUrlResponse(originalUrl, shortUrl);
        }

        long shortCodeId = snowflakeService.nextId();
        String shortCode = shortCodeService.generateShortCode(shortCodeId);

        UrlEntity entity = UrlEntity.builder()
                .originalUrl(originalUrl)
                .shortCode(shortCode)
                .isActive(true)
                .build();

        urlRepo.save(entity);
        return new CreateUrlResponse(originalUrl, appProperties.getDomain() + "/" + shortCode);
    }

    public String redirectToOriginalUrl(String shortCode){

        Optional<UrlEntity> existing = urlRepo.findByShortCode(shortCode);

        if(existing.isEmpty()) throw new ShortUrlNotFoundException("Short URL not found.");

        return existing.get().getOriginalUrl();

    }

}
