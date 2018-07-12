package com.example.polestaruser.imageslider;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class List extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        final ListView listView=findViewById(R.id.list);
        TextView textView=findViewById(R.id.textView);
        String[] mobileArray = {"Android","IPhone","WindowsMobile","Blackberry",
                "WebOS","Ubuntu","Windows7","Max OS X","a","b","c","d","e","f","g","h"};
        final ArrayAdapter arrayAdapter=new ArrayAdapter(this, android.R.layout.simple_list_item_1,android.R.id.text1,mobileArray);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String value= String.valueOf(arrayAdapter.getItem(position));
                Toast.makeText(List.this, value, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
