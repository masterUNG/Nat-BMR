package appewtc.masterung.natbmr;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class SignUpActivity extends AppCompatActivity {

    //Explicit
    private EditText nameEditText, userEditText,
            passwordEditText, rePasswordEditText;
    private String nameString, userString,
            passwordString, rePasswordString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //Bind Widget
        nameEditText = (EditText) findViewById(R.id.editText);
        userEditText = (EditText) findViewById(R.id.editText2);
        passwordEditText = (EditText) findViewById(R.id.editText3);
        rePasswordEditText = (EditText) findViewById(R.id.editText4);

    }   // Main Method

    public void clickSignUpSign(View view) {

        //Get Value From Edit Text
        nameString = nameEditText.getText().toString().trim();
        userString = userEditText.getText().toString().trim();
        passwordString = passwordEditText.getText().toString().trim();
        rePasswordString = rePasswordEditText.getText().toString().trim();

        //Check Space
        if (nameString.equals("") ||
                userString.equals("") ||
                passwordString.equals("") ||
                rePasswordString.equals("")) {
            //Have Space
            MyAlert myAlert = new MyAlert();
            myAlert.myDialog(this, R.drawable.doremon48,
                    "มีช่องว่าง", "กรุณากรอก ทุกช่องคะ");
        } else if (!(passwordString.equals(rePasswordString))) {
            // Password False
            MyAlert myAlert = new MyAlert();
            myAlert.myDialog(this, R.drawable.nobita48,
                    "Password ไม่ตรงกัน", "กรุณากรอก Password ให้เหมือนๆ กันคะ");
        } else {
            // Password OK
        }


    }   // clickSignUp

}   // Main Class
