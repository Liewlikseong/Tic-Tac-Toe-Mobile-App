package com.example.tictactoe;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class TicTacToeBoard extends View {
    private final int boardColor;
    private final int XColor;
    private final int OColor;
    private final int winningLineColor;
    private boolean winningLine = false;
    private final Paint paint = new Paint();
    private final Logic logic;
    private int cellSize = getWidth()/3;

    public TicTacToeBoard(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        logic = new Logic();

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs,R.styleable.TicTacToeBoard, 0, 0);

        try{
            boardColor = a.getInteger(R.styleable.TicTacToeBoard_boardColor, 0);
            XColor = a.getInteger(R.styleable.TicTacToeBoard_XColor,0);
            OColor = a.getInteger(R.styleable.TicTacToeBoard_OColor, 0);
            winningLineColor= a.getInteger(R.styleable.TicTacToeBoard_winningLineColor,0);
        }finally {
            a.recycle();
        }
    }

    @Override
    protected void onMeasure(int width, int height){
        super.onMeasure(width, height);

        int dimension = Math.min(getMeasuredWidth(), getMeasuredHeight());
        cellSize = dimension/3;
        setMeasuredDimension(dimension, dimension);
    }

    @Override
    protected void onDraw(Canvas canvas){
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);

        drawGameBoard(canvas);
        drawMarkers(canvas);
        if (winningLine){
            paint.setColor(winningLineColor);
            drawWinningLine(canvas);
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event){
        float x = event.getX();
        float y = event.getY();

        int action = event.getAction();

        if(action == MotionEvent.ACTION_DOWN){
            int row = (int)Math.ceil(y/cellSize);
            int col = (int)Math.ceil(x/cellSize);

            if(!winningLine){
                //switch player turn
                if(logic.updateGameBoard(row, col)){
                    invalidate();
                    if(logic.winnerCheck()){
                        winningLine = true;
                        invalidate();
                    }
                    if(logic.getPlayer() % 2 ==0){
                        logic.setPlayer(logic.getPlayer()-1);
                    }
                    else{
                        logic.setPlayer(logic.getPlayer()+1);
                    }
            }

            }

            invalidate();
            return true;
        }
        return false;
    }

    private void drawGameBoard(Canvas canvas){
        paint.setColor(boardColor);
        paint.setStrokeWidth(16);
        for(int c=1; c<3; c++){
            canvas.drawLine(cellSize * c, 0, cellSize * c, canvas.getWidth(), paint);
        }
        for(int r=1; r<3; r++){
            canvas.drawLine(0, cellSize * r, canvas.getWidth(), cellSize * r, paint);
        }
    }

    private void drawMarkers(Canvas canvas){
        for(int r=0; r<3; r++){
            for(int c=0; c<3; c++) {
                if(logic.getGameBoard()[r][c] !=0){
                    if(logic.getGameBoard()[r][c] == 1){
                        drawX(canvas, r, c);
                    }
                    else{
                        drawO(canvas, r, c);
                    }
                }
            }
        }
    }
    private void drawX(Canvas canvas, int row, int col){
        paint.setColor(XColor);

        canvas.drawLine((float)((col+1)*cellSize-cellSize*0.1), (float)(row*cellSize+cellSize*0.1), (float)(col*cellSize+cellSize*0.1), (float)((row+1)*cellSize-cellSize*0.1), paint);
        canvas.drawLine((float)(col*cellSize+cellSize*0.1), (float)(row*cellSize+cellSize*0.1), (float)((col+1)*cellSize-cellSize*0.1), (float)((row+1)*cellSize-cellSize*0.1), paint);
    }
    private void drawO(Canvas canvas, int row, int col){
        paint.setColor(OColor);
        canvas.drawOval((float)(col*cellSize+cellSize*0.1), (float)(row*cellSize+cellSize*0.1), (float)((col*cellSize+cellSize)-cellSize*0.1), (float)((row*cellSize+cellSize)-cellSize*0.1), paint);
    }
    private void drawHorizontalLine(Canvas canvas, int row, int col){
        canvas.drawLine(col, row*cellSize + (float)cellSize/2,
                cellSize*3, row*cellSize + (float)cellSize/2,
                paint);
    }
    private void drawVerticalLine(Canvas canvas, int row, int col){
        canvas.drawLine(col*cellSize + (float)cellSize/2, row,
                col*cellSize + (float)cellSize/2, cellSize*3,
                paint);
    }
    private void drawDiagonalLinePos(Canvas canvas){
        canvas.drawLine(0, cellSize*3, cellSize*3, 0, paint);
    }

    private void drawDiagonalLineNeg(Canvas canvas){
        canvas.drawLine(0, 0, cellSize*3, cellSize*3, paint);
    }
    private void drawWinningLine(Canvas canvas){
        int row = logic.getWinType()[0];
        int col = logic.getWinType()[1];
        switch (logic.getWinType()[2]){
            case 1:
                drawHorizontalLine(canvas, row, col);
                break;
            case 2:
                drawVerticalLine(canvas, row, col);
                break;
            case 3:
                drawDiagonalLineNeg(canvas);
                break;
            case 4:
                drawDiagonalLinePos(canvas);
                break;
        }
    }
    public void setUpGame(Button reset, Button home, TextView playerDisplay, String[] name){
        logic.setResetBtn(reset);
        logic.setHomeBtn(home);
        logic.setPlayerTurn(playerDisplay);
        logic.setPlayerName(name);
    }
    public void resetGame(){
        logic.resetGame();
        winningLine = false;
    }
}
