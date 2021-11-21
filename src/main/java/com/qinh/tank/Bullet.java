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
    /**
     * 子弹速度
     */
    private static final int SPEED = 10;
    /**
     * 子弹位置
     */
    private int x, y;
    /**
     * 子弹方向
     */
    private Dir dir;
    /**
     * 子弹宽度
     */
    private static final int WIDTH = 5;
    /**
     * 子弹高度
     */
    private static final int HEIGHT = 5;
    /**
     * 子弹是否存活
     */
    private boolean live = true;

    /**
     * 子弹分组
     */
    private Group group;

    public Bullet(int x, int y, Dir dir, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
    }

    public void paint(Graphics g) {

//        Color color = g.getColor();
//        g.setColor(Color.RED);
//        g.fillOval(x, y, WIDTH, HEIGHT);
//        g.setColor(color);
        move(g);
    }

    private void move(Graphics g) {
        switch (dir) {
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

        //collidesWithTank();

        boundsCheck();
    }

    private void boundsCheck() {
        if (x < 0 || x > TankFrame.INSTANCE.getWidth() || y < 30 || y > TankFrame.INSTANCE.getHeight()) {
            live = false;
        }
    }

    /**
     * 检测子弹和坦克是否碰撞
     * @param tank
     */
    public void collidesWithTank(Tank tank) {
        if (!tank.isLive() || !this.isLive()){
            return;
        }
        if (this.group == tank.getGroup()){
            return;
        }
        Rectangle rectBullet = new Rectangle(x, y, ResourceMgr.bulletU.getWidth(), ResourceMgr.bulletU.getHeight());
        Rectangle rectTank = new Rectangle(tank.getX(), tank.getY(), ResourceMgr.goodTankU.getWidth(), ResourceMgr.goodTankU.getHeight());

        if (rectBullet.intersects(rectTank)) {
            this.die();
            tank.die();
        }
    }

    private void die(){
        this.setLive(false);
    }


    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }
}
