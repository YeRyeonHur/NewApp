package com.example.newapp.explain;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.newapp.R;

public class explain_main extends FragmentActivity {
    private ViewPager2 vp;
    private FragmentStateAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exp_main);

        // Instantiate a ViewPager2 and a PagerAdapter.
        vp = findViewById(R.id.pager);
        adapter = new ScreenSlidePagerAdapter(this);
        vp.setAdapter(adapter);
        vp.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        vp.setCurrentItem(0);
        vp.setOffscreenPageLimit(3);
    }

    private class ScreenSlidePagerAdapter extends FragmentStateAdapter{
        public ScreenSlidePagerAdapter(FragmentActivity fa) {
            super(fa);
        }

        @Override
        public Fragment createFragment(int position) {
            int index = position % 3;
            if(index == 0) return new explain_page1();
            else if(index == 1) return new explain_page2();
            else return new explain_page3();
        }

        @Override
        public int getItemCount() {
            return 3;
        }
    }
}
