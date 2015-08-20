package apps.myapplication;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

/**
 * Created by Tim on 8/19/2015.
 */
public class InGameActivity extends ActionBarActivity {
    private Toolbar toolbar;
    MediaPlayer mediaPlayer = null;



    /**
     * Game Data
     **/
    // Player A
    private static ArrayList<Integer> arrayA = new ArrayList<Integer>();
    // Player B
    private static ArrayList<Integer> arrayB = new ArrayList<Integer>();
    // X for the disabled blocks in game
    private static ArrayList<Integer> arrayX = new ArrayList<Integer>();
    // array with disabled blocks map
    private static ArrayList<Integer> arrayD = new ArrayList<Integer>();

    // Score
    private static  int scoreA =0;
    private static  int scoreB =0;

    private static int clickA = 0;
    private static int clickB = 0;

    // turn A(0) B(1) or Done(2)
    private static int turn = 0;

    @Override
    protected void onStop(){
        super.onStop();
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_game);
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        //start media
        if (mediaPlayer == null){
            mediaPlayer = MediaPlayer.create(this, R.raw.carefree);
            mediaPlayer.isLooping();
            mediaPlayer.start();
        }

        String levelNumber = getIntent().getExtras().getString("LevelNumber");
        setTitle(levelNumber);
        
        /**
         * setup the map
         */

        int blocksize = blockSize(5);

        LinearLayout llHorizontal = (LinearLayout) findViewById(R.id.linearlayout_main);
        for(int c =1; c <6; c++) {
            LinearLayout llVertical = new LinearLayout(this);
            llVertical.setOrientation(LinearLayout.VERTICAL);


            arrayD.add(22);
            arrayD.add(31);
            arrayD.add(44);
            arrayD.add(23);

            //add 5 blocks
            for (int r = 1; r < 6; r++) {
                if( !arrayD.contains(r * 10 + c ) ) {

                    ImageButton ib = new ImageButton(this);
                    Bitmap b = decodeSampledBitmapFromResource(getResources(), R.drawable.empty_block, blocksize, blocksize);
                    BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), b);
                    ib.setBackground(bitmapDrawable);
                    int id = r * 10 + c;
                    ib.setOnClickListener(onClickListener);
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

    private View.OnClickListener onClickListener = new View.OnClickListener() {

        @Override
        public void onClick(final View v) {
            int blockID = v.getId();
            // validate move
            if(!arrayX.contains(blockID) && !arrayA.contains(blockID) && !arrayB.contains(blockID)) {
                if (turn == 0) {
                    clickA = blockID;
                }
                if (turn == 1) {
                    clickB = blockID;
                }
                turn++;

                if (turn == 2) {
                    if (clickA == clickB) {
                        arrayX.add(clickA);

                        ImageButton ib1 = (ImageButton) findViewById(clickA);
                        Bitmap b1 = decodeSampledBitmapFromResource(getResources(), R.drawable.block_black, blockSize(5), blockSize(5));
                        BitmapDrawable bitmapDrawable1 = new BitmapDrawable(getResources(), b1);
                        ib1.setBackground(bitmapDrawable1);

                    } else {
                        arrayA.add(clickA);
                        Log.d("test", "clickA: " + clickA);
                        ImageButton ib2 = (ImageButton) findViewById(clickA);
                        Bitmap b2 = decodeSampledBitmapFromResource(getResources(), R.drawable.orange, blockSize(5), blockSize(5));
                        BitmapDrawable bitmapDrawable2 = new BitmapDrawable(getResources(), b2);
                        ib2.setBackground(bitmapDrawable2);

                        arrayB.add(clickB);
                        Log.d("test", "clickB: " + clickB);
                        ImageButton ib3 = (ImageButton) findViewById(clickB);
                        Bitmap b3 = decodeSampledBitmapFromResource(getResources(), R.drawable.purple, blockSize(5), blockSize(5));
                        BitmapDrawable bitmapDrawable3 = new BitmapDrawable(getResources(), b3);
                        ib3.setBackground(bitmapDrawable3);
                    }
                    turn = 0;
                }
            }

           // Log.d( "test", v.getId() + "a");
        }
    };

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
