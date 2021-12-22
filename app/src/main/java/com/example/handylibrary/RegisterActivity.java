package com.example.handylibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;

public class RegisterActivity extends AppCompatActivity {

    private EditText editTextName, editTextEmail, editTextPassword,editTextConfirmPassword;
    private CircularProgressButton signup;
    private DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        changeStatusBarColour();
        initUI();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editTextEmail.getText().toString();
                //*****************************************
                Log.i("SendMailActivity", "Send Button Clicked.");

                String fromEmail = "";
                String fromPassword = "";
                String toEmails = "";

                new SendMailTask(RegisterActivity.this).execute(email);
                //*****************************************
                String name = editTextName.getText().toString();

                String password = editTextPassword.getText().toString();
                String repassword = editTextConfirmPassword.getText().toString();

                if(name.equals("")||email.equals("")||password.equals("")||repassword.equals(""))
                    Toast.makeText(RegisterActivity.this,"Please enter all the fields",Toast.LENGTH_LONG).show();
                else
                    if(password.equals(repassword)){
                        Boolean checkEmail = DB.checkEmail(email);
                        if(checkEmail==false){
                            Boolean insert = DB.insertData(name,email,password);
                            if(insert==true){
                                Toast.makeText(RegisterActivity.this,"Registered Successfully",Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(RegisterActivity.this,"Registration Failed",Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(RegisterActivity.this,"User Already Exists",Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(RegisterActivity.this,"Password is not matching",Toast.LENGTH_SHORT).show();
                    }
            }
        });
    }

    private void initUI() {
        editTextName = findViewById(R.id.editTextName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextConfirmPassword = findViewById(R.id.editTextConfirmPassword);
        signup = findViewById(R.id.cirRegisterButton);
        DB = new DBHelper(this);
    }

    private void changeStatusBarColour() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){

            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.register_bk_color));
        }
    }

    public void onLoginClick(View view){
        startActivity(new Intent(this,LoginActivity.class));
        overridePendingTransition(R.anim.slide_in_left,android.R.anim.slide_out_right);
    }
}