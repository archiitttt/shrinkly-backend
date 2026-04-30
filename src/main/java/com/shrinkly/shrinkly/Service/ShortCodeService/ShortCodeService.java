package com.shrinkly.shrinkly.Service.ShortCodeService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShortCodeService{

    private final ShortCodeStrategy strategy;

    public String generateShortCode(long id){
        return strategy.generateShortCode(id);
    }

}
