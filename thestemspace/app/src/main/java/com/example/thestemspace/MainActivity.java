package com.example.thestemspace;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;

import org.w3c.dom.Node;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void migrateToSci(View view){
        Intent intent = new Intent(this,SciActivity.class);
        startActivity(intent);
    }

    public void migrateToTec(View view){
        Intent intent = new Intent(this,TechActivity.class);
        startActivity(intent);
    }

    public void migrateToMat(View view){
        Intent intent = new Intent(this,MathActivity.class);
        startActivity(intent);
    }

    public void migrateToExtras(View view){
        Intent intent = new Intent(this,ExtrasActivity.class);
        startActivity(intent);
    }
}