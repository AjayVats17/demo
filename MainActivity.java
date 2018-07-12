package com.example.polestaruser.imageslider;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button submit,fetch,clear,first;
    String name,pass;
    EditText ed1,ed2;
    TextView fp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        submit=findViewById(R.id.submit);
        fetch=findViewById(R.id.fetch);
        ed1=findViewById(R.id.name);
        ed2=findViewById(R.id.pass);
        clear=findViewById(R.id.clear);
        first=findViewById(R.id.first);
        fp=findViewById(R.id.fp);

       fp.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if(ed1.getText().toString().equals("")) {
                   util.showError(MainActivity.this, "Please enter your name");
                   ed1.requestFocus();

                 getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
               }
               else{
                   String n=ed1.getText().toString();
                   Database d=new Database(MainActivity.this);
                   User u=d.forgot(n);
                   String p=u.getPass();
                   Toast.makeText(MainActivity.this, "Your Password is : "+ p, Toast.LENGTH_SHORT).show();
               }
           }
       });

        first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Database d=new Database(MainActivity.this);
                User u=d.first();
                String n=u.getName();
                String p=u.getPass();
                Toast.makeText(MainActivity.this, "Name is : "+n+"  and Password is : "+p, Toast.LENGTH_SHORT).show();

            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Database d=new Database(MainActivity.this);
                d.deleteall();
                Toast.makeText(MainActivity.this, "Successfully deleted", Toast.LENGTH_SHORT).show();
            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (valid()) {
                    User u=new User();
                    u.setName(name);
                    u.setPass(pass);
                    Database db = new Database(MainActivity.this);
                    db.openDatabase();
                    if (db.insert(u) > 0) {
                        Toast.makeText(MainActivity.this, "Succesfully Registered", Toast.LENGTH_SHORT).show();
                        clear();
                    } else {
                        Toast.makeText(MainActivity.this, "registration Failed", Toast.LENGTH_SHORT).show();
                        clear();
                    }
                    db.closeDatabase();
                }
            }
        });

        fetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Database db = new Database(MainActivity.this);
                User us=db.Fetch();
                String n=us.getName();
                String p=us.getPass();
                Toast.makeText(MainActivity.this, "Name is : "+n+"  and Password is : "+p, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean valid()
    {
        name=ed1.getText().toString();
        pass=ed2.getText().toString();


        if(name.equals(""))
        {
            util.showError(this,"Please Enter Your Uname");
            ed1.requestFocus();
            return false;
        }
        else if(pass.equals("")){
            util.showError(this,"Please Enter Your Password");
            ed2.requestFocus();
            return  false;
        }
        else
        {
            return true;
        }
    }

    public void clear(){
        ed1.setText("");
        ed2.setText("");
    }

    public void Time(View view){
        Intent i=new Intent(this,Time_picker.class);
        startActivity(i);
    }

    public void List(View view){
        Intent i=new Intent(this,List.class);
        startActivity(i);
    }
}
