package com.qinh.tank;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * 封装tank对象，在主窗口需要tank时就new一个tank对象出来
 * 合适的方法放在合适的类里
 *
 * @author Qh
 * @version 1.0
 * @date 2021-11-05 23:51
 */
public class Tank {
    /** 坦克坐标 */
    private int x = 200,y = 200;
    /** 坦克方向 */
    private Dir dir = Dir.DOWN;
    /** 坦克速度 */
    private static final int SPEED = 5;
    /** 坦克是否运动，初始为静止状态 */
    private boolean moving = false;
    /** 画板 */
    private TankFrame tf = null;

    public Tank(int x, int y, Dir dir,TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
    }

    public void paint(Graphics g) {
        Color color = g.getColor();
        g.setColor(Color.YELLOW);
        g.fillRect(x, y, 50, 50);
        g.setColor(color);
        move();
    }

    private void move() {
        if (!moving) {
            return;
        }
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
    }

    public void setMainTankDir(int keyCode) {
        if (keyCode != KeyEvent.VK_LEFT
                && keyCode != KeyEvent.VK_RIGHT
                && keyCode != KeyEvent.VK_UP
                && keyCode != KeyEvent.VK_DOWN
        ){
            moving = false;
            return;
        }
        //在按下方向键后设置坦克移动
        moving = true;
        switch (keyCode){
            case KeyEvent.VK_LEFT:
                setDir(Dir.LEFT);
                break;
            case KeyEvent.VK_RIGHT:
                setDir(Dir.RIGHT);
                break;
            case KeyEvent.VK_UP:
                setDir(Dir.UP);
                break;
            case KeyEvent.VK_DOWN:
                setDir(Dir.DOWN);
                break;
            default:
                break;
        }
    }

    public void fire() {
//        try {
//            TimeUnit.MILLISECONDS.sleep(20);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        tf.getBullets().add(new Bullet(x, y, dir,this.tf));
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public static int getSPEED() {
        return SPEED;
    }


}
