package com.example.leena.pbcustomer;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Data_ret extends AsyncTask {
    private static final String baseurl="https://smartcart2.000webhostapp.com/pitneyget.php";

    String value,custid;
    Context context;
    TextView pname,address,phone,delid,price,identifier;

    public Data_ret(String value, String custid, Context context, TextView pname, TextView address, TextView phone, TextView delid, TextView price) {
        this.value = value;
        this.custid = custid;
        this.context = context;
        this.pname = pname;
        this.address = address;
        this.delid = delid;
        this.price = price;
        this.phone = phone;
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
                 //Toast.makeText(context,response, Toast.LENGTH_SHORT).show();
                try {
                    JSONArray jsonarray = new JSONArray(response);
                    JSONObject reader= jsonarray.getJSONObject(0);
                    String saddress=reader.getString("address");
                    String scustid=reader.getString("custid");
                    String sphone=reader.getString("phone");
                    String sdelid=reader.getString("delid");
                    String spname=reader.getString("pname");
                    String sprice = reader.getString("price");
                    //String address=reader.getString("address");

                   // Toast.makeText(context,saddress,Toast.LENGTH_LONG).show();

                    address.setText(saddress);
                    phone.setText(sphone);
                    pname.setText(spname);
                    delid.setText(sdelid);
                    price.setText(sprice);


                    //hud.dismiss();


                } catch (JSONException e) {
                    e.printStackTrace();

                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();
                        //hud.dismiss();

                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();
                //params.put("product_id",value);
                //params.put("user_id",user_id);
                params.put("cust_id",custid);
                params.put("id",value);
                return params;
            }
        };
        mRequestQueue.add(postrequest);

        return null;
    }
}
