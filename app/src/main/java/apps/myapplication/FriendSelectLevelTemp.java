package apps.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class FriendSelectLevelTemp extends AppCompatActivity {


    public void selectLevelsComp(View view) {
        Intent intent = new Intent(this, FriendInGameActivity.class);
        Button button = (Button) findViewById(view.getId());
        String buttonText = (String) button.getText();
        intent.putExtra("LevelNumber", buttonText);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_select_level_temp);

        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        if(displayMetrics.heightPixels / displayMetrics.widthPixels < 1){
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.homescreen_background));
        }else{
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.homescreen_background_long));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_select_difficulty, menu);
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
