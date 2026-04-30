package com.shrinkly.shrinkly.Controller;
import com.shrinkly.shrinkly.DTO.Request.CreateUrlRequest;
import com.shrinkly.shrinkly.DTO.Response.CreateUrlResponse;
import com.shrinkly.shrinkly.Service.URLService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class URLController {

    private final URLService urlService;

    @GetMapping("/test/{id}")
    public String testRoute(@PathVariable int id){
        return urlService.test(id);
    }

    @PostMapping("/new")
    public ResponseEntity<CreateUrlResponse> createShortUrl(@RequestBody CreateUrlRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(urlService.saveUrl(request.getUrl()));
    }

    @GetMapping("/{shortCode}")
    public ResponseEntity<?> redirectToOriginalUrl(@PathVariable String shortCode){
        String originalUrl = urlService.redirectToOriginalUrl(shortCode);
        return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY)
                .header("Location", originalUrl)
                .build();
    }

}
