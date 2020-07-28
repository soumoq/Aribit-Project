package com.example.arobitproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;


public class OtpActivity extends AppCompatActivity {

    private EditText otpText1;
    private EditText otpText2;
    private EditText otpText3;
    private EditText otpText4;
    private EditText otpText5;
    private EditText otpText6;
    private TextView phoneNoTextView;
    private FirebaseAuth mAuth;
    private String verificationId;
    private ImageView backArrow;
    private String phoneNo;
    private MyKeyboard keyboard;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);


        keyboard = (MyKeyboard) findViewById(R.id.keyboard);
        backArrow = findViewById(R.id.back_arrow);
        otpText1 = findViewById(R.id.et_otp1);
        otpText2 = findViewById(R.id.et_otp2);
        otpText3 = findViewById(R.id.et_otp3);
        otpText4 = findViewById(R.id.et_otp4);
        inputKey(otpText1);


        phoneNoTextView = findViewById(R.id.phone_no_text_view);

        otpText1.addTextChangedListener(new GenericTextWatcher(otpText1));
        otpText2.addTextChangedListener(new GenericTextWatcher(otpText2));
        otpText3.addTextChangedListener(new GenericTextWatcher(otpText3));
        otpText4.addTextChangedListener(new GenericTextWatcher(otpText4));
        phoneNo = "7477540540";
        phoneNoTextView.setText("Please enter the verification\ncode sent to " + phoneNo);

        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OtpActivity.this, SigningInActivity.class));
                finish();
            }
        });


    }

    private void inputKey(EditText otpText) {
        otpText.setRawInputType(InputType.TYPE_CLASS_TEXT);
        otpText.setTextIsSelectable(true);
        InputConnection ic = otpText.onCreateInputConnection(new EditorInfo());
        keyboard.setInputConnection(ic);
    }


    public class GenericTextWatcher implements TextWatcher {
        private View view;

        GenericTextWatcher(View view) {
            this.view = view;
        }

        @Override
        public void afterTextChanged(Editable editable) {
            // TODO Auto-generated method stub
            String text = editable.toString();
            switch (view.getId()) {

                case R.id.et_otp1:
                    if (text.length() == 1) {
                        otpText2.requestFocus();
                        inputKey(otpText2);
                    }
                    break;
                case R.id.et_otp2:
                    if (text.length() == 1) {
                        otpText3.requestFocus();
                        inputKey(otpText3);
                    } else if (text.length() == 0) {
                        otpText1.requestFocus();
                        inputKey(otpText1);
                    }
                    break;
                case R.id.et_otp3:
                    if (text.length() == 1) {
                        otpText4.requestFocus();
                        inputKey(otpText4);
                    } else if (text.length() == 0) {
                        otpText2.requestFocus();
                        inputKey(otpText2);
                    }
                    break;
                case R.id.et_otp4:
                    if (text.length() == 0) {
                        otpText3.requestFocus();
                        inputKey(otpText3);
                    } else if (text.length() == 1) {
                        startActivity(new Intent(OtpActivity.this, HomeActivity.class));
                    }
                    break;
            }
        }

        @Override
        public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            // TODO Auto-generated method stub
        }

        @Override
        public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            // TODO Auto-generated method stub
        }
    }
}