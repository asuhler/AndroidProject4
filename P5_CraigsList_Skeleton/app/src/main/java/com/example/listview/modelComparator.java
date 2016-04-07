package com.example.listview;

import java.util.Comparator;

/**
 * Created by Austin on 4/7/2016.
 */
public class modelComparator implements Comparator<BikeData> {

    @Override
    public int compare(BikeData o1, BikeData o2) {
        if(o1.Model.equals(o2.Model)){
            return 0;
        }
        if(o1.Model.equals(null)){
            return -1;
        }
        if(o2.Model.equals(null)){
            return 1;
        }
        return o1.Model.compareTo(o2.Model);
    }
};
