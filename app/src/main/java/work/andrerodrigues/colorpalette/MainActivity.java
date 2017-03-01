package work.andrerodrigues.colorpalette;

import android.app.ProgressDialog;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

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
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    private RequestQueue requestQueue;
    private JsonObjectRequest jsonObjectRequest;

    private List<Item> list;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private ProgressDialog progress;
    private boolean isLoading;

    public static String GROUP_NAME = "";
    public static List<Group> groups;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestQueue = VolleySingleton.getInstance(this).getRequestQueue();
        if (savedInstanceState != null) {
            list = savedInstanceState.getParcelableArrayList("LIST");
            adapter = new ItemBaseAdapter(list);
        } else {
            list = new ArrayList<Item>();
            adapter = new ItemBaseAdapter(list);
            // load items
            progress = ProgressDialog.show(this, "Carregando", "aguarde", true);
            updateItemList(Util.GET_ITEM_URL(), false, false);
        }
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        recyclerView.setAdapter(adapter);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.drawer_layout_open, R.string.drawer_layout_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Menu menu = navigationView.getMenu();
        menu.add(0, 0, 0, "All").setChecked(true);
        for (Group group : groups) {
            menu.add(0, group.getId(), group.getId(), group.getName());
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList("LIST", (ArrayList<? extends Parcelable>) list);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int group_id = item.getItemId();

        drawerLayout.closeDrawer(GravityCompat.START);
        progress = ProgressDialog.show(this, "Carregando", "aguarde", true);
        // All items
        if (group_id == 0) {
            updateItemList(Util.GET_ITEM_URL(), true, false);
        // Specific group
        } else {
            GROUP_NAME = (String) item.getTitle();
            updateItemList(Util.GET_ITEM_URL(group_id), true, true);
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (requestQueue != null) {
            requestQueue.cancelAll(Util.ITEM_TAG);
        }
    }

    private void updateItemList(String url, final boolean clear, final boolean hasHeader) {
        isLoading = true;
        jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("items");
                            Gson gson = new Gson();
                            Item[] items = gson.fromJson(jsonArray.toString(), Item[].class);
                            if (clear) {
                                list.clear();
                            }
                            if (hasHeader) {
                                list.add(null);
                            }
                            list.addAll(Arrays.asList(items));
                            adapter.notifyDataSetChanged();
                            isLoading = false;
                            if (progress.isShowing()) {
                                progress.dismiss();
                            }
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
        jsonObjectRequest.setTag(Util.ITEM_TAG);
        requestQueue.add(jsonObjectRequest);
    }
}
