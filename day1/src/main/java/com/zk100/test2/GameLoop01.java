package com.zk100.test2;

import java.awt.*;
import java.util.Date;

import static java.lang.Thread.sleep;

public class GameLoop01 {

    private boolean keepOnRunning;// ①根据游戏是否在运行，决定何时停止循环

    public void handleInput(){
        // ②处理输入
    }

    public void updateGameState(){
        // ③更新游戏状态
    }

    public void render(){
        // ④渲染
    }

    /**
     * 获取当前时间
     * @return
     */
    public double getTime(){
        return new Date().getTime();
    }

    /**
     * 001.简单游戏循环逻辑
     */
    public void loop001(){

        while (keepOnRunning) {   // ①根据游戏是否在运行，决定何时停止循环
            handleInput();  // ②处理输入
            updateGameState();    // ③更新游戏状态
            render(); // ④渲染
        }
    }

    /**
     * 002.加入恒定帧，使循环每次以恒定帧的频率进行刷新
     */
    public void loop002(){
        double secsPerFrame = 1.0d / 50.0d;   // 设置一个恒定帧（50FPS）

        while (keepOnRunning) {
            double now = getTime();   // 获取循环开始时间
            handleInput();
            updateGameState();
            render();
            try {
                sleep((long) (now + secsPerFrame - getTime()));    // 休眠一段时间（当前时间-循环开始时间+恒定帧时间）
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 003.如果渲染没有完成，跳过它
     */
    public void loop003(){
        double secsPerUpdate = 1.0d / 30.0d;
        double previous = getTime();
        double steps = 0.0;
        while (true) {
            double loopStartTime = getTime();
            double elapsed = loopStartTime - previous;
            double current = getTime();
            previous = current;
            steps += elapsed;

            handleInput();

            while (steps >= secsPerUpdate) {
                updateGameState();
                steps -= secsPerUpdate;
            }

            render();
            sync(current);
        }
    }

    /**
     * 004.设置同步，减少计算机损耗
     *      当游戏循环时间 不足 固定帧率的时间时，休眠1ms
     * @param loopStartTime 循环开始时间
     */
    private void sync(double loopStartTime) {
        float loopSlot = 1f / 50;
        double endTime = loopStartTime + loopSlot;
        while(getTime() < endTime) {
            try {
                sleep(1);
            } catch (InterruptedException ie) {}
        }
    }

    //同步视频卡的刷新率，交换时间间隔
    glfwSwapInterval(1);

    //将信息存储到缓冲区，交换缓冲区
    glfwSwapBuffers(windowHandle);

    /**
     * 检测游戏循环中的按键
     */
    public boolean isKeyPressed(int keyCode) {
        return glfwGetKey(windowHandle, keyCode) == GLFW_PRESS;
    }

    /**
     * 设置窗体缓冲区的大小回调
     */
    // Setup resize callback
    glfwSetFramebufferSizeCallback(windowHandle, (window, width, height) -> {
        Window.this.width = width;
        Window.this.height = height;
        Window.this.setResized(true);
    });

    /**
     * 加载
     */
    public void init() throws Exception {
    }

    /**
     * 清除
     */
    public void clear() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }



}
