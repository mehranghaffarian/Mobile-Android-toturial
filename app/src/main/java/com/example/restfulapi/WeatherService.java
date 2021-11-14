package com.example.restfulapi;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class WeatherService {
    private String cityID = "";
    private final RelativeLayout parentView;
    private final String CITY_ID_QUERY_URL = "https://www.metaweather.com/api/location/search/?query=";
    private final String REPORT_QUERY_URL_WITH_CITY_ID = "https://www.metaweather.com/api/location/";
    private final Context mainContext;

    public WeatherService(RelativeLayout parentView, Context mainContext) {
        this.parentView = parentView;
        this.mainContext = mainContext;
    }

    public interface VolleyResponseListener {
        void onError(String message);

        void onResponse(String cityID);
    }

    public void getCityID(String cityName, VolleyResponseListener vrl){
        String url = CITY_ID_QUERY_URL + cityName;

        JsonArrayRequest jarrReq = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    cityID = response.getJSONObject(0).getString("woeid");
                } catch (JSONException e) {
                    Toast.makeText(mainContext, "This message should not be seen!!! check the reason.", Toast.LENGTH_SHORT).show();
                }
                vrl.onResponse(cityID);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                vrl.onError("Something went wrong please try again.");
            }
        });
        MySingleton.getInstance(mainContext).addToRequestQueue(jarrReq);
//                queue.add(jarrReq);
    }

    public List<WeatherReport> getCityReportById(String ID, VolleyResponseListener vrl){
        String url = REPORT_QUERY_URL_WITH_CITY_ID + ID;

        JsonObjectRequest jor = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(mainContext, "correct", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(mainContext, "incorrect", Toast.LENGTH_SHORT).show();
            }
        });

        return null;
    }
//
//    public list<WeatherReport> getCityReportById(String name){
//
//        return null;
//    }
}
