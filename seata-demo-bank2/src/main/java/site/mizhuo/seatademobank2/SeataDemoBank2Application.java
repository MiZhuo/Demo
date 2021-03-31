package site.mizhuo.seatademobank2;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableDiscoveryClient
@EnableHystrix
@MapperScan({"site.mizhuo.seatademobank2.mapper"})
public class SeataDemoBank2Application {

    public static void main(String[] args) {
        SpringApplication.run(SeataDemoBank2Application.class, args);
    }

}
