package com.example.arobitproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        getSupportActionBar().hide(); //hide the title bar
        setContentView(R.layout.activity_otp);

        mAuth = FirebaseAuth.getInstance();
        Intent intent = getIntent();
        String phoneNo = intent.getStringExtra("phone");

        otpText1 = findViewById(R.id.et_otp1);
        otpText2 = findViewById(R.id.et_otp2);
        otpText3 = findViewById(R.id.et_otp3);
        otpText4 = findViewById(R.id.et_otp4);
        otpText5 = findViewById(R.id.et_otp5);
        otpText6 = findViewById(R.id.et_otp6);
        phoneNoTextView = findViewById(R.id.phone_no_text_view);

        otpText1.addTextChangedListener(new GenericTextWatcher(otpText1));
        otpText2.addTextChangedListener(new GenericTextWatcher(otpText2));
        otpText3.addTextChangedListener(new GenericTextWatcher(otpText3));
        otpText4.addTextChangedListener(new GenericTextWatcher(otpText4));
        otpText5.addTextChangedListener(new GenericTextWatcher(otpText5));
        otpText6.addTextChangedListener(new GenericTextWatcher(otpText6));
        phoneNoTextView.setText("Please enter the verification\ncode sent to " + phoneNo);

        sendOtp("+91" + phoneNo);
    }


    private void sendOtp(String phoneNumber) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                mCallbacks);        // OnVerificationStateChangedCallbacks
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks
            mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            verificationId = s;

        }


        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            String code = phoneAuthCredential.getSmsCode();
            if (code != null) {
                enterEditText(code);
                Toast.makeText(OtpActivity.this, "Successful", Toast.LENGTH_LONG).show();
                verifyCode(code);
            }
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            Toast.makeText(OtpActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    };

    private void verifyCode(String code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
        signInWithCredential(credential);
    }

    private void signInWithCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Intent intent = new Intent(OtpActivity.this, HomeActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                } else {
                    Toast.makeText(OtpActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    private void enterEditText(String number) {
        try {
            char[] num = number.toCharArray();

            otpText1.setText(String.valueOf(num[0]));
            otpText2.setText(String.valueOf(num[1]));
            otpText3.setText(String.valueOf(num[2]));
            otpText4.setText(String.valueOf(num[3]));
            otpText5.setText(String.valueOf(num[4]));
            otpText6.setText(String.valueOf(num[5]));

        } catch (Exception e) {
            Toast.makeText(this, "Error: " + e, Toast.LENGTH_LONG).show();
        }
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
                    if (text.length() == 1)
                        otpText2.requestFocus();
                    break;
                case R.id.et_otp2:
                    if (text.length() == 1)
                        otpText3.requestFocus();
                    else if (text.length() == 0)
                        otpText1.requestFocus();
                    break;
                case R.id.et_otp3:
                    if (text.length() == 1)
                        otpText4.requestFocus();
                    else if (text.length() == 0)
                        otpText2.requestFocus();
                    break;
                case R.id.et_otp4:
                    if (text.length() == 1)
                        otpText5.requestFocus();
                    else if (text.length() == 0)
                        otpText3.requestFocus();
                    break;

                case R.id.et_otp5:
                    if (text.length() == 1)
                        otpText6.requestFocus();
                    else if (text.length() == 0)
                        otpText4.requestFocus();
                    break;
                case R.id.et_otp6:
                    if (text.length() == 0) {
                        otpText5.requestFocus();
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