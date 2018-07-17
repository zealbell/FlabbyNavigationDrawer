package linkersoft.blackpanther.flabb;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import linkersoft.blackpanther.flabb.flabbKit.subNav;

public class FlabbyNavigationDrawer extends ViewPager{

    View DrawerLayout;
    View Drawer;
    subNav Flabb;
    int virtualScrollX;
    int Width;
    int Old;
    int size;
    int flabbColor;
    float Flnkr;
    boolean once =true;

    public FlabbyNavigationDrawer(Context context) {
        super(context);
    }
    public FlabbyNavigationDrawer(Context context, AttributeSet attrs) {
        super(context, attrs);
        in(context, attrs);
    }
    private void in(Context context, AttributeSet attrs){
        TypedArray _attrs = context.getTheme().obtainStyledAttributes(attrs, R.styleable.FlabbyNavigationDrawer, 0, 0);
        try {size = _attrs.getInt(R.styleable.FlabbyNavigationDrawer_size, 0);
             String color =_attrs.getString(R.styleable.FlabbyNavigationDrawer_flabbcolor);
             flabbColor=(color==null)?0x20FFFFFF: Color.parseColor(color);
        }finally{ _attrs.recycle();}
    }

    private ViewPager.OnPageChangeListener FlabbChange =new ViewPager.OnPageChangeListener(){
        boolean USERswipesRIGHT;
        boolean USERswipesLEFT;
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            USERswipesLEFT=!((virtualScrollX = getScrollX()) > Old);
            Old = virtualScrollX;
            USERswipesRIGHT = !USERswipesLEFT;

            if(USERswipesRIGHT && position == 0){
                Drawer.setX(-positionOffsetPixels);
            }else if(USERswipesLEFT && position+1 == 1){
                Drawer.setX(-positionOffsetPixels);
            }if(Width !=0) Flnkr =(Width -virtualScrollX)/(float) Width;

            Flabb.FlabbNim.start();
            Flabb.FlabbNim.setCurrentPlayTime(Math.round(Flnkr * Flabb.DURATION));
            Flabb.FlabbNim.cancel();

        }

        @Override
        public void onPageSelected(int position){
        }

        @Override
        public void onPageScrollStateChanged(int state){
        }
    };

    public void setup(Context context,int LayoutResId){
        setup(context,inflate(context, LayoutResId,null));
    }
    public void setup(Context context,View Drawer){
        View GlassView=new View(context);
        setAdapter(new FragmentlessViewPagerAdapter(2, DrawerLayout =inflate(context, R.layout.flabby,null),GlassView));
        this.Drawer = Drawer;
        Flabb =(subNav) DrawerLayout.findViewById(R.id.subNav);
        Flabb.paint.setColor(flabbColor);
        switch (size){
            case 0:setWeightedWidth(0.25F);break;
            case 1:setWeightedWidth(0.50F);break;
            case 2:setWeightedWidth(0.75F);break;
        }setFlabbOffset(dp2px(15,context));
    }
    private void setFlabbOffset(final int Offset){
        Flabb.post(new Runnable(){
            @Override
            public void run(){
                int OFFSET=(Offset-(Offset%10))*2;
                Flabb.setOFFSET(OFFSET);
                Flabb.preFlabb(Flabb.getWidth(),Flabb.getHeight());
                addOnPageChangeListener(FlabbChange);
            }
        });
    }
    private void setWeightedWidth(float weight){
        View Container=DrawerLayout.findViewById(R.id.Container);
        ViewGroup.LayoutParams vLp=Container.getLayoutParams();
        ((LinearLayout.LayoutParams)vLp).weight=weight;
        Container.setLayoutParams(vLp);
        ((ViewGroup)Container).addView(Drawer);
    }
    private int dp2px(float Xdp, Context context) {
        return Math.round(Xdp * context.getResources().getDisplayMetrics().density);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if(once &&getChildCount()>0){
              once =false;
              Width =getMeasuredWidth();
              final View Drawer=getChildAt(0);
              removeViewAt(0);
              setup(getContext(),Drawer);
              post(new Runnable() {
                  @Override
                  public void run() {
                      setCurrentItem(1);
                  }
              });
        }
    }
}

