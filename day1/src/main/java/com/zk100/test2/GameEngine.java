package com.zk100.test2;

import java.awt.*;

public class GameEngine implements Runnable{

    private Window window;

    private IGameLogic gameLogic;

    @Override
    public void run() {

    }

    private final Thread gameLoopThread;

    public GameEngine(String windowTitle, int width, int height, boolean vsSync, IGameLogic gameLogic) throws Exception {
        gameLoopThread = new Thread(this, "GAME_LOOP_THREAD");
//        this.window = new Window(windowTitle, width, height, vsSync); //设置窗口的标题，宽度，高度，垂直同步
        this.gameLogic = gameLogic;
        //..[Removed code]..
    }


}
