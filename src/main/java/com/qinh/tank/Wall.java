package com.qinh.tank;

import java.awt.*;

/**
 * 墙
 *
 * @author Qh
 * @version 1.0
 * @date 2021-11-24 0:04
 */
public class Wall extends AbstractGameObject{
    private int x, y, w, h;

    public Wall(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public void paint(Graphics g){
        Color color = g.getColor();
        g.setColor(Color.gray);
        g.fillRect(x, y, w, h);
        g.setColor(color);
    }

}
