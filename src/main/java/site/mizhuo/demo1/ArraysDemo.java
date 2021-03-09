package site.mizhuo.demo1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: Mizhuo
 * @time: 2021/3/9 10:21 下午
 * @description:
 */
public class ArraysDemo {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7);
        List<Integer> list2 = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7));
        System.out.println(list2);
        list2.add(8);
        System.out.println(list2);
        list.add(8);
        System.out.println(list);
    }
}
