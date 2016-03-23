package com.example.matt.doodle;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.view.View;
import android.app.Activity;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Toast;

public class Doodle extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doodle);

        RelativeLayout rl = (RelativeLayout)findViewById(R.id.layout);
        rl.setBackgroundColor(Color.GRAY);
        DoodleView myView = (DoodleView) findViewById(R.id.view);
        myView.setBackgroundColor(Color.WHITE);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.planets_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progresValue, boolean fromUser) {
                progress = progresValue;
                DoodleView myView = (DoodleView) findViewById(R.id.view);
                myView.changeOpacity(progress);


            }


            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //Do Nothing
            }


            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //Do Nothing
            }
        });

        SeekBar seekBarTwo = (SeekBar) findViewById(R.id.seekBar2);
        seekBarTwo.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progresValue, boolean fromUser) {
                progress = progresValue;
                DoodleView myView = (DoodleView) findViewById(R.id.view);
                myView.changeBrushSize(progress);

                //Toast.makeText(getApplicationContext(), "Changing seekbar's progress", Toast.LENGTH_SHORT).show();

            }


            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //Toast.makeText(getApplicationContext(), "Started tracking seekbar", Toast.LENGTH_SHORT).show();
            }


            @Override

            public void onStopTrackingTouch(SeekBar seekBar) {
                //textView.setText("Covered: " + progress + "/" + seekBar.getMax());
                //Toast.makeText(getApplicationContext(), "Stopped tracking seekbar", Toast.LENGTH_SHORT).show();
            }
        });

        Button clear = (Button) findViewById(R.id.clearScreen);
        clear.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                DoodleView myView = (DoodleView) findViewById(R.id.view);
                myView.clearScreen();
                //myView.setDrawingCacheBackgroundColor(Color.WHITE);

            }
        });

        Button undoButton = (Button) findViewById(R.id.undo);
        undoButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                DoodleView myView = (DoodleView) findViewById(R.id.view);
                myView.undo();
                //myView.setDrawingCacheBackgroundColor(Color.WHITE);

            }
        });

        Button redoButton = (Button) findViewById(R.id.redo);
        redoButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                DoodleView myView = (DoodleView) findViewById(R.id.view);
                myView.redo();
                //myView.setDrawingCacheBackgroundColor(Color.WHITE);

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();
        DoodleView myView = (DoodleView) findViewById(R.id.view);
        myView.changeColor(Color.parseColor(item));

    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
}
