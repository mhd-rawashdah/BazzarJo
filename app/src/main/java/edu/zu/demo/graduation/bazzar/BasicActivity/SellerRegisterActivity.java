package edu.zu.demo.graduation.bazzar.BasicActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import edu.zu.demo.graduation.bazzar.DatabaseLayer.SellerRegisterDb;
import edu.zu.demo.graduation.bazzar.Model.Seller;
import edu.zu.demo.graduation.bazzar.R;


public class SellerRegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private Toolbar mToolbar;
    private ImageView imageProfile;
    private EditText firstNameEdit, lastNameEdit, storeNameEdit, locationEdit, emailEdit, passwordEdit, confirmPasswordEdit, birthDateEdit;
    private EditText deliveryTimeEdit, areaNameEdit, deliveryPriceEdit, minimumPriceEdit;
    private Spinner typeCategorySpinner, citySpinner;
    private Button registerInfoBtn, continueInfoBtn;
    private ImageButton getLocationbtn, addImage;
    private RadioButton maleRadio, femaleRadio;
    private LinearLayout layoutPersonalInfo, layoutStoreInfo;
    private CheckBox deliveryServiceCheck, onlinnePaymentCheck;
    private Bitmap imageBitmap = null;
    private static String phoneNumber;
    private static String gender = null;
    private static String city, typeCategory;
    private static int indexCity = 0, indexCategory = 0;
    private Calendar calander;
    private SimpleDateFormat simpledateformat;
    private DatePickerDialog.OnDateSetListener mOnDateSetListener;
    private static int yearCheck;
    private static boolean flag = false;
    private static boolean isDeliveryService, isOnlinePayment;
    private Seller seller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_register);
        initialView();
        setTitle("Register seller information");
        setSupportActionBar(mToolbar);
        // get phone number from phoneRegisterActivity
        Bundle bundle = getIntent().getExtras();
        phoneNumber = bundle.getString("phoneNumber");

        Toast.makeText(getApplicationContext(), phoneNumber, Toast.LENGTH_LONG).show();

        // sellected country from Spinner cities
        citySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                city = citySpinner.getSelectedItem().toString();
                indexCity = citySpinner.getSelectedItemPosition();
                //      Toast.makeText(getApplicationContext(),city,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        // selected type category from spinner categories
        typeCategorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                typeCategory = typeCategorySpinner.getSelectedItem().toString();
                indexCategory = typeCategorySpinner.getSelectedItemPosition();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        // this method to setup birth of date
        mOnDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month += 1;
                yearCheck = year;
                birthDateEdit.setText(year + "/" + month + "/" + dayOfMonth);
            }
        };
        birthDateEdit.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    getBirthDate();
                }

                return false;
            }
        });

    } //end onCreate()

    public void initialView() {
        mToolbar = findViewById(R.id.Home_toolbar);
        imageProfile = findViewById(R.id.SellerRegister_profileImage);
        firstNameEdit = findViewById(R.id.SellerRegister_firstNameEdit);
        lastNameEdit = findViewById(R.id.SellerRegister_lastNameEdit);
        storeNameEdit = findViewById(R.id.SellerRegister_StoreName);
        locationEdit = findViewById(R.id.SellerRegister_locationEdit);
        emailEdit = findViewById(R.id.SellerRegister_emailEdit);
        passwordEdit = findViewById(R.id.SellerRegister_password);
        deliveryTimeEdit = findViewById(R.id.SellerRegister_deliveryTime);
        areaNameEdit = findViewById(R.id.SellerRegister_areaName);
        confirmPasswordEdit = findViewById(R.id.SellerRegister_confirmPass);
        typeCategorySpinner = findViewById(R.id.SellerRegister_typeHomemadeSpinner);
        citySpinner = findViewById(R.id.SellerRegister_citySpinner);
        maleRadio = findViewById(R.id.SellerRegister_maleRdio);
        femaleRadio = findViewById(R.id.SellerRegister_femaleRadio);
        getLocationbtn = findViewById(R.id.SellerRegister_getLocationBtn);
        continueInfoBtn = findViewById(R.id.SellerRegister_continueInfoBtn);
        registerInfoBtn = findViewById(R.id.SellerRegister_registerInfoBtn);
        addImage = findViewById(R.id.SellerRegister_addImage);
        birthDateEdit = findViewById(R.id.SellerRegister_birthDate);
        layoutPersonalInfo = findViewById(R.id.SellerRegister_layoutPersonalInfo);
        layoutStoreInfo = findViewById(R.id.SellerRegister_layoutStoreInfo);
        deliveryServiceCheck = findViewById(R.id.SellerRegister_deliveryServiceCheckBox);
        onlinnePaymentCheck = findViewById(R.id.SellerRegister_onlinePaymentCheckBox);
        deliveryPriceEdit = findViewById(R.id.SellerRegister_deliveryPriceEdit);
        minimumPriceEdit = findViewById(R.id.SellerRegister_minimumPriceEdit);

        maleRadio.setOnClickListener(this);
        femaleRadio.setOnClickListener(this);
        getLocationbtn.setOnClickListener(this);
        continueInfoBtn.setOnClickListener(this);
        registerInfoBtn.setOnClickListener(this);
        addImage.setOnClickListener(this);
        birthDateEdit.setOnClickListener(this);
        seller = new Seller();

    }  //end initialView()

    @Override
    public void onClick(View view) {
        int Id = view.getId();
        switch (Id) {
            case R.id.SellerRegister_addImage:
                getImage();
                break;
            case R.id.SellerRegister_maleRdio:
                gender = maleRadio.getText().toString();
                break;
            case R.id.SellerRegister_femaleRadio:
                gender = femaleRadio.getText().toString();
                break;
            case R.id.SellerRegister_continueInfoBtn:
                registerPersonalInfo();
                break;
            case R.id.SellerRegister_registerInfoBtn:
                finalRegisterInfo();
                break;


        }
    }

    public void getBirthDate() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog = new DatePickerDialog(
                SellerRegisterActivity.this,
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                mOnDateSetListener,
                year, month, day);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    public void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        switch (view.getId()) {
            case R.id.SellerRegister_deliveryServiceCheckBox:
                if (checked) {
                    deliveryPriceEdit.setEnabled(true);
                    minimumPriceEdit.setEnabled(true);
                    isDeliveryService = true;
                } else {
                    deliveryPriceEdit.setEnabled(false);
                    minimumPriceEdit.setEnabled(false);
                    isDeliveryService = false;
                }
                break;
            case R.id.SellerRegister_onlinePaymentCheckBox:
                if (checked) {
                    isOnlinePayment = true;
                } else {
                    isOnlinePayment = false;
                }
        }

    }

    public void registerPersonalInfo() {
        String firstName = firstNameEdit.getText().toString().trim();
        String lastName = lastNameEdit.getText().toString().trim();
        String email = emailEdit.getText().toString();
        String birthDay = birthDateEdit.getText().toString();
        String password = passwordEdit.getText().toString().trim();
        String confirmPassword = confirmPasswordEdit.getText().toString().trim();


        if (!seller.setFirstName(firstName) || firstName.isEmpty()) {
            alertMsg(R.string.errorMessage, 1);
            return;
        }
        if (!seller.setLastName(lastName) || lastName.isEmpty()) {
            alertMsg(R.string.errorMessage, 2);
            return;
        }

        if (!seller.setEmail(email)) {
            alertMsg(R.string.errorMessage, 9);
            return;
        }
        if (yearCheck > 2001 || birthDay.isEmpty()) {
            alertMsg(R.string.selectBirthOfDate, 15);
            return;
        } else {
            seller.setDateOfBirth(birthDay);
        }
        if (password.isEmpty() || password.length() < 8) {
            alertMsg(R.string.passMessageError, 7);
            return;
        }
        if (confirmPassword.equals(password)) {
            seller.setPassword(password);
        } else {
            alertMsg(R.string.confPassMessageError, 8);
            return;
        }
        if (gender == null) {
            alertMsg(R.string.selectGenderMessage, 10);
            return;
        }
        seller.setPhoneNumber(phoneNumber);
        seller.setGender(gender);

        layoutPersonalInfo.setVisibility(View.GONE);
        layoutStoreInfo.setVisibility(View.VISIBLE);
        flag = true;


    } // end register() method

    public void finalRegisterInfo() {

        String storeName = storeNameEdit.getText().toString().trim();
        String location = locationEdit.getText().toString();
        String areaName = areaNameEdit.getText().toString();
        String deliveryTime = deliveryTimeEdit.getText().toString();
        String deliveryPrice = deliveryPriceEdit.getText().toString();
        String minimumPrice = minimumPriceEdit.getText().toString();

        if (imageBitmap == null) {
            alertMsg(R.string.selectImageMessage, 0);
            return;
        }
        if (storeName.isEmpty()) {
            alertMsg(R.string.errorMessage, 3);
            return;
        } else {
            seller.setStoreName(storeName);
        }
        if (!seller.setTimeDelivery(deliveryTime) || deliveryTime.isEmpty()) {
            alertMsg(R.string.errorMessage, 14);
            return;
        }
        if (indexCategory == 0) {
            alertMsg(R.string.selectTypeCategoryMessage, 5);
            return;
        } else {
            seller.setTypeCategory(typeCategory);
        }
        if (indexCity == 0) {
            alertMsg(R.string.selectCityMessage, 4);
            return;
        } else {
            seller.setCity(city);
        }
        if (areaName.isEmpty()) {
            alertMsg(R.string.errorMessage, 13);
            return;
        } else {
            seller.setAreaName(areaName);
        }
        if (location.isEmpty()) {
            alertMsg(R.string.selectLocationMessage, 6);
            return;
        } else {
            seller.setLocation(location);
        }
        if (isDeliveryService) {
            if (deliveryPrice.isEmpty()) {
                alertMsg(R.string.errorMessage, 16);
                return;
            } else {
                seller.setDeliveryPrice(Double.parseDouble(deliveryPrice));
            }
            if (minimumPrice.isEmpty()) {
                alertMsg(R.string.errorMessage, 16);
                return;
            } else {
                seller.setDeliveryPrice(Double.parseDouble(minimumPrice));
            }
        }

        String image = convertImageToString(imageBitmap);
        seller.setImageProfile(image);
        seller.setState(true);
        seller.setRatting(3.7);
        seller.setDeliveryService(isDeliveryService);
        seller.setOnlinePaymentService(isOnlinePayment);

        String Date = getCurrentDateTime();
        seller.setDate(Date);

        SellerRegisterDb sellerRegisterDb = new SellerRegisterDb(SellerRegisterActivity.this, seller);
        sellerRegisterDb.sendInfoSellerToDb();
        Toast.makeText(getApplicationContext(), "successfull " + seller.getDate(), Toast.LENGTH_LONG).show();


    } //end finalRegisterInfo()

    // get current time
    public String getCurrentDateTime() {
        calander = Calendar.getInstance();
        simpledateformat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        return simpledateformat.format(calander.getTime());
    } //end getCurrentDateTime()

    public void alertMsg(int str, final int flag) {
        AlertDialog.Builder alert = new AlertDialog.Builder(SellerRegisterActivity.this);
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
                                lastNameEdit.setError("Invalid input");
                                break;
                            case 3:
                                storeNameEdit.setError("Invalid input");
                                break;
                            case 4:
                                citySpinner.requestFocus();
                                break;
                            case 5:
                                typeCategorySpinner.requestFocus();
                                break;
                            case 6:
                                locationEdit.requestFocus();
                                break;
                            case 7:
                                passwordEdit.setError("Invalid input");
                                break;
                            case 8:
                                confirmPasswordEdit.setError("Invalid input");
                                break;
                            case 9:
                                emailEdit.setError("Invalid input");
                                break;
                            case 10:
                                maleRadio.requestFocus();
                                break;
                        }
                    }
                }).create().show();
    }

    public void getImage() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 120);
    }//end get image

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 120 && data != null) {
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                imageBitmap = BitmapFactory.decodeStream(inputStream);
                imageProfile.setImageBitmap(imageBitmap);
                imageProfile.setScaleType(ImageView.ScaleType.CENTER_CROP);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    } // end onActivityResult()


    // this method convert image bitmap to string in base64
    private String convertImageToString(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] imageByte = byteArrayOutputStream.toByteArray();
        String imageString = Base64.encodeToString(imageByte, Base64.DEFAULT);    // encode image to string
        return imageString;
    } //end convertImageToString()

    @Override
    public void onBackPressed() {
        if (flag) {
            layoutStoreInfo.setVisibility(View.GONE);
            layoutPersonalInfo.setVisibility(View.VISIBLE);
            flag = false;
        } else {
            super.onBackPressed();
        }

    }
}  //end Class
