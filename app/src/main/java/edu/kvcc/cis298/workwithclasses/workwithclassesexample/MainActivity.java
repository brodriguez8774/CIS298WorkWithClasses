package edu.kvcc.cis298.workwithclasses.workwithclassesexample;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //region Variables

    private TextView mResultTextView;
    private EditText mInputEditText;
    private Button mSubmitButton;
    private Button mActivityButton;

    private double mInputDouble;
    private double mResultdouble;

    private Calculate myCalculate;

    private final int KEY_INT = 1234;

    //endregion



    //region Override Methods

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Sets variables to proper xml id's.
        mResultTextView = (TextView) findViewById(R.id.result_text_view);
        mInputEditText = (EditText) findViewById(R.id.number_input);
        mSubmitButton = (Button) findViewById(R.id.submit_button);
        mActivityButton = (Button) findViewById(R.id.new_activity_button);

        myCalculate = new Calculate();

        // Loads saved instance if available.
        if (savedInstanceState != null) {
            mResultTextView.setText(Double.toString(savedInstanceState.getDouble("The_Key", 0)));
        }

        // On click listener to calculate user's input.
        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mInputEditText.length() > 0) {
                    mInputDouble = Double.parseDouble(mInputEditText.getText().toString());
                    mResultdouble = myCalculate.MultiplyByFour(mInputDouble);

                    mResultTextView.setText(Double.toString(mResultdouble));
                }
            }
        });

        mActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = SubActivity.newIntent(MainActivity.this, "This is a message.");

                // Line below is used to start activity without a result.
                // startActivity(i);

                // Alternatively, if you want a result, use this line. Needs intent + unique number.
                startActivityForResult(i, KEY_INT);
            }
        });

    }

    // Saves the result on instance change.
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putDouble("The_Key", mResultdouble);
    }

    // Gets the result of ended activity (sub activity).
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Checks what result was.
        if (resultCode != Activity.RESULT_OK) {
            // Do work here because it wasn't successful.
            return;
        }

        // Checks the request code to identify which activity result it is. Request codes are
        // assigned to activities on creation of activity.
        if (requestCode == KEY_INT) {
            // If intent is valid and not null.
            if (data != null) {
                boolean activityVisitedBool = SubActivity.userVisitedActivity(data);
                // Checks to see what the bool returned as. If true, then display toast.
                if (activityVisitedBool)
                {
                    Toast.makeText(this, "Activity was visited.", Toast.LENGTH_SHORT).show();
                }
            }
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

    //endregion
}
