package com.example.lab2;

/*

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class Game  extends AppCompatActivity{

    //public static final String EXTRA_MESSAGE="TEXT";



    public void display()
    {

    }

    @Override
    protected  void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        //TextView text= (TextView) findViewById(R.id.textViewOne);
       // text.setText(message);
    }
}

 */


import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import android.view.View.OnClickListener;

public class Game extends AppCompatActivity {
    private  int gamerOneWin=0;
    private int gamerTwoWin=0;
    private int draw=0;

    private Gamer next_move;
    private Gamestate state;
    private int who[];
    private Field this_move=Field.EMPTY;
    private String next_gamer;
    private Gamer winner;
    private Field board[];

    private Button buttons[];


    private TextView textView1, textView2;
    private TextView textView_One, textView_Two, textView_draw;

    private boolean have_won(int x)
    {
        int move=who[x];
        switch(x)
        {
            case 0:
                if((who[0]==who[1])&&(who[1]==who[2])) return true;
                if((who[0]==who[3])&&(who[3]==who[6])) return true;
                if((who[0]==who[4])&&(who[4]==who[8])) return true;
                break;
            case 1:
                if((who[0]==who[1])&&(who[1]==who[2])) return true;
                if((who[1]==who[4])&&(who[4]==who[7])) return true;
                break;
            case 2:
                if((who[0]==who[1])&&(who[1]==who[2])) return true;
                if((who[2]==who[5])&&(who[5]==who[8])) return true;
                if((who[2]==who[4])&&(who[4]==who[6])) return true;
                break;

            case 3:
                if((who[0]==who[3])&&(who[3]==who[6])) return true;
                if((who[3]==who[4])&&(who[4]==who[5])) return true;
                break;
            case 4:
                if((who[1]==who[4])&&(who[4]==who[7])) return true;
                if((who[3]==who[4])&&(who[4]==who[5])) return true;
                if((who[0]==who[4])&&(who[4]==who[8])) return true;
                if((who[6]==who[4])&&(who[4]==who[2])) return true;
                break;
            case 5:
                if((who[3]==who[4])&&(who[4]==who[5])) return true;
                if((who[2]==who[5])&&(who[5]==who[8])) return true;
                break;

            case 6:
                if((who[6]==who[7])&&(who[7]==who[8])) return true;
                if((who[0]==who[3])&&(who[3]==who[6])) return true;
                if((who[6]==who[4])&&(who[4]==who[2])) return true;
                break;
            case 7:
                if((who[6]==who[7])&&(who[7]==who[8])) return true;
                if((who[1]==who[4])&&(who[4]==who[7])) return true;
                break;
            case 8:
                if((who[6]==who[7])&&(who[7]==who[8])) return true;
                if((who[2]==who[5])&&(who[5]==who[8])) return true;
                if((who[0]==who[4])&&(who[4]==who[8])) return true;
                break;

        }


        return false;
    }

    private boolean have_end()
    {
        for(int i=0;i<9;++i)
        {
            if(board[i]==Field.EMPTY) return false;
        }
        return true;
    }


    private void reset()
    {
        state=Gamestate.GAME;
        for(int i=0;i<9;++i)
        {
            board[i]=Field.EMPTY;
            who[i]=-1;
            buttons[i].setText("");
        }

        textView2.setText("");
        next_gamer="gracz 1";
        textView1.setText("Ruch: "+next_gamer);
    }

    private void set_on_click(int i)
    {
        buttons[i].setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                if((i==9)&&(state==Gamestate.OVER))
                {
                    reset();

                    return;
                }

                if(state==Gamestate.OVER) return;
                if(who[i]!=-1) return;



                if(next_move==Gamer.GAMER_ONE) buttons[i].setText("O");
                else if(next_move==Gamer.GAMER_TWO) buttons[i].setText("X");
                if(next_move==Gamer.GAMER_ONE) buttons[i].setTextColor(getColor(R.color.white));
                else if(next_move==Gamer.GAMER_TWO) buttons[i].setTextColor(getColor(R.color.black));

                if(next_move==Gamer.GAMER_ONE) who[i]=1;
                else if(next_move==Gamer.GAMER_TWO) who[i]=2;
                board[i]=Field.SIGN;
                //------

                if(have_won(i)==true) {
                    winner=next_move;
                    textView2.setText("Wygral: "+next_gamer);
                    state=Gamestate.OVER;
                    buttons[9].setVisibility(View.VISIBLE);
                    if(winner==Gamer.GAMER_ONE) gamerOneWin++;
                    else gamerTwoWin++;
                    textView_One.setText(Integer.toString(gamerOneWin));
                    textView_Two.setText(Integer.toString(gamerTwoWin));
                    return;
                }else if(have_end()==true)//remis
                {
                    textView2.setText("REMIS");
                    state=Gamestate.OVER;
                    buttons[9].setVisibility(View.VISIBLE);
                    draw++;
                    textView_draw.setText(Integer.toString(draw));
                    return;
                }

                if(next_move==Gamer.GAMER_ONE)
                {
                    next_move=Gamer.GAMER_TWO;
                    next_gamer="gracz 2";
                }
                else{
                    next_move=Gamer.GAMER_ONE;
                    next_gamer="gracz 1";
                }


                textView1.setText("Ruch: "+next_gamer);



            }
        });


    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        state=Gamestate.GAME;

        textView1=findViewById(R.id.textView1);
        textView2=findViewById(R.id.textView2);

        textView2.setText("");
        next_gamer="gracz 1";
        textView1.setText("Ruch: "+next_gamer);

        next_move=Gamer.GAMER_ONE;
        board=new Field[9];
        who= new int[9];

        for(int i=0;i<9;++i)
        {
            board[i]=Field.EMPTY;
        }

        buttons= new Button[10];

        buttons[0]=findViewById(R.id.button1_1);
        buttons[1]=findViewById(R.id.button1_2);
        buttons[2]=findViewById(R.id.button1_3);
        buttons[3]=findViewById(R.id.button2_1);
        buttons[4]=findViewById(R.id.button2_2);
        buttons[5]=findViewById(R.id.button2_3);
        buttons[6]=findViewById(R.id.button3_1);
        buttons[7]=findViewById(R.id.button3_2);
        buttons[8]=findViewById(R.id.button3_3);



        buttons[9]=findViewById(R.id.button4);
        buttons[9].setVisibility(View.INVISIBLE);

        textView_One=findViewById(R.id.textView_G1);
        textView_Two=findViewById(R.id.textView_G2);
        textView_draw=findViewById(R.id.textView_R);

        textView_One.setText(Integer.toString(gamerOneWin));
        textView_Two.setText(Integer.toString(gamerTwoWin));
        textView_draw.setText(Integer.toString(draw));


        for(int i=0;i<9;++i)
        {
            buttons[i].setText("");
            who[i]=-1;
        }


        for(int i=0;i<10;++i)
        {
            set_on_click(i);
        }


    }




}


