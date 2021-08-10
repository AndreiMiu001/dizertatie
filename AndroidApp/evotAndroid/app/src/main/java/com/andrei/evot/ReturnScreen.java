package com.andrei.evot;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ReturnScreen extends AppCompatActivity {
    private TextView finalMsg;
    private Button finishBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.return_screen);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Bundle b =getIntent().getExtras();
        finalMsg = (TextView) findViewById(R.id.finalMsg);
        finishBtn = (Button) findViewById(R.id.finishBtn);
        finalMsg.setText(b.getString("final"));

        finishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRestart();
            }
        });

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Intent intent = new Intent(getBaseContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }
}
