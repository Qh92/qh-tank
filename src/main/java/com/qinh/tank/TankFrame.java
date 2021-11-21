package com.qinh.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * 面向对象的封装过程
 * 继承Frame的目的是为了重写父类的方法
 *
 * @author Qh
 * @version 1.0
 * @date 2021-11-02 21:29
 */
public class TankFrame extends Frame {

    public static final TankFrame INSTANCE = new TankFrame();

    private static final int GAME_WIDTH = 800;
    private static final int GAME_HEIGHT = 600;

    private Player myTank = null;

    //private Tank enemyTank = new Tank(300, 300, Dir.DOWN,Group.BAD);
    private  List<Tank> tanks = null;

    private List<Bullet> bullets = null;

    //private Explode explode = new Explode(150, 150);
    private List<Explode> explodes = null;

    //private Bullet bullet = new Bullet(300, 300, Dir.DOWN);

    private TankFrame() throws HeadlessException {
        //设置大小
        setSize(GAME_WIDTH,GAME_HEIGHT);
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

        initGameObjects();

    }

    private void initGameObjects() {
        myTank = new Player(200, 200, Dir.DOWN,Group.GOOD);
        tanks = new ArrayList<>();
        bullets = new ArrayList<>();
        explodes = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            tanks.add(new Tank(100 + 50 * i, 200, Dir.DOWN,Group.BAD));
        }
    }

    /**
     * 用双缓冲解决闪烁问题
     */
    private Image offScreenImage = null;
    @Override
    public void update(Graphics g) {
        //先在内存中将图画画好
        if (offScreenImage == null){
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics graphics = offScreenImage.getGraphics();
        Color color = graphics.getColor();
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        graphics.setColor(color);
        paint(graphics);
        //内存中画好后，一次性的将其输出到屏幕上
        g.drawImage(offScreenImage, 0, 0, null);
    }

    /**
     * @param g 画笔
     */
    @Override
    public void paint(Graphics g) {
        Color color = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("子弹的数量: " + bullets.size(), 10, 60);
        g.drawString("enemies: " + tanks.size(), 10, 70);
        g.drawString("爆炸数量: " + explodes.size(), 10, 80);
        g.setColor(color);
        /*
        画一个小黑方块
        以窗口的左上角为起点，横着向右的为x轴，竖着向下的为y轴
         */
        //封装了一个tank过后，如果需要还按这种方式取x,y出来，就会破坏tank的封装性
        //当然tank对象最清楚自己该画成什么样子
        myTank.paint(g);
        for (int i = 0; i < tanks.size(); i++) {
            if (!tanks.get(i).isLive()){
                tanks.remove(i);
            }else {
                tanks.get(i).paint(g);
            }
        }
        for (int i = 0; i < bullets.size(); i++) {
            //先检测是否碰撞
            for (int j = 0; j < tanks.size(); j++) {
                bullets.get(i).collidesWithTank(tanks.get(j));
            }
            if (!bullets.get(i).isLive()){
                bullets.remove(i);
            }else {
                bullets.get(i).paint(g);
            }
        }
        for (int i = 0; i < explodes.size(); i++) {
            if (!explodes.get(i).isLive()){
                explodes.remove(i);
            }else {
                explodes.get(i).paint(g);
            }
        }
    }

    /**
     * 高内聚，低耦合
     */
    class MyKeyListener extends KeyAdapter {

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

            //将方法封装到tank内部
            myTank.keyPressed(e);

            /*
            根据4个boolean值，计算坦克方向，根据tank方向和速度，自动移动位置（假设tank不能停）
             */
            //myTank.setMainTankDir(e);
        }

        /**
         * 键盘的一个键被抬起来的时候调用
         * Ctrl键被抬起的时候打出一颗子弹
         * @param e
         */
        @Override
        public void keyReleased(KeyEvent e) {
            //System.out.println("keyReleased");
            //取出键值代码
            myTank.keyReleased(e);

        }

        /*private void setMainTankDir() {
            if (bL) myTank.setDir(Dir.LEFT);
            if (bU) myTank.setDir(Dir.UP);
            if (bR) myTank.setDir(Dir.RIGHT);
            if (bD) myTank.setDir(Dir.DOWN);
        }*/
    }

    public void add(Explode explode){
        this.explodes.add(explode);
    }

    public void setBullets(List<Bullet> bullets) {
        this.bullets = bullets;
    }

    public List<Bullet> getBullets() {
        return bullets;
    }

    public static int getGameWidth() {
        return GAME_WIDTH;
    }

    public static int getGameHeight() {
        return GAME_HEIGHT;
    }
}
