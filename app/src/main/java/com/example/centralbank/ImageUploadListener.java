package com.example.centralbank;

public interface ImageUploadListener {
    void onImageUploaded(String imageURL);
    void onImageUploadFailed(String errorMessage);
}