package com.edkarmanov.firstapigradle.service;

import com.edkarmanov.firstapigradle.entity.Item;

import java.util.List;

public interface ItemService {
    public void createItem(Item item);

    public Item getItem(String uuid);

    public List<Item> getAllItems();
}
