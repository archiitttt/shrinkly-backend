package com.shrinkly.shrinkly.DTO.Response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateUrlResponse {

    private String originalUrl;
    private String shortUrl;

}
