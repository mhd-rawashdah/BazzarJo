package edu.zu.demo.graduation.bazzar.BasicActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.github.ybq.android.spinkit.style.FadingCircle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

import edu.zu.demo.graduation.bazzar.Adapter.StoresAdapter;
import edu.zu.demo.graduation.bazzar.DatabaseLayer.Mysingleton;
import edu.zu.demo.graduation.bazzar.Model.Seller;
import edu.zu.demo.graduation.bazzar.R;
import edu.zu.demo.graduation.bazzar.SearchActivity;

public class StoresActivity extends AppCompatActivity {
    private TextView numberStores;
    private RecyclerView recyclerStores;
    private Toolbar mToolbar;
    private ArrayList<Seller> list = new ArrayList<>();
    private String url = "http://192.168.43.88/selectStores.php";
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stores);
        initialView();
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Stores");
        getStoresFromDb();     // excute method to fetch data

//        SelectStoresDb storesDb = new SelectStoresDb(StoresActivity.this);
//        list= storesDb.getSellerArrayList();
//        setupRecycleView(list);


    }  // end onCreate()

    public void initialView() {
        numberStores = findViewById(R.id.StoresActivity_numberStores);
        recyclerStores = findViewById(R.id.StoresActivity_recycleViewStores);
        mToolbar = findViewById(R.id.Home_toolbar);
        progressBar=findViewById(R.id.StoresActivity_spinKit);
        FadingCircle fadingCircle=new FadingCircle();
        progressBar.setIndeterminateDrawable(fadingCircle);

    }// end initialView()


    // this method to fetch data from data base by json
    public void getStoresFromDb() {
        progressBar.setVisibility(View.VISIBLE);
        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.POST, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject object = null;
                try {
                    for (int i = 0; i < response.length(); i++) {
                        object = response.getJSONObject(i);
                        Seller seller = new Seller();
                        seller.setStoreName(object.getString("storeName"));
                        seller.setTimeDelivery(object.getString("deliveryTime"));
                        seller.setRatting(object.getDouble("ratting"));
                        int state=object.getInt("status");
                        if (state==1)
                        seller.setState(true);
                        else
                            seller.setState(false);
                        seller.setImageProfile(object.getString("image_url"));
                        list.add(seller);
                        //    Toast.makeText(mContext, i+"", Toast.LENGTH_LONG).show();
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Toast.makeText(getApplicationContext(), list.size()+"", Toast.LENGTH_LONG).show();
                progressBar.setVisibility(View.INVISIBLE);

                setupRecycleView(list);

            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(getApplicationContext(), "make sure of internet connection  ", Toast.LENGTH_LONG).show();

            }
        });
        //Toast.makeText(mContext,list.get(2).getStoreName(),Toast.LENGTH_LONG).show();
          Mysingleton.getMysinglton(getApplicationContext()).addToRequestQueue(arrayRequest);


    }

    //setup recycleview and pass data to adapter
    private void setupRecycleView(ArrayList<Seller> list) {

        numberStores.setText("No. stores: "+list.size());
        StoresAdapter adapter = new StoresAdapter(getApplicationContext(), list);
        recyclerStores.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerStores.setAdapter(adapter);

    } //end setupRecycleView();

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu_stores, menu);
        return true;
    } // end onCreateOption()

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_search:
                goTo(SearchActivity.class);
                break;

        }
        return super.onOptionsItemSelected(item);
    } // end onOptionItemSelectec()

    // thid method to moving between activity
    public void goTo(Class activity){
        Intent intent=new Intent(StoresActivity.this,activity);
        startActivity(intent);

    } // end goTo()

}  // end class



