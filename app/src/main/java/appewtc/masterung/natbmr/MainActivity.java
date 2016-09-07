package appewtc.masterung.natbmr;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    //Explicit
    private static final String urlLogo = "http://swiftcodingthai.com/natt/ImageMaster/logo_bmi_master.png";
    private EditText userEditText, passwordEditText;
    private ImageView imageView;
    private String userString, passwordString;
    private static final String urlOffice365 = "http://www.office365thailand.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Bind Widget
        userEditText = (EditText) findViewById(R.id.editText5);
        passwordEditText = (EditText) findViewById(R.id.editText6);
        imageView = (ImageView) findViewById(R.id.imageView);

        //Load Image From Server
        Picasso.with(this).load(urlLogo).resize(180, 180).into(imageView);

    }   // Main Method

    //Create Inner Class
    private class SynUser extends AsyncTask<Void, Void, String> {

        //Explicit
        private Context context;
        private String myUserString, myPasswordString;
        private String[] loginStrings;
        private static final String urlJSON = "http://swiftcodingthai.com/natt/get_user_master.php";
        private boolean statusABoolean = true;

        public SynUser(Context context, String myUserString, String myPasswordString) {
            this.context = context;
            this.myUserString = myUserString;
            this.myPasswordString = myPasswordString;
        }

        @Override
        protected String doInBackground(Void... voids) {

            try {

                OkHttpClient okHttpClient = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                Request request = builder.url(urlJSON).build();
                Response response = okHttpClient.newCall(request).execute();
                return response.body().string();

            } catch (Exception e) {
                Log.d("NattV2", "e doInBack ==> " + e.toString());
                return null;
            }


        }   // doInBack

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Log.d("NattV2", "JSON ==> " + s);

            try {

                JSONArray jsonArray = new JSONArray(s);
                for (int i = 0; i < jsonArray.length(); i += 1) {

                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    if (myUserString.equals(jsonObject.getString("User"))) {

                        loginStrings = new String[9];
                        loginStrings[0] = jsonObject.getString("id");
                        loginStrings[1] = jsonObject.getString("Name");
                        loginStrings[2] = jsonObject.getString("User");
                        loginStrings[3] = jsonObject.getString("Password");
                        loginStrings[4] = jsonObject.getString("Position");
                        loginStrings[5] = jsonObject.getString("Weight");
                        loginStrings[6] = jsonObject.getString("Height");
                        loginStrings[7] = jsonObject.getString("Age");
                        loginStrings[8] = jsonObject.getString("BMI");

                        statusABoolean = false;

                    }   // if
                }   // for

                if (statusABoolean) {
                    MyAlert myAlert = new MyAlert();
                    myAlert.myDialog(context, R.drawable.kon48, "User Fasle",
                            "No " + myUserString + " in my Database");
                } else if (!(myPasswordString.equals(loginStrings[3]))) {
                    //Password False
                    MyAlert myAlert = new MyAlert();
                    myAlert.myDialog(context, R.drawable.rat48, "Password False",
                            "Please Try Again Password False");
                } else {
                    // Password True
                    Toast.makeText(context, "Welcome " + loginStrings[1],
                            Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, ServiceActivity.class);
                    intent.putExtra("Login", loginStrings);
                    startActivity(intent);
                    finish();
                }

            } catch (Exception e) {
                Log.d("NattV2", "e onPost ==> " + e.toString());
            }

        }   // onPost

    }   // SynUser Class


    public void clickWebSite(View view) {

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(urlOffice365));
        startActivity(intent);

    }   // clickWebSite


    public void clickSignIn(View view) {

        userString = userEditText.getText().toString().trim();
        passwordString = passwordEditText.getText().toString().trim();

        //Check SPace
        if (userString.equals("") || passwordString.equals("")) {
            //Have Space
            MyAlert myAlert = new MyAlert();
            myAlert.myDialog(this, R.drawable.bird48, "Have Space",
                    "Please Fill All Every Blank");
        } else {
            //No Space
            SynUser synUser = new SynUser(this, userString, passwordString);
            synUser.execute();
        }

    }   // clickSignIn

    public void clickSignUpMain(View view) {
        startActivity(new Intent(MainActivity.this, SignUpActivity.class));
    }

}   // Main Class
