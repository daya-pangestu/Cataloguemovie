package com.example.daya.cataloguemovie;

import android.app.LoaderManager;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity{
    private EditText edt_cari;
    private ProgressBar pro_bar_brey;

    private static final String API_KEY = BuildConfig.TMDb_API_KEY;
    private static final String TAG = MainActivity.class.getSimpleName();

    public static String EXTRA_PERSON = "extra_person";
    public static String BASE_URL_IMAGE = "http://image.tmdb.org/t/p/w185/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edt_cari = findViewById(R.id.editText);
        Button btn_cari = findViewById(R.id.btn_search);
        pro_bar_brey = findViewById(R.id.proges_bar);

        pro_bar_brey.setVisibility(View.GONE);




        btn_cari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String text = edt_cari.getText().toString();
                pro_bar_brey.setVisibility(View.VISIBLE);

                if (!checkConection()) {
                    Toast.makeText(MainActivity.this, "anda dalam mode offline", Toast.LENGTH_SHORT).show();
                    pro_bar_brey.setVisibility(View.GONE);
                } else {

                    parseResult(text);
                }
            }
        });


    }

    private boolean checkConection() {
        ConnectivityManager cekm = (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = Objects.requireNonNull(cekm).getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    private void parseResult(String search) {
        String url = "https://api.themoviedb.org/3/search/movie?api_key=" + BuildConfig.TMDb_API_KEY + "&language=en-US&query="+search;

        AsyncHttpClient client = new AsyncHttpClient();

        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Log.i(TAG, "onSuccess respons dari get : " + statusCode);

                String result = new String(responseBody);

                try {
                    JSONObject findResult = new JSONObject(result);
                    JSONArray resultsInArray =  findResult.getJSONArray("results");
                    Log.i(TAG, "onSuccess: panjang array :" + resultsInArray.length());
                    if (resultsInArray.length() == 0) {
                        Toast.makeText(MainActivity.this, "hasil tidak ditemukan!", Toast.LENGTH_SHORT).show();
                    }

                    LoadKeRecyclerView(resultsInArray);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                pro_bar_brey.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    private void LoadKeRecyclerView(JSONArray jsonArray) throws JSONException {
        ArrayList<FilmHandler> daftarpilem = new ArrayList<>();

        for (int q = 0; q < jsonArray.length(); q++) {
            JSONObject objectke = jsonArray.getJSONObject(q);
            Log.i(TAG, "onSuccess: " + objectke.getString("title") + "\n");

            String gambar = objectke.getString("poster_path");
            String judul = objectke.getString("title");
            String deskripsi = objectke.getString("overview");
            String tanggal = objectke.getString("release_date");

            daftarpilem.add(new FilmHandler(gambar, judul, deskripsi, tanggal));
            Log.d(TAG, "LoadKeRecyclerViewCuy: " + gambar + judul + deskripsi + tanggal + "\n");
        }

        RecyclerView rcv = findViewById(R.id.diulang_ulang);
        FilmAdapter adapter = new FilmAdapter(daftarpilem);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        rcv.setLayoutManager(layoutManager);
        rcv.setAdapter(adapter);
    }

    }
