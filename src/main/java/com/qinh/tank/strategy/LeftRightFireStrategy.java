package com.qinh.tank.strategy;

import com.qinh.tank.*;

/**
 * @author Qh
 * @version 1.0
 * @date 2021-11-22 22:09
 */
public class LeftRightFireStrategy implements FireStrategy{

    @Override
    public void fire(Player player) {
        int bulletX = player.getX() + (ResourceMgr.goodTankU.getWidth() - ResourceMgr.bulletU.getWidth()) / 2;
        int bulletY = player.getY() + (ResourceMgr.goodTankU.getHeight() - ResourceMgr.bulletU.getHeight()) / 2;
        TankFrame.INSTANCE.getBullets().add(new Bullet(bulletX, bulletY, Dir.LEFT,player.getGroup()));
        TankFrame.INSTANCE.getBullets().add(new Bullet(bulletX, bulletY, Dir.RIGHT,player.getGroup()));
    }
}
