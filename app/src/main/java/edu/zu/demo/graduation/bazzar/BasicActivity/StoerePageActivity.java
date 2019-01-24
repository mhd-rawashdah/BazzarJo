package edu.zu.demo.graduation.bazzar.BasicActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;

import edu.zu.demo.graduation.bazzar.Adapter.TabLayoutAdapter;
import edu.zu.demo.graduation.bazzar.Fragment.ItemsFragment;
import edu.zu.demo.graduation.bazzar.Fragment.RatingFragment;
import edu.zu.demo.graduation.bazzar.Model.Bus;
import edu.zu.demo.graduation.bazzar.R;


public class StoerePageActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ImageView storeImage;
    private TextView storeStatusTxt;
    private TextView deliveryTimeTxt;
    private TextView deliveryPriceTxt;
    private TextView minimumPriceTxt;
    private TextView storeTypeTxt;
    private TextView numFeedbackTxt;
    private ImageView shippingImage, onlinePayImage, cashPayImage;
    private RatingBar ratingBar;
    private Button callBtn;
    private Toolbar mToolbar;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private static final NumberFormat cf = NumberFormat.getCurrencyInstance();
    private static String phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stoere_page);
        initialView();
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        showStorePage();
        setupTablayout();


        callBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uri = "tel:" + phoneNumber.trim();
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse(uri));

                if (ActivityCompat.checkSelfPermission(StoerePageActivity.this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                startActivity(intent);

            }
        });



    } // end onCreate()

    public void initialView() {
        storeImage = findViewById(R.id.StorePage_storeImage);
        storeStatusTxt = findViewById(R.id.StorePage_status);
        deliveryTimeTxt = findViewById(R.id.StorePage_timeDelivery);
        callBtn = findViewById(R.id.StorePage_callBtn);
        collapsingToolbarLayout = findViewById(R.id.StorePage_Collapse);
        mToolbar = findViewById(R.id.StorePage_mtoolBar);
        ratingBar = findViewById(R.id.ratingBar);
        storeTypeTxt = findViewById(R.id.StorePage_storeTypeTxt);
        numFeedbackTxt = findViewById(R.id.StorePage_numberFeedbacks);
        deliveryPriceTxt = findViewById(R.id.StorePage_deliveryPrice);
        minimumPriceTxt = findViewById(R.id.StorePage_minimumPrice);
        shippingImage = findViewById(R.id.StorePage_shippingImg);
        cashPayImage = findViewById(R.id.StorePage_cashPayImg);
        onlinePayImage = findViewById(R.id.StorePage_onlinePayImg);
        tabLayout=findViewById(R.id.StorePage_tabLayout);
        viewPager=findViewById(R.id.StorePage_viewPager);

    } // end initialView()

     // this method to setup  tabLayout
     public void setupTablayout(){
         TabLayoutAdapter tabLayoutAdapter=new TabLayoutAdapter(getSupportFragmentManager());

         // add fragment
         tabLayoutAdapter.addFragment(new ItemsFragment(),"Items");
         tabLayoutAdapter.addFragment(new RatingFragment(),"Rating");

         viewPager.setAdapter(tabLayoutAdapter);
         tabLayout.setupWithViewPager(viewPager);
     }

    private void showStorePage() {
        Bundle bundle = getIntent().getExtras();
        int Id=bundle.getInt("seller_Id");
        phoneNumber=bundle.getString("phoneNumber");
        String image_url = bundle.getString("imageStore");
        String storeName = bundle.getString("storeName");
        String storeType=bundle.getString("storeType");
        double ratting = bundle.getDouble("ratingStore");
        int numFeedback=bundle.getInt("numFeedback");
        String time = bundle.getString("deliveryTime");
        boolean status = bundle.getBoolean("status");
        boolean isDeliveryService=bundle.getBoolean("isDeliveryService");
        boolean isOnlinePayment=bundle.getBoolean("isOnlinePayment");
        double deliveryPrice=bundle.getDouble("deliveryPrice");
        double minimumPrice=bundle.getDouble("minimumPrice");
        collapsingToolbarLayout.setTitleEnabled(true);
        collapsingToolbarLayout.setTitle(storeName);
        storeTypeTxt.setText(storeType);
        ratingBar.setRating((float) ratting);
        numFeedbackTxt.setText(numFeedback+"");
        deliveryTimeTxt.setText("Time: " + time + " m");


        if (status) {
            storeStatusTxt.setText(R.string.online);
            storeStatusTxt.setTextColor(getResources().getColor(R.color.colorGreen));
        } else {
            storeStatusTxt.setText(R.string.closed);
            storeStatusTxt.setTextColor(Color.GRAY);
        }
        if (!isDeliveryService){
             shippingImage.setVisibility(View.GONE);
             deliveryPriceTxt.setVisibility(View.GONE);
             minimumPriceTxt.setVisibility(View.GONE);
        }else{
            shippingImage.setVisibility(View.VISIBLE);
            deliveryPriceTxt.setVisibility(View.VISIBLE);
            minimumPriceTxt.setVisibility(View.VISIBLE);
            deliveryPriceTxt.setText(cf.format(deliveryPrice)+"");
             minimumPriceTxt.setText(cf.format(minimumPrice)+"");
        }
        if (!isOnlinePayment){
            onlinePayImage.setVisibility(View.GONE);
        }else
        {
            onlinePayImage.setVisibility(View.VISIBLE);
        }

        Picasso.with(getApplicationContext()).load(image_url).placeholder(R.drawable.loading_background)
                .error(R.drawable.loading_background)
                .into(storeImage, new Callback() {
                    @Override
                    public void onSuccess() {
                        //   Toast.makeText(StoerePageActivity.this, "Sucess", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError() {

                    }
                });



    }

}
