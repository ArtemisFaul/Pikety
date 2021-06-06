package com.example.pikety.api.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Picket implements Parcelable {
    public int id;
    public int companyId;
    public String companyName;
    public String name;
    public String description;
    public Double latitude;
    public Double longitude;
    public String positionAddress;
    public List<Worker> workers;

    protected Picket(Parcel in) {
        id = in.readInt();
        companyId = in.readInt();
        companyName = in.readString();
        name = in.readString();
        description = in.readString();
        latitude = in.readDouble();
        longitude = in.readDouble();
        positionAddress = in.readString();
        workers = in.createTypedArrayList(Worker.CREATOR);
    }

    public static final Creator<Picket> CREATOR = new Creator<Picket>() {
        @Override
        public Picket createFromParcel(Parcel in) {
            return new Picket(in);
        }

        @Override
        public Picket[] newArray(int size) {
            return new Picket[size];
        }
    };

    public Picket() {
    }

    @Override
    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags){
        dest.writeInt(this.id);
        dest.writeInt(this.companyId);
        dest.writeString(this.companyName);
        dest.writeString(this.name);
        dest.writeString(this.description);
        dest.writeDouble(this.latitude);
        dest.writeDouble(this.longitude);
        dest.writeString(this.positionAddress);
        dest.writeTypedList(this.workers);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public void setPositionAddress(String positionAddress) {
        this.positionAddress = positionAddress;
    }

    public void setWorkers(List<Worker> workers) {
        this.workers = workers;
    }
}
