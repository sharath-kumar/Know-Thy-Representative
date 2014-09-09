package net.sharathkumar.android.apps.knowthyrepresentative;

import android.app.Application;
import android.content.Context;

public class KnowThySenatorApplicationContext extends Application {
	
    private static Context gameContext;
    
    public void onCreate() {
        super.onCreate();
        gameContext = getApplicationContext(); 
    }

    public static Context getContext() {
        return gameContext;
    }

}
