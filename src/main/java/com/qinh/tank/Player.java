package com.qinh.tank;

import com.qinh.tank.strategy.DefaultFireStrategy;
import com.qinh.tank.strategy.FireStrategy;
import com.qinh.tank.strategy.FourDirFireStrategy;
import com.qinh.tank.strategy.LeftRightFireStrategy;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * 封装tank对象，在主窗口需要tank时就new一个tank对象出来
 * 合适的方法放在合适的类里
 *
 * @author Qh
 * @version 1.0
 * @date 2021-11-05 23:51
 */
public class Player {
    /** 坦克坐标 */
    private int x = 200,y = 200;
    /** 坦克方向 */
    private Dir dir = Dir.DOWN;
    /** 四个方向值 */
    private boolean bL,bU,bR,bD;
    /** 坦克速度 */
    private static final int SPEED = 5;
    /** 坦克是否运动，初始为静止状态 */
    private boolean moving = false;
    /** 坦克是否存活 */
    private boolean live = true;

    /** 坦克分组 */
    private Group group;

    public Player(int x, int y, Dir dir, Group group) {
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
                g.drawImage(ResourceMgr.goodTankL,x,y,null);
                break;
            case UP:
                g.drawImage(ResourceMgr.goodTankU,x,y,null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.goodTankR,x,y,null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.goodTankD,x,y,null);
                break;
            default:
                break;
        }
        move();
    }

    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode){
            case KeyEvent.VK_LEFT:
                bL = true;
                break;
            case KeyEvent.VK_RIGHT:
                bR = true;
                break;
            case KeyEvent.VK_UP:
                bU = true;
                break;
            case KeyEvent.VK_DOWN:
                bD = true;
                break;
            default:
                break;
        }
        setMainTankDir();
    }

    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode){
            case KeyEvent.VK_LEFT:
                bL = false;
                break;
            case KeyEvent.VK_RIGHT:
                bR = false;
                break;
            case KeyEvent.VK_UP:
                bU = false;
                break;
            case KeyEvent.VK_DOWN:
                bD = false;
                break;
            case KeyEvent.VK_CONTROL:
                fire();
                break;
            default:
                break;
        }
        setMainTankDir();
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

    public void setMainTankDir() {

        if (!bL && !bU && !bR && !bD){
            moving = false;
            return;
        }
        moving = true;
        if (bL && !bU && !bR && !bD){
            dir = Dir.LEFT;
        }
        if (!bL && bU && !bR && !bD){
            dir = Dir.UP;
        }
        if (!bL && !bU && bR && !bD){
            dir = Dir.RIGHT;
        }
        if (!bL && !bU && !bR && bD){
            dir = Dir.DOWN;
        }
    }

    private void fire() {
        ClassLoader loader = this.getClass().getClassLoader();
        String className = PropertyMgr.get("tankFireStrategy");

        try {
            Class<?> aClass = loader.loadClass("com.qinh.tank.strategy." + className);
            FireStrategy strategy = (FireStrategy)aClass.newInstance();
            strategy.fire(this);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
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
