package choujiangji.Engulf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Chou extends JFrame {
    int lines = 0;
    //创建一个索引
    int index = 0;
    List<String> list = new ArrayList<>();
    JLabel l1 = null;
    //设置窗体的一些效果(标题，大小，位置)
    public Chou(){
        this.setTitle("英雄抽奖机");
        this.setSize(300,200);
        //设置窗体能否再改变窗体大小
        this.setResizable(false);
        //设置窗体在屏幕正中间
        this.setLocationRelativeTo(null);
        //设置自定义布局
        this.setLayout(null);
        init();
        getNames();
        //设置窗体可见
        this.setVisible(true);
        //关闭窗口时关闭进程
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    //初始化(创建一些需要的控件)
    public void init(){
        //创建两个按钮控件
        JButton jbStart = new JButton("开始");
        JButton jbEnd = new JButton("停止");
        //设置按钮控件位置和大小
        jbStart.setBounds(32,107,93,37);
        jbEnd.setBounds(165,107,93,37);
        //将控件添加到窗体中
        this.add(jbStart);
        this.add(jbEnd);
        //创建一个标签控件
        l1 = new JLabel("???");
        //设置字体属性
        l1.setFont(new Font(Font.DIALOG,Font.BOLD,30));
        //设置标签位置
        l1.setBounds(70,45,120,30);
        this.add(l1);


        //定时器(作用，每隔一段时间，执行一次)
        //100 表示延迟时间单位为毫秒
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //要执行的代码
                int random = 0;
                Random rand = new Random();
                random = rand.nextInt(lines);
                l1.setText(list.get(index));
                index+=random;
                if(index >= list.size() && lines!=0){
                    index = index % lines;
                }
            }
        });
        //点击开始按钮让定时器start
        jbStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //点击以后需要执行的代码
                timer.start();
            }
        });

        jbEnd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer.stop();
            }
        });
    }


    //获取名称的方法
    public void getNames(){
        /*list.add("纳尔");
        list.add("妮蔻");
        list.add("锤石");
        list.add("派克");
        list.add("露露");*/
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("src/choujiangji/Engulf/names.txt"));
            String name = br.readLine();
            while (name != null){
                list.add(name);
                name = br.readLine();
                lines++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new Chou();
    }
}
