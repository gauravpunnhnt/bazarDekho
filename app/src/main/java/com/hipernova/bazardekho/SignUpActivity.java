package com.hipernova.bazardekho;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Switch;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    private TextInputEditText nameup,emailup,passup,dobup;
    private DatePickerDialog.OnDateSetListener onDateSetListener;
    private String name,email,pass,dob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        nameup = findViewById(R.id.name_up);
        emailup = findViewById(R.id.email_up);
        passup = findViewById(R.id.pass_up);
        dobup = findViewById(R.id.dob_up);

        findViewById(R.id.sign_up_Btn).setOnClickListener(this);
        findViewById(R.id.log_in_tv).setOnClickListener(this);

        /*dobup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        SignUpActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        onDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

                onDateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String date = year + "-" + month + "-" + dayOfMonth;
                        dobup.setText(date);
                    }
                };
            }
        });*/
        final Calendar calendar = Calendar.getInstance();
        dobup.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                    new  DatePickerDialog(SignUpActivity.this,onDateSetListener,
                            calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String date = year + "-" + month + "-" + dayOfMonth;
                dobup.setText(date);
            }
        };

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.sign_up_Btn:
                userSignUp();
                break;

            case R.id.log_in_tv:
                startActivity(new Intent(SignUpActivity.this,LoginActivity.class));
                break;
        }

    }

    private void userSignUp() {
        name = nameup.getText().toString().trim();
        email = emailup.getText().toString().trim();
        pass = passup.getText().toString().trim();
        dob = dobup.getText().toString().trim();

        if (name.isEmpty()){
            nameup.setError("Name should be required");
            nameup.requestFocus();
            return;
        }

        if(email.isEmpty()){
            emailup.setError("Email should be required");
            emailup.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailup.setError("Please enter valid email");
            emailup.requestFocus();
            return;
        }

        if (pass.isEmpty()){
            passup.setError("Password should be required");
            passup.requestFocus();
            return;
        }

        if(pass.length()<6){
            passup.setError("Password length should be greater than 6");
            passup.requestFocus();
            return;
        }

        if (dob.isEmpty()){
            dobup.setError("Enter your Date of Birth");
            dobup.requestFocus();
            return;
        }
        String type = "register";
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type,name,email,pass,dob);
    }
}
