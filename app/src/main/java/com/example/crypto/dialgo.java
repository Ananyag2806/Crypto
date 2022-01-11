package com.example.crypto;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
public class dialgo extends AppCompatActivity {
    TextView myCustomMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myCustomMessage = (TextView)findViewById(R.id.myCustommessage);
    }
    public void btn_showMessage(View view){
        final AlertDialog.Builder alert = new AlertDialog.Builder(dialgo.this);
        View mView = getLayoutInflater().inflate(R.layout.custom_dialog,null);
        final EditText txt_inputText = (EditText)mView.findViewById(R.id.txt_input);
        Button btn_cancel = (Button)mView.findViewById(R.id.btn_cancel);
        Button btn_okay = (Button)mView.findViewById(R.id.btn_okay);
        alert.setView(mView);
        final AlertDialog alertDialog = alert.create();
        alertDialog.setCanceledOnTouchOutside(false);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        btn_okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myCustomMessage.setText(txt_inputText.getText().toString());
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }
}