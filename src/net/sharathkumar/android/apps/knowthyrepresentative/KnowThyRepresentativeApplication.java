package net.sharathkumar.android.apps.knowthyrepresentative;

import android.app.Application;
import android.content.Context;

public class KnowThyRepresentativeApplication extends Application {
	
    private static Context applicationContext;
    
    public void onCreate() {
        super.onCreate();
        applicationContext = getApplicationContext(); 
    }

    public static Context getContext() {
        return applicationContext;
    }

}
