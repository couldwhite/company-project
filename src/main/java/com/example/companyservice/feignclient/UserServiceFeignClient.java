package com.example.companyservice.feignclient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "user-service",
        path = "/api/users",
        url = "http://user-service:8089"
)
public interface UserServiceFeignClient {

    @GetMapping("/exist-by-id/{userId}")
    ResponseEntity<Boolean> existById(@PathVariable("userId") Long userId);

    @GetMapping("/exist-by-id-for-company/{userId}")
    boolean existByIdCompany(@PathVariable("userId") Long userId);

    @GetMapping("/name-by-id/{id}")
    String getNameById(@PathVariable("id") Long id);
}
