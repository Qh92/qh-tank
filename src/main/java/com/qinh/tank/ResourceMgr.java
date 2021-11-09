package com.qinh.tank;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 图片管理
 *
 * @author Qh
 * @version 1.0
 * @date 2021-11-09 22:46
 */
public class ResourceMgr {

    private static BufferedImage tankL,tankU,tankR,tankD;

    private static BufferedImage bulletL,bulletU,bulletR,bulletD;

    static {
        try {
            tankL = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/tankL.gif"));
            tankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/tankU.gif"));
            tankR = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/tankR.gif"));
            tankD = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/tankD.gif"));



        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static BufferedImage getTankL() {
        return tankL;
    }

    public static BufferedImage getTankU() {
        return tankU;
    }

    public static BufferedImage getTankR() {
        return tankR;
    }

    public static BufferedImage getTankD() {
        return tankD;
    }
}
