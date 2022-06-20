package com.stardust.app.api;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;

import androidx.fragment.app.Fragment;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class IntentBuilder extends Builder {
    protected final Context context;
    protected Fragment  fragment;
    protected final Intent intent;

    public IntentBuilder(Context context, Class<?> clazz) {
        this(context, new Intent(context, clazz));
    }

    public IntentBuilder(Context context, Intent intent) {
        this.context = context;
        this.intent = intent;
    }

    public Context getContext() {
        return this.context;
    }

    public Intent get() {
        return this.intent;
    }

    public IntentBuilder setFragment(Fragment fragment){
        this.fragment = fragment;
        return this;
    }

    public IntentBuilder flags(int flags) {
        this.intent.setFlags(flags);
        return this;
    }

    public IntentBuilder action(String action) {
        this.intent.setAction(action);
        return this;
    }

    public IntentBuilder type(String type) {
        this.intent.setType(type);
        return this;
    }

    public IntentBuilder category(String category) {
        this.intent.addCategory(category);
        return this;
    }

    public IntentBuilder data(Uri data) {
        this.intent.setData(data);
        return this;
    }

    public IntentBuilder extra(String name, boolean value) {
        this.intent.putExtra(name, value);
        return this;
    }

    public IntentBuilder extra(String name, byte value) {
        this.intent.putExtra(name, value);
        return this;
    }

    public IntentBuilder extra(String name, char value) {
        this.intent.putExtra(name, value);
        return this;
    }

    public IntentBuilder extra(String name, short value) {
        this.intent.putExtra(name, value);
        return this;
    }

    public IntentBuilder extra(String name, int value) {
        this.intent.putExtra(name, value);
        return this;
    }

    public IntentBuilder extra(String name, long value) {
        this.intent.putExtra(name, value);
        return this;
    }

    public IntentBuilder extra(String name, float value) {
        this.intent.putExtra(name, value);
        return this;
    }

    public IntentBuilder extra(String name, double value) {
        this.intent.putExtra(name, value);
        return this;
    }

    public IntentBuilder extra(String name, String value) {
        this.intent.putExtra(name, value);
        return this;
    }

    public IntentBuilder extra(String name, CharSequence value) {
        this.intent.putExtra(name, value);
        return this;
    }

    public IntentBuilder extra(String name, Parcelable value) {
        this.intent.putExtra(name, value);
        return this;
    }

    public IntentBuilder extra(String name, Parcelable[] value) {
        this.intent.putExtra(name, value);
        return this;
    }

    public IntentBuilder parcelableArrayListExtra(String name, ArrayList<? extends Parcelable> value) {
        this.intent.putParcelableArrayListExtra(name, value);
        return this;
    }

    public IntentBuilder integerArrayListExtra(String name, ArrayList<Integer> value) {
        this.intent.putIntegerArrayListExtra(name, value);
        return this;
    }

    public IntentBuilder stringArrayListExtra(String name, ArrayList<String> value) {
        this.intent.putStringArrayListExtra(name, value);
        return this;
    }

    public IntentBuilder extra(String name, Serializable value) {
        this.intent.putExtra(name, value);
        return this;
    }

    public IntentBuilder extra(String name, boolean[] value) {
        this.intent.putExtra(name, value);
        return this;
    }

    public IntentBuilder extra(String name, byte[] value) {
        this.intent.putExtra(name, value);
        return this;
    }

    public IntentBuilder extra(String name, short[] value) {
        this.intent.putExtra(name, value);
        return this;
    }

    public IntentBuilder extra(String name, char[] value) {
        this.intent.putExtra(name, value);
        return this;
    }

    public IntentBuilder extra(String name, int[] value) {
        this.intent.putExtra(name, value);
        return this;
    }

    public IntentBuilder extra(String name, long[] value) {
        this.intent.putExtra(name, value);
        return this;
    }

    public IntentBuilder extra(String name, float[] value) {
        this.intent.putExtra(name, value);
        return this;
    }

    public IntentBuilder extra(String name, double[] value) {
        this.intent.putExtra(name, value);
        return this;
    }

    public IntentBuilder extra(String name, String[] value) {
        this.intent.putExtra(name, value);
        return this;
    }

    public IntentBuilder extra(String name, Bundle value) {
        this.intent.putExtra(name, value);
        return this;
    }

    public IntentBuilder extras(Intent src) {
        this.intent.putExtras(src);
        return this;
    }

    public abstract PostActivityStarter start();

    public abstract PostActivityStarter startForResult(int var1);



}
