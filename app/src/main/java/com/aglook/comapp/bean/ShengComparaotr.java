package com.aglook.comapp.bean;

import java.util.Comparator;

/**
 * Created by aglook on 2015/12/22.
 */
public class ShengComparaotr implements Comparator<Sheng> {
    @Override
    public int compare(Sheng lhs, Sheng rhs) {
        return lhs.getSortLetters().compareTo(rhs.getSortLetters());
    }
}
