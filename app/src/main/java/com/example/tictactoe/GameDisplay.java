package com.example.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class GameDisplay extends AppCompatActivity {
    private TicTacToeBoard tictacToeBoard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_game_display);
        getSupportActionBar().setLogo(R.mipmap.ttt_ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        Button resetBtn = findViewById(R.id.resetbtn);
        Button homeBtn = findViewById(R.id.homeBtn);
        TextView txtPlayer = findViewById(R.id.txtPlayer);

        resetBtn.setVisibility(View.GONE);
        homeBtn.setVisibility(View.GONE);

        String[] playerName = getIntent().getStringArrayExtra("PLAYER_NAME");
        if(playerName != null){
            txtPlayer.setText(playerName[0] + ": I'll GO FIRST!");
        }
        tictacToeBoard = findViewById(R.id.ticTacToeBoard);
        tictacToeBoard.setUpGame(resetBtn, homeBtn, txtPlayer, playerName);
    }
    public void playAgainBtnOnClick(View view){
        tictacToeBoard.resetGame();
        tictacToeBoard.invalidate();
    }
    public void homeBtnOnClick(View view){
        startActivity(new Intent(this, Menu.class));
    }
}