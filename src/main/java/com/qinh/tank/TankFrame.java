package com.qinh.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * 继承Frame的目的是为了重写父类的方法
 *
 * @author Qh
 * @version 1.0
 * @date 2021-11-02 21:29
 */
public class TankFrame extends Frame {

    private int x = 200,y = 200;
    private int speed = 10;

    public TankFrame() throws HeadlessException {
        //设置大小
        setSize(800,600);
        //设置是否可以改变窗口大小
        setResizable(false);
        //设置窗口名称
        setTitle("tank war");
        //设置窗口是否可见
        setVisible(true);
        //关闭窗口，创建匿名子类的匿名对象
        addWindowListener(new WindowAdapter() {
            //窗口正在关闭
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        //键盘监听事件
        addKeyListener(new MyKeyListener());

    }

    /**
     * @param g 画笔
     */
    @Override
    public void paint(Graphics g) {
        //System.out.println("paint");
        /*
        画一个小黑方块
        以窗口的左上角为起点，横着向右的为x轴，竖着向下的为y轴
         */
        g.fillRect(x, y, 50, 50);
        //让方块移动起来
        //x += 10;
        //y += 10;
    }

    class MyKeyListener extends KeyAdapter {

        /**
         * 4个方向值，根据按键判定坦克的移动方向
         */
        boolean bL = false;
        boolean bR = false;
        boolean bU = false;
        boolean bD = false;
        /**
         * 键盘的一个键被按下去的时候调用
         * @param e
         */
        @Override
        public void keyPressed(KeyEvent e) {
            //System.out.println("keyPressed");
            //x += 200;
            //画笔重新画
            //repaint();
            //取出键值代码
            int keyCode = e.getKeyCode();
            switch (keyCode){
            case KeyEvent.VK_LEFT:
                //x -= 10;
                bL = true;
                break;
            case KeyEvent.VK_RIGHT:
                //x += 10;
                bR = true;
                break;
            case KeyEvent.VK_UP:
                //y -= 10;
                bU = true;
                break;
            case KeyEvent.VK_DOWN:
                //y += 10;
                bD = true;
                break;
            default:
                break;
            }
            /*
            根据4个boolean值，计算坦克方向，根据tank方向和速度，自动移动位置（假设tank不能停）
             */


        }
        /**
         * 键盘的一个键被抬起来的时候调用
         * @param e
         */
        @Override
        public void keyReleased(KeyEvent e) {
            //System.out.println("keyReleased");
            //取出键值代码
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
                default:
                    break;
            }
        }
    }

}
