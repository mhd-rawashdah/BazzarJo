package edu.zu.demo.graduation.bazzar.BasicActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.github.ybq.android.spinkit.style.FadingCircle;

import edu.zu.demo.graduation.bazzar.DatabaseLayer.LoginActivityDb;
import edu.zu.demo.graduation.bazzar.Model.Buyer;
import edu.zu.demo.graduation.bazzar.Model.User;
import edu.zu.demo.graduation.bazzar.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    EditText phoneNumber, password;
    Button login, registerAsBuyer, registerAsSeller;
    Toolbar mToolbar;
    TextView forgetPassword;
    Spinner userTypeSpinner;
    private static int userType;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initialView();
        setTitle("Login");
        setSupportActionBar(mToolbar);                    // add toolBar
        userTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                userType=userTypeSpinner.getSelectedItemPosition();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    } //end onCreate()

    public void initialView() {
        phoneNumber = findViewById(R.id.Login_phone_edit);
        password = findViewById(R.id.Login_password_edit);
        login = findViewById(R.id.Login_login);
        registerAsBuyer = findViewById(R.id.Login_registerAsBuyer);
        registerAsSeller = findViewById(R.id.Login_registerAsSeller);
        mToolbar = findViewById(R.id.Home_toolbar);
        forgetPassword = findViewById(R.id.Login_forgetPassword_txt);
        userTypeSpinner=findViewById(R.id.userTypeSpinner);
        progressBar=findViewById(R.id.LoginActivity_spinKit);
        FadingCircle fadingCircle=new FadingCircle();
        progressBar.setIndeterminateDrawable(fadingCircle);

        login.setOnClickListener(this);
        registerAsBuyer.setOnClickListener(this);
        registerAsSeller.setOnClickListener(this);
        forgetPassword.setOnClickListener(this);

    } //end initialView()

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.Login_login:
                loginToApp();
//                goTo(MainActivity.class,0);
//                finish();
                break;
            case R.id.Login_registerAsBuyer:
                goTo(PhoneRegisterActivity.class,R.id.Login_registerAsBuyer);

                break;
            case R.id.Login_registerAsSeller:
                goTo(PhoneRegisterActivity.class,R.id.Login_registerAsSeller);


        }
    }  //end onClick()

    public void loginToApp(){
       String phone=phoneNumber.getText().toString();
       String pass=password.getText().toString();

        User user=new User();

        if (phone.length()<9 || phone.isEmpty()){
            Toast.makeText(this, "Invalid phone number", Toast.LENGTH_SHORT).show();
            phoneNumber.setError("Invalid");
            return;
        }else if(phone.charAt(0)=='0')
        {
            phone="962"+phone.substring(1);

        }else if(phone.charAt(0)=='7'){
            phone="962"+phone;
        }
        if (pass.length()<8 || pass.isEmpty()){
            Toast.makeText(this, "password at least 8 character ", Toast.LENGTH_SHORT).show();
            password.setError("Invalid");
            return;
        }
        user.setPhoneNumber(phone);
        user.setPassword(pass);
        user.setUserType(userType);
        LoginActivityDb loginActivityDb=new LoginActivityDb(getApplicationContext(),user,progressBar);
        loginActivityDb.checkUserIno();

    }

    // go to another activity
    public void goTo(Class activity,int id) {

        Intent in = new Intent(LoginActivity.this, activity);
        Bundle bundle=new Bundle();
        bundle.putInt("typeUser", id);
        in.putExtras(bundle);
        startActivity(in);

    } //end goTo()
}//end class
