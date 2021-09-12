package site.mizhuo;

import org.junit.Test;
import site.mizhuo.designPattern.adapter.DockingStation;
import site.mizhuo.designPattern.bridge.DriverManager;
import site.mizhuo.designPattern.bridge.MyDriverManager;
import site.mizhuo.designPattern.bridge.MysqlDriver;
import site.mizhuo.designPattern.bridge.OracleDriver;
import site.mizhuo.designPattern.builder.Meal;
import site.mizhuo.designPattern.builder.MealBuilder;
import site.mizhuo.designPattern.chain.AbstractLogger;
import site.mizhuo.designPattern.chain.ConsoleLogger;
import site.mizhuo.designPattern.chain.ErrorLogger;
import site.mizhuo.designPattern.chain.FileLogger;
import site.mizhuo.designPattern.command.Command;
import site.mizhuo.designPattern.command.Commander;
import site.mizhuo.designPattern.command.MilitaryOrder;
import site.mizhuo.designPattern.command.Soldier;
import site.mizhuo.designPattern.composite.Area;
import site.mizhuo.designPattern.decorator.*;
import site.mizhuo.designPattern.facade.Computer;
import site.mizhuo.designPattern.factory.*;
import site.mizhuo.designPattern.filter.*;
import site.mizhuo.designPattern.filter.ApplePhone;
import site.mizhuo.designPattern.filter.HuaWeiPhone;
import site.mizhuo.designPattern.flyweight.ConnectionPool;
import site.mizhuo.designPattern.interpreter.Context;
import site.mizhuo.designPattern.interpreter.Expression;
import site.mizhuo.designPattern.interpreter.Minus;
import site.mizhuo.designPattern.interpreter.Plus;
import site.mizhuo.designPattern.iterator.Collection;
import site.mizhuo.designPattern.iterator.Iterator;
import site.mizhuo.designPattern.iterator.MyCollection;
import site.mizhuo.designPattern.mediator.User;
import site.mizhuo.designPattern.memento.Archive;
import site.mizhuo.designPattern.memento.ArchiveTaker;
import site.mizhuo.designPattern.memento.Game;
import site.mizhuo.designPattern.prototype.MobileCache;
import site.mizhuo.designPattern.proxy.MySelfRentHouse;
import site.mizhuo.designPattern.proxy.ProxyRentHouse;
import site.mizhuo.designPattern.proxy.RentHouse;
import site.mizhuo.designPattern.singleton.Singleton2;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName TestDemo
 * @Description:
 * @Author: MiZhuo
 * @Create: 2021-08-23 22:17
 * @Version 1.0
 **/
public class TestDemo {
    @Test
    public void test1(){
        MethodFactory factory = new HuaWeiPhoneFactory();
        Mobile mobile = factory.producationMobile();
        mobile.mobileInfo();
    }

    @Test
    public void test2(){
        AbstractFactory factory = new XiaoMiPhoneFactory();
        Mobile mobile = factory.producationMobile();
        mobile.mobileInfo();
    }

    @Test
    public void test3(){
        Singleton2 instance = Singleton2.getInstance();
        System.out.println(instance);
    }

    @Test
    public void test4(){
        MealBuilder mealBuilder = new MealBuilder();
        Meal meal = mealBuilder.prepareVegMeal();
        System.out.println("蔬菜套餐:");
        meal.showItems();
        System.out.println("花费:" + meal.getCost());
        System.out.println("鸡肉套餐:");
        Meal meal2 = mealBuilder.prepareChickenMeal();
        meal2.showItems();
        System.out.println("花费:" + meal2.getCost());
    }

    @Test
    public void test5(){
        MobileCache.loadCache();

        site.mizhuo.designPattern.prototype.Mobile mobile1 = MobileCache.getMobile("apple");
        System.out.println(mobile1);

        site.mizhuo.designPattern.prototype.Mobile  mobile2 = MobileCache.getMobile("huawei");
        System.out.println(mobile2);

        site.mizhuo.designPattern.prototype.Mobile  mobile3 = MobileCache.getMobile("xiaomi");
        System.out.println(mobile3);

    }

    @Test
    public void test6(){
        DockingStation dock = new DockingStation();
        dock.function("typeC-E","220V");
        dock.function("typeC-T","希捷硬盘");
        dock.function("hdmi","LG显示器");
    }

    @Test
    public void test7(){
        DriverManager manager = new MyDriverManager(new MysqlDriver());
        manager.connectDB();
        DriverManager manager2 = new MyDriverManager(new OracleDriver());
        manager2.connectDB();
    }

    @Test
    public void test8(){
        List<Phone> phones = new ArrayList<>();
        phones.add(new Phone("Apple","iphone12 mini",5999.00));
        phones.add(new Phone("Apple","iphone12",6999.00));
        phones.add(new Phone("Apple","iphone12 Pro Max",9999.00));
        phones.add(new Phone("HuaWei","P40",5699.00));
        phones.add(new Phone("HuaWei","Mate40",7999.00));
        phones.add(new Phone("HuaWei","Mate40 Pro",9999.00));
        phones.add(new Phone("XiaoMi","Mix4",9999.00));

        CriteriaPhones p1 = new ApplePhone();
        CriteriaPhones p2 = new HuaWeiPhone();
        CriteriaPhones p3 = new HighEndPhones();
        CriteriaPhones p4 = new MultipleConditionAnd(p1,p3);
        CriteriaPhones p5 = new MultipleConditionOr(p2,p3);

        System.out.println("p1:" + p1.filterPhones(phones));
        phones.stream().filter(p-> "Apple".equalsIgnoreCase(p.getBrand())).forEach(System.out::println);
        System.out.println("--------------------------");
        System.out.println("p2:" +p2.filterPhones(phones));
        phones.stream().filter(p-> "HuaWei".equalsIgnoreCase(p.getBrand())).forEach(System.out::println);
        System.out.println("--------------------------");
        System.out.println("p3:" +p3.filterPhones(phones));
        phones.stream().filter(p-> p.getPrice() > 8888).forEach(System.out::println);
        System.out.println("--------------------------");
        System.out.println("p4:" +p4.filterPhones(phones));
        phones.stream().filter(p-> "Apple".equalsIgnoreCase(p.getBrand())).filter(p -> p.getPrice() > 8888).forEach(System.out::println);
        System.out.println("--------------------------");
        System.out.println("p5:" +p5.filterPhones(phones));
        phones.stream().filter(p-> "HuaWei".equalsIgnoreCase(p.getBrand()) || p.getPrice() > 8888).forEach(System.out::println);
    }

    @Test
    public void test9(){
        Area china = new Area("中国");
        Area beijing = new Area("北京");
        Area zhejiang = new Area("浙江");
        Area hangzhou = new Area("杭州");
        Area chaoyang = new Area("朝阳区");
        Area dongcheng = new Area("东城区");
        beijing.add(chaoyang);
        beijing.add(dongcheng);
        zhejiang.add(hangzhou);
        china.add(beijing);
        china.add(zhejiang);
        System.out.println(china.toString());
    }

    @Test
    public void test10(){
        Animal animal = new Monkey();
        AnimalWithSoul animal1 = new ImmortalAnimal(new Monkey());
        AnimalWithSoul animal2 = new ImmortalAnimal(new Pig());

        animal.abilities();
        animal1.abilities();
        animal2.abilities();
    }

    @Test
    public void test11(){
        Computer computer = new Computer();
        computer.startUp();
        System.out.println("打会儿游戏...");
        computer.shutDown();
    }

    @Test
    public void test12(){
        ConnectionPool connectionPool = new ConnectionPool();
        for(int i = 0;i < 25;i++) {
            Connection connection = connectionPool.getConnection();
            System.out.println(connection);
        }
    }

    @Test
    public void test13(){
        RentHouse rentHouse = new MySelfRentHouse("mizhuo");
        rentHouse.findHouse();
        rentHouse.rentHouse();
        RentHouse house = new ProxyRentHouse("中介","mizhuo");
        house.rentHouse();
    }

    private static AbstractLogger chain(){
        AbstractLogger logger1 = new ConsoleLogger(AbstractLogger.DEBUG);
        AbstractLogger logger2 = new FileLogger(AbstractLogger.INFO);
        AbstractLogger logger3 = new ErrorLogger(AbstractLogger.ERROR);

        logger3.setNextLogger(logger1);
        logger1.setNextLogger(logger2);
        return logger3;
    }

    @Test
    public void test14(){
        AbstractLogger chain = chain();
        chain.log(AbstractLogger.INFO,"This is an information!");
        chain.log(AbstractLogger.DEBUG,"This is a debug level information!");
        chain.log(AbstractLogger.ERROR,"This is an error information!");
    }

    @Test
    public void test115(){
        Soldier soldier = new Soldier();
        Command command = new MilitaryOrder(soldier);
        Commander commander = new Commander(command);
        commander.pushCommand();
    }

    @Test
    public void test16(){
        Context context = new Context();
        context.setNum1(20);
        context.setNum2(10);
        Expression expression = new Plus();
        System.out.println(expression.interpreter(context));
        Expression expression2 = new Minus();
        System.out.println(expression2.interpreter(context));
    }

    @Test
    public void test17(){
        Collection collection = new MyCollection();
        Iterator it = collection.iterator();
        while (it.hasNext()){
            System.out.println(it.next());
        }
    }

    @Test
    public void test18(){
        User user = new User("mizhuo");
        user.sendMessage("你们好呀，我是迷浊。");

        User user2 = new User("miao");
        user2.sendMessage("你好呀，迷浊，我是喵喵。");
    }

    @Test
    public void test19(){
        Archive archive = new Archive();
        ArchiveTaker taker = new ArchiveTaker();
        archive.setStatus("存档1");
        archive.setStatus("存档2");
        taker.add(archive.saveArchive());
        archive.setStatus("存档3");
        taker.add(archive.saveArchive());
        archive.setStatus("存档4");
        System.out.println("当前存档是:" + archive.getStatus());
        System.out.println("恢复第一次存档");
        archive.getStatusFromArchive(taker.getGame(0));
        System.out.println("当前存档是:" + archive.getStatus());
        System.out.println("恢复第二次存档");
        archive.getStatusFromArchive(taker.getGame(1));
        System.out.println("当前存档是:" + archive.getStatus());
    }
}
