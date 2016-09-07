package appewtc.masterung.natbmr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class ServiceActivity extends AppCompatActivity {

    private String[] loginStrings;
    private EditText weightEditText, heightEditText, ageEditText;
    private TextView textView;
    private static final String urlPHP = "http://swiftcodingthai.com/natt/edit_user_master.php";

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

        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = new FormEncodingBuilder()
                .add("isAdd", "true")
                .add("Weight", weightEditText.getText().toString().trim())
                .add("Height", heightEditText.getText().toString().trim())
                .add("Age", ageEditText.getText().toString().trim())
                .add("BMI", textView.getText().toString().trim())
                .build();
        Request.Builder builder = new Request.Builder();
        Request request = builder.url(urlPHP).post(requestBody).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                Log.d("NattV4", "Result ==> " + response.body().string());
            }
        });


    }   // clickUpdate

}   // Main Class
