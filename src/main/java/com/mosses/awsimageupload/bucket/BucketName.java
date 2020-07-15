package com.mosses.awsimageupload.bucket;

public enum BucketName {

    PROFILE_IMAGE("image-upload-java-aws");

    private final String bucketName;

    BucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getBucketName() {
        return bucketName;
    }
}
