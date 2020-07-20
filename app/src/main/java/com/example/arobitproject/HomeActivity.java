package com.example.arobitproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Window;
import android.widget.ListView;

public class HomeActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        getSupportActionBar().hide(); //hide the title bar
        setContentView(R.layout.activity_home);

        String name[]={"Ujjwal maity","Indroneel Mondol"};
        listView = findViewById(R.id.list_view);
        DashBordListView adopter = new DashBordListView(this,name);
        listView.setAdapter(adopter);
    }
}