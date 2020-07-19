package com.example.arobitproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SigningInActivity extends AppCompatActivity {

    private Button login;
    private EditText phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        getSupportActionBar().hide(); //hide the title bar
        setContentView(R.layout.activity_signin);

        phone = findViewById(R.id.phone);
        login = findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneNo=phone.getText().toString();
                if(phoneNo.length()==10) {
                    Intent intent = new Intent(SigningInActivity.this, OtpActivity.class);
                    intent.putExtra("phone", phoneNo);
                    startActivity(intent);
                }else {
                    Toast.makeText(SigningInActivity.this,"Please enter correct phone number",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}