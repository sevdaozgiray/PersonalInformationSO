package com.example.personalinformationso.model;

/**
 * Author by Sevda$, Email xx@xx.com, Date on 13.11.2020$.
 * PS: Not easy to write code, please indicate.
 */

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel
public class PersonalModel implements Parcelable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("avatar")
    @Expose
    private String avatar;
    @SerializedName("age")
    @Expose
    private Integer age;
    @SerializedName("eyeColor")
    @Expose
    private String eyeColor;
    @SerializedName("full_name")
    @Expose
    private String fullName;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("job_title")
    @Expose
    private String jobTitle;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("about")
    @Expose
    private String about;
    @SerializedName("country_name")
    @Expose
    private String countryName;
    @SerializedName("city_name")
    @Expose
    private String cityName;
    @SerializedName("city_picture")
    @Expose
    private String cityPicture;

    protected PersonalModel(android.os.Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        avatar = in.readString();
        if (in.readByte() == 0) {
            age = null;
        } else {
            age = in.readInt();
        }
        eyeColor = in.readString();
        fullName = in.readString();
        gender = in.readString();
        jobTitle = in.readString();
        email = in.readString();
        phone = in.readString();
        address = in.readString();
        about = in.readString();
        countryName = in.readString();
        cityName = in.readString();
        cityPicture = in.readString();
    }

    public static final Creator<PersonalModel> CREATOR = new Creator<PersonalModel>() {
        @Override
        public PersonalModel createFromParcel(android.os.Parcel in) {
            return new PersonalModel(in);
        }

        @Override
        public PersonalModel[] newArray(int size) {
            return new PersonalModel[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(String eyeColor) {
        this.eyeColor = eyeColor;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityPicture() {
        return cityPicture;
    }

    public void setCityPicture(String cityPicture) {
        this.cityPicture = cityPicture;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(id);
        }
        dest.writeString(avatar);
        if (age == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(age);
        }
        dest.writeString(eyeColor);
        dest.writeString(fullName);
        dest.writeString(gender);
        dest.writeString(jobTitle);
        dest.writeString(email);
        dest.writeString(phone);
        dest.writeString(address);
        dest.writeString(about);
        dest.writeString(countryName);
        dest.writeString(cityName);
        dest.writeString(cityPicture);
    }
}
