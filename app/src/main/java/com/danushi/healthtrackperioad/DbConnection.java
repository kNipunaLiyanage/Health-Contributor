package com.danushi.healthtrackperioad;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class DbConnection {
    public static Connection c;
    public static String usernameloadingz;
    public static String emailzloadingz;
    public static String pin_veryfy;
    public static String load_id_preg_tips;
    public static String load_id_contra_tips;
    public static String load_user_passwordz;
    public static String music_loadingid;
    public static String load_nextdate;
    public static String load_avetagecycle;

    public static Connection createCon() throws Exception {

        Class.forName("com.mysql.jdbc.Driver");
       // c = DriverManager.getConnection("jdbc:mysql://192.168.43.200:3307/finalhadcraft", "root", "123");
        //c = DriverManager.getConnection("jdbc:mysql://192.168.42.157:3307/finalhadcraft", "root", "123");
        //c = DriverManager.getConnection("jdbc:mysql://192.168.42.40:3307/finalhadcraft", "root", "123");
        c = DriverManager.getConnection("jdbc:mysql://192.168.1.10:3307/healthappweb", "root", "123");

        return c;

    }

    public static void iud(String sql) throws Exception {

        if (c == null) {
            createCon();
        }
        c.createStatement().executeUpdate(sql);
    }

    public static ResultSet search(String sql) throws Exception {

        if (c == null) {
            createCon();
        }
        return c.createStatement().executeQuery(sql);
    }

}
