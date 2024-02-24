package com.example.volley_json_array;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class BackgroundTask {
    Context context;
    ArrayList<Contact> arrayList=new ArrayList<>();
    String  json_url="https://mocki.io/v1/d4867d8b-b5d5-4a48-a4ab-79131b5809b8";

    public BackgroundTask(Context context) {
        this.context = context;
    }

    public ArrayList<Contact> getList(){

        JsonArrayRequest jsonArrayRequest=
                new JsonArrayRequest(Request.Method.POST, json_url,null,
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {

                                int count=0;
                                while(count<response.length()){
                                    try {
                                        JSONObject jsonObject= response.getJSONObject(count);
                                        //fetch the data and save into contact class
                                        Contact contact=new Contact(jsonObject.getString("Name"),jsonObject.getString("Email"));
                                        arrayList.add(contact);
                                        count++;
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, "Error.....", Toast.LENGTH_SHORT).show();
                        error.printStackTrace();
                    }
                });
        return arrayList;
    }
}
