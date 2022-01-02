package com.edkarmanov.firstapigradle.entity;

import com.edkarmanov.firstapigradle.controller.ItemController;

import java.time.LocalDateTime;
import java.util.TimerTask;


public class MyTimerTask extends TimerTask {
    private Item item;
    private ItemController itemController;

    public MyTimerTask(Item item, ItemController itemController) {
        this.item = item;
        this.itemController = itemController;
    }

    @Override
    public void run() {
        item.setDataTime(LocalDateTime.now());
        item.setStatus(MyStatus.FINISHED.getTitle());

        itemController.updateStatus(item, MyStatus.FINISHED.getTitle());
    }
}
