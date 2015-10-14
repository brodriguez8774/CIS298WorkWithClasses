package edu.kvcc.cis298.workwithclasses.workwithclassesexample;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class SubActivity extends AppCompatActivity {

    private TextView mMessageTextView;

    private static final String KEY_INDEX = "the_sub_key";
    private static final String VISITED_KEY_INDEX = "been_there";

    private boolean mVisitedActivity;

    /**
     * Tells the calling activity how to launch this activity.
     * @param packageContext The activity trying to call. In this example, MainActivity.this.
     * @param theMessage The message the calling activity wants to pass to this activity.
     * @return The Intent the calling activity will use to launch this activity.
     */
    public static Intent newIntent(Context packageContext, String theMessage) {
        Intent i = new Intent(packageContext, SubActivity.class);
        i.putExtra(KEY_INDEX, theMessage);
        return i;
    }

    public static boolean userVisitedActivity(Intent result) {
        return result.getBooleanExtra(VISITED_KEY_INDEX, false);
    }

    //region Override methods

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        mMessageTextView = (TextView) findViewById(R.id.message);
        mVisitedActivity = true;

        String theMessageString =getIntent().getStringExtra(KEY_INDEX);
        mMessageTextView.setText(theMessageString);

        Intent data = new Intent();
        data.putExtra(VISITED_KEY_INDEX, mVisitedActivity);
        setResult(RESULT_OK, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sub, menu);
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

    //endregion

}
