package linkersoft.blackpanther.flabb.flabbKit;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.BounceInterpolator;

/**
 * Created by LiNKeR on 7/17/2018.
 */
public class subNav extends View {


    public subNav(Context context, AttributeSet attrs) {
        super(context, attrs);
        in();
    }
    public subNav(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        in();
    }

    Path FlabbPath;
    int OFFSET=100;
    public int DURATION =2000;
    int[][] qN0,qC0,qN1,qC1,qN2,qC2;
    Panther black0, black1, black2;
    public ObjectAnimator FlabbNim;
    public Paint paint=new Paint(){
        {
            setColor(Color.parseColor("#20ffffff"));
            setStyle(Style.FILL);
            setAntiAlias(true);
        }
    };

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    void in(){
        FlabbPath =new Path();
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view){
                FlabbNim.start();
            }
        });
    }
    private void Flabb(int w, int h){
        int qW=w-OFFSET;
        qN0 = new int[][]{new int[]{0, 0}, new int[]{qW, 0}, new int[]{qW, h}, new int[]{0, h}};
        qC0 =  new int[][]{new int[]{qW/4, 0}, new int[]{qW-qW/4, 0},
                new int[]{qW, h/4}, new int[]{qW,h-h/4},
                new int[]{qW-qW/4, h}, new int[]{qW/4, h},
                new int[]{0,h-h/4}, new int[]{0, h/4}
        };

        qN1 =  new int[][]{new int[]{0, 0}, new int[]{qW, 0}, new int[]{qW, h}, new int[]{0, h}};
        qC1 =  new int[][]{new int[]{qW/4, 0}, new int[]{qW-qW/4, 0},
                new int[]{qW-OFFSET, h/4}, new int[]{qW-OFFSET,h-h/4},//
                new int[]{qW-qW/4, h}, new int[]{qW/4, h},
                new int[]{0,h-h/4}, new int[]{0, h/4}
        };

        qN2 =  new int[][]{new int[]{0, 0}, new int[]{qW, 0}, new int[]{qW, h}, new int[]{0, h}};
        qC2 =  new int[][]{new int[]{qW/4, 0}, new int[]{qW-qW/4, 0},
                new int[]{qW+OFFSET, h/4}, new int[]{qW+OFFSET,h-h/4},//
                new int[]{qW-qW/4, h}, new int[]{qW/4, h},
                new int[]{0,h-h/4}, new int[]{0, h/4}
        };
        black0 =new Panther(4,8,qN0,qC0);
        black1 =new Panther(4,8,qN1,qC1);
        black2 =new Panther(4,8,qN2,qC2);

        PantherEvaluator panev=new PantherEvaluator(4,8);
        FlabbNim = ObjectAnimator.ofObject(this,"",panev, black0, black1, black2, black0);
        FlabbNim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                Panth((Panther)valueAnimator.getAnimatedValue());
            }
        });
        FlabbNim.setDuration(DURATION);
        FlabbNim.setInterpolator(new BounceInterpolator());
    }
    private void Panth(Panther panther){
        FlabbPath =panther.path;
        invalidate();
    }
    public void preFlabb(int w, int h){
        Flabb(w,h);
        Panth(black0);
    }

    @Override
    protected void onDraw(Canvas canvas){
        canvas.drawPath(FlabbPath,paint);
    }

    public void setOFFSET(int OFFSET) {
        //w+offset mustb<=realwidth of d view
        this.OFFSET = OFFSET;
    }
}
