package com.alexiscv.t4ejem_toolbar_designlibrary;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

public class MiFragmentPageAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT = 6;
    private String tabTitle[] = new String[]{"Tab uno", "Tab dos", "Tab tres", "Tab cuatro", "Tab cinco", "Tab seis"};

    /**
     * Constructor
     *
     * @param fm
     */
    public MiFragmentPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        Fragment f;

        Log.e("TEST", "Se ha llamado a getItem(" + i + ")");
        switch (i) {
            case 0:
                f = Fragment1.newInstance();
                break;

            case 1:
                f = Fragment2.newInstance();
                break;

            default:
                f = Fragment2.newInstance();
                break;

        }

        return f;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Genera el titulo basado en la posición del item
        return tabTitle[position];

    }
}
