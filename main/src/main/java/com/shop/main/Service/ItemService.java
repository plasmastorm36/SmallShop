package com.shop.main.service;

import com.shop.main.entity.Item;
import com.shop.main.repository.ItemRepository;

import org.springframework.stereotype.Service;

/**
 * Item service to handle item logic
 * @author Noah Rouse
 * @email noahrouse36@gmail.com
 */
@Service
public class ItemService {
   private final ItemRepository repos;
   public ItemService (final ItemRepository itemRepository) {
      this.repos = itemRepository;
   }

   public void save (final Item item) {
      repos.save(item);
   }
}
