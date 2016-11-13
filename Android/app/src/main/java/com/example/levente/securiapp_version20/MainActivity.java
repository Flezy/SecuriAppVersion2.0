package com.example.levente.securiapp_version20;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.view.Window;
import android.widget.Toast;

import com.example.levente.barcode_util.IntentIntegrator;
import com.example.levente.barcode_util.IntentResult;
import com.example.levente.models.Person;
import com.example.levente.models.Rating;


public class MainActivity extends FragmentActivity {

    CollectionPagerAdapter mCollectionPagerAdapter;
    ViewPager mViewPager;
    FirstFragment mFragment1;
    SecondFragment mFragment2;
    ThirdFragment mFragment3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFragment1 = new FirstFragment();
        mFragment2 = new SecondFragment();
        mFragment3 = new ThirdFragment();
        mCollectionPagerAdapter =
                new CollectionPagerAdapter(
                        getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setOffscreenPageLimit(2);
        mViewPager.setAdapter(mCollectionPagerAdapter);

    }

    public void refreshPointsOnThirdActivity() {
         mFragment3.fillUpContent();
    }

    public void setPostButtonText(String text) {
        mFragment1.setPostButton(text);
        mFragment3.refreshPersonalityTextThirdFragment();
    }


    public class CollectionPagerAdapter extends FragmentPagerAdapter {
        public CollectionPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return mFragment1;
                case 1:
                    return mFragment2;
                case 2:
                    return mFragment3;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Identity";
                case 1:
                    return "Rating";
                case 2:
                    return "Conclusion";
                default:
                    return null;
            }
        }
    }
    public void scanBarcode(){
        IntentIntegrator scanIntegrator = new IntentIntegrator(this);
        scanIntegrator.initiateScan();
    }
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scnResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scnResult != null) {
            String scnCnt = scnResult.getContents();
            Toast.makeText(getApplicationContext(),scnCnt, Toast.LENGTH_SHORT).show();
            mFragment1.changeText(scnCnt);
            mFragment3.refreshPersonalityTextThirdFragment();
        }
    }

    public void refreshPersonality(int id, String name){
        Person.getInstance().setId(id);
        Person.getInstance().setName(name);
    }


}
