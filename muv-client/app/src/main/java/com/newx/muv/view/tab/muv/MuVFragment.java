package com.newx.muv.view.tab.muv;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.newx.base.frameworks.route.facade.annotation.Route;
import com.newx.base.frameworks.support.mvvm.BaseViewModel;
import com.newx.base.proxy.ResourceProxy;
import com.newx.base.frameworks.widget.toast.ToastUtil;
import com.newx.base.ui.viewpager.indicator.Indicator;
import com.newx.base.ui.viewpager.indicator.IndicatorViewPager;
import com.newx.base.ui.viewpager.indicator.slidebar.ColorBar;
import com.newx.base.ui.viewpager.indicator.transition.OnTransitionTextListener;
import com.newx.muv.R;
import com.newx.base.frameworks.support.mvvm.NxMvvMFragment;
import com.newx.muv.base.def.KEY;
import com.newx.muv.databinding.FragmentMuvBinding;
import com.newx.muv.proxy.MiddlewareProxy;
import com.newx.muv.view.route.FRAGMENT;


/**
 * Created by xuzhijian on 2018/4/8 0008.
 */
@Route(path = FRAGMENT.MuVFragment)
public class MuVFragment extends NxMvvMFragment<FragmentMuvBinding, BaseViewModel> {

    private IndicatorViewPager mIndicatorViewPager;


    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);

        init();
    }

    @Override
    public BaseViewModel initVM() {
        return null;
    }

    public void init(){
        ViewPager viewPager = mBinding.fragmentTabMainViewPager;
        Indicator indicator = mBinding.fragmentTabMainIndicator;


        indicator.setScrollBar(new ColorBar(getApplicationContext(), Color.RED, 5));

        float unSelectSize = 12;
        float selectSize = unSelectSize * 1.2f;

        int selectColor = ResourceProxy.getColor(R.color.Black);
        int unSelectColor = ResourceProxy.getColor(R.color.Gray_AA);
        indicator.setOnTransitionListener(new OnTransitionTextListener().setColor(selectColor, unSelectColor).setSize(selectSize, unSelectSize));


        mIndicatorViewPager = new IndicatorViewPager(indicator, viewPager);

        // 注意这里 的FragmentManager 是 getChildFragmentManager(); 因为是在Fragment里面
        // 而在activity里面用FragmentManager 是 getSupportFragmentManager()
        mIndicatorViewPager.setAdapter(new MuVAdapter(getChildFragmentManager()));

        mIndicatorViewPager.setCurrentItem(1, false);
        viewPager.setOffscreenPageLimit(4);

        mIndicatorViewPager.getViewPager().addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                switch (position) {
                    case 2:
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        ToastUtil.showShort("初始化完毕");
    }

    @Override
    public int initContentView() {
        return R.layout.fragment_muv;
    }

    @Override
    public int initVariableId() {
        return 0;
    }

    private class MuVAdapter extends IndicatorViewPager.IndicatorFragmentPagerAdapter {

        private String[] tabNames = {
                ResourceProxy.getString(R.string.live_text),
                ResourceProxy.getString(R.string.popular_text),
                ResourceProxy.getString(R.string.featured_text),
                ResourceProxy.getString(R.string.same_city_text),};


        public MuVAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public int getCount() {
            return tabNames.length;
        }

        @Override
        public View getViewForTab(int position, View convertView, ViewGroup container) {
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.tab_top, container, false);
            }
            TextView textView = (TextView) convertView;
            textView.setText(tabNames[position]);
            return convertView;
        }

        @Override
        public Fragment getFragmentForPage(int position) {
            switch (position) {
                case 0:
                    return MiddlewareProxy.findFragment(FRAGMENT.LiveFragment);
                case 1:
                    Bundle bundle = new Bundle();
                    bundle.putFloat(KEY.KEY_BUNDLE_PADDING_BOTTOM, ResourceProxy.getDimension(R.dimen.default_bar_height));
                    return MiddlewareProxy.findFragment(FRAGMENT.PopularFragment,bundle);
                case 2:
                    return MiddlewareProxy.findFragment(FRAGMENT.FeaturedFragment);
                case 3:
                    return MiddlewareProxy.findFragment(FRAGMENT.SameCityFragment);
            }
            return null;
        }
    }

}
