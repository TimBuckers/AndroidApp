package apps.myapplication;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class FriendLevelsOverview extends FragmentActivity {

   AppSectionsPagerAdapter mAppSectionsPagerAdapter;
    ViewPager mViewPager;
    static String[] titles = {"1", "2", "3"};

    public void inGame(View view) {
        Intent intent = new Intent(this, FriendInGameActivity.class);
        Button button = (Button) findViewById(view.getId());
        String buttonText = (String) button.getText();
        intent.putExtra("LevelNumber", buttonText);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_levels_overview);


        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        if(displayMetrics.heightPixels / displayMetrics.widthPixels < 1){
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.homescreen_background));
        }else{
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.homescreen_background_long));
        }

        // ViewPager and its adapters use support library
        // fragments, so use getSupportFragmentManager.
        mAppSectionsPagerAdapter =
                new AppSectionsPagerAdapter(
                        getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mAppSectionsPagerAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_levels_overview, menu);
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

    public static class AppSectionsPagerAdapter extends FragmentPagerAdapter {
        public AppSectionsPagerAdapter(FragmentManager fm) { super(fm); }

        @Override
        public Fragment getItem(int i)
        {
            switch(i) {
                case 0:
                    return new LevelOneFragment();
                case 1:
                    return new LevelTwoFragment();
                case 2:
                    return new LevelThreeFragment();
                default:
                    return new LevelOneFragment();
            }
        }

        @Override
        public int getCount() { return 3; }

        @Override
        public CharSequence getPageTitle(int position)
        {
            return titles[position];
        }
    }

}
