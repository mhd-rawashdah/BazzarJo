package edu.zu.demo.graduation.bazzar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import edu.zu.demo.graduation.bazzar.Adapter.SearchAdapter;
import edu.zu.demo.graduation.bazzar.DatabaseLayer.Mysingleton;
import edu.zu.demo.graduation.bazzar.Model.Seller;

public class SearchActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    private Toolbar mToolbar;
    private String url = "http://192.168.43.88/search.php";
    private RecyclerView recyclerView;
    // private String queryString;
    private TextView searchResultTxt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initialView();
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void initialView() {
        mToolbar = findViewById(R.id.SearchActivity_toolbar);
        recyclerView = findViewById(R.id.SearchActivity_recycleView);
        searchResultTxt = findViewById(R.id.SearchActivity_searchResult);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_option_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.SearchActivity_actionSearch);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setIconifiedByDefault(false);
        searchView.setQueryHint("search ...");
        searchView.setSubmitButtonEnabled(true);
        searchView.requestFocus();
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        // queryString=query;
        if (!query.equals(""))
            getQueryfromDb(query);

        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    public void getQueryfromDb(final String searchQuery) {
        StringRequest jsonArrayRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                ArrayList<Seller> list = new ArrayList<>();

                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        Seller seller = new Seller();
                        seller.setStoreName(object.getString("storeName"));
                        seller.setTimeDelivery(object.getString("deliveryTime"));
                        seller.setRatting(object.getDouble("ratting"));
                        int state = object.getInt("status");
                        if (state == 1)
                            seller.setState(true);
                        else
                            seller.setState(false);
                        seller.setImageProfile(object.getString("image_url"));
                        list.add(seller);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

                setupRecycleView(list);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error + "", Toast.LENGTH_LONG).show();


            }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("searchQuery", searchQuery);
                return params;
            }
        };
        Mysingleton.getMysinglton(getApplicationContext()).addToRequestQueue(jsonArrayRequest);
    }

    private void setupRecycleView(ArrayList<Seller> list) {
        if (list.size() >0) {
            searchResultTxt.setText("Search result: " + list.size());
        } else {
            searchResultTxt.setText(" No found result");

        }
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        SearchAdapter adapter = new SearchAdapter(list, getApplicationContext());
        recyclerView.setAdapter(adapter);
    }

}
