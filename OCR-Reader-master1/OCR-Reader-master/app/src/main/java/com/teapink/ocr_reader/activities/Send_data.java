package com.teapink.ocr_reader.activities;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class Send_data extends AsyncTask {

    private static final String baseurl="https://smartcart2.000webhostapp.com/pitneyset.php";

    Context context;
    String address, custid;

    public Send_data(Context context, String address, String custid) {
        this.context = context;
        this.address = address;
        this.custid = custid;
    }
    @Override
    protected Object doInBackground(Object[] objects) {
        RequestQueue mRequestQueue;
        Cache cache = new DiskBasedCache(context.getCacheDir(), 1024 * 1024);
        Network network = new BasicNetwork(new HurlStack());
        mRequestQueue = new RequestQueue(cache, network);
        mRequestQueue.start();
        StringRequest postrequest= new StringRequest(Request.Method.POST, baseurl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    if (response.contains("success")) {
                        Toast.makeText(context, "Data Updated", Toast.LENGTH_SHORT).show();

                    }
                    else Toast.makeText(context, "failed" +response, Toast.LENGTH_SHORT).show();
                }
                catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(context,e.toString(),Toast.LENGTH_SHORT).show();
                }


            }

        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();
                params.put("custid",custid);
                params.put("address",address);


                return params;
            }
        };
        mRequestQueue.add(postrequest);



        return null;
    }
}
