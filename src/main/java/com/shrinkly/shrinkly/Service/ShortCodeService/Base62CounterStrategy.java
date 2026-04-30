package com.shrinkly.shrinkly.Service.ShortCodeService;

import org.springframework.stereotype.Component;

@Component
public class Base62CounterStrategy implements ShortCodeStrategy{

    private static final String ALPHABET = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public String generateShortCode(long id){
        if(id < 0){
            throw new IllegalArgumentException("Value must be non-negative");
        }
        if(id ==0) return "0";

        StringBuilder sb = new StringBuilder();
        while(id > 0){
            int index = (int) (id %62);
            sb.append(ALPHABET.charAt(index));
            id /= 62;
        }

        return sb.reverse().toString();
    }

}
