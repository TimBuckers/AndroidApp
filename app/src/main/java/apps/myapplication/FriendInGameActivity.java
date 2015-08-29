package apps.myapplication;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Tim on 8/19/2015.
 */
public class FriendInGameActivity extends ActionBarActivity {
    private Toolbar toolbar;
    MediaPlayer mediaPlayer = null;


    /**
     * Level Data (first two values is the width x length)
     **/
    // Level 1
    private static int[] arrayLevel1 = {5, 5, 13, 14, 15, 24, 43, 52, 53};
    // Level 2
    private static int[] arrayLevel2 = {5, 5, 22, 32, 34, 43, 44};
    // Level 3
    private static int[] arrayLevel3 = {5, 5, 21, 25, 42, 53, 55};
    // Level 4
    private static int[] arrayLevel4 = {6, 6, 16, 23, 32, 34, 35, 45, 61};
    // Level 5
    private static int[] arrayLevel5 = {6, 6, 12, 14, 25, 32, 43, 45, 51, 63};
    // Level 6
    private static int[] arrayLevel6 = {6, 6, 16, 22, 26, 34, 43, 52, 54};
    // Level 7
    private static int[] arrayLevel7 = {7, 7, 12, 17, 23, 27, 34, 42, 45, 53, 64, 75};
    // Level 8
    private static int[] arrayLevel8 = {7, 7, 16, 22, 32, 35, 43, 46, 51, 56, 63, 65};
    // Level 9
    private static int[] arrayLevel9 = {7, 7, 11, 13, 15, 17, 31, 34, 37, 43, 45, 51, 54, 57, 71, 73, 75, 77};
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
    private static int levelNumberInt;

    // Score
    private static  int scoreA = 0;
    private static  int scoreB = 0;

    private static int clickA = 0;
    private static int clickB = 0;

    // turn A(0) B(1) or Done(2)
    private static int turn = 0;

    public static void boardHoles()
    {
        switch(levelNumberInt)
        {
            case 1:
                levelWidth = arrayLevel1[0];
                levelLength = arrayLevel1[1];
                for(int i = 2; i < arrayLevel1.length; i++)
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
                for(int i = 2; i < arrayLevel2.length; i++)
                {
                    arrayD.add(arrayLevel2[i]);
                }
                break;
            case 3:
                levelWidth = arrayLevel3[0];
                levelLength = arrayLevel3[1];
                for(int i = 2; i < arrayLevel3.length; i++)
                {
                    arrayD.add(arrayLevel3[i]);
                }
                break;
            case 4:
                levelWidth = arrayLevel4[0];
                levelLength = arrayLevel4[1];
                for(int i = 2; i < arrayLevel4.length; i++)
                {
                    arrayD.add(arrayLevel4[i]);
                }
                break;
            case 5:
                levelWidth = arrayLevel5[0];
                levelLength = arrayLevel5[1];
                for(int i = 2; i < arrayLevel5.length; i++)
                {
                    arrayD.add(arrayLevel5[i]);
                }
                break;
            case 6:
                levelWidth = arrayLevel6[0];
                levelLength = arrayLevel6[1];
                for(int i = 2; i < arrayLevel6.length; i++)
                {
                    arrayD.add(arrayLevel6[i]);
                }
                break;
            case 7:
                levelWidth = arrayLevel7[0];
                levelLength = arrayLevel7[1];
                for(int i = 2; i < arrayLevel7.length; i++)
                {
                    arrayD.add(arrayLevel7[i]);
                }
                break;
            case 8:
                levelWidth = arrayLevel8[0];
                levelLength = arrayLevel8[1];
                for(int i = 2; i < arrayLevel8.length; i++)
                {
                    arrayD.add(arrayLevel8[i]);
                }
                break;
            case 9:
                levelWidth = arrayLevel9[0];
                levelLength = arrayLevel9[1];
                for(int i = 2; i < arrayLevel9.length; i++)
                {
                    arrayD.add(arrayLevel9[i]);
                }
                break;
            default:
                levelWidth = arrayLevel1[0];
                levelLength = arrayLevel1[1];
                for(int i = 2; i < arrayLevel1.length; i++)
                {
                    arrayD.add(arrayLevel1[i]);
                }
                break;
        }
    }

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
        turn = 0;
        blockNumbers = 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_in_game);
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
        levelNumberInt = Integer.parseInt(numberString);
        
        /**
         * setup the map
         */

        boardHoles();

        int blocksize = blockSize(levelWidth);

        LinearLayout llHorizontal = (LinearLayout) findViewById(R.id.linearlayout_main);
        llHorizontal.setBackgroundColor(R.color.bgBlue);
        for(int c =1; c <levelWidth + 1; c++) {
            LinearLayout llVertical = new LinearLayout(this);
            llVertical.setOrientation(LinearLayout.VERTICAL);

            //add 5 blocks
            for (int r = 1; r < levelLength + 1; r++) {
                if( !arrayD.contains(r * 10 + c ) ) {

                    ImageView ib = new ImageView(this);
                    Bitmap b = decodeSampledBitmapFromResource(getResources(), R.drawable.empty_block, blocksize, blocksize);
                    BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), b);
                    ib.setImageDrawable(bitmapDrawable);

                    //set border
                    final int sdk = android.os.Build.VERSION.SDK_INT;
                    if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                        ib.setBackgroundDrawable( getResources().getDrawable(R.drawable.border) );
                    } else {
                        ib.setBackground( getResources().getDrawable(R.drawable.border));
                    }
                    int id = r * 10 + c;
                    ib.setOnClickListener(onClickListener);
                    ib.setId(id);
                    llVertical.addView(ib);
                    blockNumbers++;
                }else{
                    ImageView ib = new ImageView(this);
                    Bitmap b = decodeSampledBitmapFromResource(getResources(), R.drawable.block_black, blocksize, blocksize);
                    BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), b);
                    ib.setImageDrawable(bitmapDrawable);
                    //set border
                    final int sdk = android.os.Build.VERSION.SDK_INT;
                    if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                        ib.setBackgroundDrawable( getResources().getDrawable(R.drawable.border) );
                    } else {
                        ib.setBackground( getResources().getDrawable(R.drawable.border));
                    }
                    int id = 1000 + r * 10 + c;
                    ib.setId(id);
                    llVertical.addView(ib);
                    blockNumbers++;
                }
            }

            llHorizontal.addView(llVertical);

            // turn
            TextView tv = (TextView) findViewById(R.id.playerAB);
            if(turn == 0)
            {
                tv.setText("Orange");
                tv.setTextColor(Color.parseColor("#ff9000"));
            }
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

                        ImageView ib1 = (ImageView) findViewById(clickA);
                        Bitmap b1 = decodeSampledBitmapFromResource(getResources(), R.drawable.block_black, blockSize(5), blockSize(5));
                        BitmapDrawable bitmapDrawable1 = new BitmapDrawable(getResources(), b1);
                        ib1.setImageDrawable(bitmapDrawable1);

                    } else {
                        arrayA.add(clickA);
                        Log.d("test", "clickA: " + clickA);
                        ImageView ib2 = (ImageView) findViewById(clickA);
                        Bitmap b2 = decodeSampledBitmapFromResource(getResources(), R.drawable.orange, blockSize(5), blockSize(5));
                        BitmapDrawable bitmapDrawable2 = new BitmapDrawable(getResources(), b2);
                        ib2.setImageDrawable(bitmapDrawable2);

                        arrayB.add(clickB);
                        Log.d("test", "clickB: " + clickB);
                        ImageView ib3 = (ImageView) findViewById(clickB);
                        Bitmap b3 = decodeSampledBitmapFromResource(getResources(), R.drawable.purple, blockSize(5), blockSize(5));
                        BitmapDrawable bitmapDrawable3 = new BitmapDrawable(getResources(), b3);
                        ib3.setImageDrawable(bitmapDrawable3);
                    }

                    calculateScores();
                    TextView textViewPlayer1 = (TextView) findViewById(R.id.scorePlayerA);
                    TextView textViewPlayer2 = (TextView) findViewById(R.id.scorePlayerB);
                    textViewPlayer1.setText("Player 1: " + scoreA);
                    textViewPlayer2.setText("Player 2: " + scoreB);

                    Log.d("Level: ", levelNumberString);
                    Log.d("arrayA: ", arrayA.size() + " elements");
                    Log.d("arrayB: ", arrayB.size() + " elements");
                    Log.d("arrayD for case 1: ", arrayD.size() + " elements");
                    Log.d("arrayX: ", arrayX.size() + " elements");
                    Log.d("Total blocks: ", blockNumbers + " blocks");
                    turn = 0;
                }

                TextView tv = (TextView) findViewById(R.id.playerAB);
                if(turn == 0){
                    tv.setText("Orange");
                    tv.setTextColor(Color.parseColor("#ff9000"));
                }
                if(turn == 1){
                    tv.setText("Blue");
                    tv.setTextColor(Color.parseColor("#24c2da"));
                }
            }

            if(boardFull())
            {
                calculateScores();
                arrayA.clear();
                arrayB.clear();
                arrayD.clear();
                arrayX.clear();
                blockNumbers = 0;
                turn = 0;
                Intent intent = new Intent(v.getContext(), FriendPostGameActivity.class);
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

    public static void calculateScores()
    {
        switch(levelNumberInt)
        {
            case 1:
                scoreA = TheScore.totalScore(arrayA, arrayLevel1[0], arrayLevel1[1]);
                scoreB = TheScore.totalScore(arrayB, arrayLevel1[0], arrayLevel1[1]);
                break;
            case 2:
                scoreA = TheScore.totalScore(arrayA, arrayLevel2[0], arrayLevel2[1]);
                scoreB = TheScore.totalScore(arrayB, arrayLevel2[0], arrayLevel2[1]);
                break;
            case 3:
                scoreA = TheScore.totalScore(arrayA, arrayLevel3[0], arrayLevel3[1]);
                scoreB = TheScore.totalScore(arrayB, arrayLevel3[0], arrayLevel3[1]);
                break;
            case 4:
                scoreA = TheScore.totalScore(arrayA, arrayLevel4[0], arrayLevel4[1]);
                scoreB = TheScore.totalScore(arrayB, arrayLevel4[0], arrayLevel4[1]);
                break;
            case 5:
                scoreA = TheScore.totalScore(arrayA, arrayLevel5[0], arrayLevel5[1]);
                scoreB = TheScore.totalScore(arrayB, arrayLevel5[0], arrayLevel5[1]);
                break;
            case 6:
                scoreA = TheScore.totalScore(arrayA, arrayLevel6[0], arrayLevel6[1]);
                scoreB = TheScore.totalScore(arrayB, arrayLevel6[0], arrayLevel6[1]);
                break;
            case 7:
                scoreA = TheScore.totalScore(arrayA, arrayLevel7[0], arrayLevel7[1]);
                scoreB = TheScore.totalScore(arrayB, arrayLevel7[0], arrayLevel7[1]);
                break;
            case 8:
                scoreA = TheScore.totalScore(arrayA, arrayLevel8[0], arrayLevel8[1]);
                scoreB = TheScore.totalScore(arrayB, arrayLevel8[0], arrayLevel8[1]);
                break;
            case 9:
                scoreA = TheScore.totalScore(arrayA, arrayLevel9[0], arrayLevel9[1]);
                scoreB = TheScore.totalScore(arrayB, arrayLevel9[0], arrayLevel9[1]);
                break;
            default:
                scoreA = TheScore.totalScore(arrayA, arrayLevel1[0], arrayLevel1[1]);
                scoreB = TheScore.totalScore(arrayB, arrayLevel1[0], arrayLevel1[1]);
                break;
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
