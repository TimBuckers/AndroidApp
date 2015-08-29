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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Created by Tim on 8/19/2015.
 */
public class ComputerInGameActivity extends ActionBarActivity {
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
    // array with all available blocks in the map
    private static ArrayList<Integer> arrayAll = new ArrayList<Integer>();
    // Total amount of blocks
    private static int blockNumbers;
    // Which level
    private static String levelNumberString;
    private static int levelNumberInt;
    // Which difficulty (0 = easy, 1 = medium, 2 = hard)
    private static int difficulty = 0;
    private static String difficultyString = "Easy";

    // Score
    private static  int scoreA = 0;
    private static  int scoreComp = 0;

    private static int clickA = 0;
    private static int clickComp = 0;
    private static boolean firstClickComp = true;
    private static boolean secondClickComp = false;
    private static boolean compCanClick = true;

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
        arrayAll.clear();
        scoreA = 0;
        scoreComp = 0;
        clickA = 0;
        clickComp = 0;
        blockNumbers = 0;
        firstClickComp = true;
        secondClickComp = false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_computer_in_game);
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        String computerDifficulty = getIntent().getExtras().getString("Difficulty");
        difficultyString = computerDifficulty;
        if(computerDifficulty.equals("Medium"))
        {
            difficulty = 1;
        }
        if(computerDifficulty.equals("Hard"))
        {
            difficulty = 2;
        }

        Log.d("Difficulty: ", difficulty + "...");

        //start media
        /*
        if (mediaPlayer == null){
            mediaPlayer = MediaPlayer.create(this, R.raw.carefree);
            mediaPlayer.isLooping();
            mediaPlayer.start();
        }
        */

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
                    arrayAll.add(id);
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
                    computerMove();
                }
                turn++;

                if (turn == 1) {
                    if (clickA == clickComp) {
                        arrayX.add(clickA);
                        arrayAll.remove(new Integer(clickA));
                        ImageButton ib1 = (ImageButton) findViewById(clickA);
                        Bitmap b1 = decodeSampledBitmapFromResource(getResources(), R.drawable.block_black, blockSize(levelWidth), blockSize(levelWidth));
                        BitmapDrawable bitmapDrawable1 = new BitmapDrawable(getResources(), b1);
                        ib1.setBackground(bitmapDrawable1);

                    } else {
                        arrayA.add(clickA);
                        Log.d("test", "clickA: " + clickA);
                        arrayAll.remove(new Integer(clickA));
                        ImageButton ib2 = (ImageButton) findViewById(clickA);
                        Bitmap b2 = decodeSampledBitmapFromResource(getResources(), R.drawable.orange, blockSize(levelWidth), blockSize(levelWidth));
                        BitmapDrawable bitmapDrawable2 = new BitmapDrawable(getResources(), b2);
                        ib2.setBackground(bitmapDrawable2);
                        arrayB.add(clickComp);
                        Log.d("test", "clickB: " + clickComp);
                        arrayAll.remove(new Integer(clickComp));
                        ImageButton ib3 = (ImageButton) findViewById(clickComp);
                        Bitmap b3 = decodeSampledBitmapFromResource(getResources(), R.drawable.purple, blockSize(levelWidth), blockSize(levelWidth));
                        BitmapDrawable bitmapDrawable3 = new BitmapDrawable(getResources(), b3);
                        ib3.setBackground(bitmapDrawable3);
                    }

                    compCanClick = true;
                    calculateScores();
                    TextView textViewPlayer = (TextView) findViewById(R.id.scorePlayerA);
                    TextView textViewComputer = (TextView) findViewById(R.id.scorePlayerB);
                    textViewPlayer.setText("Player 1: " + scoreA);
                    textViewComputer.setText("Computer: " + scoreComp);

                    /*
                    //Log.d("Level: ", levelNumberString);
                    Log.d("arrayA: ", arrayA.size() + " elements");
                    Log.d("arrayB: ", arrayB.size() + " elements");
                    Log.d("arrayD: ", arrayD.size() + " elements");
                    Log.d("arrayX: ", arrayX.size() + " elements");
                    Log.d("arrayAll: ", arrayAll.size() + " elements");
                    Log.d("Total blocks: ", blockNumbers + " blocks");*/

                    turn = 0;
                }
            }

            if(boardFull() || noUsefullBlocksLeft())
            {

                Log.d("Game is over", "!");

                calculateScores();
                arrayA.clear();
                arrayB.clear();
                arrayD.clear();
                arrayX.clear();
                arrayAll.clear();
                blockNumbers = 0;
                Intent intent = new Intent(v.getContext(), ComputerPostGameActivity.class);
                intent.putExtra("LevelNumber", levelNumberString);
                intent.putExtra("ScoreA", scoreA);
                intent.putExtra("ScoreB", scoreComp);
                intent.putExtra("Difficulty", difficultyString);
                scoreA = 0;
                scoreComp = 0;
                clickA = 0;
                clickComp = 0;
                firstClickComp = true;
                secondClickComp = false;
                startActivity(intent);
            }

            // Log.d( "test", v.getId() + "a");
        }
    };

    public static void computerMove()
    {
        // First move of the computer is always random
        if(firstClickComp)
        {
            clickComp = arrayAll.get(randomInt(0, arrayAll.size() - 1));
            firstClickComp = false;
            secondClickComp = true;
        }
        else
        {
            switch(difficulty)
            {
                // Easy computer tries to put his next set in a neighbour square
                case 0:
                    compEasyMove();
                    break;
                // Medium computer selects neighbours and will get a point if possible
                case 1:
                    compMediumMove();
                    break;
                // Hard computer should be smart and make the best move possible
                case 2:
                    compHardMove();
                    break;
                default:
                    clickComp = arrayAll.get(randomInt(0, arrayAll.size() - 1));
                    break;
            }
        }
    }

    public static void compEasyMove() {
        ArrayList<Integer> neighbours = neighbours(clickComp);
        Collections.shuffle(neighbours);
        for(int i = 0; i < neighbours.size(); i++)
        {
            if(arrayAll.contains(neighbours.get(i)) && compCanClick)
            {
                clickComp = neighbours.get(i);
                compCanClick = false;
            }
        }
        if(compCanClick)
        {
            clickComp = arrayAll.get(randomInt(0, arrayAll.size() - 1));
            compCanClick = false;
        }
        neighbours.clear();
    }

    public static void compMediumMove() {

        tryToGetPoints();

        if(compCanClick)
        {
            ArrayList<Integer> neighbours = neighbours(clickComp);
            Collections.shuffle(neighbours);
            for(int i = 0; i < neighbours.size(); i++)
            {
                if(arrayAll.contains(neighbours.get(i)) && compCanClick)
                {
                    clickComp = neighbours.get(i);
                    compCanClick = false;
                }
            }
            neighbours.clear();
        }

        if(compCanClick)
        {
            clickComp = arrayAll.get(randomInt(0, arrayAll.size() - 1));
            compCanClick = false;
        }
    }

    public static void compHardMove()
    {
        if(secondClickComp)
        {
            ArrayList<Integer> neighbours2 = neighbours(clickComp);
            Collections.shuffle(neighbours2);
            for(int i = 0; i < neighbours2.size(); i++)
            {
                if(arrayAll.contains(neighbours2.get(i)))
                {
                    clickComp = neighbours2.get(i);
                    compCanClick = false;
                }
            }
            secondClickComp = false;
        }

        // Random selecting if he prefers to block a player or to score a point for each turn
        int oneOrTwo = randomInt(1, 2);

        if(compCanClick)
        {
            switch(oneOrTwo)
            {
                case 1:
                    tryToGetPoints();
                    tryToBlockPlayer();

                    if(compCanClick)
                    {
                        ArrayList<Integer> neighbours2 = neighbours(clickComp);
                        Collections.shuffle(neighbours2);
                        for(int i = 0; i < neighbours2.size(); i++)
                        {
                            if(arrayAll.contains(neighbours2.get(i)))
                            {
                                clickComp = neighbours2.get(i);
                                compCanClick = false;
                            }
                        }
                    }

                    if(compCanClick)
                    {
                        clickComp = arrayAll.get(randomInt(0, arrayAll.size() - 1));
                        compCanClick = false;
                    }

                    break;
                case 2:
                    tryToBlockPlayer();
                    tryToGetPoints();

                    if(compCanClick)
                    {
                        ArrayList<Integer> neighbours3 = neighbours(clickComp);
                        Collections.shuffle(neighbours3);
                        for(int i = 0; i < neighbours3.size(); i++)
                        {
                            if(arrayAll.contains(neighbours3.get(i)))
                            {
                                clickComp = neighbours3.get(i);
                                compCanClick = false;
                            }
                        }
                    }

                    if(compCanClick)
                    {
                        clickComp = arrayAll.get(randomInt(0, arrayAll.size() - 1));
                        compCanClick = false;
                    }

                    break;
                default:
                    tryToGetPoints();
                    tryToBlockPlayer();

                    if(compCanClick)
                    {
                        ArrayList<Integer> neighbours4 = neighbours(clickComp);
                        Collections.shuffle(neighbours4);
                        for(int i = 0; i < neighbours4.size(); i++)
                        {
                            if(arrayAll.contains(neighbours4.get(i)))
                            {
                                clickComp = neighbours4.get(i);
                                compCanClick = false;
                            }
                        }
                    }

                    if(compCanClick)
                    {
                        clickComp = arrayAll.get(randomInt(0, arrayAll.size() - 1));
                        compCanClick = false;
                    }

                    break;
            }
        }

    }

    public static void tryToGetPoints()
    {
        if(compCanClick)
        {
            ArrayList<Integer>[] combinations = combinationOfArray(arrayB);
            for(int i = 0; i < combinations.length; i++)
            {
                if(combinations[i].size() != 0)
                {
                    ThreeRow temp = new ThreeRow(combinations[i].get(0), combinations[i].get(1));
                    int[] possibleBlocks = temp.calcOptions();
                    if((possibleBlocks != null) && arrayAll.contains(new Integer(possibleBlocks[0])))
                    {
                        clickComp = possibleBlocks[0];
                        compCanClick = false;
                    }
                    else if((possibleBlocks != null) && arrayAll.contains(new Integer(possibleBlocks[1])))
                    {
                        clickComp = possibleBlocks[1];
                        compCanClick = false;
                    }
                }
            }
        }

    }

    public static void tryToBlockPlayer()
    {
        if(compCanClick)
        {
            ArrayList<Integer>[] combinations = combinationOfArray(arrayA);
            for(int i = 0; i < combinations.length; i++)
            {
                if(combinations[i].size() != 0)
                {
                    ThreeRow temp = new ThreeRow(combinations[i].get(0), combinations[i].get(1));
                    int[] possibleBlocks = temp.calcOptions();
                    if((possibleBlocks != null) && arrayAll.contains(new Integer(possibleBlocks[0])))
                    {
                        clickComp = possibleBlocks[0];
                        compCanClick = false;
                    }
                    else if((possibleBlocks != null) && arrayAll.contains(new Integer(possibleBlocks[1])))
                    {
                        clickComp = possibleBlocks[1];
                        compCanClick = false;
                    }
                }
            }
        }

    }

    public static ArrayList<Integer>[] combinationOfArray(ArrayList<Integer> usedBlocks)
    {
        ArrayList<Integer>[] res2 = new ArrayList[500];
        for(int i = 0; i < res2.length; i++)
        {
            res2[i] = new ArrayList<Integer>();
        }

        int index3 = 0;

        for(int index1 = 0; index1 < usedBlocks.size(); index1++)
        {
            for(int index2 = 0; index2 < usedBlocks.size(); index2++)
            {
                int block1 = usedBlocks.get(index1);
                int block2 = usedBlocks.get(index2);
                if(block1 != block2)
                {
                    res2[index3].add(block1);
                    res2[index3].add(block2);
                    index3++;
                }
            }
        }

        return res2;

    }

    public static boolean noUsefullBlocksLeft()
    {
        ArrayList<Integer> playerBlocks = new ArrayList<Integer>(arrayA);
        ArrayList<Integer> computerBlocks = new ArrayList<Integer>(arrayB);
        ArrayList<Integer> blocksLeft = new ArrayList<Integer>(arrayAll);

        calculateScores();

        int currentScoreOfPlayer = scoreA;
        int currentScoreOfComputer = scoreComp;

        int newScoreOfPlayer = 0;
        int newScoreOfComputer = 0;

        for(int i = 0; i < blocksLeft.size(); i++)
        {
            int randomBlock = blocksLeft.get(i);
            playerBlocks.add(randomBlock);
            computerBlocks.add(randomBlock);

        }

            switch(levelNumberInt)
            {
                case 1:
                    newScoreOfPlayer = TheScore.totalScore(playerBlocks, arrayLevel1[0], arrayLevel1[1]);
                    newScoreOfComputer = TheScore.totalScore(computerBlocks, arrayLevel1[0], arrayLevel1[1]);
                    break;
                case 2:
                    newScoreOfPlayer = TheScore.totalScore(playerBlocks, arrayLevel2[0], arrayLevel2[1]);
                    newScoreOfComputer = TheScore.totalScore(computerBlocks, arrayLevel2[0], arrayLevel2[1]);
                    break;
                case 3:
                    newScoreOfPlayer = TheScore.totalScore(playerBlocks, arrayLevel3[0], arrayLevel3[1]);
                    newScoreOfComputer = TheScore.totalScore(computerBlocks, arrayLevel3[0], arrayLevel3[1]);
                    break;
                case 4:
                    newScoreOfPlayer = TheScore.totalScore(playerBlocks, arrayLevel4[0], arrayLevel4[1]);
                    newScoreOfComputer = TheScore.totalScore(computerBlocks, arrayLevel4[0], arrayLevel4[1]);
                    break;
                case 5:
                    newScoreOfPlayer = TheScore.totalScore(playerBlocks, arrayLevel5[0], arrayLevel5[1]);
                    newScoreOfComputer = TheScore.totalScore(computerBlocks, arrayLevel5[0], arrayLevel5[1]);
                    break;
                case 6:
                    newScoreOfPlayer = TheScore.totalScore(playerBlocks, arrayLevel6[0], arrayLevel6[1]);
                    newScoreOfComputer = TheScore.totalScore(computerBlocks, arrayLevel6[0], arrayLevel6[1]);
                    break;
                case 7:
                    newScoreOfPlayer = TheScore.totalScore(playerBlocks, arrayLevel7[0], arrayLevel7[1]);
                    newScoreOfComputer = TheScore.totalScore(computerBlocks, arrayLevel7[0], arrayLevel7[1]);
                    break;
                case 8:
                    newScoreOfPlayer = TheScore.totalScore(playerBlocks, arrayLevel8[0], arrayLevel8[1]);
                    newScoreOfComputer = TheScore.totalScore(computerBlocks, arrayLevel8[0], arrayLevel8[1]);
                    break;
                case 9:
                    newScoreOfPlayer = TheScore.totalScore(playerBlocks, arrayLevel9[0], arrayLevel9[1]);
                    newScoreOfComputer = TheScore.totalScore(computerBlocks, arrayLevel9[0], arrayLevel9[1]);
                    break;
                default:
                    newScoreOfPlayer = TheScore.totalScore(playerBlocks, arrayLevel1[0], arrayLevel1[1]);
                    newScoreOfComputer = TheScore.totalScore(computerBlocks, arrayLevel1[0], arrayLevel1[1]);
                    break;
            }

            if(newScoreOfPlayer!=currentScoreOfPlayer || newScoreOfComputer!=currentScoreOfComputer)
            {
                return false;
            }

        return true;
    }

    public static void calculateScores()
    {
        switch(levelNumberInt)
        {
            case 1:
                scoreA = TheScore.totalScore(arrayA, arrayLevel1[0], arrayLevel1[1]);
                scoreComp = TheScore.totalScore(arrayB, arrayLevel1[0], arrayLevel1[1]);
                break;
            case 2:
                scoreA = TheScore.totalScore(arrayA, arrayLevel2[0], arrayLevel2[1]);
                scoreComp = TheScore.totalScore(arrayB, arrayLevel2[0], arrayLevel2[1]);
                break;
            case 3:
                scoreA = TheScore.totalScore(arrayA, arrayLevel3[0], arrayLevel3[1]);
                scoreComp = TheScore.totalScore(arrayB, arrayLevel3[0], arrayLevel3[1]);
                break;
            case 4:
                scoreA = TheScore.totalScore(arrayA, arrayLevel4[0], arrayLevel4[1]);
                scoreComp = TheScore.totalScore(arrayB, arrayLevel4[0], arrayLevel4[1]);
                break;
            case 5:
                scoreA = TheScore.totalScore(arrayA, arrayLevel5[0], arrayLevel5[1]);
                scoreComp = TheScore.totalScore(arrayB, arrayLevel5[0], arrayLevel5[1]);
                break;
            case 6:
                scoreA = TheScore.totalScore(arrayA, arrayLevel6[0], arrayLevel6[1]);
                scoreComp = TheScore.totalScore(arrayB, arrayLevel6[0], arrayLevel6[1]);
                break;
            case 7:
                scoreA = TheScore.totalScore(arrayA, arrayLevel7[0], arrayLevel7[1]);
                scoreComp = TheScore.totalScore(arrayB, arrayLevel7[0], arrayLevel7[1]);
                break;
            case 8:
                scoreA = TheScore.totalScore(arrayA, arrayLevel8[0], arrayLevel8[1]);
                scoreComp = TheScore.totalScore(arrayB, arrayLevel8[0], arrayLevel8[1]);
                break;
            case 9:
                scoreA = TheScore.totalScore(arrayA, arrayLevel9[0], arrayLevel9[1]);
                scoreComp = TheScore.totalScore(arrayB, arrayLevel9[0], arrayLevel9[1]);
                break;
            default:
                scoreA = TheScore.totalScore(arrayA, arrayLevel1[0], arrayLevel1[1]);
                scoreComp = TheScore.totalScore(arrayB, arrayLevel1[0], arrayLevel1[1]);
                break;
        }
    }

    public static ArrayList<Integer> neighbours(int block)
    {
        ArrayList<Integer> res = new ArrayList<Integer>();
        int temp = block - 11;
        res.add(temp);
        int temp2 = block - 10;
        res.add(temp2);
        int temp3 = block - 9;
        res.add(temp3);
        int temp4 = block - 1;
        res.add(temp4);
        int temp5 = block + 1;
        res.add(temp5);
        int temp6 = block + 9;
        res.add(temp6);
        int temp7 = block + 10;
        res.add(temp7);
        int temp8 = block + 11;
        res.add(temp8);
        return res;
    }

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

    public static int randomInt(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
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

