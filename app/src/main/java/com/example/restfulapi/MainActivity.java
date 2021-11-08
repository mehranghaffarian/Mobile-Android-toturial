package com.example.restfulapi;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private RelativeLayout parentView;
    private Button get_city_id, use_city_id, use_city_name;
    private EditText input;
    private ListView weather_reports;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeElements();
        setListeners();
    }

    private void initializeElements() {
        get_city_id = findViewById(R.id.get_city_id);
        use_city_id = findViewById(R.id.use_city_id);
        use_city_name = findViewById(R.id.use_city_name);
        input = findViewById(R.id.city_input);
        weather_reports = findViewById(R.id.weather_list);
        parentView = findViewById(R.id.parentView);
    }

    private void setListeners() {
        get_city_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
// Instantiate the RequestQueue.
//                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                String url ="https://www.metaweather.com/api/location/search/?query=" + input.getText().toString();

                JsonArrayRequest jarrReq = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            String cityID = response.getJSONObject(0).getString("woeid");

                            Snackbar.make(parentView, "City ID is " + cityID, Snackbar.LENGTH_INDEFINITE).setAction("OK", new View.OnClickListener() {
//                                @RequiresApi(api = Build.VERSION_CODES.M)
                                @Override
                                public void onClick(View view) {
//                                    ClipboardManager clipboard = (ClipboardManager)
//                                            getSystemService(Context.CLIPBOARD_SERVICE);
//                                    ClipData clip = ClipData.newPlainText("City ID", cityID);
//
//                                    Toast.makeText(MainActivity.this, "Copied!!", Toast.LENGTH_SHORT).show();
                                }
                            }).setBackgroundTint(Color.rgb(0, 0, 0))
                              .setActionTextColor(Color.rgb(100, 255, 100))
                              .show();
//                            Toast.makeText(MainActivity.this, "City ID is " + response.getJSONObject(0).getString("woeid"), Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            Toast.makeText(MainActivity.this, "Something went wrong please try again.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Something went wrong, try again.", Toast.LENGTH_SHORT).show();
                    }
                });
                MySingleton.getInstance(MainActivity.this).addToRequestQueue(jarrReq);
//                queue.add(jarrReq);
            }
        });
        use_city_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        use_city_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
//                ClipData.Item item = clipboard.getPrimaryClip().getItemAt(0);
//                String pasteData = item.getText().toString();
//                Toast.makeText(MainActivity.this, pasteData, Toast.LENGTH_SHORT).show();
            }
        });
    }
}