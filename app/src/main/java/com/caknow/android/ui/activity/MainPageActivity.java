package com.caknow.android.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.caknow.android.R;
import com.caknow.android.ui.BaseFragmentArgs;
import com.caknow.android.ui.adapter.ViewPagerMainAdapter;
import com.caknow.android.ui.fragment.DisableFragment;
import com.caknow.android.widget.MainViewPager;

import net.gtr.framework.app.activity.RxBaseActivity;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by caroline on 2017/12/10.
 */

public class MainPageActivity extends RxBaseActivity {
    @BindView(R.id.main_pager_container)
    MainViewPager viewPager;
    @BindView(R.id.main_pager_layout)
    LinearLayout mainPagerLayout;
    private ViewPagerMainAdapter mainPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<BaseFragmentArgs> fragmentArgs = new ArrayList<BaseFragmentArgs>();
        fragmentArgs.add(new BaseFragmentArgs(DisableFragment.class, R.string.tab_service, R.drawable.icons_tab_service));
        fragmentArgs.add(new BaseFragmentArgs(DisableFragment.class, R.string.tab_community, R.drawable.icons_tab_community));
        fragmentArgs.add(new BaseFragmentArgs(DisableFragment.class, R.string.tab_advice, R.drawable.icons_tab_advice));
        fragmentArgs.add(new BaseFragmentArgs(DisableFragment.class, R.string.tab_settings, R.drawable.icons_tab_settings));
        initPager(viewPager, fragmentArgs);
    }

    @Override
    protected void onNewIntent(Intent intent) {
//        int item = intent.getIntExtra("page_position", 1);
//        viewPager.setCurrentItem(item, false);
//        Fragment fragment = mainPagerAdapter.getItem(item);
//        if (fragment instanceof CurrentOrdersFragment) {
//            ((CurrentOrdersFragment) fragment).receiveData(intent);
//        }
        super.onNewIntent(intent);
    }

    /**
     * 初始化 Viewpager
     *
     * @param viewPager
     * @param fragmentArgs
     */
    private void initPager(final MainViewPager viewPager, final ArrayList<BaseFragmentArgs> fragmentArgs) {
        // set pager
        mainPagerAdapter = new ViewPagerMainAdapter(getSupportFragmentManager(), fragmentArgs);
        viewPager.setAdapter(mainPagerAdapter);
        viewPager.setOffscreenPageLimit(5);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int postion) {
                // final Fragment selectedFragment =
                // mainPagerAdapter.getItem(postion);
                BaseFragmentArgs curpage = fragmentArgs.get(postion);
                Class curFragment = curpage.getFragment();
//                if (curFragment.getName().equals(LocationFragment.class.getName())) {
                //TripRecordService.onlocationPage = true;
//                } else {
                //TripRecordService.onlocationPage = false;
//                }
                for (int i = 0; i < mainPagerLayout.getChildCount(); i++) {
                    LinearLayout itemBoom = (LinearLayout) mainPagerLayout.getChildAt(i);
                    if (i == postion) {
                        itemBoom.setSelected(true);
                    } else {
                        itemBoom.setSelected(false);
                    }
                }
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });
        for (int index = 0; index < fragmentArgs.size(); index++) {
            addItemArg(fragmentArgs, index, viewPager);
        }
        // initial
        final int initPosition = 0;
        viewPager.setCurrentItem(initPosition, false);
        for (int i = 0; i < mainPagerLayout.getChildCount(); i++) {
            LinearLayout itemBoom = (LinearLayout) mainPagerLayout.getChildAt(i);
            if (i == initPosition) {
                itemBoom.setSelected(true);
            } else {
                itemBoom.setSelected(false);
            }
        }
    }

    @SuppressLint("InflateParams")
    private void addItemArg(ArrayList<BaseFragmentArgs> fragmentArgs, final int index, final MainViewPager viewPager) {
        BaseFragmentArgs args = fragmentArgs.get(index);
        LinearLayout layout = (LinearLayout) LayoutInflater.from(MainPageActivity.this).inflate(R.layout.layout_item_page,
                null);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setLayoutParams(params);
        ImageView icon = (ImageView) layout.findViewById(R.id.page_icon);
        TextView name = (TextView) layout.findViewById(R.id.page_name);
        name.setSingleLine();
        icon.setImageResource(args.drawable);
        try {
            ColorStateList csl = MainPageActivity.this.getResources().getColorStateList(R.color.color_tab_text);
            name.setTextColor(csl);
        } catch (Exception e) {
            e.printStackTrace();
        }
        name.setText(args.name);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(index, false);
            }
        });
        mainPagerLayout.addView(layout);
    }
}