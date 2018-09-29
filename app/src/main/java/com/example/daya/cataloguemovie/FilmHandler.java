package com.example.daya.cataloguemovie;


import android.os.Parcel;
import android.os.Parcelable;

class FilmHandler implements Parcelable {
    private final String gambar;
    private final String judul;
    private final String deskripsi;
    private final String tanggal;

    public FilmHandler(String gambar, String judul, String deskripsi, String tanggal) {
        this.gambar = gambar;
        this.judul = judul;
        this.deskripsi = deskripsi;
        this.tanggal = tanggal;
    }

    public String getGambar() {
        return gambar;
    }

    public String getJudul() {
        return judul;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public String getTanggal() {
        return tanggal;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.gambar);
        dest.writeString(this.judul);
        dest.writeString(this.deskripsi);
        dest.writeString(this.tanggal);
    }

    protected FilmHandler(Parcel in) {
        this.gambar = in.readString();
        this.judul = in.readString();
        this.deskripsi = in.readString();
        this.tanggal = in.readString();
    }

    public static final Parcelable.Creator<FilmHandler> CREATOR = new Parcelable.Creator<FilmHandler>() {
        @Override
        public FilmHandler createFromParcel(Parcel source) {
            return new FilmHandler(source);
        }

        @Override
        public FilmHandler[] newArray(int size) {
            return new FilmHandler[size];
        }
    };
}











