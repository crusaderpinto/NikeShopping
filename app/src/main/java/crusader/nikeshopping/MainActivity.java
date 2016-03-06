package crusader.nikeshopping;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;

import crusader.nikeshopping.adapters.MyAdapter;
import crusader.nikeshopping.models.RetriveByKeyWord;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity implements Callback<RetriveByKeyWord> {
    RecyclerView rv_prodPreview;
    private MyAdapter mAdapter;
    RetriveByKeyWord myDataset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        initComponents();

        makeApiCall();
    }

    private void initComponents() {
        rv_prodPreview = (RecyclerView) findViewById(R.id.rv_detail_list);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv_prodPreview.setLayoutManager(llm);
    }

    private void makeApiCall() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
// set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient httpClient = new OkHttpClient();
// add your other interceptors …

// add logging as last interceptor
        httpClient.interceptors().add(logging);  // <-- this is the important line!
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AppConstants.BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build();
        RetroApi api = retrofit.create(RetroApi.class);
        Call<RetriveByKeyWord> call = api.getProducts(getDataMap());
        call.enqueue(this);
    }

    private HashMap getDataMap() {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("OPERATION-NAME", "findItemsByKeywords");
        map.put("SERVICE-VERSION", "1.0.0");
        map.put("SECURITY-APPNAME", getResources().getString(R.string.ebay_appid_production));
        map.put("GLOBAL-ID", "EBAY-IN");
        map.put("RESPONSE-DATA-FORMAT", "JSON");
        map.put("REST-PAYLOAD", "");
        try {
            map.put("keywords", URLEncoder.encode("Nike","utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        map.put("paginationInput.entriesPerPage", "10");
        return map;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResponse(Response<RetriveByKeyWord> response, Retrofit retrofit) {
        myDataset = response.body();
        mAdapter = new MyAdapter(this, myDataset.getFindItemsByKeywordsResponse().get(0).getSearchResult());
        rv_prodPreview.setAdapter(mAdapter);

        /**Endless ADapter**/
//        endlessRecyclerViewAdapter = new EndlessRecyclerViewAdapter(this, mAdapter, this);
//        rv_prodPreview.setAdapter(endlessRecyclerViewAdapter);
        /********************/

        String responsetxt = new Gson().toJson(response);
//        Log.d("CRUSADER", responsetxt);
        Utility.showLog(responsetxt);
    }

    @Override
    public void onFailure(Throwable t) {
        Log.d("CRUSADER", t.getMessage());
    }

}
