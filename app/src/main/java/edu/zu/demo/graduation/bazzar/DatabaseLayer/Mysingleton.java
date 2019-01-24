package edu.zu.demo.graduation.bazzar.DatabaseLayer;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import android.content.Context;


public class Mysingleton {
    private static Mysingleton mysingleton;
    private static RequestQueue requestQueue;
    Context context;

    private Mysingleton(Context context){
        this.context=context;
        requestQueue=getRequestQueue();

    }

    public RequestQueue getRequestQueue() {
        if (requestQueue==null){
            this.requestQueue= Volley.newRequestQueue(context.getApplicationContext());
        }
        return requestQueue;
    }
    public  static synchronized Mysingleton getMysinglton(Context context){
        if (mysingleton==null){
            mysingleton=new Mysingleton(context);
        }
        return mysingleton;
    }
    public <T> void addToRequestQueue(Request<T> request){
        requestQueue.add(request);
    }
}
