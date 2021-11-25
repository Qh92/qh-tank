package com.qinh.tank.strategy;

import com.qinh.tank.*;

/**
 * @author Qh
 * @version 1.0
 * @date 2021-11-22 22:02
 */
public class DefaultFireStrategy implements FireStrategy{
    @Override
    public void fire(Player player) {
        //将子弹的位置设置在坦克正中心
        int bulletX = player.getX() + (ResourceMgr.goodTankU.getWidth() - ResourceMgr.bulletU.getWidth()) / 2;
        int bulletY = player.getY() + (ResourceMgr.goodTankU.getHeight() - ResourceMgr.bulletU.getHeight()) / 2;
        TankFrame.INSTANCE.add(new Bullet(bulletX, bulletY, player.getDir(),player.getGroup()));
    }
}
