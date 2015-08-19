package apps.myapplication;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by Tim on 8/19/2015.
 */
public class InGameActivity extends ActionBarActivity {
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_game);
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        // array with empty blocks
        int[] array = {12,13,14, 22, 31};
        int blocksize = blockSize(5);

        LinearLayout llHorizontal = (LinearLayout) findViewById(R.id.linearlayout_main);
        for(int c =0; c <5; c++) {
            LinearLayout llVertical = new LinearLayout(this);
            llVertical.setOrientation(LinearLayout.VERTICAL);


            //add 5 blocks
            for (int r = 0; r < 5; r++) {
                if( !contains(array, r * 10 + c ) ) {

                    ImageButton ib = new ImageButton(this);
                    Bitmap b = decodeSampledBitmapFromResource(getResources(), R.drawable.empty_block, blocksize, blocksize);
                    BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), b);
                    ib.setBackground(bitmapDrawable);
                    int id = 1000 + r * 10 + c;
                    ib.setId(id);
                    llVertical.addView(ib);
                }else{
                    ImageView ib = new ImageButton(this);
                    Bitmap b = decodeSampledBitmapFromResource(getResources(), R.drawable.block_black, blocksize, blocksize);
                    BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), b);
                    ib.setBackground(bitmapDrawable);
                    int id = 1000 + r * 10 + c;
                    ib.setId(id);
                    llVertical.addView(ib);

                }
            }

            llHorizontal.addView(llVertical);
        }


    }
    
    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                         int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }
    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    public int blockSize(int blocks){
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        float toolbarHeight = displayMetrics.density * 50;
        int width = displayMetrics.widthPixels;
        float realHeight = displayMetrics.heightPixels - toolbarHeight;

        if( realHeight < width) {
            return Math.round( (realHeight ) / (blocks + 2) );
        }else{
            return Math.round( (width ) / (blocks + 2) );
        }
    }

    public boolean contains( int[] array, int number){
        for(int i = 0 ; i < array.length; i++){
            if(array[i] == number){
                return true;
            }
        }
        return false;
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

}
