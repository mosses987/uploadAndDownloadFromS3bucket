package com.mosses.awsimageupload.datastore;

import com.mosses.awsimageupload.profile.UserProfile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class FakeUserProfileDataStore {

    private List<UserProfile> USER_PROFILES = new ArrayList<>();


    public List<UserProfile> getUserProfiles(){
        USER_PROFILES.add(new UserProfile(UUID.fromString("d43c05c8-8462-4409-b583-6146365c9028"), "Mosses", null));
        USER_PROFILES.add(new UserProfile(UUID.fromString("e9e4bb99-b983-4c3b-b729-0eb36369b2db"), "John", null));
        return USER_PROFILES;
    }

}


// product and customer domain