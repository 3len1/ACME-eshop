package com.acme.eshop.service;

import com.acme.eshop.domain.Item;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component("itemService")
@Transactional
public class ItemServiceImpl implements ItemService {

    @Override
    public List<Item> getAll() {
        return null;
    }

    @Override
    public Item getItem(int id) {
        return null;
    }

    @Override
    public Item updateItem(int id) {
        return null;
    }

    @Override
    public void deleteItem(int id) {

    }

    @Override
    public Item createItem() {
        return null;
    }
}
