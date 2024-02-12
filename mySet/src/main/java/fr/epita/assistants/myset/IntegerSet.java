package fr.epita.assistants.myset;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class IntegerSet {
    ArrayList<Integer> base_;

    public IntegerSet() {
        base_ = new ArrayList<>();
    }

    public void insert(Integer i) {
        if (!base_.contains(i))
        {
            int pos = Collections.binarySearch(base_, i);
            if (pos < 0)
                base_.add(-pos - 1, i);
        }
    }

    public void remove(Integer i) {
        base_.remove(i);
    }

    public boolean has(Integer i) {
        return base_.contains(i);
    }

    public boolean isEmpty() {
        return base_.size() == 0;
    }

    public Integer min() {
        return base_.get(0);
    }

    public Integer max() {
        return base_.get(base_.size() - 1);
    }

    public int size() {
        return base_.size();
    }

    public static IntegerSet intersection(IntegerSet a, IntegerSet b) {
        IntegerSet c = new IntegerSet();
        for (int i = 0; i < a.size(); i++) {
            for (int j = 0; j < b.size(); j++) {
                if (a.base_.get(i).equals(b.base_.get(j)) && !c.has(a.base_.get(i)))
                    c.insert(a.base_.get(i));
            }
        }

        return c;
    }

    public static IntegerSet union(IntegerSet a, IntegerSet b) {
        IntegerSet c = new IntegerSet();
        for (int i = 0; i < a.size(); i++) {
            if (!c.has(a.base_.get(i)))
                c.insert(a.base_.get(i));
        }

        for (int i = 0; i < b.size(); i++) {
            if (!c.has(b.base_.get(i)))
                c.insert(b.base_.get(i));
        }

        return c;
    }
}
