/**
 * This class contains all the components of the drawing and draws them.
 *
 * @author Sophie Arcangel
 */
package edu.up.arcangelcolor;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceView;
import java.util.ArrayList;

public class Drawing extends SurfaceView {
    //Sets up all the components to be drawn and an ArrayList to hold them
    private ArrayList<CustomElement> myArray = new ArrayList<>();
    private CustomCircle shell = new CustomCircle("Shell", 0xFFFFFFFF, 1000, 325,300);
    private CustomRect body =  new CustomRect("Body", 0xFFe29ae3, 500, 450,1500, 650);
    private CustomRect ground = new CustomRect("Ground", 0xFF699c54, 0, 500,2500, 1000);
    private CustomRect sky = new CustomRect("Sky", 0xFF89d9d6, 0, 0,2500, 500);
    private CustomRect rightStalk = new CustomRect("Right Stalk", 0xFF284231, 1450, 200,1500, 450);
    private CustomRect leftStalk = new CustomRect("Left Stalk", 0xFF95c2a5, 1325, 200,1375, 450);
    //current holds the current element being modified.
    //Since the shell is a dominant component, it defaults to it.
    private CustomElement current = shell;

    /**
     External Citation
     Date: 2/8/22
     Problem: Didn't know how to implement Surface View

     Resource: CS 301 coding example from lecture: 1/25/22
     Solution: Copied the code and edited it for my needs
     */

    /**
     *All the following are various constructors for the drawing class
     * They ensure that the components can be drawn and instantiate the array
     *
     * @param context state of the app
     * @param attrs XML attributes
     * @param defStyleAttr style of XML
     *
     *
     */

    /**
     External Citation
     Date: 2/10/22
     Problem: Didn't know what the parameters meant in SurfaceView

     Resource:
     https://stackoverflow.com/questions/5316686/what-is-attributeset-and-how-can-i-use-it
     https://blog.mindorks.com/understanding-context-in-android-application-330913e32514

     Solution: Used the definitions for the comments
     */

    public Drawing(Context context) {
        super(context);
        initArray();
        setWillNotDraw(false);
    }

    public Drawing(Context context, AttributeSet attrs) {
        super(context, attrs);
        initArray();
        setWillNotDraw(false);
    }

    public Drawing(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initArray();
        setWillNotDraw(false);
    }

    /**
     * Adds all the components to the array
     */
    private void initArray(){
        myArray.add(sky);
        myArray.add(ground);
        myArray.add(shell);
        myArray.add(body);
        myArray.add(rightStalk);
        myArray.add(leftStalk);
    }

    /**
     * Draws all the components
     *
     * @param canvas something to draw on
     */
    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        sky.drawMe(canvas);
        ground.drawMe(canvas);
        body.drawMe(canvas);
        shell.drawMe(canvas);
        rightStalk.drawMe(canvas);
        leftStalk.drawMe(canvas);
    }

    /**
     * Takes an int that holds the a color and assigns the color of the current
     * component to that color. Calls to update the drawing.
     *
     * @param color hex value of a color
     */
    public void choosePaint(int color){
        current.setColor(color);
        invalidate();
    }

    public ArrayList<CustomElement> getArray (){
        return myArray;
    }

    public void setCurrent(CustomElement e){
        current = e;
    }

}
