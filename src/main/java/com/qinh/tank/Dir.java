package com.qinh.tank;

import java.util.Random;

/**
 * 坦克方向枚举类
 *
 * @author Qh
 * @version 1.0
 * @date 2021-11-05 23:31
 */
public enum Dir {

    /** 左 */
    LEFT,
    /** 上 */
    UP,
    /** 右 */
    RIGHT,
    /** 下 */
    DOWN;


    private static Random r = new Random();
    public static Dir randomDir(){
        return Dir.values()[r.nextInt(Dir.values().length)];
    }

}
