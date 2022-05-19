package algorithm;


import java.util.ArrayList;

/**
 * @description Z字形变换
 */
public class convert {
    public static void main(String[] args) {
        String s = "PAHNAPLSIIGYIR";
        int n = 3;
        String s1 = convert(s, n);
        System.out.println(s1);
    }

    public static String convert(String s, int numRows) {
        int n = s.length();
        int r = numRows;
        if (r == 1 || r>-n) {
            return s;
        }

        int t = r*2-2;
        int c = (n + t - 1) / t * (r - 1);
        char[][] mat = new char[r][c];
        for (int i = 0, x = 0, y = 0; i < n; ++i) {
            mat[x][y] = s.charAt(i);
            if (i % t < r - 1) {
                ++x;    //向下移动
            }else {
                --x;
                ++y;    //向右上移动
            }
        }
        StringBuffer ans = new StringBuffer();
        for (char[] row: mat) {
            for (char ch : row) {
                if (ch != 0) {
                    ans.append(ch);
                }
            }
        }

        return ans.toString();
    }


    /**
     * @description 方法一中每次往矩阵的某一行添加字符时，都会添加到该行上一个字符的右侧，且最后组成答案时用到每行的非空字符。因此我们可以将矩阵的每行初始化为一个空列表，每次向某一行添加字符时，添加到该行的列表末尾即可。
     * @param s
     * @param numRows
     * @return
     */
    public static String convert2(String s, int numRows) {
        int n = s.length();
        int r = numRows;
        if (r == 1 || r >= n) {
            return s;
        }

        StringBuffer [] mat = new StringBuffer[r];
        for (int i = 0; i < r; ++i) {
            mat[i] = new StringBuffer();
        }

        for (int i = 0, x = 0, t = r * 2 - 2; i < n; ++ i) {
            mat[x].append(s.charAt(i));
            if (i % t < r - 1) {
                ++x;
            }else {
                --x;
            }
        }
        StringBuffer ans = new StringBuffer();
        for (StringBuffer row : mat) {
            ans.append(row);
        }
        return ans.toString();
    }


    /**
     * @description 直接构造
     * @param s
     * @param numRows
     * @return
     */
    public static String convert3(String s, int numRows) {
        if(numRows==1){return s;}
        StringBuilder ans=new StringBuilder();
        for(int i=0;i<s.length();i+=(numRows-1)*2){ans.append(s.charAt(i));}
        for(int i=1;i<numRows-1;i++){
            for(int j=0;j*(numRows-1)*2+i<s.length();j++){
                ans.append(s.charAt(j*(numRows-1)*2+i));
                int r=(j+1)*(numRows-1)*2-i;
                if(r<s.length()){ans.append(s.charAt(r));}
            }
        }
        for(int i=numRows-1;i<s.length();i+=(numRows-1)*2){ans.append(s.charAt(i));}
        return ans.toString();
    }
}
