package com.alexander.broadcast;

import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView mTextView;
    private Button mButton;

    private CustomBroadcastReceiver mReceiver;
    private IntentFilter mIntentFilter;

    private String [] states = new String[]{"A", "B", "C", "D", "E"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initListeners();
        init();
    }

    private void initViews() {

        mTextView = findViewById(R.id.text);
        mButton = findViewById(R.id.button);
    }

    private void initListeners() {

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startService(MyIntentService.getIntetnForSend(MainActivity.this, states[new Random().nextInt(states.length)]));
            }
        });
    }

    private void init() {

        mReceiver = new CustomBroadcastReceiver(new ViewCallback() {
            @Override
            public void onStateChanged(String state) {
                mTextView.setText(state);
            }
        });

        mIntentFilter = new IntentFilter("com.alexander.SEND_MESSAGES_FILTER");
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(mReceiver, mIntentFilter, "com.alexander.SEND_MESSAGES_PERMISSION", null);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mReceiver);
    }
}
