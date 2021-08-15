package com.andrei.evot;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.andrei.evot.model.User;

import java.lang.ref.WeakReference;


public class CommonBasicActivity extends AppCompatActivity {

    private final WeakReference<Context> context = new WeakReference<>(this);

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.bottom_nav_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.navigation_home) {
            Intent showDetailActivity = new Intent(context.get(), MainMenu.class);
            context.get().startActivity(showDetailActivity);
        } else if (itemId == R.id.navigation_logout){
            User.clear();
            Intent intent = new Intent(getBaseContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}