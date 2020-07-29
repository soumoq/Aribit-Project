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
        setContentView(R.layout.activity_home);

        String name[]={"Indroneel Mondol","Indroneel Mondol","Indroneel Mondol"};
        listView = findViewById(R.id.list_view);
        DashBordListView adopter = new DashBordListView(this,name);
        listView.setAdapter(adopter);
    }
}