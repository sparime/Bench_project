package com.tdd.demo.userservice;

import com.tdd.demo.userservice.user.PostBean;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

//@FeignClient(name = "post-service", url = "localhost:8080")
//@FeignClient(name = "post-service") //--> Feign client with Eureka naming server
@FeignClient(name = "zuul-api-gateway-server") // --> with Zuul API gateway
@RibbonClient(name = "post-service")
public interface PostServiceProxy {
    @GetMapping("post-service/users/{userId}/posts/")
        //--> for direct feign call
        //@GetMapping("post-service/users/{userId}/posts/") // --> updated mapping to make the request run through Zuul
    List<PostBean> getAllPostsOfAllUser(@PathVariable("userId") int userId);
}
