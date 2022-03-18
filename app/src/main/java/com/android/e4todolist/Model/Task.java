package com.android.e4todolist.Model;

public class Task {

    private String name;
    private boolean done;


    public Task(String name) {
        this.name = name;
        this.done = false;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }


}
