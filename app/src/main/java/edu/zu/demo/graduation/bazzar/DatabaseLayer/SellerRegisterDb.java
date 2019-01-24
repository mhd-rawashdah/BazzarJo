package edu.zu.demo.graduation.bazzar.DatabaseLayer;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import edu.zu.demo.graduation.bazzar.BasicActivity.MainActivity;
import edu.zu.demo.graduation.bazzar.Model.Seller;

public class SellerRegisterDb {
    Context context;
    String url_server="http://192.168.43.88/sellerRegister.php";
    Seller seller =new Seller();

    public SellerRegisterDb(Context context,Seller seller){
        this.context=context;
       this.seller=seller;
    }  // end constructer

    // this method sending seller info to Db
    public void sendInfoSellerToDb(){
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url_server, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(context,response,Toast.LENGTH_LONG).show();
                try {
                    JSONArray jsonArray=new JSONArray(response);
                    JSONObject jsonObject=jsonArray.getJSONObject(0);
                    String code=jsonObject.getString("code");
                    if (code.equals("register_success")){
                        Toast.makeText(context,code,Toast.LENGTH_LONG).show();
                        Intent intent=new Intent(context, MainActivity.class);
                        context.startActivity(intent);
                    }
                    else
                        Toast.makeText(context,code,Toast.LENGTH_LONG).show();

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected HashMap<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params=new HashMap<String, String>() ;
                 params.put("firstName",seller.getFirstName());
                 params.put("lastName",seller.getlastName());
                 params.put("phone",seller.getPhoneNumber());
                 params.put("storName",seller.getStoreName());
                 params.put("categoryId",seller.getTypeCategory());
                 params.put("city",seller.getCity());
              //   params.put("areaName",seller.getAreaName());
                 params.put("loaction",seller.getLocation());
                 params.put("email",seller.getEmail());
                 params.put("birthDate",seller.getDateOfBirth());
                 params.put("password",seller.getPassword());
                 params.put("gender",seller.getGender());
                 params.put("deliveryTime",seller.getTimeDelivery());
                 params.put("imageStore",seller.getImageProfile());
                 params.put("registerDate",seller.getDate());
                 params.put("ratting", seller.getRatting()+"");
                 if (seller.getState())
                     params.put("status", "1");
                 else
                     params.put("status", "0");

                return params;
            }
        };

        Mysingleton.getMysinglton(context).addToRequestQueue(stringRequest);
    } // end sendToDB()
}  // end Class
