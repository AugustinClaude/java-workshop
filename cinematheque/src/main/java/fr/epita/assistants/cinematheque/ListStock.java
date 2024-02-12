package fr.epita.assistants.cinematheque;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class ListStock<T> extends Stock<T> {
    private final List<T> stocks;

    public ListStock() {
        stocks = new ArrayList<>();
    }

    public ListStock(List<T> stocks) {
        if (stocks == null) {
            this.stocks = null;
            return;
        }

        for (int i = 0; i < stocks.size(); i++) {
            for (int j = 0; j < stocks.size(); j++) {
                if (i != j && stocks.get(i).equals(stocks.get(j)))
                    throw new IllegalArgumentException();
            }
        }

        this.stocks = stocks;
    }

    @Override
    public boolean add(T t) {
        if (t == null || stocks.contains(t))
            return false;

        stocks.add(t);
        property.firePropertyChange(null, null, Operation.Add);
        return true;
    }

    @Override
    public boolean remove(T t) {
        boolean status = stocks.remove(t);
        if (status)
            property.firePropertyChange(null, null, Operation.Delete);
        return status;
    }

    @Override
    public boolean contains(T t) {
        return stocks.contains(t);
    }

    @Override
    public Collection<? extends T> list() {
        return stocks;
    }

    boolean isSorted(List<T> list, Comparator<? super T> cmp) {
        for (int i = 0; i < list.size() - 1; i++) {
            if (cmp.compare(list.get(i), list.get(i + 1)) > 0)
                return false;
        }
        return true;
    }

    @Override
    public boolean sort(Comparator<? super T> cmp) {
        if (cmp == null)
            return false;

        try {
            if (isSorted(stocks, cmp))
                return false;

            stocks.sort(cmp);
            if (!isSorted(stocks, cmp))
                return false;

            property.firePropertyChange(null, null, Operation.Sort);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Stock<T> filter(Predicate<? super T> p) {
        if (p == null)
            throw new IllegalArgumentException();
        return new ListStock<>(stocks.stream().filter(p).toList());
    }
}
