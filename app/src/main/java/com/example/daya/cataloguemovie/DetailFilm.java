package com.example.daya.cataloguemovie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailFilm extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailpelim);

        ImageView gambar_detail = findViewById(R.id.gambar_detail);
        TextView judul_detail = findViewById(R.id.judul_detail);
        TextView desckr_detail= findViewById(R.id.desc_Detail);
        TextView tanggal_detail = findViewById(R.id.tanggal_rilis_detail);

        FilmHandler filmHandler = getIntent().getParcelableExtra(MainActivity.EXTRA_PERSON);
        Glide.with(getApplicationContext())
                .load(MainActivity.BASE_URL_IMAGE+filmHandler.getGambar())
                .into(gambar_detail);

        judul_detail.setText(filmHandler.getJudul());
        desckr_detail.setText(filmHandler.getDeskripsi());
        tanggal_detail.setText(filmHandler.getTanggal());



    }
}
