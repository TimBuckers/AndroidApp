package apps.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class FriendPostGameActivity extends AppCompatActivity {

    // Which level you just came from
    private static String levelNumber;
    // Score
    private static  int scoreA;
    private static  int scoreB;

    public void playAgain(View view) {

        levelNumber = getIntent().getExtras().getString("LevelNumber");
        Intent intent = new Intent(this, FriendInGameActivity.class);
        intent.putExtra("LevelNumber", levelNumber);
        startActivity(intent);
    }

    public void goToHomeScreen(View view) {
        Intent intent = new Intent(this, HomeScreenActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_post_game_friend);

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
