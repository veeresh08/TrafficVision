package com.example.project.Activity.alert;

import android.os.Parcel;
import android.os.Parcelable;

public class postRVModal implements Parcelable {
    //creating variables for our different fields.
    private String postName;
    private String postDescription;
    private String postPrice;
    private String bestSuitedFor;
    private String postImg;
    private String postLink;
    private String postId;


    public String getpostId() {
        return postId;
    }

    public void setpostId(String postId) {
        this.postId = postId;
    }


    //creating an empty constructor.
    public postRVModal() {

    }

    protected postRVModal(Parcel in) {
        postName = in.readString();
        postId = in.readString();
        postDescription = in.readString();
        postPrice = in.readString();
        bestSuitedFor = in.readString();
        postImg = in.readString();
        postLink = in.readString();
    }

    public static final Creator<postRVModal> CREATOR = new Creator<postRVModal>() {
        @Override
        public postRVModal createFromParcel(Parcel in) {
            return new postRVModal(in);
        }

        @Override
        public postRVModal[] newArray(int size) {
            return new postRVModal[size];
        }
    };

    //creating getter and setter methods.
    public String getpostName() {
        return postName;
    }

    public void setpostName(String postName) {
        this.postName = postName;
    }

    public String getpostDescription() {
        return postDescription;
    }

    public void setpostDescription(String postDescription) {
        this.postDescription = postDescription;
    }

    public String getpostPrice() {
        return postPrice;
    }

    public void setpostPrice(String postPrice) {
        this.postPrice = postPrice;
    }

    public String getBestSuitedFor() {
        return bestSuitedFor;
    }

    public void setBestSuitedFor(String bestSuitedFor) {
        this.bestSuitedFor = bestSuitedFor;
    }

    public String getpostImg() {
        return postImg;
    }

    public void setpostImg(String postImg) {
        this.postImg = postImg;
    }

    public String getpostLink() {
        return postLink;
    }

    public void setpostLink(String postLink) {
        this.postLink = postLink;
    }


    public postRVModal(String postId, String postName, String postDescription, String postPrice, String bestSuitedFor, String postImg, String postLink) {
        this.postName = postName;
        this.postId = postId;
        this.postDescription = postDescription;
        this.postPrice = postPrice;
        this.bestSuitedFor = bestSuitedFor;
        this.postImg = postImg;
        this.postLink = postLink;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(postName);
        dest.writeString(postId);
        dest.writeString(postDescription);
        dest.writeString(postPrice);
        dest.writeString(bestSuitedFor);
        dest.writeString(postImg);
        dest.writeString(postLink);
    }
}
