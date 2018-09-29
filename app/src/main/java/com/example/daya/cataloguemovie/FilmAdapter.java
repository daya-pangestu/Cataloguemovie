package com.example.daya.cataloguemovie;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.PilemHolder>{
    private final ArrayList<FilmHandler> daftarfilm;
    private Context context;
    public FilmAdapter(ArrayList<FilmHandler> daftarfilm) {
        this.daftarfilm = daftarfilm;
    }


    @NonNull
    @Override
    public PilemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_view,
                parent, false);

        context = parent.getContext();

        return new PilemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final PilemHolder holder, final int position) {

        Glide.with(context)
                .load(MainActivity.BASE_URL_IMAGE+daftarfilm.get(position).getGambar())
                .into(holder.ivGambar);


        holder.txtJudul.setText(daftarfilm.get(position).getJudul());
        holder.txtDeskripsi.setText(daftarfilm.get(position).getDeskripsi());
        holder.txtTanggal.setText(daftarfilm.get(position).getTanggal());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent opindahto = new Intent(context, DetailFilm.class);

/*
                String alamatGambar = daftarfilm.get(position).getGambar();
                String judul = daftarfilm.get(position).getJudul();
                String deskr = daftarfilm.get(position).getDeskripsi();
                String tanggal = daftarfilm.get(position).getTanggal();

                opindahto.putExtra("gambar", alamatGambar);
                opindahto.putExtra("judul", judul);
                opindahto.putExtra("deskr", deskr);
                opindahto.putExtra("tanggal", tanggal);
*/
                FilmHandler filmHandler = new FilmHandler(daftarfilm.get(position).getGambar(), daftarfilm.get(position).getJudul(), daftarfilm.get(position).getDeskripsi(), daftarfilm.get(position).getTanggal());
                opindahto.putExtra(MainActivity.EXTRA_PERSON, filmHandler);
                context.startActivity(opindahto);
            }
        });

    }

    @Override
    public int getItemCount() {
        return daftarfilm.size();
    }

    public static class PilemHolder extends RecyclerView.ViewHolder {
        final ImageView ivGambar;
        final TextView txtJudul;
        final TextView txtDeskripsi;
        final TextView txtTanggal;

        PilemHolder(View itemView) {
            super(itemView);

            ivGambar= itemView.findViewById(R.id.gambar);
            txtJudul= itemView.findViewById(R.id.judul);
            txtDeskripsi= itemView.findViewById(R.id.deskripsi);
            txtTanggal= itemView.findViewById(R.id.tanggal_rilis);

        }
    }

}
