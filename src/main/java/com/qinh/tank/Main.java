package com.qinh.tank;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * 前期准备工作，认识Frame类
 *
 * @author Qh
 * @version 1.0
 * @date 2021-11-02 21:09
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {

        new Thread(() -> new Audio("audio/war1.wav").loop()).start();

        while (true){
            Thread.sleep(50);
            TankFrame.INSTANCE.repaint();
        }
    }
}
