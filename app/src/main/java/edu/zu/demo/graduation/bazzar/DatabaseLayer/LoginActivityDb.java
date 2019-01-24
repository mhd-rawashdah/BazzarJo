package edu.zu.demo.graduation.bazzar.DatabaseLayer;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ProgressBar;
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
import edu.zu.demo.graduation.bazzar.Model.User;

public class LoginActivityDb {
    Context context;
    String url_server="http://192.168.43.88/loginUser.php";
    User user;
    ProgressBar progressBar;

    public LoginActivityDb(Context context, User user,ProgressBar progressBar) {
        this.context = context;
        this.user = user;
        this.progressBar=progressBar;
    }

    public void checkUserIno(){
        progressBar.setVisibility(View.VISIBLE);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url_server, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONArray jsonArray= null;
                try {
                    jsonArray = new JSONArray(response);
                    JSONObject jsonObject=jsonArray.getJSONObject(0);
                    String code=jsonObject.getString("code");
                    String message=jsonObject.getString("message");
                    if (code.equals("login_success")){
                        Toast.makeText(context,message,Toast.LENGTH_LONG).show();
                        Intent intent=new Intent(context, MainActivity.class);
                        context.startActivity(intent);
                    }
                    else
                        Toast.makeText(context,message,Toast.LENGTH_LONG).show();

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                progressBar.setVisibility(View.INVISIBLE);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "make sure of internet connection  ", Toast.LENGTH_LONG).show();

                progressBar.setVisibility(View.INVISIBLE);
            }
        }){
            @Override
            protected HashMap<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params=new HashMap<String, String>() ;
                params.put("phone",user.getPhoneNumber());
                params.put("password",user.getPassword());
                params.put("userType",user.getUserType()+"");
                return params;
            }
        };
        Mysingleton.getMysinglton(context).addToRequestQueue(stringRequest);
    }
}
