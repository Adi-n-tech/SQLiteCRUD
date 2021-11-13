package com.exampple.sqlite;

import android.os.Parcel;
import android.os.Parcelable;

public class Emp implements Parcelable {
    String name;
    int age;

    protected Emp(Parcel in) {
        name = in.readString();
        age = in.readInt();
    }

    public Emp(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public static final Creator<Emp> CREATOR = new Creator<Emp>() {
        @Override
        public Emp createFromParcel(Parcel in) {
            return new Emp(in);
        }

        @Override
        public Emp[] newArray(int size) {
            return new Emp[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeInt(age);
    }

    @Override
    public String toString() {
        return "Emp{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
