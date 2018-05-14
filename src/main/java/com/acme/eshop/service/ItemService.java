package com.acme.eshop.service;

import com.acme.eshop.domain.Item;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ItemService {

    public List<Item> getAll();
    public Item getItem(int id);
    public Item updateItem(int id);
    public void deleteItem(int id);
    public Item createItem();

}
