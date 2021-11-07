package com.qinh.tank;

import java.awt.*;

/**
 * 子弹类
 *
 * @author Qh
 * @version 1.0
 * @date 2021-11-06 21:44
 */
public class Bullet {
    /** 子弹速度 */
    private static final int SPEED = 10;
    /** 子弹位置 */
    private int x,y;
    /** 子弹方向 */
    private Dir dir;
    /** 子弹宽度 */
    private static final int WIDTH = 5;
    /** 子弹高度 */
    private static final int HEIGHT = 5;
    /** 子弹是否存活 */
    private boolean live = true;
    private TankFrame tf;

    public Bullet(int x, int y, Dir dir,TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
    }

    public void paint(Graphics g) {
        if (!live){
            tf.getBullets().remove(this);
        }

        Color color = g.getColor();
        g.setColor(Color.RED);
        g.fillOval(x, y, WIDTH, HEIGHT);
        g.setColor(color);
        move();
    }

    private void move() {
        switch (dir){
            case LEFT:
                x -= SPEED;
                break;
            case UP:
                y -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
            default:
                break;
        }

        if (x < 0 || x > tf.getWidth() || y < 0 || y > tf.getHeight()){
            live = false;
        }

    }

}
