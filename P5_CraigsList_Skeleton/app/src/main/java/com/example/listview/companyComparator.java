package com.example.listview;

import java.util.Comparator;

/**
 * Created by Austin on 4/7/2016.
 */
public class companyComparator implements Comparator<BikeData> {

    @Override
    public int compare(BikeData o1, BikeData o2) {
        if(o1.Company.equals(o2.Company)){
            return 0;
        }
        if(o1.Company.equals(null)){
            return -1;
        }
        if(o2.Company.equals(null)){
            return 1;
        }
        return o1.Company.compareTo(o2.Company);
    }
};
