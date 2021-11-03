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
        /*//窗口
        Frame frame = new Frame();
        //设置大小
        frame.setSize(800,600);
        //设置是否可以改变窗口大小
        frame.setResizable(false);
        //设置窗口名称
        frame.setTitle("tank war");
        //设置窗口是否可见
        frame.setVisible(true);
        //关闭窗口，创建匿名子类的匿名对象
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });*/

        TankFrame tankFrame = new TankFrame();

        while (true){
            Thread.sleep(50);
            tankFrame.repaint();
        }


    }
}
