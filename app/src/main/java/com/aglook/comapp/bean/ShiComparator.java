package com.aglook.comapp.bean;

import java.util.Comparator;

/**
 * Created by aglook on 2015/12/22.
 */
public class ShiComparator implements Comparator<Shi> {
    @Override
    public int compare(Shi lhs, Shi rhs) {
        return lhs.getnLetters().compareTo(rhs.getnLetters());
    }
}
