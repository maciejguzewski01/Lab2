package com.example.lab2;

//import static com.example.lab2.DisplayMessageActivity.EXTRA_MESSAGE;

import com.example.lab2.Game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;

public class MainActivity extends AppCompatActivity {

    //toast test
    public static final String EXTRA_MESSAGE="test";


    private void toast_test()
    {
        Context context=getApplicationContext();
        CharSequence text= "TEST!";
        int duration= Toast.LENGTH_LONG;


        Toast toast= Toast.makeText(context, text, duration);
        toast.show();
    }


    //test intent
    private void intent_test()
    {
        String ur1= "http://www.google.com";
        Intent i= new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(ur1));
        startActivity(i);
    }

    /*
    private void intent_test_two()
    {
        Intent intent= new Intent(this, Game.class);
        EditText editText= (EditText) findViewById(R.id.edit_message);
        String message= editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, EXTRA_MESSAGE);
        startActivity(intent);
    }

     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //toast_test();
        //intent_test();
       // intent_test_two();

        final Button button_google = findViewById(R.id.button_google);
        final Button button_wiki=findViewById(R.id.button_wiki);
        final Button button_game=findViewById(R.id.button_activity);
        final Button button_own=findViewById(R.id.button_own);
        EditText editText=findViewById(R.id.edit_message);
        TextView textView=findViewById(R.id.textViewOne);

        button_google.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String ur1= "http://www.google.com";
                Intent i= new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(ur1));
                startActivity(i);
            }
            });

        button_wiki.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String ur1= "http://www.wikipedia.org";
                Intent i= new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(ur1));
                startActivity(i);
            }
        });

        button_own.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String ur1=editText.getText().toString();
                ur1="http://"+ur1;
                Intent i= new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(ur1));
                startActivity(i);


            }
        });

        button_game.setOnClickListener(new View.OnClickListener() {
           public void onClick(View v) {

                Intent intent= new Intent(MainActivity.this, Game.class );
                //EditText editText= (EditText) findViewById(R.id.edit_message);
                //String message= editText.getText().toString();
                //intent.putExtra(EXTRA_MESSAGE, EXTRA_MESSAGE);
                startActivity(intent);


           }
        });

    }
}
