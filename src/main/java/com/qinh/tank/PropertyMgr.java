package com.qinh.tank;

import java.io.IOException;
import java.util.Properties;

/**
 * 配置类管理
 *
 * @author Qh
 * @version 1.0
 * @date 2021-11-22 20:58
 */
public class PropertyMgr {

    private static Properties porps = new Properties();

    static {
        try {
            porps.load(PropertyMgr.class.getClassLoader().getResourceAsStream("config/config"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String get(String key){
        return (String) porps.get(key);
    }




}
