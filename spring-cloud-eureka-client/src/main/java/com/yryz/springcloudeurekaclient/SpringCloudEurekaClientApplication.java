package com.yryz.springcloudeurekaclient;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaClient
@RestController
@EnableHystrix
@EnableHystrixDashboard
public class SpringCloudEurekaClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudEurekaClientApplication.class, args);
    }

    @Value("${server.port}")
    public String port;

    @RequestMapping("hi")
    @HystrixCommand(fallbackMethod = "hiError")
    public String helloWorld(@RequestParam String name){
        return "hi " + name + ",Im from port: " + port;
    }

    public String hiError(String name) {
        return "Hi,"+ name +" this is a error page of service hi!";
    }
}
