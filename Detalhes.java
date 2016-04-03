package com.example.wizzydj.stockbeta;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Detalhes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent();
        i.setClass(Detalhes.this,MainActivity.class);
        startActivity(i);
        super.onBackPressed();
    }
}
