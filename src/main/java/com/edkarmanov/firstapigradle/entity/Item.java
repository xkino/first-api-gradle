package com.edkarmanov.firstapigradle.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "uuid")
    private String uuid;

    @Column(name = "datatime")
    private LocalDateTime dataTime;

    @Column(name = "status")
    private String status;

    public Item() {
    }

    public Item(String uuid, LocalDateTime dataTime, String status) {
        this.uuid = uuid;
        this.dataTime = dataTime;
        this.status = status;
    }

    // 1. изменить статусс через 2 мин.

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public LocalDateTime getDataTime() {
        return dataTime;
    }

    public void setDataTime(LocalDateTime dataTime) {
        this.dataTime = dataTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
