package com.example.arobitproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.EditText;

public class OtpActivity extends AppCompatActivity {

    private EditText otpText1;
    private EditText otpText2;
    private EditText otpText3;
    private EditText otpText4;
    private EditText otpText5;
    private EditText otpText6;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        getSupportActionBar().hide(); //hide the title bar
        setContentView(R.layout.activity_otp);

        otpText1=findViewById(R.id.et_otp1);
        otpText2=findViewById(R.id.et_otp2);
        otpText3=findViewById(R.id.et_otp3);
        otpText4=findViewById(R.id.et_otp4);
        otpText5=findViewById(R.id.et_otp5);
        otpText6=findViewById(R.id.et_otp6);

        otpText1.addTextChangedListener(new GenericTextWatcher(otpText1));
        otpText2.addTextChangedListener(new GenericTextWatcher(otpText2));
        otpText3.addTextChangedListener(new GenericTextWatcher(otpText3));
        otpText4.addTextChangedListener(new GenericTextWatcher(otpText4));
        otpText5.addTextChangedListener(new GenericTextWatcher(otpText5));
        otpText6.addTextChangedListener(new GenericTextWatcher(otpText6));


    }

    public class GenericTextWatcher implements TextWatcher
    {
        private View view;
        GenericTextWatcher(View view)
        {
            this.view = view;
        }

        @Override
        public void afterTextChanged(Editable editable) {
            // TODO Auto-generated method stub
            String text = editable.toString();
            switch(view.getId())
            {

                case R.id.et_otp1:
                    if(text.length()==1)
                        otpText2.requestFocus();
                    break;
                case R.id.et_otp2:
                    if(text.length()==1)
                        otpText3.requestFocus();
                    else if(text.length()==0)
                        otpText1.requestFocus();
                    break;
                case R.id.et_otp3:
                    if(text.length()==1)
                        otpText4.requestFocus();
                    else if(text.length()==0)
                        otpText2.requestFocus();
                    break;
                case R.id.et_otp4:
                    if(text.length()==1)
                        otpText5.requestFocus();
                    else if (text.length()==0)
                        otpText3.requestFocus();
                    break;

                case R.id.et_otp5:
                    if(text.length()==1)
                        otpText6.requestFocus();
                    else if(text.length()==0)
                        otpText4.requestFocus();
                    break;
                case R.id.et_otp6:
                    if(text.length()==0)
                        otpText5.requestFocus();
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