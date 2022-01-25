package com.example.crypto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class PasswordVault extends AppCompatActivity {
    Button dialog, save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_vault);
        dialog=findViewById(R.id.see);
        save = findViewById(R.id.save);

        dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent temp=new Intent(PasswordVault.this,Dialog.class);
                startActivity(temp);
                Context context = getApplicationContext();
            }
        } );

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = getApplicationContext();
                Toast.makeText(context, "Saved Password", Toast.LENGTH_LONG).show();
            }
        } );
    }


}

