package edu.zu.demo.graduation.bazzar.DatabaseLayer;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;

import edu.zu.demo.graduation.bazzar.Model.Buyer;
public class BuyerRegisterDb {
   Context context;
   String url_server="http://192.168.43.88/BuyerRegister.php";
   Buyer buyer;
   public BuyerRegisterDb(Context context,Buyer buyer){
       this.context=context;
       this.buyer=buyer;
   }

   public void sendInfoBuyerToDb(){
       StringRequest stringRequest=new StringRequest(Request.Method.POST, url_server, new Response.Listener<String>() {
           @Override
           public void onResponse(String response) {

               Toast.makeText(context,response+"",Toast.LENGTH_LONG).show();

           }
       }, new Response.ErrorListener() {
           @Override
           public void onErrorResponse(VolleyError error) {

           }
       }){
           @Override
           protected HashMap<String, String> getParams() throws AuthFailureError {
               HashMap<String, String> params=new HashMap<String, String>() ;
                params.put("firstName",buyer.getFirstName());
                params.put("lastName",buyer.getlastName());
                params.put("phone",buyer.getPhoneNumber());
                params.put("password",buyer.getPassword());
                params.put("gender",buyer.getGender());
                params.put("registerDate",buyer.getDate());
               params.put("birthDate",buyer.getDateOfBirth());
               return params;
           }
       };
       Mysingleton.getMysinglton(context).addToRequestQueue(stringRequest);
   }
}
