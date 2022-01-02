package com.edkarmanov.firstapigradle.service;

import com.edkarmanov.firstapigradle.dao.ItemDAO;
import com.edkarmanov.firstapigradle.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService{

    @Autowired
    private  ItemDAO itemDAO;


    @Override
    @Transactional
    public void createItem(Item item) {
        itemDAO.createItem(item);
   }

    @Override
    @Transactional
    public Item getItem(String uuid) {
        return itemDAO.getItem(uuid);
    }

    @Override
    @Transactional
    public List<Item> getAllItems() {
        return itemDAO.getAllItems();
    }


}
