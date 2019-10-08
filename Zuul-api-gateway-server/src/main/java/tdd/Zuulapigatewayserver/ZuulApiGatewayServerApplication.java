package tdd.Zuulapigatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
public class ZuulApiGatewayServerApplication {

    // to run a request through Zuul API server
    // http://localhost:8765/{app name}/{uri}
    // eg: http://localhost:8765/user-service/users/20/posts/

    public static void main(String[] args) {
        SpringApplication.run(ZuulApiGatewayServerApplication.class, args);
    }


}
