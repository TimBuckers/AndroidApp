package apps.myapplication;

import android.content.Intent;
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
import android.widget.Button;
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
     * Level Data (first two values is the length x width)
     **/
    // Level 1
    private static int[] arrayLevel1 = {5, 5, 13, 14, 15, 23, 24, 52, 53};
    // Level 2
    private static int[] arrayLevel2 = {5, 5, 11, 12, 15, 25, 33, 51, 52};
    // Level 3
    private static int[] arrayLevel3 = {6, 6, 33, 12, 65, 23, 24, 62, 53};
    // Width of the Level
    private static int levelWidth;
    // Length of the Level
    private static int levelLength;

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
    // Total amount of blocks
    private static int blockNumbers;
    // Which level
    private static String levelNumberString;

    // Score
    private static  int scoreA =2;
    private static  int scoreB =1;

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
        arrayA.clear();
        arrayB.clear();
        arrayD.clear();
        arrayX.clear();
        scoreA = 0;
        scoreB = 0;
        clickA = 0;
        clickB = 0;
        blockNumbers = 0;
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

        levelNumberString = getIntent().getExtras().getString("LevelNumber");
        setTitle(levelNumberString);
        String numberString = levelNumberString.substring(levelNumberString.length() - 1);
        int levelNumberInt = Integer.parseInt(numberString);
        
        /**
         * setup the map
         */
        switch(levelNumberInt)
        {
            case 1:
                levelWidth = arrayLevel1[0];
                levelLength = arrayLevel1[1];
                for(int i = 2; i < 9; i++)
                {
                    arrayD.add(arrayLevel1[i]);
                }
                /*
                Log.d("Level: ", levelNumberString);
                Log.d("arrayA: ", arrayA.size() + " elements");
                Log.d("arrayB: ", arrayB.size() + " elements");
                Log.d("arrayD for case 1: ", arrayD.size() + " elements");
                Log.d("arrayX: ", arrayX.size() + " elements");
                Log.d("Total blocks: ", blockNumbers + " blocks");*/
                break;
            case 2:
                levelWidth = arrayLevel2[0];
                levelLength = arrayLevel2[1];
                for(int i = 2; i < 9; i++)
                {
                    arrayD.add(arrayLevel2[i]);
                }
                break;
            case 3:
                levelWidth = arrayLevel3[0];
                levelLength = arrayLevel3[1];
                for(int i = 2; i < 9; i++)
                {
                    arrayD.add(arrayLevel3[i]);
                }
                break;
            default:
                levelWidth = arrayLevel1[0];
                levelLength = arrayLevel1[1];
                for(int i = 2; i < 9; i++)
                {
                    arrayD.add(arrayLevel1[i]);
                }
        }

        int blocksize = blockSize(levelWidth);

        LinearLayout llHorizontal = (LinearLayout) findViewById(R.id.linearlayout_main);
        for(int c =1; c <levelWidth + 1; c++) {
            LinearLayout llVertical = new LinearLayout(this);
            llVertical.setOrientation(LinearLayout.VERTICAL);

            //add 5 blocks
            for (int r = 1; r < levelLength + 1; r++) {
                if( !arrayD.contains(r * 10 + c ) ) {

                    ImageButton ib = new ImageButton(this);
                    Bitmap b = decodeSampledBitmapFromResource(getResources(), R.drawable.empty_block, blocksize, blocksize);
                    BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), b);
                    ib.setBackground(bitmapDrawable);
                    int id = r * 10 + c;
                    ib.setOnClickListener(onClickListener);
                    ib.setId(id);
                    llVertical.addView(ib);
                    blockNumbers++;
                }else{
                    ImageView ib = new ImageButton(this);
                    Bitmap b = decodeSampledBitmapFromResource(getResources(), R.drawable.block_black, blocksize, blocksize);
                    BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), b);
                    ib.setBackground(bitmapDrawable);
                    int id = 1000 + r * 10 + c;
                    ib.setId(id);
                    llVertical.addView(ib);
                    blockNumbers++;
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
                    Log.d("Level: ", levelNumberString);
                    Log.d("arrayA: ", arrayA.size() + " elements");
                    Log.d("arrayB: ", arrayB.size() + " elements");
                    Log.d("arrayD for case 1: ", arrayD.size() + " elements");
                    Log.d("arrayX: ", arrayX.size() + " elements");
                    Log.d("Total blocks: ", blockNumbers + " blocks");
                }
                if (turn == 1) {
                    clickB = blockID;
                    Log.d("Level: ", levelNumberString);
                    Log.d("arrayA: ", arrayA.size() + " elements");
                    Log.d("arrayB: ", arrayB.size() + " elements");
                    Log.d("arrayD for case 1: ", arrayD.size() + " elements");
                    Log.d("arrayX: ", arrayX.size() + " elements");
                    Log.d("Total blocks: ", blockNumbers + " blocks");
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
                    Log.d("Level: ", levelNumberString);
                    Log.d("arrayA: ", arrayA.size() + " elements");
                    Log.d("arrayB: ", arrayB.size() + " elements");
                    Log.d("arrayD for case 1: ", arrayD.size() + " elements");
                    Log.d("arrayX: ", arrayX.size() + " elements");
                    Log.d("Total blocks: ", blockNumbers + " blocks");
                    turn = 0;
                }
            }

            if(boardFull())
            {
                arrayA.clear();
                arrayB.clear();
                arrayD.clear();
                arrayX.clear();
                blockNumbers = 0;
                Intent intent = new Intent(v.getContext(), PostGameActivity.class);
                intent.putExtra("LevelNumber", levelNumberString);
                intent.putExtra("ScoreA", scoreA);
                intent.putExtra("ScoreB", scoreB);
                scoreA = 0;
                scoreB = 0;
                clickA = 0;
                clickB = 0;
                startActivity(intent);
            }

           // Log.d( "test", v.getId() + "a");
        }
    };

    public static boolean boardFull()
    {
        int usedAmountOfBlocks = arrayA.size() + arrayB.size() + arrayD.size() + arrayX.size();
        return (blockNumbers==usedAmountOfBlocks);
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
