package com.edkarmanov.firstapigradle.entity;

public enum MyStatus {
    CREATED("CREATED"),
    RUNNING("RUNNING"),
    FINISHED("FINISHED");

    private String title;

    MyStatus(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public static MyStatus getByTitle(String title) {
        for (MyStatus status : values()) {
            if (status.getTitle().equalsIgnoreCase(title)) {
                return status;
            }
        }
        throw new RuntimeException("Unknown action type: " + title);
    }
}
