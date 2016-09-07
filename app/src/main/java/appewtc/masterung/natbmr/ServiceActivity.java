package appewtc.masterung.natbmr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ServiceActivity extends AppCompatActivity {

    private String[] loginStrings;
    private EditText weightEditText, heightEditText, ageEditText;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        //Bind Widget
        weightEditText = (EditText) findViewById(R.id.editText7);
        heightEditText = (EditText) findViewById(R.id.editText8);
        ageEditText = (EditText) findViewById(R.id.editText9);
        textView = (TextView) findViewById(R.id.textView11);

        //Receive Value from Intent
        loginStrings = getIntent().getStringArrayExtra("Login");

        for (int i=0;i<loginStrings.length;i++) {
            Log.d("NattV3", "login(" + i + ") = " + loginStrings[i]);
        }   // for

        //Show View
        weightEditText.setText(loginStrings[5]);
        heightEditText.setText(loginStrings[6]);
        ageEditText.setText(loginStrings[7]);
        textView.setText(loginStrings[8]);

    }   // Main Method

    public void clickUpdateBMI(View view) {

    }   // clickUpdate

}   // Main Class
