package com.gurov.educational_practic;

import java.util.Comparator;

public class ExpensesHouseComparator implements Comparator<Expenses> {

    @Override
    public int compare(Expenses o1, Expenses o2) {

        if (o1.getHouse() > o2.getHouse())
            return 1;
        else if (o1.getHouse() < o2.getHouse())
            return -1;
        else
            return 0;
    }
}
