package com.IDDev.stemspace;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.RequiresApi;
import java.util.Random;
import static android.graphics.Color.rgb;

@RequiresApi(api = Build.VERSION_CODES.O)
public class HomeNodeView extends View {
    static int height;
    static int width;
    public HomeNodeView(Context context, AttributeSet attrs) {
        super(context,attrs);
        this.setLayoutParams(new ViewGroup.LayoutParams(width,height));

    }
    private static Canvas c;
    network net = new network(c,this);
    @RequiresApi(api = Build.VERSION_CODES.O)

    @Override protected void onMeasure(int widthSpecId, int heightSpecId) {
        setMeasuredDimension(getScreenWidth(), getScreenHeight());
        height = getScreenHeight();
        width = getScreenWidth();
    }

    protected void onDraw(Canvas canvas) {
        invalidate();
        super.onDraw(canvas);
        this.c = canvas;
        net.update();
    }

    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }


    public static class node{
        int screenWidth = HomeNodeView.getScreenWidth();
        int screenHeight = HomeNodeView.getScreenHeight();
        Random rand = new Random();
        private Paint myCorePaint;
        private Paint myDetectPaint;
        float[] loc = new float[2];
        float[] drift = new float[2];
        float[] nodeCol = new float[3];
        int connect = 0;
        @RequiresApi(api = Build.VERSION_CODES.O)
        node(Canvas c){
            loc[0] = rand.nextFloat()*screenWidth;
            loc[1] = rand.nextFloat()*screenHeight;
            nodeCol[0] = rand.nextInt(256);
            nodeCol[1] = rand.nextInt(256);
            nodeCol[2] = rand.nextInt(256);
            drift[0] = (float) (Math.random()*(5+5+1)-5);
            drift[1] = (float) (Math.random()*(5+5+1)-5);
            myCorePaint = new Paint();
            myCorePaint.setColor(rgb(nodeCol[0],nodeCol[1],nodeCol[2]));
            myCorePaint.setAntiAlias(true);
            myCorePaint.setStrokeWidth(5);
            myCorePaint.setStyle(Paint.Style.FILL_AND_STROKE);
            myCorePaint.setStrokeJoin(Paint.Join.ROUND);
            myCorePaint.setStrokeCap(Paint.Cap.ROUND);
            myDetectPaint = new Paint();
            myDetectPaint.setColor(rgb(nodeCol[0],nodeCol[1],nodeCol[2]));
            myDetectPaint.setAntiAlias(true);
            myDetectPaint.setStrokeWidth(5);
            myDetectPaint.setStyle(Paint.Style.STROKE);
            myDetectPaint.setStrokeJoin(Paint.Join.ROUND);
            myDetectPaint.setStrokeCap(Paint.Cap.ROUND);
        }
        node(float x, float y, Canvas c){
            loc[0] = x;
            loc[1] = y;
            nodeCol[0] = rand.nextInt(256);
            nodeCol[1] = rand.nextInt(256);
            nodeCol[2] = rand.nextInt(256);
            drift[0] = (float) (Math.random()*(2+2+1)+-2);
            drift[1] = (float) (Math.random()*(2+2+1)+-2);
        }
        public void drift(){
            loc[0]=drift[0]+loc[0];
            loc[1]=drift[1]+loc[1];
        }
        public void show(){
            c.drawCircle(loc[0],loc[1],10,myCorePaint);
            c.drawCircle(loc[0],loc[1],100,myDetectPaint);
        }
    }

}

class network{
    Random rand = new Random();
    String[] edgeBehaviors = {"loop","bounce","terminate"};
    int edgeSelector = rand.nextInt(3);
    String edgeBehavior = edgeBehaviors[edgeSelector];
    HomeNodeView.node[] nodes = new HomeNodeView.node[40];
    float[][] locs= new float[nodes.length][2];
    float[][] paths= new float[nodes.length][2];
    Canvas can;
    View view;
    @RequiresApi(api = Build.VERSION_CODES.O)
    network(Canvas c, View v){
        can = c;
        view = v;
        for(int i = 0; i < nodes.length; i++){
            nodes[i] = new HomeNodeView.node(c);
            locs[i] = nodes[i].loc;
            paths[i] = nodes[i].drift;
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    void update() {
        for (int num = 0; num < nodes.length; num++) {
            nodes[num].drift();
            if (edgeBehavior == "loop"){
                if (nodes[num].loc[0]>nodes[num].screenWidth){
                    nodes[num].loc[0]=0;
                }
                if (nodes[num].loc[1]>nodes[num].screenHeight){
                    nodes[num].loc[1]=0;
                }
                if (nodes[num].loc[0]<0){
                    nodes[num].loc[0] = nodes[num].screenWidth;
                }
                if (nodes[num].loc[1]<0){
                    nodes[num].loc[1] = nodes[num].screenHeight;
                }
            }
            else if (edgeBehavior == "bounce"){
                if (nodes[num].loc[0]>nodes[num].screenWidth||nodes[num].loc[0]<0){
                    nodes[num].drift[0] = -nodes[num].drift[0];
                }
                if (nodes[num].loc[1]>nodes[num].screenHeight||nodes[num].loc[1]<0){
                    nodes[num].drift[1] = -nodes[num].drift[1];
                }
            }
            else{
                if (nodes[num].loc[0]>nodes[num].screenWidth){
                    nodes[num] = new HomeNodeView.node(can);
                }
                if (nodes[num].loc[1]>nodes[num].screenHeight){
                    nodes[num] = new HomeNodeView.node(can);
                }
                if (nodes[num].loc[0]<0){
                    nodes[num] = new HomeNodeView.node(can);
                }
                if (nodes[num].loc[1]<0){
                    nodes[num] = new HomeNodeView.node(can);
                }
            }
            nodes[num].show();
            locs[num] = nodes[num].loc;
            paths[num] = nodes[num].drift;
            view.invalidate();
        }
    }
}
