package apps.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class ComputerPostGameActivity extends AppCompatActivity {

    // Which level you just came from
    private static String levelNumber;
    // How high the difficulty is
    private static String difficulty;
    // Score
    private static  int scoreA;
    private static  int scoreB;

    public void playAgain(View view) {

        levelNumber = getIntent().getExtras().getString("LevelNumber");
        difficulty = getIntent().getExtras().getString("Difficulty");
        Intent intent = new Intent(this, ComputerInGameActivity.class);
        intent.putExtra("LevelNumber", levelNumber);
        intent.putExtra("Difficulty", difficulty);
        startActivity(intent);
    }

    public void selectLevel(View view) {
        Intent intent = new Intent(this, ComputerSelectLevelActivity.class);
        difficulty = getIntent().getExtras().getString("Difficulty");
        intent.putExtra("Difficulty", difficulty);
        startActivity(intent);
    }

    public void selectDifficulty(View view) {
        Intent intent = new Intent(this, ComputerSelectDifficultyActivity.class);
        startActivity(intent);
    }

    public void goToHomeScreen(View view) {
        Intent intent = new Intent(this, HomeScreenActivity.class);
        startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_computer_post_game);

        levelNumber = getIntent().getExtras().getString("LevelNumber");
        setTitle("Finished " + levelNumber);
        scoreA = getIntent().getExtras().getInt("ScoreA");
        scoreB = getIntent().getExtras().getInt("ScoreB");

        TextView viewScoreA =  (TextView) findViewById(R.id.scorePA);
        TextView viewScoreB =  (TextView) findViewById(R.id.scorePB);

        viewScoreA.setText("Player A: " + "\n" + scoreA);
        viewScoreB.setText("Player B: " + "\n" + scoreB);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_post_game, menu);
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
