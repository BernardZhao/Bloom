package com.vb.ups.activities;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.vb.ups.R;
import com.vb.ups.async.NotificationFirstUpdater;
import com.vb.ups.async.NotificationManager;
import com.vb.ups.objects.Notification;
import com.vb.ups.services.RegistrationService;
import com.vb.ups.adapters.ViewPagerAdapter;

import net.danlew.android.joda.JodaTimeAndroid;

public class MainActivity extends AppCompatActivity {

    //Declaring All The Variables Needed

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NotificationFirstUpdater firstUpdater = new NotificationFirstUpdater();
        firstUpdater.execute(new Notification("","","","","",false));
        JodaTimeAndroid.init(this);
        setContentView(R.layout.activity_main);
        Intent i = new Intent(this, RegistrationService.class);
        startService(i);
        /*
        Assigning view variables to their respective view in xml
        by findViewByID method
         */

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        viewPager = (ViewPager) findViewById(R.id.viewpager);

        /*
        Creating Adapter and setting that adapter to the viewPager
        setSupportActionBar method takes the toolbar and sets it as
        the default action bar thus making the toolbar work like a normal
        action bar.
         */

        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);
        setSupportActionBar(toolbar);

        /*
        TabLayout.newTab() method creates a tab view, Now a Tab view is not the view
        which is below the tabs, its the tab itself.
         */

        final TabLayout.Tab tab1 = tabLayout.newTab();
        final TabLayout.Tab tab2 = tabLayout.newTab();
        final TabLayout.Tab tab3 = tabLayout.newTab();

        /*
        Setting Title text for our tabs respectively
         */

        tab1.setText("Feed");
        tab2.setText("Find");
        tab3.setText("Share");

        /*
        Adding the tab view to our tablayout at appropriate positions
        As I want home at first position I am passing home and 0 as argument to
        the tablayout and like wise for other tabs as well
         */
        tabLayout.addTab(tab1, 0);
        tabLayout.addTab(tab2, 1);
        tabLayout.addTab(tab3, 2);

        /*
        TabTextColor lets the color for the title of the tabs, passing a ColorStateList here makes
        tab change colors in different situations such as selected, active, inactive etc
        TabIndicatorColor sets the color for the indicator below the tabs
         */

        tabLayout.setTabTextColors(ContextCompat.getColorStateList(this, R.color.tab_selector));
        tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(this, R.color.colorAccent));
        /*
        Adding a onPageChangeListener to the viewPager
        1st we add the PageChangeListener and pass a TabLayoutPageChangeListener so that Tabs Selection
        changes when a viewpager page changes.
         */

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        NotificationManager notificationManager = new NotificationManager();
        notificationManager.execute(new Notification("","","","","",false));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void fabtap(View view) {
        Intent intent = new Intent(this, FormActivity.class);
        startActivity(intent);
    }

    public void addOneClicked(View view) {
        ((CardView) findViewById(R.id.card_view11)).setVisibility(View.GONE);
    }

    public void addFourClicked(View view) {
        ((CardView) findViewById(R.id.card_view12)).setVisibility(View.GONE);

    }

    public void addThreeClicked(View view) {
        ((CardView) findViewById(R.id.card_view13)).setVisibility(View.GONE);

    }

    public void addTwoClicked(View view) {
        ((CardView) findViewById(R.id.card_view14)).setVisibility(View.GONE);

    }
}