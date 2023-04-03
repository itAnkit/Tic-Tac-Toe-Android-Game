package com.example.tictactoegame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //player
    /*
        1 - X
        0 - o
        2 - NULL
     */
    int activeplayer = 1;
    int countmoves=0;
    boolean gameactive = true;
    int[] gamestate = { 2 , 2 , 2 , 2 , 2 , 2 , 2 , 2 , 2 };
    int[][] winpos = {{0,1,2},{3,4,5},{6,7,8},
                      {0,3,6},{1,4,7},{2,5,8},
                      {0,4,8},{2,4,6}};
    public void playtap(View view)
    {
        if(!gameactive)
            reset(view);

        ImageView img = (ImageView)view;
        int tap = Integer.parseInt(img.getTag().toString());
        TextView stat = findViewById(R.id.status);

        if(gamestate[tap]==2)
        {
            countmoves++;
            gamestate[tap] = activeplayer;
            img.setTranslationX(-100f);
            if(activeplayer==0)
            {
                img.setImageResource(R.drawable.zero);
                activeplayer=1;
                stat.setText("X's Turn Tap to play");
            }
            else
            {
                img.setImageResource(R.drawable.cross);
                activeplayer=0;
                stat.setText("O's Turn Tap to play");
            }
            img.animate().translationXBy(100f).setDuration(200);
        }

        String winstr = "";
        for(int[] winarr : winpos )
        {
            if(gamestate[winarr[0]]==gamestate[winarr[1]] && gamestate[winarr[1]]==gamestate[winarr[2]] && gamestate[winarr[0]]!=2)
            {
                if(gamestate[winarr[0]]==0)
                    winstr="O Wins !!!";
                else
                    winstr="X Wins !!!";

                stat.setText(winstr);
                gameactive=false;
                break;
            }
        }

        if(winstr.equals("") && countmoves==9)
        {
            stat.setText("NO ONE Wins !! <Tap To RESTART>");
            gameactive=false;
        }

    }

    public void reset(View view)
    {
        //Restarting Game
        gameactive=true;
        activeplayer=0;
        countmoves=1;
        TextView stat = findViewById(R.id.status);
        stat.setText("O's Turn Tap to play");

        for(int i=0 ; i<gamestate.length ; i++ )
            gamestate[i]=2;

        //Resetting images
        ((ImageView)findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}