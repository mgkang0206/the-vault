package com.genassembly.dotdashdot.crackmysafe2;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.apache.commons.lang3.RandomStringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

import static java.util.Objects.hash;

public class MainActivity extends AppCompatActivity {

    private static int LENGTHADD = 1;
    private SaltHolder salts;
    private SafeLock lock;

    private FloatingActionButton attemptButton;
    private TextView results;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        results = (TextView) findViewById(R.id.results);

        salts = new SaltHolder();
        lock = new SafeLock();

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.attempt);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptClick();
            }
        });
    }

    //This is the attempt to open the vault
    private void openLock(int id){
        ((FloatingActionButton) findViewById(id)).setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorSecondary)));
    }

    //This function creates the attempts
    public void attemptClick(){
        Date date1 = new Date();
        int[] views = new int[]{R.id.lock1, R.id.lock2, R.id.lock3, R.id.lock4};

        for (int i = 0; i < views.length; i++) {
            initFab(views[i]);
        }

        MyGuesser guess = new MyGuesser(salts);

        for (int i = 0; i < views.length; i++) {
            int[] check = new int[LENGTHADD];
            for(int k = 0; k < check.length; k++){
                check[k] = 0;
            }

            for (int k = 0; k < check.length * 52; k++) {
                //Check our tumbler's rotation and formulate a guess...
                String guessing = guess.guess(check);
                System.out.println("Guessing: " + guessing);
                //Rotate tumbler to next position
                check = guess.rotate(check);
                //This is the password after we hash it => guess.hashPassword(i+1,guessing)
                //We are passing that hash into our password function
                //If it passes, we get to open our lock!
                if (lock.checkPassword(i+1,guess.hashPassword(i+1,guessing))){
                    openLock(views[i]);
                    break;
                } else {
                    Log.i("Miss","Boo");
                }
            }
        }
        calcDifference(date1, new Date());
    }

    //This sets the difference textview
    public void calcDifference(Date start, Date finish){
        String resultString = "Time: " + (finish.getTime() - start.getTime());
        results.setText(resultString);
    }

    //This locks the vault
    private void initFab(int id){
        final FloatingActionButton fab = (FloatingActionButton) findViewById(id);
        if (fab != null) {
            final int tag = Integer.valueOf(fab.getTag().toString());
            salts.setMap(tag, BCrypt.gensalt());

            String rawPass = RandomStringUtils.randomAlphabetic(LENGTHADD);

            System.out.println("Password: " + rawPass);

            rawPass = "H";

            String pass = salts.printPassword(tag, rawPass);
            lock.lockSafe(tag, pass);

            ((FloatingActionButton) findViewById(id)).setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorAccent)));
        }
    }
}
