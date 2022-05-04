package linkedList;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

public class LinkedListTest {
    public static void main(String[] args) {
        var a = new LinkedList<>();
        a.add("Amy");
        a.add("Shoidhj");
        a.add("hdowqdoqdq");

        var b = new LinkedList<>();
        b.add("Bob");
        b.add("Hohdioa");
        b.add("Bjdoiajh");
        b.add("Bjdoaj");

        //merge the world from b to a

        ListIterator<Object> aIter = a.listIterator();
        Iterator<Object> bIter = b.iterator();

        while (bIter.hasNext()) {
            if (aIter.hasNext())  ;
            aIter.add(bIter.next());
        }

        System.out.println(a);

        //remove every second word from b

        bIter = b.iterator();
        while (bIter.hasNext()) {
            bIter.next();
            if (bIter.hasNext()) {
                bIter.next();
                bIter.remove();
            }
        }
        System.out.println(b);

        //remove all words in b from a
        a.removeAll(b);

        System.out.println(a);
    }
}
