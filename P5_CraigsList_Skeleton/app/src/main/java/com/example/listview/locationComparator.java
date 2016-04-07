package com.example.listview;

import java.util.Comparator;

/**
 * Created by Austin on 4/7/2016.
 */
public class locationComparator implements Comparator<BikeData> {

    @Override
    public int compare(BikeData o1, BikeData o2) {
        if(o1.Location.equals(o2.Location)){
            return 0;
        }
        if(o1.Location.equals(null)){
            return -1;
        }
        if(o2.Location.equals(null)){
            return 1;
        }
        return o1.Location.compareTo(o2.Location);
    }
};
