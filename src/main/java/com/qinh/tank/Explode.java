package com.qinh.tank;

import java.awt.*;

/**
 * 爆炸类
 *
 * @author Qh
 * @version 1.0
 * @date 2021-11-06 21:44
 */
public class Explode {
    /**
     * 爆炸位置
     */
    private int x, y;
    /**
     * 爆炸宽度
     */
    private int width;
    /**
     * 爆炸高度
     */
    private int height;

    /** 爆炸画到第几步 */
    private int step;
    /** 爆炸是否结束 */
    private boolean live = true;

    public Explode(int x, int y) {
        this.x = x;
        this.y = y;

        this.width = ResourceMgr.explodes[0].getWidth();
        this.height = ResourceMgr.explodes[0].getHeight();

        new Thread(() -> new Audio("audio/explode.wav").play()).start();
    }

    public void paint(Graphics g) {
        if (!isLive()){
            return;
        }
        g.drawImage(ResourceMgr.explodes[step],x,y,null);
        step++;
        if (step >= ResourceMgr.explodes.length){
            this.die();
        }
    }

    private void die() {
        this.live = false;
    }

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }
}
