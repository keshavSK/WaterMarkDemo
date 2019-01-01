package com.example.dell_pc.demomatrimony;

import android.app.DownloadManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.FileNameMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private Spinner GotraSpinner;
    List<GottraModel> gotrList;
    private static final String GotraUrl="http://app.myssksamaj.com/matrimonyapp.asmx/SelectAllGotraMaster";
    private static final String TAG=MainActivity.class.getSimpleName();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadGotraData();

        GotraSpinner=(Spinner)findViewById(R.id.GottraSpinner);
        gotrList=new ArrayList<>();

        GotraSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                GottraModel gottraModel=(GottraModel)parent.getItemAtPosition(position);

                int GotraId=gottraModel.getGottraId();

                String gotraid= String.valueOf(GotraId);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void loadGotraData() {

       // mProgressBar.setVisibility(View.VISIBLE);

        final StringRequest stringRequest = new StringRequest(Request.Method.POST,GotraUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d(TAG, "state response" + response);
                        //mProgressBar.setVisibility(View.INVISIBLE);
                        try {
                            JSONArray jsonArray = null;

                            JSONObject jsonObject = new JSONObject(response);

                            int success = jsonObject.getInt("success");

                            if (success == 1) {

                                jsonArray = jsonObject.getJSONArray("data");

                                for (int i = 0; i < jsonArray.length(); i++) {

                                    GottraModel model = new GottraModel();

                                    JSONObject object = jsonArray.getJSONObject(i);

                                    String stateId = object.getString("gotraid");
                                    String stateName = object.getString("gotraname");

                                    model.setGottraId(Integer.parseInt(stateId));
                                    model.setGottraName(stateName);

                                    gotrList.add(model);
                                }

                                GottraAdapter gotraAdpater = new GottraAdapter(MainActivity.this, gotrList);
                                GotraSpinner.setAdapter(gotraAdpater);
                                gotraAdpater.notifyDataSetChanged();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return super.getParams();
            }
        };

        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }
}
