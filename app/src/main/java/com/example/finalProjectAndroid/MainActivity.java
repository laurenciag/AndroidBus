package com.example.finalProjectAndroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText textHello,textWelcome;
    Button exBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        exBtn = findViewById(R.id.bTest);
//        textHello = findViewById(R.id.textHello);

        exBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(MainActivity.this, "henlo", Toast.LENGTH_LONG).show();
                Intent k = new Intent(MainActivity.this, RegisPage.class);
                startActivity(k);
            }
        });


    }
}