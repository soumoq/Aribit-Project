package com.example.arobitproject;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputConnection;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MyKeyboard extends LinearLayout implements View.OnClickListener {

    // constructors
    public MyKeyboard(Context context) {
        this(context, null, 0);
    }

    public MyKeyboard(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyKeyboard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    // keyboard keys (buttons)
    private TextView mButton1;
    private TextView mButton2;
    private TextView mButton3;
    private TextView mButton4;
    private TextView mButton5;
    private TextView mButton6;
    private TextView mButton7;
    private TextView mButton8;
    private TextView mButton9;
    private TextView mButton0;
    private ImageView mButtonDelete;

    // This will map the button resource id to the String value that we want to
    // input when that button is clicked.
    SparseArray<String> keyValues = new SparseArray<>();

    // Our communication link to the EditText
    InputConnection inputConnection;

    private void init(Context context, AttributeSet attrs) {

        // initialize buttons
        LayoutInflater.from(context).inflate(R.layout.keyboard, this, true);
        mButton1 = findViewById(R.id.one);
        mButton2 = findViewById(R.id.two);
        mButton3 = findViewById(R.id.three);
        mButton4 = findViewById(R.id.four);
        mButton5 = findViewById(R.id.five);
        mButton6 = findViewById(R.id.six);
        mButton7 = findViewById(R.id.seven);
        mButton8 = findViewById(R.id.eight);
        mButton9 = findViewById(R.id.nine);
        mButton0 = findViewById(R.id.zero);
        mButtonDelete = findViewById(R.id.button_delete);

        // set button click listeners
        mButton1.setOnClickListener(this);
        mButton2.setOnClickListener(this);
        mButton3.setOnClickListener(this);
        mButton4.setOnClickListener(this);
        mButton5.setOnClickListener(this);
        mButton6.setOnClickListener(this);
        mButton7.setOnClickListener(this);
        mButton8.setOnClickListener(this);
        mButton9.setOnClickListener(this);
        mButton0.setOnClickListener(this);
        mButtonDelete.setOnClickListener(this);

        // map buttons IDs to input strings
        keyValues.put(R.id.one, "1");
        keyValues.put(R.id.two, "2");
        keyValues.put(R.id.three, "3");
        keyValues.put(R.id.four, "4");
        keyValues.put(R.id.five, "5");
        keyValues.put(R.id.six, "6");
        keyValues.put(R.id.seven, "7");
        keyValues.put(R.id.eight, "8");
        keyValues.put(R.id.nine, "9");
        keyValues.put(R.id.zero, "0");
    }

    @Override
    public void onClick(View v) {

        // do nothing if the InputConnection has not been set yet
        if (inputConnection == null) return;

        // Delete text or input key value
        // All communication goes through the InputConnection
        if (v.getId() == R.id.button_delete) {
            CharSequence selectedText = inputConnection.getSelectedText(0);
            if (TextUtils.isEmpty(selectedText)) {
                // no selection, so delete previous character
                inputConnection.deleteSurroundingText(1, 0);
            } else {
                // delete the selection
                inputConnection.commitText("", 1);
            }
        } else {
            String value = keyValues.get(v.getId());
            inputConnection.commitText(value, 1);
        }
    }

    // The activity (or some parent or controller) must give us
    // a reference to the current EditText's InputConnection
    public void setInputConnection(InputConnection ic) {
        this.inputConnection = ic;
    }
}