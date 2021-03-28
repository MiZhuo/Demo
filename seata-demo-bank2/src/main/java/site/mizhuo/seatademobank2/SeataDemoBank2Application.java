package site.mizhuo.seatademobank2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class SeataDemoBank2Application {

    public static void main(String[] args) {
        SpringApplication.run(SeataDemoBank2Application.class, args);
    }

}
