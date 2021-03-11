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
import androidx.annotation.RequiresApi;
import java.util.Random;

@RequiresApi(api = Build.VERSION_CODES.N)
public class SciHomeView extends View {
    static int height;
    static int width;
    public SciHomeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setLayoutParams(new ViewGroup.LayoutParams(width,height));
    }

    private static Canvas c;
    cellMachine cells = new cellMachine(c,this);
    @Override protected void onMeasure(int widthSpecId, int heightSpecId){
        setMeasuredDimension(getScreenWidth(),getScreenHeight());
        height = getScreenHeight();
        width = getScreenWidth();
    }

    protected void onDraw(Canvas canvas){
        invalidate();
        super.onDraw(canvas);
        this.c = canvas;
        cells.update();
    }

    public static int getScreenWidth(){
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight(){
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    public static class cellMachine{
        View view;
        Paint cellDeadPaint;
        Paint cellLivePaint;
        int rulesetSelector;
        int size = 1;
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
        int[][] rRuleset = {{1,3,5,7},{1,3,5,7}};
        Random r = new Random();

        @RequiresApi(api = Build.VERSION_CODES.N)
        cellMachine(Canvas c, View v){
            view = v;
            rulesetSelector = r.nextInt(4);
            cellDeadPaint.setColor(Color.BLACK);
            cellDeadPaint.setAntiAlias(true);
            cellDeadPaint.setStrokeWidth(5);
            cellDeadPaint.setStyle(Paint.Style.FILL_AND_STROKE);
            cellDeadPaint.setStrokeJoin(Paint.Join.ROUND);
            cellDeadPaint.setStrokeCap(Paint.Cap.ROUND);
            cellLivePaint.setColor(Color.WHITE);
            cellLivePaint.setAntiAlias(true);
            cellLivePaint.setStrokeWidth(5);
            cellLivePaint.setStyle(Paint.Style.FILL_AND_STROKE);
            cellLivePaint.setStrokeJoin(Paint.Join.ROUND);
            cellLivePaint.setStrokeCap(Paint.Cap.ROUND);
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
                        if (rulesetSelector==0){
                            for (int element:hRuleset[1]){
                                if (element==liveNeighbors){
                                    statesBuffer[ix][iy] = true;
                                }
                                else{
                                    statesBuffer[ix][iy] = false;
                                }
                            }
                        }
                        else if (rulesetSelector==1){
                            for (int element:fRuleset[1]){
                                if (element==liveNeighbors){
                                    statesBuffer[ix][iy] = true;
                                }
                                else{
                                    statesBuffer[ix][iy] = false;
                                }
                            }
                        }
                        else if (rulesetSelector==2){
                            for (int element:gRuleset[1]){
                                if (element==liveNeighbors){
                                    statesBuffer[ix][iy] = true;
                                }
                                else{
                                    statesBuffer[ix][iy] = false;
                                }
                            }
                        }
                        else{
                            for (int element:rRuleset[1]){
                                if (element==liveNeighbors){
                                    statesBuffer[ix][iy] = true;
                                }
                                else{
                                    statesBuffer[ix][iy] = false;
                                }
                            }
                        }
                    }
                    else{
                        if (rulesetSelector==0){
                            for (int element:hRuleset[0]){
                                if (element==liveNeighbors){
                                    statesBuffer[ix][iy] = true;
                                }
                                else{
                                    statesBuffer[ix][iy] = false;
                                }
                            }
                        }
                        else if (rulesetSelector==1){
                            for (int element:fRuleset[0]){
                                if (element==liveNeighbors){
                                    statesBuffer[ix][iy] = true;
                                }
                                else{
                                    statesBuffer[ix][iy] = false;
                                }
                            }
                        }
                        else if (rulesetSelector==2){
                            for (int element:gRuleset[0]){
                                if (element==liveNeighbors){
                                    statesBuffer[ix][iy] = true;
                                }
                                else{
                                    statesBuffer[ix][iy] = false;
                                }
                            }
                        }
                        else{
                            for (int element:rRuleset[0]){
                                if (element==liveNeighbors){
                                    statesBuffer[ix][iy] = true;
                                }
                                else{
                                    statesBuffer[ix][iy] = false;
                                }
                            }
                        }
                    }
                }
            }
            for (int ix = 0; ix < states.length; ix++){
                for (int iy = 0; iy < states[0].length; iy++){
                    states[ix][iy] = statesBuffer[ix][iy];
                }
            }
            for (int ix = 0; ix < states.length; ix++){
                for (int iy = 0; iy < states[0].length; iy++){
                    if (states[ix][iy]){
                        c.drawRect(ix*size,iy*size,ix*size+size,iy*size+size,cellLivePaint);
                    }
                    else{
                        c.drawRect(ix*size,iy*size,ix*size+size,iy*size+size,cellDeadPaint);
                    }
                }
            }
            view.invalidate();
        }
    }
}
