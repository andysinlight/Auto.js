package com.stardust.app.api;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;

public class ActivityIntentBuilder extends IntentBuilder implements ActivityStarter {
    protected Bundle lastOptions;

    public ActivityIntentBuilder(Context context, Class<?> clazz) {
        super(context, clazz);
    }

    public ActivityIntentBuilder(Context context, Intent intent) {
        super(context, intent);
    }

    public PostActivityStarter start() {
        this.startForResult(-1);
        return new PostActivityStarter(this.context);
    }

    public  PostActivityStarter startForResult( int requestCode){
        if (fragment!= null) {
            fragment.startActivityForResult(intent, requestCode, lastOptions);
        } else {
            if (context instanceof Activity) {
                Activity activity = ((Activity) context);
                ActivityCompat.startActivityForResult(activity, intent, requestCode, lastOptions);
            } else {
                context.startActivity(intent, lastOptions);
            }
        }
        return new PostActivityStarter(this.context);
    }

    public ActivityStarter withOptions(Bundle options) {
        this.lastOptions = options;
        return this;
    }
}
