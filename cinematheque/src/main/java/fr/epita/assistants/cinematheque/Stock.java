package fr.epita.assistants.cinematheque;

import java.beans.PropertyChangeSupport;
import java.util.Collection;
import java.util.Comparator;
import java.util.function.Predicate;

public abstract class Stock<T> {

    protected PropertyChangeSupport property = new PropertyChangeSupport(this);

    public enum Operation {
        Add,
        Delete,
        Sort
    }

    /**
     * Adds an element to the stock. Two equal elements must not be present in the stock.
     *
     * @param t the element to be inserted
     * @return true if the insertion succeeded, false otherwise
     */
    public abstract boolean add(T t);

    /**
     * Removes an element from the stock.
     *
     * @param t the element to be removed
     * @return true if the element was effectively removed, false otherwise
     */
    public abstract boolean remove(T t);

    /**
     * Tells if an element is present in the stock.
     *
     * @param t the element to be searched
     * @return true if the element is in the stock, false otherwise
     */
    public abstract boolean contains(T t);

    /**
     * Gets the underlying collection.
     *
     * @return the underlying collection
     */
    public abstract Collection<? extends T> list();

    /**
     * Sorts the stock in place.
     *
     * @param cmp the comparator used to sort
     * @return true if the sort succeeded and if the list was not already sorted, false otherwise
     */
    public abstract boolean sort(Comparator<? super T> cmp);


    /**
     * Creates a new stock containing only elements following a given predicate.
     *
     * @param p the predicate one element has to respect to be kept
     * @return the new filtered set
     * @throws IllegalArgumentException if the Predicate is null
     */
    public abstract Stock<T> filter(Predicate<? super T> p);
}
