package com.example.crypto;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    LinearLayout txtEnc, txtDec, passVaul, voiceEnc, imgEnc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtEnc=findViewById(R.id.textEnc);
        txtDec=findViewById(R.id.textDec);
        passVaul = findViewById(R.id.LayoutPasswordVault);
        voiceEnc = findViewById(R.id.LayoutTextVoice);
        imgEnc = findViewById(R.id.imageEnc);

        txtEnc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent temp=new Intent(MainActivity.this,Encoder.class);
                startActivity(temp);
            }
        } );

        txtDec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent temp=new Intent(MainActivity.this,Decoder.class);
                startActivity(temp);
            }
        } );

        passVaul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent temp=new Intent(MainActivity.this,PasswordVault.class);
                startActivity(temp);
            }
        } );

        voiceEnc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent temp=new Intent(MainActivity.this,VoiceEncryption.class);
                startActivity(temp);
            }
        } );

        imgEnc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent temp=new Intent(MainActivity.this,ImageEncryption.class);
                startActivity(temp);
            }
        } );

    }
}