package umn.ac.id.uts_mobile_32827;

import android.os.Parcel;
import android.os.Parcelable;

public class Audio implements Parcelable {
    private String path, name, album, artist, duration;

    public Audio(String path, String name, String album, String artist, String duration) {
        this.path = path;
        this.name = name;
        this.album = album;
        this.artist = artist;
        this.duration = duration;
    }

    protected Audio(Parcel in) {
        path = in.readString();
        name = in.readString();
        album = in.readString();
        artist = in.readString();
        duration = in.readString();
    }

    public static final Creator<Audio> CREATOR = new Creator<Audio>() {
        @Override
        public Audio createFromParcel(Parcel in) {
            return new Audio(in);
        }

        @Override
        public Audio[] newArray(int size) {
            return new Audio[size];
        }
    };

    // Getters
    public String getPath() {
        return path;
    }

    public String getName() {
        return name;
    }

    public String getAlbum() {
        return album;
    }

    public String getArtist() {
        return artist;
    }

    public String getDuration() {
        return duration;
    }

    // Setters
    public void setPath(String path) {
        this.path = path;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.path);
        dest.writeString(this.name);
        dest.writeString(this.album);
        dest.writeString(this.artist);
        dest.writeString(this.duration);
    }
}
