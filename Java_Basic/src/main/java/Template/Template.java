package Template;

import java.util.ArrayList;
import java.util.List;

public class Template {

/*    泛型的本质是为了参数化类型（在不创建新的类型的情况下，通过泛型指定的不同类型来控制形参具体
    限制的类型）。也就是说在泛型使用过程中，操作的数据类型被指定为一个参数，这种参数类型可以用在
    类、接口和方法中，分别被称为泛型类、泛型接口、泛型方法。*/

    private static double add(double a, double b) {
        System.out.println(a + "+" + b + "=" + (a + b));
        return a + b;
    }

    private static <T extends Number> double add(T a,T b){
        System.out.println(a + "+" + b + "=" + (a.doubleValue() + b.doubleValue()));
        return a.doubleValue()+b.doubleValue();
    }

   List<String> list =new ArrayList<String>();
    // 设置后list中只能放String, 不能放其它类型的元素


}
