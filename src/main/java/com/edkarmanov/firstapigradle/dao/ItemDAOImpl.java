package com.edkarmanov.firstapigradle.dao;

import com.edkarmanov.firstapigradle.entity.Item;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class ItemDAOImpl implements ItemDAO{

    @Autowired
    private EntityManager entityManager;

    @Override
    public void createItem(Item item) {
        Session session = entityManager.unwrap(Session.class);
        session.saveOrUpdate(item);
    }

    @Override
    public Item getItem(String uuid) {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createQuery("from Item where uuid = :uuid");
        query.setParameter("uuid", uuid);

        return (Item) query.uniqueResult();
    }

    @Override
    public List<Item> getAllItems() {
        Session session = entityManager.unwrap(Session.class);
        List<Item> items = session.createQuery("from Item ", Item.class)
                .getResultList();

        return items;
    }
}
