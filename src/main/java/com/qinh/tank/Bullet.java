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
    private TankFrame tf = TankFrame.INSTANCE;

    /** 子弹分组 */
    private Group group;

    public Bullet(int x, int y, Dir dir,Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
    }

    public void paint(Graphics g) {
        if (!live){
            tf.getBullets().remove(this);
        }

//        Color color = g.getColor();
//        g.setColor(Color.RED);
//        g.fillOval(x, y, WIDTH, HEIGHT);
//        g.setColor(color);
        move(g);
    }

    private void move(Graphics g) {
        switch (dir){
            case LEFT:
                g.drawImage(ResourceMgr.bulletL, x, y, null);
                x -= SPEED;
                break;
            case UP:
                g.drawImage(ResourceMgr.bulletU, x, y, null);
                y -= SPEED;
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.bulletR, x, y, null);
                x += SPEED;
                break;
            case DOWN:
                g.drawImage(ResourceMgr.bulletD, x, y, null);
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
