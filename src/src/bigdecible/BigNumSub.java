package src.bigdecible;

import java.math.BigDecimal;

public class BigNumSub {
    public static void main(String[] args) {
        double s1 = 100.00;
        double s2 = 1.00;
        double s3 = 1.00;
        sub(s1,s2,s3);
    }

    public static double sub(double v1, double v2, double v3){
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        BigDecimal b3 = new BigDecimal(Double.toString(v3));
        return b1.subtract(b2).subtract(b3).doubleValue();
    }
}