package com.edkarmanov.firstapigradle.dao;

import com.edkarmanov.firstapigradle.entity.Item;

import java.util.List;

public interface ItemDAO {
    public void createItem(Item item);

    public Item getItem(String uuid);

    public List<Item> getAllItems();
}
