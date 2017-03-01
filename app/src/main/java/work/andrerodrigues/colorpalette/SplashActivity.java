package work.andrerodrigues.colorpalette;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

public class SplashActivity extends AppCompatActivity {

    private RequestQueue requestQueue;
    private JsonObjectRequest jsonObjectRequest;
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, Util.GET_GROUP_URL(), null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("groups");
                            Gson gson = new Gson();
                            Group[] groups = gson.fromJson(jsonArray.toString(), Group[].class);
                            MainActivity.groups = new ArrayList<Group>();
                            MainActivity.groups.addAll(Arrays.asList(groups));

                            Intent intent = new Intent(context, MainActivity.class);
                            startActivity(intent);
                            finish();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub

                    }
                });
        jsonObjectRequest.setTag(Util.GROUP_TAG);

        requestQueue = VolleySingleton.getInstance(this).getRequestQueue();
        requestQueue.add(jsonObjectRequest);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (requestQueue != null) {
            requestQueue.cancelAll(Util.GROUP_TAG);
        }
    }
}
