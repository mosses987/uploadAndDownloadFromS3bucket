package com.mosses.awsimageupload.filestore;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Optional;

@Service
public class FileStore {

    private final AmazonS3 s3;

    @Autowired
    public FileStore(AmazonS3 s3) {
        this.s3 = s3;
    }

    public void save(String path,
                     String fileName,
                     Optional<Map<String,String>> optionalMetadata,
                        InputStream inputStream){

        ObjectMetadata objectMetadata = new ObjectMetadata();
        optionalMetadata.ifPresent(map -> {
            if(!map.isEmpty()){
                map.forEach((key, val)->objectMetadata.addUserMetadata(key,val));
            }
        });

        try{
            s3.putObject(path,fileName,inputStream,objectMetadata);
        }catch (AmazonServiceException e){
            throw new IllegalStateException("Unable to store the file",e);
        }


    }

    public byte[] download(String path, String key){
        try {
            S3Object s3Object = s3.getObject(path, key);
            return IOUtils.toByteArray(s3Object.getObjectContent());

        }catch (AmazonServiceException | IOException e){
            throw  new IllegalStateException(e);
        }
    }
}
