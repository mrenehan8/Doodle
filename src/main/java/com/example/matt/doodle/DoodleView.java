package com.example.matt.doodle;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.graphics.Path;
import java.util.ArrayList;

/**
 * Created by Matt on 3/10/2016.
 */
public class DoodleView extends View {

    private Paint paintDoodle = new Paint();
    private Path path =  new Path();
    ArrayList<Path> pathes = new ArrayList<Path>();
    ArrayList<Paint> paintDoodles = new ArrayList<Paint>();
    ArrayList<Path> undonePathes = new ArrayList<Path>();
    ArrayList<Paint> undonePaints = new ArrayList<Paint>();




    public DoodleView(Context context){
        super(context);
        init(null, 0);
    }

    public DoodleView(Context context, AttributeSet atts){
        super(context, atts);
        init(atts, 0);
    }

    public DoodleView(Context context, AttributeSet attrs, int defStyle){
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }
    public void init(AttributeSet atts, int defStyle){
        paintDoodle.setColor(Color.RED);
        paintDoodle.setAntiAlias(true);
        paintDoodle.setStyle(Paint.Style.STROKE);
        paintDoodle.setAlpha(255);
        //paintDoodle.setStrokeWidth(100);
        paintDoodles.add(paintDoodle);
        path = new Path();
        pathes.add(path);

    }

    public void onDraw(Canvas canvas){
        super.onDraw(canvas);
        for(int i = 0; i<pathes.size();i++){
            canvas.drawPath(pathes.get(i), paintDoodles.get(i));

        }
    }

    public void changeColor(int c){
        Paint tempPaint = new Paint();
        tempPaint.setColor(c);
        tempPaint.setAntiAlias(true);
        tempPaint.setStyle(paintDoodles.get(paintDoodles.size() - 1).getStyle());
        tempPaint.setAlpha(paintDoodles.get(paintDoodles.size() - 1).getAlpha());
        tempPaint.setStrokeWidth(paintDoodles.get(paintDoodles.size() - 1).getStrokeWidth());
        Path temp = new Path();
        paintDoodles.add(tempPaint);
        pathes.add(temp);
    }

    public void changeOpacity(int o){
        Paint tempPaint = new Paint();
        tempPaint.setColor(paintDoodles.get(paintDoodles.size()-1).getColor());
        tempPaint.setAntiAlias(true);
        tempPaint.setStyle(paintDoodles.get(paintDoodles.size() - 1).getStyle());
        tempPaint.setAlpha(o);
        tempPaint.setStrokeWidth(paintDoodles.get(paintDoodles.size() - 1).getStrokeWidth());
        Path temp = new Path();
        paintDoodles.add(tempPaint);
        pathes.add(temp);
    }

    public void changeBrushSize(int s) {
        Paint tempPaint = new Paint();
        tempPaint.setColor(paintDoodles.get(paintDoodles.size() - 1).getColor());
        tempPaint.setAntiAlias(true);
        tempPaint.setStyle(paintDoodles.get(paintDoodles.size() - 1).getStyle());
        tempPaint.setAlpha(paintDoodles.get(paintDoodles.size() - 1).getAlpha());
        tempPaint.setStrokeWidth(s);
        Path temp = new Path();
        paintDoodles.add(tempPaint);
        pathes.add(temp);
    }

    public void clearScreen(){
        Path temp = new Path();
        paintDoodle.setColor(paintDoodles.get(paintDoodles.size() - 1).getColor());
        paintDoodle.setAntiAlias(true);
        paintDoodle.setStyle(paintDoodles.get(paintDoodles.size() - 1).getStyle());
        paintDoodle.setAlpha(paintDoodles.get(paintDoodles.size() - 1).getAlpha());
        paintDoodle.setStrokeWidth(paintDoodles.get(paintDoodles.size() - 1).getStrokeWidth());
        paintDoodles.clear();
        pathes.clear();
        invalidate();
        paintDoodles.add(paintDoodle);
        pathes.add(temp);
    }

    public void undo(){
        if (pathes.size()>0 && paintDoodles.size()>0) {
            Paint temp = new Paint();
            Path tempP = new Path();
            temp.setColor(paintDoodles.get(paintDoodles.size() - 1).getColor());
            temp.setAlpha(paintDoodles.get(paintDoodles.size() - 1).getAlpha());
            temp.setStrokeWidth(paintDoodles.get(paintDoodles.size() - 1).getStrokeWidth());
            temp.setAntiAlias(true);
            temp.setStyle(Paint.Style.STROKE);
            undonePathes.add(pathes.get(pathes.size() - 1));
            pathes.remove(pathes.size() - 1);
            undonePaints.add(paintDoodles.get(paintDoodles.size() - 1));
            paintDoodles.remove(paintDoodles.size() - 1);
            if(pathes.size()==0) {
                paintDoodles.add(temp);
                pathes.add(tempP);
            } else {

            }


            invalidate();
        }
    }

    public void redo(){
        if (undonePathes.size()>0 && undonePaints.size()>0) {
            Paint temp = new Paint();
            Path tempP = new Path();
            temp.setColor(undonePaints.get(undonePaints.size() - 1).getColor());
            temp.setAlpha(undonePaints.get(undonePaints.size() - 1).getAlpha());
            temp.setStrokeWidth(undonePaints.get(undonePaints.size() - 1).getStrokeWidth());
            temp.setAntiAlias(true);
            temp.setStyle(Paint.Style.STROKE);
            pathes.add(undonePathes.get(undonePathes.size() - 1));
            undonePathes.remove(undonePathes.size() - 1);
            paintDoodles.add(undonePaints.get(undonePaints.size() - 1));
            undonePaints.remove(undonePaints.size() - 1);
            //  if(pathes.size()==0) {
            //paintDoodles.add(temp);
            //pathes.add(tempP);
            //} else {

            //}


            invalidate();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent){
        float touchX = motionEvent.getX();
        float touchY = motionEvent.getY();

        switch(motionEvent.getAction()){
            case MotionEvent.ACTION_DOWN:
                pathes.get(pathes.size()-1).moveTo(touchX, touchY);
                break;
            case MotionEvent.ACTION_MOVE:
                pathes.get(pathes.size()-1).lineTo(touchX, touchY);
                break;
            case MotionEvent.ACTION_UP:
                break;
        }

        invalidate();
        return true;
    }
}
