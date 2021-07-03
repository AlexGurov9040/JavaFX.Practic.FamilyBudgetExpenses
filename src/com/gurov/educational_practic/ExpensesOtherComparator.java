package com.gurov.educational_practic;

import java.util.Comparator;

public class ExpensesOtherComparator implements Comparator<Expenses> {

    @Override
    public int compare(Expenses o1, Expenses o2) {

        if (o1.getOther() > o2.getOther())
            return 1;
        else if (o1.getOther() > o2.getOther())
            return -1;
        else
            return 0;
    }
}
