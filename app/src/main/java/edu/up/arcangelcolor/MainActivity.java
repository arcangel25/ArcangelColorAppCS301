/**
 * This class handles all listeners, events, and user interaction.
 *
 * @author Sophie Arcangel
 */
package edu.up.arcangelcolor;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.SeekBar;
import android.widget.TextView;
import android.view.View;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener, View.OnTouchListener {
    //Sets up all the references to various widgets
    //that I will use to edit said widgets
    SeekBar redSeek;
    TextView redNum;
    SeekBar greenSeek;
    TextView greenNum;
    SeekBar blueSeek;
    TextView blueNum;
    Drawing myDrawing;
    ArrayList<CustomElement> myAL;
    TextView currentObj;

     /**
    External Citation
        Date: 2/8/22
        Problem: Didn't know how to work with listener events

        Resource: Prof. Nuxoll
        Solution: Nuxoll helped me write the code.
     */

    /**
     * Ultimately sets up the app and is called when the app is started
     *
     * @param savedInstanceState state of the app
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //These find references to the things I will be modifying
        //Since I can't access them directly
        redSeek = findViewById(R.id.redBar);
        redNum = findViewById(R.id.redNum);
        greenSeek = findViewById(R.id.greenBar);
        greenNum = findViewById(R.id.greenNum);
        blueNum = findViewById(R.id.blueNum);
        blueSeek = findViewById(R.id.blueBar);
        myDrawing = findViewById(R.id.surfaceView);
        myAL = myDrawing.getArray();
        currentObj = findViewById(R.id.currentObj);


        //Sets up listeners for the widgets using the references above
        redSeek.setOnSeekBarChangeListener(this);
        greenSeek.setOnSeekBarChangeListener(this);
        blueSeek.setOnSeekBarChangeListener(this);
        myDrawing.setOnTouchListener(this);


    }

    /**
    External Citation
        Date: 2/8/22
        Problem: Didn't know how to work with seekbars

        Resource: Prof. Nuxoll
        Solution: Nuxoll helped me write the code.
     */

    /**
     * External Citation
     *         Date: 2/8/22
     *         Problem: Didn't know how to make a color
     *
     *         Resource: https://developer.android.com/reference/android/graphics/Color
     *         Solution: Used SDK to find the needed method
     */

    /**
     *Tracks when seekbar progress is changed and changes the
     * displayed values accordingly
     *
     * @param seekBar the seekbar being changed
     * @param i seekbar progress
     * @param b whether or not the seekbar is being changed by the user
     */
    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        //if statements to verify which bar was changed to edit accordingly
        if(seekBar == redSeek){
            redNum.setText(""+i);
        }
        if(seekBar == greenSeek){
            greenNum.setText(""+i);
        }
        if(seekBar == blueSeek){
            blueNum.setText(""+i);
        }
        //The value the bars are at are used to determine the color
        //to draw the component with
        int redColor = redSeek.getProgress();
        int greenColor = greenSeek.getProgress();
        int blueColor = blueSeek.getProgress();
        int newColor = Color.rgb(redColor,greenColor,blueColor);
        myDrawing.choosePaint(newColor);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        //null
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        //null
    }

    /**
     External Citation
     Date: 2/8/22
     Problem: Didn't know how to get RGB values from an int
     Resource:
     http:https://developer.android.com/reference/android/graphics/Color
     Solution: Used and modified code from the SDK.
     */

    /**
     External Citation
     Date: 2/8/22
     Problem: Didn't know how to use onTouch

     Resource: CS 301 lecture from 2/3/22
     Solution: Copied and modified code
     */


    /**
     * Called when a user touches the screen to determine what component was clicked on.
     * That component is set as current and data pertaining to it is displayed
     *
     * @param view the view we are working with
     * @param motionEvent the event itself
     * @return true all the time
     */
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        int x = (int)motionEvent.getX();
        int y = (int)motionEvent.getY();
        //for loop to check if that shape contains the x y coordinates
        //if it does, assign that element as current
        for(int i = 0; i < myAL.size(); i++){
            if(myAL.get(i).containsPoint(x,y)){
                //After setting as current, displays name of component on the screen
                myDrawing.setCurrent(myAL.get(i));
                currentObj.setText(myAL.get(i).getName());
                //Gets the current RGB values of component to display on screen
                //and edit the seekbars
                int redCom = (myAL.get(i).getColor() >> 16) & 0xff;
                int greenCom = (myAL.get(i).getColor() >> 8) & 0xff;
                int blueCom = (myAL.get(i).getColor()) & 0xff;
                redSeek.setProgress(redCom);
                greenSeek.setProgress(greenCom);
                blueSeek.setProgress(blueCom);
            }
        }
        return true;
    }
}