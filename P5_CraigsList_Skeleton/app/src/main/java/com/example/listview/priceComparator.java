package com.example.listview;

import java.util.Comparator;

/**
 * Created by Austin on 4/7/2016.
 */
public class priceComparator implements Comparator<BikeData> {

    @Override
    public int compare(BikeData o1, BikeData o2) {
        if(o1.Price==o2.Price){
            return 0;
        }
        if(o1.Price.equals(null)){
            return -1;
        }
        if(o2.Price.equals(null)){
            return 1;
        }
        return (o1.Price>o2.Price ? -1 : (o1.Price==o2.Price ? 0 : 1));
    }
};