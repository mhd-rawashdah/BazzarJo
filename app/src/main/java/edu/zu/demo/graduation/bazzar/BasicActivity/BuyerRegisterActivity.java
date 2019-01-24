package edu.zu.demo.graduation.bazzar.BasicActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import edu.zu.demo.graduation.bazzar.Model.Buyer;
import edu.zu.demo.graduation.bazzar.DatabaseLayer.BuyerRegisterDb;
import edu.zu.demo.graduation.bazzar.R;

public class BuyerRegisterActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private EditText firstNameEdit, lastNameEdit, passwrodEdit, confirmPasswordEdit, birthDateEdit;
    private RadioButton maleRadio, femaleRadio;
    private Button continueRegidster;
    private static String gender = null;
    private Calendar calander;
    private SimpleDateFormat simpledateformat;
    private static String phoneNumber;
    private static int yearcheck;
    private DatePickerDialog.OnDateSetListener mOnDateSetListener;
    Buyer buyer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_register);
        initialView();
        setTitle("Register as Buyer");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Bundle bundle = getIntent().getExtras();
        phoneNumber = bundle.getString("phoneNumber");
        Toast.makeText(BuyerRegisterActivity.this, phoneNumber, Toast.LENGTH_LONG).show();
        maleRadio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gender = maleRadio.getText().toString();
            }
        });

        femaleRadio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gender = femaleRadio.getText().toString();
            }
        });

        continueRegidster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                registerInfo();

            }
        });

        birthDateEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calend=Calendar.getInstance();
                int year = calend.get(Calendar.YEAR);
                int month = calend.get(Calendar.MONTH);
                int day = calend.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(
                        BuyerRegisterActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mOnDateSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        mOnDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month += 1;
                yearcheck = year;
                birthDateEdit.setText(year + "/" + month + "/" + dayOfMonth);
            }
        };


    } //end onCreate()


    public void initialView() {
        mToolbar = findViewById(R.id.Home_toolbar);
        firstNameEdit = findViewById(R.id.BuyerRegister_firstNameEdit);
        lastNameEdit = findViewById(R.id.BuyerRegister_lastNameEdit);
        passwrodEdit = findViewById(R.id.BuyerRegister_passwword);
        confirmPasswordEdit = findViewById(R.id.BuyerRegister_confirmPass);
        maleRadio = findViewById(R.id.BuyerRegister_maleRdio);
        femaleRadio = findViewById(R.id.BuyerRegister_femaleRadio);
        continueRegidster = findViewById(R.id.BuyerRegister_continueBtn);
        birthDateEdit = findViewById(R.id.BuyerRegister_birthDate);


    } //end initialView()

    private void registerInfo() {
        String firstName = firstNameEdit.getText().toString().trim();
        String lastName = lastNameEdit.getText().toString();
        String passwrod = passwrodEdit.getText().toString().trim();
        String birthDay = birthDateEdit.getText().toString();
        String confirmPassword = confirmPasswordEdit.getText().toString().trim();
        buyer = new Buyer();
        if (!buyer.setFirstName(firstName) || firstName.isEmpty()) {
            alertMsg(R.string.errorMessage, 1);
            return;
        }
        if (!buyer.setLastName(lastName) || lastName.isEmpty()) {
            alertMsg(R.string.errorMessage, 2);
            return;
        }
        if (yearcheck > 2001 || birthDay.isEmpty()) {
            alertMsg(R.string.selectBirthOfDate, 15);
            return;
        }
        if (passwrod.isEmpty() || passwrod.length() < 8) {
            alertMsg(R.string.passMessageError, 4);
            return;
        }
        if (passwrod.equals(confirmPassword)) {
            buyer.setPassword(passwrod);
        } else {
            alertMsg(R.string.confPassMessageError, 5);
            return;
        }
        if (gender == null) {
            alertMsg(R.string.selectGenderMessage, 0);
            return;
        }
        buyer.setGender(gender);
        buyer.setDateOfBirth(birthDay);

        // get current time
        calander = Calendar.getInstance();
        simpledateformat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String Date = simpledateformat.format(calander.getTime());
        buyer.setDate(Date);
        buyer.setPhoneNumber(phoneNumber);
        BuyerRegisterDb buyerRegisterDb = new BuyerRegisterDb(BuyerRegisterActivity.this, buyer);
        buyerRegisterDb.sendInfoBuyerToDb();
        Toast.makeText(BuyerRegisterActivity.this, "successfull register", Toast.LENGTH_LONG).show();

    }

    public void alertMsg(int str, final int flag) {
        AlertDialog.Builder alert = new AlertDialog.Builder(BuyerRegisterActivity.this);
        alert.setTitle("Error Message")
                .setCancelable(false)
                .setMessage(str)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (flag) {
                            case 1:
                                firstNameEdit.setError("Invalid input");
                                break;
                            case 2:
                                lastNameEdit.setText("Invalid input");
                                break;
                            case 4:
                                passwrodEdit.setText("Invalid input");
                                confirmPasswordEdit.setText("Invalid input");
                                break;
                            case 5:
                                confirmPasswordEdit.setText("Invalid input");
                        }
                    }
                }).create().show();
    }

} //end class
