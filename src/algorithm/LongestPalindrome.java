package algorithm;

/**
 * @description 给你一个字符串s，找到s中最长的回文子串
 */
public class LongestPalindrome {
    public static void main(String[] args) {
        String s = "aabbcsjiaacaca";
        String s1 = longestPalindrome(s);
        System.out.println(s1);
    }


    /**
     * @description 动态规划法 时间复杂度O(n^2)
     * @param s
     * @return
     */
    public static String longestPalindrome(String s) {
        int len = s.length();
        if (len < 2) {
            return  s;
        }

        int maxlen = 1;
        int begin = 0;
        boolean[][] dp = new boolean[len][len];
        //初始化：所有长度为1的子串都是回文串
        for (int i = 0; i<len; i++) {
            dp[i][i] = true;
        }

        char[] charArray = s.toCharArray();
        //递推开始
        //先枚举子串长度
        for (int L = 2; L<=len; L++) {
            //枚举左边界，左边界的上限设置可以宽松一些
            for (int i = 0; i<len; i++) {
                //由L和i可以确定右边界，即j-i+1=L得
                int j = L+i-1;
                //如果右边界越界，就可以推出当前循环
                if (j>= len) {
                    break;
                }
                if (charArray[i] != charArray[j]) {
                    dp[i][j] = false;
                }else {
                    if (j-i < 3) {
                        dp[i][j] = true;
                    }else {
                        dp[i][j] = dp[i+1][j-1];
                    }
                }
                //只要dp[i][L] = true成立，就表示子串s[i..L]是回文，此时记录回文长度和起始位置
                if (dp[i][j] && j-i+1>maxlen) {
                    maxlen = j-i+1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin+maxlen);
    }
}
