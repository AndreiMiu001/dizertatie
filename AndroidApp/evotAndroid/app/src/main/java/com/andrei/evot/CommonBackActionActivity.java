package com.andrei.evot;

import android.os.Bundle;
import androidx.appcompat.app.ActionBar;


public class CommonBackActionActivity extends CommonBasicActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_basic);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

}
