package com.sngist.sms.fiegn;

import com.sngist.sms.dto.responseDto.PostResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "jsonplaceholder", url = "https://jsonplaceholder.typicode.com")
public interface PostFiegnClient {

    @GetMapping("/posts")
    List<PostResponseDto> getAllPosts();
}
