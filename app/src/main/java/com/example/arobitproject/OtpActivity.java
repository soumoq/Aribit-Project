package com.example.arobitproject;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chaos.view.PinView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;


public class OtpActivity extends AppCompatActivity {


    private TextView phoneNoTextView;
    private FirebaseAuth mAuth;
    private String verificationId;
    private ImageView backArrow;
    private String phoneNo;
    private MyKeyboard keyboard;
    private PinView pinview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);


        keyboard = (MyKeyboard) findViewById(R.id.keyboard);
        backArrow = findViewById(R.id.back_arrow);


        phoneNoTextView = findViewById(R.id.phone_no_text_view);


        phoneNo = "7477540540";
        phoneNoTextView.setText("Please enter the verification\ncode sent to " + phoneNo);

        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OtpActivity.this, SigningInActivity.class));
                finish();
            }
        });

        final PinView pinView = findViewById(R.id.pin_view);
        pinView.setRawInputType(InputType.TYPE_CLASS_DATETIME);
        pinView.setHideLineWhenFilled(false);
        pinView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if(pinView.getText().toString().length() == 4)
                {
                    startActivity(new Intent(OtpActivity.this,HomeActivity.class));
                    pinView.setText(null);
                }
                return false;
            }
        });
        //inputKey(pinView);

    }

    private void inputKey(final EditText otpText) {
        otpText.setRawInputType(InputType.TYPE_CLASS_TEXT);
        otpText.setTextIsSelectable(true);
        InputConnection ic = otpText.onCreateInputConnection(new EditorInfo());

        keyboard.setInputConnection(ic);
    }

}