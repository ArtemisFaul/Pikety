package com.example.pikety.api.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Worker implements Parcelable {
    public int id;
    public String name;

    public void setId(int id){
        this.id = id;
    }
    public void setName(String name){
        this.name = name;
    }

    public Worker() {
    }

    protected Worker(Parcel in) {
        this.id=in.readInt();
        this.name=in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
    }

    public void readFromParcel(Parcel source){
        this.id=source.readInt();
        this.name=source.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Worker> CREATOR = new Creator<Worker>() {
        @Override
        public Worker createFromParcel(Parcel in) {
            return new Worker(in);
        }

        @Override
        public Worker[] newArray(int size) {
            return new Worker[size];
        }
    };
}
