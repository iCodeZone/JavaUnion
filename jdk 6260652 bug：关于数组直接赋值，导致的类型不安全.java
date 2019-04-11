import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


class BaseClass {
}

class SubClass extends BaseClass {
}

/*
 * 6260652 Bug : c.toArray might (incorrectly) not return Object[] (see 6260652)
 * 对于一个Object数组并不一定能够存放Object对象
 * */
public class BugTest {

    @Test
    public void test1() {
        SubClass[] subArray = {new SubClass(), new SubClass()};
        System.out.println(subArray.getClass());

        // class [Lcollection.SubClass;
        // 子类数组转型成父类数组
        BaseClass[] baseArray = subArray;
        System.out.println(baseArray.getClass());

        // java.lang.ArrayStoreException
        // 数组中的每个元素类型都是子类类型 故不合法
        // 也就是说假如有一个Object[]数组，并不代表可以把Object对象存进去
        baseArray[0] = new BaseClass();
    }

    //不安全的情况
    @Test
    public void test2() {
        List<String> list = Arrays.asList("abc");

        // class java.util.Arrays$ArrayList
        System.out.println(list.getClass());

        // class [Ljava.lang.String;
        Object[] objArray = list.toArray();//String[]数组
        System.out.println(objArray.getClass());

        objArray[0] = new Object(); // cause ArrayStoreException
    }

    // 安全的情况
    @Test
    public void test3() {
        List<String> dataList = new ArrayList<>();
        dataList.add("one");
        dataList.add("two");

        Object[] listToArray = dataList.toArray();

        // class [Ljava.lang.Object;返回的是Object数组
        System.out.println(listToArray.getClass());// Object数组
        listToArray[0] = "";
        listToArray[0] = 123;
        listToArray[0] = new Object();

    }


}
