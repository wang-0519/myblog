package common;

import java.sql.*;

/**
 * Created by admin on 2022/7/24.
 */
public class SQLHelper {

    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String sqltable = "jdbc:mysql://localhost:3306/myblog?useSSL=false&serverTimezone=Hongkong " +
            "&characterEncoding=utf-8&autoReconnect=true";
    private static final String sqlname = "root";
    private static final String sqlpwd = "123456";
    private static SQLHelper mInstance;
    private Connection conn;

    public static SQLHelper getInstance(){
        if (null == mInstance) {
            synchronized (SQLHelper.class) {
                if (null == mInstance) {
                    mInstance = new SQLHelper();
                }
            }
        }
        return mInstance;
    }

    private SQLHelper(){
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(sqltable, sqlname, sqlpwd);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException sql){
            sql.printStackTrace();
        }
    }

    public ResultSet getData(String sql){
        ResultSet set = null;
        try{
            Statement st = conn.createStatement();
            set = st.executeQuery(sql);
        } catch (Exception e){
            e.printStackTrace();
        }
        return set;
    }

    public void closeDatabase(){
        try{
            if (null != conn){
                conn.close();
                conn = null;
            }
        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
