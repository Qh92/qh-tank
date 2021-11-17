package com.qinh.tank;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.Random;
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
    /** 四个方向值 */
    private boolean bL,bU,bR,bD;
    /** 坦克速度 */
    private static final int SPEED = 5;
    /** 坦克是否运动，初始为静止状态 */
    private boolean moving = true;
    /** 坦克是否存活 */
    private boolean live = true;

    /** 坦克分组 */
    private Group group;

    public Tank(int x, int y, Dir dir,Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
    }



    public void paint(Graphics g) {

        if (!isLive()){
            return;
        }

        switch (dir){
            case LEFT:
                g.drawImage(ResourceMgr.badTankL,x,y,null);
                break;
            case UP:
                g.drawImage(ResourceMgr.badTankU,x,y,null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.badTankR,x,y,null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.badTankD,x,y,null);
                break;
            default:
                break;
        }

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
        randomDir();
        fire();
    }

    //private Random random = new Random();
    private void randomDir() {
        this.dir = Dir.randomDir();
    }


    private void fire() {
        //将子弹的位置设置在坦克正中心
        int bulletX = x + (ResourceMgr.goodTankU.getWidth() - ResourceMgr.bulletU.getWidth()) / 2;
        int bulletY = y + (ResourceMgr.goodTankU.getHeight() - ResourceMgr.bulletU.getHeight()) / 2;
        TankFrame.INSTANCE.getBullets().add(new Bullet(bulletX, bulletY, dir,group));
    }

    public void die() {
        this.setLive(false);
    }

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
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

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
