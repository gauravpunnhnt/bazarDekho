package com.hipernova.bazardekho;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private TextInputEditText emailin,passin;
    private TextInputLayout userInLayout,passInLayout;
    private String email,pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailin = findViewById(R.id.email_in);
        passin = findViewById(R.id.pass_in);

        userInLayout = findViewById(R.id.user_in_layout);
        passInLayout = findViewById(R.id.pass_in_layout);

        findViewById(R.id.log_in_btn).setOnClickListener(this);
        findViewById(R.id.sign_up_tv).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.log_in_btn:
                userLogin();
                break;

            case R.id.sign_up_tv:
                startActivity(new Intent(LoginActivity.this,SignUpActivity.class));
                break;
        }
    }

    private void userLogin() {
        email = emailin.getText().toString().trim();
        pass = passin.getText().toString().trim();

        if (email.isEmpty()){
            //emailin.setError("Email should be required for Log in");
            userInLayout.setError("Email is required");
            emailin.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            //emailin.setError("Please enter valid email");
            userInLayout.setError("Enter valid email");
            emailin.requestFocus();
            return;
        }

        if (pass.isEmpty()){
            //passin.setError("Password should be required for Log in");
            passInLayout.setError("Password is required");
            passin.requestFocus();
            return;
        }

        if (pass.length()<6){
            //passin.setError("Password length should be greater than 6");
            passInLayout.setError("Password length should be greater than 6");
            passin.requestFocus();
            return;
        }

        String type = "login";
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type,email,pass);
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
        System.exit(0);
    }
}
