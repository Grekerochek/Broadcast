package com.alexander.broadcast;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;



public class MyIntentService extends IntentService {

    private static final String MESSAGE_KEY = "message_key";
    private static final String MANAGER_STATE = "managerState";


    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        switch(intent.getStringExtra(MESSAGE_KEY)){
            case "A": StateManager.getInstance().setState("A");
            sendBroadcast(StateManager.getInstance().getState());
            break;
            case "B": StateManager.getInstance().setState("B");
            sendBroadcast(StateManager.getInstance().getState());
            break;
            case "C": StateManager.getInstance().setState("C");
            sendBroadcast(StateManager.getInstance().getState());
            break;
            case "D": StateManager.getInstance().setState("D");
            sendBroadcast(StateManager.getInstance().getState());
            break;
            case "E": StateManager.getInstance().setState("E");
            sendBroadcast(StateManager.getInstance().getState());
            break;

        }
    }

    private void sendBroadcast(String state){
        Intent broadcastIntent = new Intent("com.alexander.SEND_MESSAGES_FILTER");
        broadcastIntent.putExtra(MANAGER_STATE, state);
        broadcastIntent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        sendBroadcast(broadcastIntent);
    }

    public static final Intent getIntetnForSend(@NonNull Context context, @NonNull String message){
        Intent intent = newIntent(context);
        intent.putExtra(MESSAGE_KEY, message);
        return intent;
    }

    private static final Intent newIntent(Context context) {
        Intent intent = new Intent(context, MyIntentService.class);
        return intent;
    }
}