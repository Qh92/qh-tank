package com.qinh.tank;

import java.awt.*;

/**
 * 游戏中物体的抽象类
 *
 * @author Qh
 * @version 1.0
 * @date 2021-11-24 0:19
 */
public abstract class AbstractGameObject {

    /**
     * 物体都有画图功能，将其抽象出来
     * @param g
     */
    protected abstract void paint(Graphics g);
}
