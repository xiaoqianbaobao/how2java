package algorithm;


/**
 * @description 给定两个大小分别为m和n的正序（从小到大）数组nums1和nums2。请你找出并返回这两个正序数组的中位数。，算法时间复杂度应该为O(log(m+n))
 * @author csq
 * @date 2022/4/5
 */
public class minnum {
    public static void main(String[] args) {
        int[] nums1 = {1,3};
        int[] nums2 = {2};
        double mid = findMedianSortedArrays(nums1, nums2);
        System.out.println(mid);
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] c = new int[nums1.length + nums2.length];
        int i = 0, j=0,k=0;
        double mid = 0;
        while (i<nums1.length&&j<nums2.length) {
            if (nums1[i]>=nums2[j]) {
                c[k++] = nums2[j++];
            }else {
                c[k++] = nums1[i++];
            }
        }
        while (j<nums2.length) {
            c[k++] = nums2[j++];
        }
        while (i<nums1.length) {
            c[k++] = nums1[i++];
        }

        if (c.length%2 !=0) {
            mid = c[(c.length)/2];
        }else {
            mid = (float)(c[c.length/2-1] + c[c.length/2])/(float) 2;
        }
        return mid;
    }
}
