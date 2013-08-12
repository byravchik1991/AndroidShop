package com.example.Shop;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created with IntelliJ IDEA.
 * User: Иришка
 * Date: 01.08.13
 */
public class ShopPagerAdapter extends FragmentPagerAdapter {
    private BuyProductListFragment buyProductListFragment = new BuyProductListFragment();
    private DeliverProductFragment deliverProductFragment = new DeliverProductFragment(buyProductListFragment);

    public static int PAGE_COUNT = 2;

    public ShopPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return buyProductListFragment;
        }

        return deliverProductFragment;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }
}
