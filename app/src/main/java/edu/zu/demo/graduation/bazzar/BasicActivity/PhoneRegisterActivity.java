package edu.zu.demo.graduation.bazzar.BasicActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.hbb20.CountryCodePicker;
import java.util.concurrent.TimeUnit;

import edu.zu.demo.graduation.bazzar.R;

public class PhoneRegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private final static String TAG = "phoneAuth";
    private Toolbar mToolbar;
    private EditText codeMessageEdit, phoneNumberEdit;
    private CountryCodePicker countryCodePicker;
    private LinearLayout layoutSendCode, layoutReciveCode,layoutProgressBar;
    private Button verifyBtn, verifyCodeBtn, resendCodeBtn;
    private FirebaseAuth auth;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallback;
    private PhoneAuthProvider.ForceResendingToken resendingToken;
    private static String verificationCode;
    private static String phoneNumber;
    ProgressBar progressBar;
    AlertDialog.Builder alert;
   ImageView typeUserTmg;
   TextView typeUserTxt;
   private static int typeUser=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_register);
        initialView();

        setTitle("Verify your number");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Bundle bundle=getIntent().getExtras();
        typeUser=bundle.getInt("typeUser");

        alert = new AlertDialog.Builder(PhoneRegisterActivity.this);
        alert.setTitle("Message")
                .setCancelable(false);

        if (typeUser==R.id.Login_registerAsBuyer){
            typeUserTmg.setImageResource(R.drawable.ic_buyer);
            typeUserTxt.setText(R.string.welcome_buyer);

        }
        else if (typeUser==R.id.Login_registerAsSeller) {
            typeUserTmg.setImageResource(R.drawable.ic_seller);
            typeUserTxt.setText(R.string.welcome_seller);

        }




    } //end onCreate()

    public void initialView() {
        mToolbar = findViewById(R.id.Home_toolbar);
        codeMessageEdit = findViewById(R.id.PhoneRegister_CodeMessageEdit);
        phoneNumberEdit = findViewById(R.id.PhoneRegister_phoneNumberEdit);
        verifyBtn = findViewById(R.id.PhoneRegister_VerifyBtn);
        verifyCodeBtn = findViewById(R.id.PhoneRegister_VerifyCodeBtn);
        resendCodeBtn = findViewById(R.id.PhoneRegister_resendCodeBtn);
        layoutSendCode = findViewById(R.id.PhoneRegister_layoutSendCode);
        layoutReciveCode = findViewById(R.id.PhoneRegister_layoutReciveCode);
        layoutProgressBar=findViewById(R.id.PhoneRegister_layoutProgressBar);
        typeUserTmg=findViewById(R.id.PhoneRegister_typeUserImg);
        typeUserTxt=findViewById(R.id.PhoneRegister_typeUserTxt);
        auth = FirebaseAuth.getInstance();
        countryCodePicker = findViewById(R.id.PhoneRegister_countryCodePicker);
        verifyBtn.setOnClickListener(this);
        verifyCodeBtn.setOnClickListener(this);
        resendCodeBtn.setOnClickListener(this);
        progressBar=findViewById(R.id.PhoneRegister_progressBar);

        }  //edn initialView()

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.PhoneRegister_VerifyBtn:
                sendToPhoneNumber();                     //this method excute process
                break;
            case R.id.PhoneRegister_VerifyCodeBtn:
                String inputCodeMessage = codeMessageEdit.getText().toString();
                if (!inputCodeMessage.equals("")) {
                    layoutSendCode.setVisibility(View.GONE);
                    layoutReciveCode.setVisibility(View.GONE);
                    layoutProgressBar.setVisibility(View.VISIBLE);
                    verifyPhoneNumber(verificationCode, inputCodeMessage);
                }
                else{
                    alert.setMessage("Please enter verification code");
                    alert.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            codeMessageEdit.requestFocus();
                        }
                    });
                    alert.create().show();
                }
                break;
            case R.id.PhoneRegister_resendCodeBtn:
                resendCodeMessage();
                break;
        }

    }

    private void sendToPhoneNumber() {
        countryCodePicker.registerCarrierNumberEditText(phoneNumberEdit);
        String phone = countryCodePicker.getFullNumber();
        boolean cancel = false;
        View focusView = null;
        if (!checkPhoneNumber(phoneNumberEdit.getText().toString())) {
            focusView = phoneNumberEdit;
            cancel = true;
            phoneNumberEdit.setError("Invalid phone number");
        }
        if (cancel) {
            focusView.requestFocus();
        } else {
            Toast.makeText(PhoneRegisterActivity.this, phone, Toast.LENGTH_LONG).show();
            phoneNumber = phone;
            sent_code(phone);
        }
    }  //end sendTophoneNumber


    // This method send code verification for phone number
    public void sent_code(String phone) {
        layoutSendCode.setVisibility(View.GONE);
        layoutProgressBar.setVisibility(View.VISIBLE);
        setUpCallback();
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phone, 60, TimeUnit.SECONDS, this, mCallback
        );

    } // sent_code()

    public void setUpCallback() {
        mCallback = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                // This callback will be invoked in two situations:
                // 1 - Instant verification. In some cases the phone number can be instantly
                //     verified without needing to send or enter a verification code.
                // 2 - Auto-retrieval. On some devices Google Play services can automatically
                //     detect the incoming verification SMS and perform verification without
                //     user action.
                layoutProgressBar.setVisibility(View.VISIBLE);
          //      layoutSendCode.setVisibility(View.VISIBLE);
                Log.d(TAG, "onVerificationCompleted:" + phoneAuthCredential);
                Toast.makeText(PhoneRegisterActivity.this, "This account is checking", Toast.LENGTH_LONG).show();
//               alert.setMessage(R.string.userExistMessage);
//               alert.setPositiveButton("Login", new DialogInterface.OnClickListener() {
//                   @Override
//                   public void onClick(DialogInterface dialog, int which) {
//                       goTo(LoginActivity.class,phoneNumber);
//                   }
//               });
//               alert.setNegativeButton("use another number", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                      phoneNumberEdit.requestFocus();
//
//                    }
//                });
//               alert.create().show();
               signInWithPhoneAuth(phoneAuthCredential);
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                // This callback is invoked in an invalid request for verification is made,
                // for instance if the the phone number format is not valid.
                Log.w(TAG, "onVerificationFailed", e);

                if (e instanceof FirebaseAuthInvalidCredentialsException) {
                    Toast.makeText(PhoneRegisterActivity.this, "Invalid authentcation", Toast.LENGTH_LONG).show();
                    // Invalid request
                    // ...
                } else if (e instanceof FirebaseTooManyRequestsException) {
                    layoutProgressBar.setVisibility(View.GONE);
                    layoutSendCode.setVisibility(View.VISIBLE);
                    Toast.makeText(PhoneRegisterActivity.this, "SMS quota for the project has been exceeded", Toast.LENGTH_LONG).show();
                    // The SMS quota for the project has been exceeded
                    // ...
                }

                // Show a message and update the UI
                // ...

            }

            @Override
            public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {


                super.onCodeSent(s, forceResendingToken);
                verificationCode = s;
                resendingToken = forceResendingToken;
                layoutSendCode.setVisibility(View.GONE);
                layoutProgressBar.setVisibility(View.GONE);
                layoutReciveCode.setVisibility(View.VISIBLE);
                Toast.makeText(PhoneRegisterActivity.this, "Successfully", Toast.LENGTH_LONG).show();
            }
        };

    }


    public void verifyPhoneNumber(String verificationCode, String inputCodeMessage) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationCode, inputCodeMessage);
        signInWithPhoneAuth(credential);

    } //end verifyPhoneNumber()

    public void resendCodeMessage() {
        setUpCallback();
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,
                60,
                TimeUnit.SECONDS,
                this,
                mCallback,
                resendingToken
        );

    }

    public void signInWithPhoneAuth(PhoneAuthCredential credential) {
        auth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                       if (task.isSuccessful()) {

                            // Sign in success, update UI with the signed-in user's information
                            if(typeUser==R.id.Login_registerAsBuyer){
                            goTo(BuyerRegisterActivity.class,phoneNumber);
                            }
                           if (typeUser==R.id.Login_registerAsSeller){
                                goTo(SellerRegisterActivity.class,phoneNumber);
                            }
                            finish();
                            // ...
                        } else {
                            // Sign in failed, display a message and update the UI
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                                layoutSendCode.setVisibility(View.GONE);
                                layoutProgressBar.setVisibility(View.GONE);
                                layoutReciveCode.setVisibility(View.VISIBLE);
                                codeMessageEdit.setError("Invalid code");
                            }
                            else   if (!task.isSuccessful()){
                                layoutProgressBar.setVisibility(View.GONE);
                                layoutSendCode.setVisibility(View.VISIBLE);
                                Toast.makeText(PhoneRegisterActivity.this, "please check on internet", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                });
    }


    public boolean checkPhoneNumber(String phoneNumber) {
        if (phoneNumber.isEmpty() || phoneNumber.length() < 9 || phoneNumber.length() > 10) {
            return false;
        }

        return true;
    }  //end checkPhoneNumber()

    public void alertMsg(int str, final int flag) {
        AlertDialog.Builder alert = new AlertDialog.Builder(PhoneRegisterActivity.this);
        alert.setTitle("Message")
                .setCancelable(false)
                .setMessage(str)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (flag) {

                        }
                    }
                }).create().show();


    } //end Message
  public void goTo(Class activity,String phoneNumber){
        Intent intent=new Intent(getApplicationContext(),activity);
        Bundle bundle=new Bundle();
        bundle.putString("phoneNumber",phoneNumber);
        intent.putExtras(bundle);
        startActivity(intent);
  }


}//end class
