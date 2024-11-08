package com.example.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu);
        getSupportActionBar().setLogo(R.mipmap.ttt_ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

    }

    public void playBtnClick(View view){
        startActivity(new Intent(this, PlayerSetup.class));

    }
}