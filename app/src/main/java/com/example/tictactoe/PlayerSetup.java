package com.example.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PlayerSetup extends AppCompatActivity {

    private EditText player1;
    private EditText player2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_player_setup);
        getSupportActionBar().setLogo(R.mipmap.ttt_ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        player1 = findViewById(R.id.name1);
        player2 = findViewById(R.id.name2);
    }

    public void submitBtnOnclick(View view){
        String player1Name = player1.getText().toString();
        String player2Name = player2.getText().toString();

        Intent intent = new Intent(this, GameDisplay.class);
        intent.putExtra("PLAYER_NAME", new String [] {player1Name, player2Name});
        startActivity(intent);
    }
}