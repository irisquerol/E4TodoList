package com.android.e4todolist.Model;


import android.os.Parcel;
import android.os.Parcelable;


public class Task implements Parcelable {
    private String name;
    private Boolean done;

    public Task(String name) {
        this.name = name;
        done = false;
    }

    protected Task(Parcel in) {
        name = in.readString();
        byte tmpDone = in.readByte();
        done = tmpDone == 0 ? null : tmpDone == 1;
    }

    public static final Creator<Task> CREATOR = new Creator<Task>() {
        @Override
        public Task createFromParcel(Parcel in) {
            return new Task(in);
        }

        @Override
        public Task[] newArray(int size) {
            return new Task[size];
        }
    };

    public String getName() {
        return name;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeByte((byte) (done == null ? 0 : done ? 1 : 2));
    }
}
