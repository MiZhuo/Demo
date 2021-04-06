package site.mizhuo.streamDemo;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author: Mizhuo
 * @time: 2021/4/6 10:25 下午
 * @description:
 */
public class StreamDemo2 {

    /**
     * 练习
     */
    @Test
    public void test11(){
        //1:给定一个数字列表,如何返回每个数字的平方
        List<Integer> list = Arrays.asList(1,2,3,4,5,6);
        list.stream()
                .map(x -> x * x)
                .forEach(System.out::println);
        //2:使用map和reduce计数
        Integer count = list.stream()
                .map((x) -> 1)
                .reduce(Integer::sum)
                .get();
        System.out.println(count);
    }

    List<Transaction> transactions = null;

    @Before
    public void before(){
        Trader raoul = new Trader("Raoul","shanghai");
        Trader mario = new Trader("Mario","hangzhou");
        Trader alan = new Trader("Alan","shanghai");
        Trader brian = new Trader("Brian","shanghai");

        transactions = Arrays.asList(
                new Transaction(raoul,2013,300),
                new Transaction(mario,2015,1000),
                new Transaction(brian,2013,3000),
                new Transaction(alan,2013,900),
                new Transaction(brian,2011,800),
                new Transaction(alan,2015,400),
                new Transaction(alan,2011,200)
        );
    }

    //1:找出2013年发生的交易，并按金额从低到高排序。
    //2:交易员都在哪些城市工作过。
    //3:查找所有来自shanghai的交易员,并按姓名排序。
    //4:返回所有交易员的姓名字符串,按字母顺序排序。
    //5:有没有交易员在hangzhou工作过的。
    //6:打印生活在shanghai的所有交易员的所有交易额
    //7:所有交易中,交易的最高金额是多少.
    //8:找到交易最小的金额。





    class Trader{
        private String name;
        private String city;

        public Trader() {
        }

        public Trader(String name, String city) {
            this.name = name;
            this.city = city;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        @Override
        public String toString() {
            return "Trader{" +
                    "name='" + name + '\'' +
                    ", city='" + city + '\'' +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Trader trader = (Trader) o;
            return name.equals(trader.name) &&
                    city.equals(trader.city);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, city);
        }
    }

    class Transaction{
        private Trader trader;
        private int year;
        private int value;

        public Transaction() {
        }

        public Transaction(Trader trader, int year, int value) {
            this.trader = trader;
            this.year = year;
            this.value = value;
        }

        public Trader getTrader() {
            return trader;
        }

        public void setTrader(Trader trader) {
            this.trader = trader;
        }

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Transaction{" +
                    "trader=" + trader +
                    ", year=" + year +
                    ", value=" + value +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Transaction that = (Transaction) o;
            return year == that.year &&
                    value == that.value &&
                    trader.equals(that.trader);
        }

        @Override
        public int hashCode() {
            return Objects.hash(trader, year, value);
        }
    }
}
