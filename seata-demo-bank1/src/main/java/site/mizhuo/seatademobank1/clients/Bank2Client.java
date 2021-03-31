package site.mizhuo.seatademobank1.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author: Mizhuo
 * @time: 2021/3/29 10:47 下午
 * @description:
 */
@FeignClient(value = "seata-demon-bank2",fallback = Bank2ClientFallBack.class)
public interface Bank2Client {
    @GetMapping("/bank2/transfer")
    String transfer(@RequestParam double amount);
}
