package sql;

import java.sql.*;

public class createSqlFile  extends Thread{
    public void run(){
        String url = "jdbc:mysql://localhost/depodb?useUnicode=true&characterEncoding=UTF-8";
        String name = "com.mysql.jdbc.Driver";
        String user = "root";
        String password = "admin";

        Connection conn = null;
        try {
            Class.forName(name);
            conn = DriverManager.getConnection(url, user, password);//获取连接
            conn.setAutoCommit(false);//关闭自动提交，不然conn.commit()运行到这句会报错
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // 开始时间
        Long begin = System.currentTimeMillis();
        // sql前缀
        String prefix = "INSERT INTO agrm_pblc_inf (ent_num,inr_agrm_num,ext_agrm_num,cst_num) VALUES";
        try {
            // 保存sql后缀
            StringBuffer suffix = new StringBuffer();
            // 设置事务为非自动提交
            conn.setAutoCommit(false);
            // 比起st，pst会更好些
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement("");//准备执行语句
            // 外层循环，总提交事务次数
            for (int i = 1; i <= 10; i++) {
                    suffix = new StringBuffer();
                // 第j次提交步长
                for (int j = 1; j <= 100000; j++) {
                    // 构建SQL后缀
                    suffix.append("(’" +0000+ "', '"+i+ "', '"+i+ "', '" + 100000002+i + "')");
                }
                // 构建完整SQL
                String sql = prefix + suffix.substring(0, suffix.length() - 1);
                // 添加执行SQL
                pst.addBatch(sql);
                // 执行操作
                pst.executeBatch();
                // 提交事务
                conn.commit();
                // 清空上一次添加的数据
                suffix = new StringBuffer();
            }
            // 头等连接
            pst.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // 结束时间
        Long end = System.currentTimeMillis();
        // 耗时
        System.out.println("100万条数据插入花费时间 : " + (end - begin) / 1000 + "s");
    }

    public static void main(String[] args) {
        for (int i=0; i<10; i++) {
            new createSqlFile().start();
        }
    }
}
