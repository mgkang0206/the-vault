package com.genassembly.dotdashdot.crackmysafe2;

import android.content.Intent;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mauve3 on 7/22/16.
 */
public class SaltHolder {
    private Map<Integer, String> sHold;

    public SaltHolder(){
        sHold = new HashMap<>();
    }

    public int getLength(){
        return sHold.size();
    }

    public String getMap(int index){
        return sHold.get(index);
    }

    public void setMap(int index, String salt){
        sHold.put(index, salt);
    }

    public String printPassword(int fab, String raw){
        return BCrypt.hashpw(raw, sHold.get(fab));
    }
}
