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


public class MainActivity extends FragmentActivity {

    CollectionPagerAdapter mCollectionPagerAdapter;
    ViewPager mViewPager;
    FirstFragment fragment1;
    SecondFragment fragment2;
    ThirdFragment fragment3;
    Person person;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        person = null;
        fragment1 = new FirstFragment();
        fragment2 = new SecondFragment();
        fragment3 = new ThirdFragment();
        mCollectionPagerAdapter =
                new CollectionPagerAdapter(
                        getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setOffscreenPageLimit(2);
        mViewPager.setAdapter(mCollectionPagerAdapter);

    }

    public class CollectionPagerAdapter extends FragmentPagerAdapter {
        public CollectionPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return fragment1;
                case 1:
                    return fragment2;
                case 2:
                    return fragment3;
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
            fragment1.changeText(scnCnt);
        }
    }
    public void setPerson(Person p){
        person = p;
    }
    public ViewPager getVP(){
        return mViewPager;
    }
    public void swipeRight(){
        mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);
    }
    public void swipeLeft(){mViewPager.setCurrentItem(mViewPager.getCurrentItem() - 1);}

}
