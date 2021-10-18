package com.vincent.statepattern;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView tv;
    Button btn;
    State state;
    final String TAG = "SimpleStatePattern";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.textView1);
        btn = findViewById(R.id.button);
        state = new State1();
        updateText("init state");
        btn.setOnClickListener(view->state.action());
    }

    private void setState(State s){
        state = s;
    }

    public interface State{
        void action();
    }

    private void updateText(String s){
        runOnUiThread(()-> tv.setText(s));
    }

    public class State1 implements State{

        @Override
        public void action() {
            Log.d(TAG,"State1 do action");
            updateText(getClass().getSimpleName());
            setState(new State2());
        }
    }

    public class State2 implements State{

        @Override
        public void action() {
            Log.d(TAG,"State2 do action");
            updateText(getClass().getSimpleName());
            setState(new State3());
        }
    }

    public class State3 implements State{

        @Override
        public void action() {
            Log.d(TAG,"State3 do action");
            updateText(getClass().getSimpleName());
            setState(new State1());
        }
    }
}