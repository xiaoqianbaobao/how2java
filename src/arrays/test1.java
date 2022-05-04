package arrays;

import java.util.ArrayList;
import java.util.Arrays;

public class test1 {
    public static void main(String[] args) {
        int[] nums = {1, 1, 2, 3};
        int length = nums.length;
        if (length == 0) {
            System.out.println("数组长度为0");
        }
        int fast = 1, slow = 1;
        while (fast < length) {
            if (nums[fast] != nums[fast-1]) {
                nums[slow] = nums[fast];
                ++slow;
            }
            ++fast;
        }


        int j = newArrays();


        System.out.println("新数组长度为" + slow);
        System.out.println("方法二新数组长度为:" + j);
    }

    private static int newArrays(){
        int[] nums = {1, 1, 2, 3};
        int length = nums.length;
        if (length == 0) {
            System.out.println("数组长度为0");
        }
        ArrayList arrayList = new ArrayList();
        ArrayList newArrayList = new ArrayList();
        for (int i = 0; i < length; ++i) {
            arrayList.add(nums[i]);
        }
        newArrayList = arrayList;
        for (int i = 0; i < nums.length; i++) {
            if (arrayList.get(i).equals(arrayList.get(i+1))) {
                newArrayList.remove(i+1);
            }
            System.out.println(newArrayList.size());
        }
        return newArrayList.size();
    }

}

