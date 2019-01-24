package edu.zu.demo.graduation.bazzar.DatabaseLayer;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import edu.zu.demo.graduation.bazzar.Model.Seller;

public class SelectStoresDb {
    private Context mContext;
    ArrayList<Seller> list ;
    ;
    private String url = "http://192.168.43.88/selectStores.php";

    public SelectStoresDb(Context mContext) {
        this.mContext = mContext;
        list = new ArrayList<>();
    }

    public ArrayList<Seller> getSellerArrayList() {
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);

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
                        seller.setState(object.getBoolean("status"));
                        seller.setImageProfile(object.getString("image_url"));
                        list.add(seller);
                    //    Toast.makeText(mContext, i+"", Toast.LENGTH_LONG).show();
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        //Toast.makeText(mContext,list.get(2).getStoreName(),Toast.LENGTH_LONG).show();
        // Mysingleton.getMysinglton(mContext).addToRequestQueue(arrayRequest);
        requestQueue.add(arrayRequest);

   return list;
    }


}


