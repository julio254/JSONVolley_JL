package com.example.jsonvolley_jl;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataGrabber {
    private RequestQueue mQueue;

    public static Map<String, FrenchHorns> ITEM_MAP = null;

    public static List<FrenchHorns> Horns = null;

    public void getThatDataFromThatURL (Context context) {
        if(Horns != null) {
            return;
        }

        mQueue = Volley.newRequestQueue(context);
        parseJason(context);

        Horns = new ArrayList<>();
        ITEM_MAP = new HashMap<>();
    }

    private void parseJason(final Context context) {
        // Must read the JSON file from the strings.xml
        String url = context.getString(R.string.julio_json_url);

        final Gson gson = new Gson();

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("hornBrands");

                    // Checks to make sure the list isn't empty
                    if (jsonArray.length() > 0) {
                        List<FrenchHorns> horns = Arrays.asList(gson.fromJson(jsonArray.toString(), FrenchHorns[].class));

                        for (FrenchHorns frenchHorns : horns) {
                            addObjectToList(frenchHorns);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mQueue.add(request);
    }

    // Method for adding object to list
    private void addObjectToList(FrenchHorns CurrentHorn) {
        Horns.add(CurrentHorn);
        ITEM_MAP.put(CurrentHorn.hornMaker, CurrentHorn);
    }

}