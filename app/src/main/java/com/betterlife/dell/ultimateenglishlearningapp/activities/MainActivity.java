package com.betterlife.dell.ultimateenglishlearningapp.activities;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.SystemClock;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.NotificationCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.betterlife.dell.ultimateenglishlearningapp.helperclasses.FetchMeData;
import com.betterlife.dell.ultimateenglishlearningapp.Fragments.ChatFrag;
import com.betterlife.dell.ultimateenglishlearningapp.Fragments.FragGrammer;
import com.betterlife.dell.ultimateenglishlearningapp.Fragments.FragVocab;
import com.betterlife.dell.ultimateenglishlearningapp.R;
import com.betterlife.dell.ultimateenglishlearningapp.broadcast.MyNotificationPublisher;
import com.betterlife.dell.ultimateenglishlearningapp.modals.LevelData;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;


public class MainActivity extends AppCompatActivity {


    Toolbar toolbar;
    ViewPager mViewPager;
    SectionsPagerAdapter mSectionsPagerAdapter;
    DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        // setup toolbar with Tabs
        toolbar = (Toolbar) (findViewById(R.id.toolbar));
        setSupportActionBar(toolbar);

        TabLayout tabs = (TabLayout) findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setText("Tab 1"));
        tabs.addTab(tabs.newTab().setText("Tab 2"));
        tabs.addTab(tabs.newTab().setText("Tab 3"));

        //Link tabs with view pager
        tabs.setupWithViewPager(mViewPager);
        //getSupportActionBar().setTitle("Hello World");
        //toolbar.setTitle("Hello World");

        toolbar.setSubtitle("Home");

        // Create Navigation drawer and inflate layout
        final NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);

        //Adding menu icon to Toolbar
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setTitle("");
            //supportActionBar.setHomeAsUpIndicator(R.mipmap.ic_launcher);
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }


        //Set behavior of Navigation drawer
        navigationView.setNavigationItemSelectedListener
                (
                        new NavigationView.OnNavigationItemSelectedListener() {
                            // This method will trigger on item Click of navigation menu
                            @Override
                            public boolean onNavigationItemSelected(MenuItem menuItem) {


                                if (menuItem.getItemId() == R.id.action_about) {

                                    Intent in = new Intent(getApplicationContext(), About.class);
                                    startActivity(in);


                                } else if (menuItem.getItemId() == R.id.contact) {
                                    Intent in = new Intent(getApplicationContext(), Contact.class);
                                    startActivity(in);
                                } else if (menuItem.getItemId() == R.id.action_home) {
                                    mDrawerLayout.closeDrawer(GravityCompat.START);

                                } else if (menuItem.getItemId() == R.id.share_setting) {
                                    try {
                                        Intent i = new Intent(Intent.ACTION_SEND);
                                        i.setType("text/plain");
                                        i.putExtra(Intent.EXTRA_SUBJECT, "Ultimate English Learning");
                                        String sAux = "\n Download this app if you want to improve your English. It is really helpful\n\n";
                                        sAux = sAux + "https://play.google.com/store/apps/details?id=com.betterlife.dell.ultimateenglishlearningapp\n\n";
                                        i.putExtra(Intent.EXTRA_TEXT, sAux);
                                        startActivity(Intent.createChooser(i, "Choose one"));
                                    } catch (Exception e) {

                                        e.printStackTrace();
                                    }
                                }


                                mDrawerLayout.closeDrawers();
                                return true;

                            }
                        });

        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.app_name, R.string.app_name) {

            public void onDrawerClosed(View view) {
                supportInvalidateOptionsMenu();
                //drawerOpened = false;
            }

            public void onDrawerOpened(View drawerView) {
                supportInvalidateOptionsMenu();
                //drawerOpened = true;
            }
        };
        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        int notid = (int) (1 + (10000 - 1) * (Math.random()));

        SharedPreferences sp = getSharedPreferences("UEL", MODE_PRIVATE);
        String nflag = sp.getString("nflag", "na");
        if (nflag.equals("na")) {
            scheduleNotification(getApplicationContext(), (180*60000), notid);
            MobileAds.initialize(this, R.string.appid + "");

            SharedPreferences.Editor editor = sp.edit();
            editor.putString("nflag", "yes");
            editor.commit();
        }
    }

    public void scheduleNotification(Context context, long delay, int notificationId) {

        Log.d("MYMSG", "in function");

        Intent intent = new Intent(context, datadetail.class);
        LevelData obj = new FetchMeData().fetchLevel(context);
        intent.putExtra("title", obj.title);
        intent.putExtra("id", obj.id);
        intent.putExtra("answer", obj.answer);
        intent.putExtra("additional", obj.additional);
        intent.putExtra("photo", obj.photo);
        intent.putExtra("fromnotification", "yes");

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setContentTitle(obj.title)
                .setContentText(obj.answer)
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.ic_launcher_round);


        PendingIntent activity = PendingIntent.getActivity(context, notificationId, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        builder.setContentIntent(activity);
        Notification notification = builder.build();
        Intent notificationIntent = new Intent(context, MyNotificationPublisher.class);
        notificationIntent.putExtra(MyNotificationPublisher.NOTIFICATION_ID, notificationId);
        notificationIntent.putExtra(MyNotificationPublisher.NOTIFICATION, notification);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, notificationId, notificationIntent, PendingIntent.FLAG_CANCEL_CURRENT);
        long futureInMillis = SystemClock.elapsedRealtime() + delay;
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, futureInMillis, pendingIntent);


    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.mytoolbarmenu, menu);
//        return true;
//    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_about) {
            return true;
        } else if (id == android.R.id.home) {
            mDrawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            if (position == 0)
                return (new FragVocab());
            else if (position == 1)
                return (new FragGrammer());
            else if (position == 2)
                return (new ChatFrag());
            return null;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Vocabulary";
                case 1:
                    return "Grammer";
                case 2:
                    return "Conversation";
            }
            return null;
        }

    }
}
