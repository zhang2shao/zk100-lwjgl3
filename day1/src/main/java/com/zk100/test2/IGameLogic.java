package com.zk100.test2;

import java.awt.*;

/**
 * 获取输入、更新游戏状态和呈现特定于游戏的数据的方法
 */
public interface IGameLogic {

    /**
     * 初始化
     * @throws Exception
     */
    void init() throws Exception;

    /**
     * 获取输入内容
     * @param window    窗口信息
     */
    void input(Window window);

    /**
     * 更新游戏状态
     * @param interval  时间间隔
     */
    void update(float interval);

    /**
     * 游戏渲染
     * @param window    窗口信息
     */
    void render(Window window);
}
