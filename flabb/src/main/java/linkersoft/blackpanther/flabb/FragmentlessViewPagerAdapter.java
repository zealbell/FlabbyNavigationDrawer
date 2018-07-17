package linkersoft.blackpanther.flabb;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by LiNKeR on 7/17/2018.
 */
class FragmentlessViewPagerAdapter extends PagerAdapter {

    int count;
    View views[];
    public FragmentlessViewPagerAdapter(int count,View ...views){
        this.count=count;this.views=views;
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public Object instantiateItem(ViewGroup collection, int position) {
        collection.addView(views[position]);
        return views[position];
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        collection.removeView((View)view);
    }


}
