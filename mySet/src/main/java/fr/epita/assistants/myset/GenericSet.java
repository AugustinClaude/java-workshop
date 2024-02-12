package fr.epita.assistants.myset;

import java.util.ArrayList;
import java.util.Collections;

public class GenericSet<T extends Comparable<T>> {
    ArrayList<T> base_;

    public GenericSet() {
        base_ = new ArrayList<>();
    }

    public void insert(T i) {
        if (!base_.contains(i))
        {
            int pos = Collections.binarySearch(base_, i);
            if (pos < 0)
                base_.add(-pos - 1, i);
        }
    }

    public void remove(T i) {
        base_.remove(i);
    }

    public boolean has(T i) {
        return base_.contains(i);
    }

    public boolean isEmpty() {
        return base_.size() == 0;
    }

    public T min() {
        return base_.get(0);
    }

    public T max() {
        return base_.get(base_.size() - 1);
    }

    public int size() {
        return base_.size();
    }

    public static <T extends Comparable<T>> GenericSet<T> intersection(GenericSet<T> a, GenericSet<T> b) {
        GenericSet<T> c = new GenericSet<T>();
        for (int i = 0; i < a.size(); i++) {
            for (int j = 0; j < b.size(); j++) {
                if (a.base_.get(i).equals(b.base_.get(j)) && !c.has(a.base_.get(i)))
                    c.insert(a.base_.get(i));
            }
        }

        return c;
    }

    public static <T extends Comparable<T>> GenericSet<T> union(GenericSet<T> a, GenericSet<T> b) {
        GenericSet<T> c = new GenericSet<T>();
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
