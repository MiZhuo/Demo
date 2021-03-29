package site.mizhuo.seatademobank1.clients;

/**
 * @author: Mizhuo
 * @time: 2021/3/29 10:50 下午
 * @description:
 */
public class Bank2ClientFallBack implements Bank2Client {
    @Override
    public String transfer(Integer id, double amount) {
        return "fallBack";
    }
}
