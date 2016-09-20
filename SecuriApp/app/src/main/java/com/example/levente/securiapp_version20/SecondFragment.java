package com.example.levente.securiapp_version20;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.example.levente.raiting_fragments.Raiting1Fragment;
import com.example.levente.raiting_fragments.Raiting2Fragment;
import com.example.levente.raiting_fragments.Raiting3Fragment;
import com.example.levente.viewpager_util.*;

public class SecondFragment extends Fragment {


    RatingPagerAdapter mRatingPagerAdapter;
    VerticalViewPager mViewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.second_layout, container, false);
        /*mRatingPagerAdapter =
                new RatingPagerAdapter(
                        getFragmentManager());
        mViewPager = (VerticalViewPager) view.findViewById(R.id.pagerRaiting);
        MainActivity ma = (MainActivity)getActivity();
        mViewPager.setAdapter(mRatingPagerAdapter);
        mViewPager.setParent((ma.getVP()));*/

        return view;
    }
    public class RatingPagerAdapter extends FragmentPagerAdapter {
        public RatingPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new Raiting1Fragment();
                case 1:
                    return new Raiting2Fragment();
                case 2:
                    return new Raiting3Fragment();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 3;
        }

    }


}
