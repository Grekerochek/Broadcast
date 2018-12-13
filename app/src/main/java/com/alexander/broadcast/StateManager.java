package com.alexander.broadcast;

public class StateManager {

    private static final StateManager ourInstance = new StateManager();

    private String state;

    public static StateManager getInstance() {
        return ourInstance;
    }

    private StateManager() {

    }

    public void setState(String state) {
        this.state = state;
    }

    public String getState(){
        return state;
    }
}

