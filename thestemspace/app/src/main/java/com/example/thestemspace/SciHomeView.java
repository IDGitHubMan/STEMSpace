package com.example.thestemspace;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.util.Random;

public class SciHomeView extends View {
    static int height;
    static int width;
    public SciHomeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.setLayoutParams(new ViewGroup.LayoutParams(width,height));
    }

    private static Canvas c;
    @Override protected void onMeasure(int widthSpecId, int heightSpecId){
        setMeasuredDimension(getScreenWidth(),getScreenHeight());
        height = getScreenHeight();
        width = getScreenWidth();
    }

    protected void onDraw(Canvas canvas){
        invalidate();
        super.onDraw(canvas);
        this.c = canvas;
    }

    public static int getScreenWidth(){
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight(){
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    public static class cellMachine{
        Paint cellDeadPaint;
        Paint cellLivePaint;
        int rulesetSelector;
        int size = 10;
        int screenWidth = SciHomeView.getScreenWidth();
        int screenHeight = SciHomeView.getScreenHeight();
        int liveNeighbors;
        int liveCells;
        int totalCells = (screenWidth/size)*(screenHeight/size);
        boolean[][] states = new boolean[screenWidth/size][screenHeight/size];
        boolean[][] statesBuffer = new boolean[states.length][states[0].length];
        int[][] hRuleset = {{1,2,3,4,5,6,7,8},{1}};
        int[][] gRuleset = {{1},{1}};
        int[][] fRuleset = {{3},{1,2,3,4,5,6,7,8}};
        int[][] rRulest = {{1,3,5,7},{1,3,5,7}};
        Random r = new Random();

        @RequiresApi(api = Build.VERSION_CODES.N)
        cellMachine(Canvas c){
            rulesetSelector = r.nextInt(4);
            cellDeadPaint.setColor(Color.BLACK);
            cellLivePaint.setColor(Color.WHITE);
            for (int ix = 0; ix < states.length; ix++){
                for (int iy = 0; iy < states[0].length; iy++){
                    if (ix==Math.floorDiv(states.length,2) && iy==Math.floorDiv(states[0].length,2)){
                        states[ix][iy] = true;
                    }
                    else{
                        states[ix][iy] = false;
                    }
                }
            }
        }

        void update(){
            for (int ix = 0; ix < states.length; ix++){
                liveCells = 0;
                for (int iy = 0; iy < states[0].length; iy++){
                    liveNeighbors = 0;
                    if (states[ix][iy]){
                        liveCells += 1;
                    }
                    try{
                        if (states[ix-1][iy-1]){
                            liveNeighbors+=1;
                        }
                        if (states[ix][iy-1]){
                            liveNeighbors+=1;
                        }
                        if (states[ix+1][iy-1]){
                            liveNeighbors+=1;
                        }
                        if (states[ix-1][iy]){
                            liveNeighbors+=1;
                        }
                        if (states[ix+1][iy]){
                            liveNeighbors+=1;
                        }
                        if (states[ix-1][iy+1]){
                            liveNeighbors+=1;
                        }
                        if (states[ix][iy+1]){
                            liveNeighbors+=1;
                        }
                        if (states[ix+1][iy+1]){
                            liveNeighbors+=1;
                        }
                    }
                    catch (ArrayIndexOutOfBoundsException exception){
                        liveNeighbors = 0;
                        if (ix==0 && iy==0){
                            if (states[states.length-1][states[0].length-1]){
                                liveNeighbors+=1;
                            }
                            if (states[ix][states[0].length-1]){
                                liveNeighbors+=1;
                            }
                            if (states[ix+1][states[0].length-1]){
                                liveNeighbors+=1;
                            }
                            if (states[states.length-1][iy]){
                                liveNeighbors+=1;
                            }
                            if (states[states.length-1][iy+1]){
                                liveNeighbors+=1;
                            }
                            if (states[ix+1][iy]){
                                liveNeighbors+=1;
                            }
                            if (states[ix][iy+1]){
                                liveNeighbors+=1;
                            }
                            if (states[ix+1][iy+1]){
                                liveNeighbors+=1;
                            }
                        }
                        else if (ix==0 && iy==states[0].length-1){
                            if (states[states.length-1][iy-1]){
                                liveNeighbors+=1;
                            }
                            if (states[states.length-1][iy]){
                                liveNeighbors+=1;
                            }
                            if (states[states.length-1][0]){
                                liveNeighbors+=1;
                            }
                            if (states[ix][0]){
                                liveNeighbors+=1;
                            }
                            if (states[ix+1][0]){
                                liveNeighbors+=1;
                            }
                            if (states[ix+1][iy]){
                                liveNeighbors+=1;
                            }
                            if (states[ix+1][0]){
                                liveNeighbors+=1;
                            }
                            if (states[ix][0]){
                                liveNeighbors+=1;
                            }
                        }
                        else if (ix==states.length-1 && iy==0){
                            if (states[ix-1][states[0].length-1]){
                                liveNeighbors+=1;
                            }
                            if (states[ix][states[0].length-1]){
                                liveNeighbors+=1;
                            }
                            if (states[0][states[0].length-1]){
                                liveNeighbors+=1;
                            }
                            if (states[0][iy]){
                                liveNeighbors+=1;
                            }
                            if (states[0][iy+1]){
                                liveNeighbors+=1;
                            }
                        }
                        else if (ix==states.length-1 && iy==states[0].length-1){
                            if (states[ix-1][iy-1]){
                                liveNeighbors+=1;
                            }
                            if (states[ix][iy-1]){
                                liveNeighbors+=1;
                            }
                            if (states[0][iy-1]){
                                liveNeighbors+=1;
                            }
                            if (states[ix-1][iy]){
                                liveNeighbors+=1;
                            }
                            if (states[0][iy]){
                                liveNeighbors+=1;
                            }
                            if (states[0][iy-1]){
                                liveNeighbors+=1;
                            }
                            if (states[ix][0]){
                                liveNeighbors+=1;
                            }
                            if (states[0][0]){
                                liveNeighbors+=1;
                            }
                        }
                        else if (ix==0 && iy!=0 && iy!=states[0].length-1){
                            if (states[states.length-1][iy-1]){
                                liveNeighbors+=1;
                            }
                            if (states[ix][iy-1]){
                                liveNeighbors+=1;
                            }
                            if (states[ix+1][iy-1]){
                                liveNeighbors+=1;
                            }
                            if (states[states.length-1][iy]){
                                liveNeighbors+=1;
                            }
                            if (states[ix+1][iy]){
                                liveNeighbors+=1;
                            }
                            if (states[states.length-1][iy+1]){
                                liveNeighbors+=1;
                            }
                            if (states[ix][iy+1]){
                                liveNeighbors+=1;
                            }
                            if (states[ix+1][iy+1]){
                                liveNeighbors+=1;
                            }
                        }
                        else if (ix==states.length-1 && iy!=states[0].length-1 &&iy!=0){
                            if (states[ix-1][iy-1]){
                                liveNeighbors+=1;
                            }
                            if (states[ix][iy-1]){
                                liveNeighbors+=1;
                            }
                            if (states[0][iy-1]){
                                liveNeighbors+=1;
                            }
                            if (states[ix][iy-1]){
                                liveNeighbors+=1;
                            }
                            if (states[0][iy]){
                                liveNeighbors+=1;
                            }
                            if (states[ix-1][iy+1]){
                                liveNeighbors+=1;
                            }
                            if (states[ix][iy+1]){
                                liveNeighbors+=1;
                            }
                            if (states[0][iy+1]){
                                liveNeighbors+=1;
                            }
                        }
                        else if (iy==states[0].length-1 && ix!=0 && ix!=states.length-1){
                            if (states[ix-1][iy-1]){
                                liveNeighbors+=1;
                            }
                            if (states[ix][iy-1]){
                                liveNeighbors+=1;
                            }
                            if (states[ix+1][iy-1]){
                                liveNeighbors+=1;
                            }
                            if (states[ix-1][iy]){
                                liveNeighbors+=1;
                            }
                            if (states[ix+1][iy]){
                                liveNeighbors+=1;
                            }
                            if (states[ix-1][0]){
                                liveNeighbors+=1;
                            }
                            if (states[ix][0]){
                                liveNeighbors+=1;
                            }
                            if (states[ix+1][0]){
                                liveNeighbors+=1;
                            }
                        }
                        else if (iy==0 && ix!=0 && ix!=states.length-1) {
                            if (states[ix - 1][states[0].length - 1]) {
                                liveNeighbors += 1;
                            }
                            if (states[ix][states[0].length - 1]) {
                                liveNeighbors += 1;
                            }
                            if (states[ix + 1][states[0].length - 1]) {
                                liveNeighbors += 1;
                            }
                            if (states[ix - 1][iy]) {
                                liveNeighbors += 1;
                            }
                            if (states[ix + 1][iy]) {
                                liveNeighbors += 1;
                            }
                            if (states[ix - 1][iy + 1]) {
                                liveNeighbors += 1;
                            }
                            if (states[ix][iy + 1]) {
                                liveNeighbors += 1;
                            }
                            if (states[ix + 1][iy + 1]) {
                                liveNeighbors += 1;
                            }
                        }
                    }
                    if(states[ix][iy]){
                        if (rulesetSelector==0){}
                        else if (rulesetSelector==1){}
                        else if (rulesetSelector==2){}
                        else{}
                    }
                }
            }
        }
    }
}
