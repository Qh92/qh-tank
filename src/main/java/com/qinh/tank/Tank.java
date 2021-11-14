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
    /** 四个方向值 */
    private boolean bL,bU,bR,bD;
    /** 坦克速度 */
    private static final int SPEED = 5;
    /** 坦克是否运动，初始为静止状态 */
    private boolean moving = false;
    /** 画板 */
    private TankFrame tf = TankFrame.INSTANCE;

    /** 坦克分组 */
    private Group group;

    public Tank(int x, int y, Dir dir,Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
    }



    public void paint(Graphics g) {

        if (this.group == Group.GOOD){
            //画图片
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
        }else if (this.group == Group.BAD){
            //画图片
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
        }

        move();

        //g.drawImage(ResourceMgr.getTankL(),x,y,null);
        //Color color = g.getColor();
        //g.setColor(Color.YELLOW);
        //g.fillRect(x, y, 50, 50);
        //g.setColor(color);
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


        /*int keyCode = e.getKeyCode();
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
        }*/
    }

    private void fire() {
        tf.getBullets().add(new Bullet(x, y, dir,group));
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
