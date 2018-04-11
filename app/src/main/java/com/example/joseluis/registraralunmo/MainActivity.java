package com.example.joseluis.registraralunmo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void agregar(View view)
    {
        Intent activity_agregar=new Intent(MainActivity.this,Activity_agregar.class);
        startActivity(activity_agregar);
    }
    public void agregar1(View view)
    {
        Intent activity_agregar1=new Intent(MainActivity.this,Activity_agregar1.class);
        startActivity(activity_agregar1);
    }
}
