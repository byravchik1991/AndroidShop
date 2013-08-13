package com.example.Shop;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import com.example.Shop.data.ShopDao;
import com.example.Shop.data.ShopDaoFactory;

import java.util.HashMap;
import java.util.Map;

public class ShopActivity extends FragmentActivity {
    private static Context shopContext;

    FragmentPagerAdapter pagerAdapter;
    ViewPager mViewPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        initViewPager();
        initActionBar();
    }

    @Override
    protected void onResume() {
        super.onResume();

        shopContext = this;

        Map<String, Object> params = new HashMap<String, Object>();
        params.put(ShopDao.DIRECTORY_PARAM_NAME, getFilesDir().getAbsolutePath());
        ShopDaoFactory.getShopDao().initialize(params);
    }

    @Override
    protected void onPause() {
        super.onPause();

        ShopDaoFactory.getShopDao().terminate();
        shopContext = null;
    }

    private void initViewPager() {
        pagerAdapter = new ShopPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(pagerAdapter);
        mViewPager.setOnPageChangeListener(
                new ViewPager.SimpleOnPageChangeListener() {
                    @Override
                    public void onPageSelected(int position) {
                        getActionBar().setSelectedNavigationItem(position);
                    }
                });
    }

    private void initActionBar() {
        final ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        ActionBar.TabListener tabListener = new ActionBar.TabListener() {
            public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
            }

            public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
            }
        };

        ActionBar.Tab buyTab = actionBar.newTab();
        buyTab.setText(getString(R.string.buy));
        buyTab.setTabListener(tabListener);

        ActionBar.Tab saleTab = actionBar.newTab();
        saleTab.setText(getString(R.string.sale));
        saleTab.setTabListener(tabListener);

        actionBar.addTab(buyTab);
        actionBar.addTab(saleTab);
    }

    public static Context getShopContext() {
        return shopContext;
    }
}
