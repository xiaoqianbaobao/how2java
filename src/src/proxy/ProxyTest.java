package src.proxy;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Random;
import java.util.logging.Logger;

/**
 * @description 代理
 * @author YiYi
 * @date 2021/10/27
 */
public class ProxyTest {
    public static void main(String[] args) {
        var element = new Object[1000];

        for (int i = 0; i < element.length; i++) {
            Integer value = i + 1;
            var handler = new TraceHandLer(value);
            Object proxy = Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[]{Comparable.class},
                    handler);
            element[i] = proxy;
        }
        Integer key = new Random().nextInt(element.length) + 1;

        int result = Arrays.binarySearch(element, key);

        if (result >= 0) {
            System.out.println(element[result]);
        }
        Logger.getLogger("hello world").fine("showing frame");
    }

    static class TraceHandLer implements InvocationHandler {

        private Object target;

        public TraceHandLer(Object t) {
            target = t;
        }


        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("." + method.getName() + "(");
            if (args != null){
                for (int i = 0; i <args.length ; i++) {
                    System.out.println(args[i]);
                    if (i < args.length - 1){
                        System.out.println(", ");
                    }
                }
                System.out.println(")");
            }
            return method.invoke(target, args);
        }
    }
}
