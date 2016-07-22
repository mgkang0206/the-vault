package com.genassembly.dotdashdot.crackmysafe2;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mauve3 on 7/22/16.
 */
public class SafeLock {
    //SparseArray<String> safe;

    SparseArray<String> safe;
    Context self;

    public SafeLock(){
        safe = new SparseArray<>();
    }

    public void lockSafe(int index, String password){
        safe.put(index, password);
    }

    public boolean checkPassword(int lock, String password){
        if (safe.get(lock).equals(password)) {
            Log.i("Found!","Awesome");
        }
        return safe.get(lock).equals(password);
    }
}
