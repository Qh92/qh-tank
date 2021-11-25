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
public class Tank extends AbstractGameObject{
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
    /** 记录敌人坦克的上一个位置 */
    private int oldX,oldY;
    /** 坦克的宽度、高度 */
    private int width,height;

    /** 坦克分组 */
    private Group group;

    public Tank(int x, int y, Dir dir,Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.oldX = x;
        this.oldY = y;

        this.width = ResourceMgr.goodTankU.getWidth();
        this.height = ResourceMgr.goodTankU.getHeight();
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

        oldX = x;
        oldY = y;

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

        boundsCheck();
        randomDir();
        if (random.nextInt(100) > 90 ) {
            fire();
        }
    }

    private Random random = new Random();
    private void randomDir() {
        if (random.nextInt(100) > 95) {
            this.dir = Dir.randomDir();
        }
    }


    private void fire() {
        //将子弹的位置设置在坦克正中心
        int bulletX = x + (ResourceMgr.goodTankU.getWidth() - ResourceMgr.bulletU.getWidth()) / 2;
        int bulletY = y + (ResourceMgr.goodTankU.getHeight() - ResourceMgr.bulletU.getHeight()) / 2;
        TankFrame.INSTANCE.add(new Bullet(bulletX, bulletY, dir,group));
    }

    private void boundsCheck() {
        if (x < 0 || x + width > TankFrame.INSTANCE.getWidth() || y < 30 || y + height > TankFrame.INSTANCE.getHeight()) {
            this.back();
        }
    }

    /**
     * 返回上一个位置
     */
    private void back() {
        this.x = oldX;
        this.y = oldY;
    }

    public void die() {
        this.setLive(false);
        TankFrame.INSTANCE.add( new Explode(x, y));
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
