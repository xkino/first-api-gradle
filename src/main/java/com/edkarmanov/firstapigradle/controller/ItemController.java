package com.edkarmanov.firstapigradle.controller;

import com.edkarmanov.firstapigradle.entity.Item;
import com.edkarmanov.firstapigradle.entity.MyStatus;
import com.edkarmanov.firstapigradle.entity.MyTimerTask;
import com.edkarmanov.firstapigradle.exceptions.ItemIncorect;
import com.edkarmanov.firstapigradle.exceptions.NoSuchItemException;
import com.edkarmanov.firstapigradle.exceptions.NotUUIDException;
import com.edkarmanov.firstapigradle.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
@RequestMapping("/api")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/all")
    public List<Item> showAllItems() {
        List<Item> items = itemService.getAllItems();

        return items;
    }

    @GetMapping("/item/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public List<Map<String, String>> getItem(@PathVariable String uuid) {
        if(!uuid.matches("[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}")){
            throw new NotUUIDException("передан не UUID");
        }

        Item item = itemService.getItem(uuid);

        if (item == null){
            throw new NoSuchItemException("такой задачи нет");
        }

        List<Map<String, String>> answer = new ArrayList<>();
        answer.add(new HashMap<>() {{
            put("status", item.getStatus());
            put("timestamp", item.getDataTime().format(DateTimeFormatter.ISO_DATE_TIME));
        }});

        return answer;
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String addNewItem() {
        String uuid = String.valueOf(UUID.randomUUID());
        LocalDateTime dateTime = LocalDateTime.now();
        Item item = new Item(uuid, dateTime, MyStatus.CREATED.getTitle());
        itemService.createItem(item);

        updateStatus(item, MyStatus.RUNNING.getTitle());
        toDoFinished(item);

        return item.getUuid();
    }

    @ExceptionHandler
    public ResponseEntity<ItemIncorect> handlerException(RuntimeException ex) {
        ItemIncorect itemIncorect = new ItemIncorect();
        itemIncorect.setInfo(ex.getMessage());
        ResponseEntity response = null;
        
        if (ex instanceof NoSuchItemException) {
            response = new ResponseEntity<>(itemIncorect, HttpStatus.NOT_FOUND);
        } else if (ex instanceof NotUUIDException) {
            response = new ResponseEntity<>(itemIncorect, HttpStatus.BAD_REQUEST);
        }

        return response;
    }

    public void updateStatus(Item item, String status){
        item.setDataTime(LocalDateTime.now());
        item.setStatus(status);
        itemService.createItem(item);
    }

    private void toDoFinished(Item item){
        Timer myTimer = new Timer();
        MyTimerTask myTimerTask = new MyTimerTask(item, this);
        myTimer.schedule(myTimerTask, (60000*2));
    }
}
