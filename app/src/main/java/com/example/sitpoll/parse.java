package com.example.sitpoll;
import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseUser;

import android.app.Application;




public class parse extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Parse.enableLocalDatastore(this);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("myappID")
                // if desired
                .clientKey("jiAi6oVywgZW")
                .server("http://3.15.156.7/parse/")
                .build()
        );

        //ParseUser.enableAutomaticUser();
        ParseACL defaultACL= new ParseACL();
        defaultACL.setPublicReadAccess(true);
        defaultACL.setPublicWriteAccess(true);

        ParseACL.setDefaultACL(defaultACL ,true);

    }


}