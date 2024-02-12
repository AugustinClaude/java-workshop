package fr.epita.assistants.seq;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public interface Seq<ELEMENT_TYPE> extends ExtendedStream<ELEMENT_TYPE> {
    class SubSeq<ELEMENT_TYPE> implements Seq<ELEMENT_TYPE> {
        Stream<ELEMENT_TYPE> stream;

        public SubSeq(Stream<ELEMENT_TYPE> stream) {
            this.stream = stream;
        }

        @Override
        public <KEY_TYPE> Map<KEY_TYPE, ELEMENT_TYPE> toMap(Function<ELEMENT_TYPE, KEY_TYPE> keyMapper) {
            Map<KEY_TYPE, ELEMENT_TYPE> map = new HashMap<>();
            stream.forEach(e -> map.put(keyMapper.apply(e), e));
            return map;
        }

        @Override
        public <KEY_TYPE, VALUE_TYPE, MAP_TYPE extends Map<KEY_TYPE, VALUE_TYPE>> MAP_TYPE toMap(MAP_TYPE map, Function<ELEMENT_TYPE, KEY_TYPE> keyMapper, Function<ELEMENT_TYPE, VALUE_TYPE> valueMapper) {
            stream.forEach(e -> map.put(keyMapper.apply(e), valueMapper.apply(e)));
            return map;
        }

        @Override
        public <KEY_TYPE, VALUE_TYPE> Map<KEY_TYPE, VALUE_TYPE> toMap(Function<ELEMENT_TYPE, KEY_TYPE> keyMapper, Function<ELEMENT_TYPE, VALUE_TYPE> valueMapper) {
            Map<KEY_TYPE, VALUE_TYPE> map = new HashMap<>();
            stream.forEach(e -> map.put(keyMapper.apply(e), valueMapper.apply(e)));
            return map;
        }

        @Override
        public Stream<ELEMENT_TYPE> filter(Predicate<? super ELEMENT_TYPE> predicate) {
            return stream.filter(predicate);
        }

        @Override
        public <R> Stream<R> map(Function<? super ELEMENT_TYPE, ? extends R> mapper) {
            return stream.map(mapper);
        }

        @Override
        public IntStream mapToInt(ToIntFunction<? super ELEMENT_TYPE> mapper) {
            return stream.mapToInt(mapper);
        }

        @Override
        public LongStream mapToLong(ToLongFunction<? super ELEMENT_TYPE> mapper) {
            return stream.mapToLong(mapper);
        }

        @Override
        public DoubleStream mapToDouble(ToDoubleFunction<? super ELEMENT_TYPE> mapper) {
            return stream.mapToDouble(mapper);
        }

        @Override
        public <R> Stream<R> flatMap(Function<? super ELEMENT_TYPE, ? extends Stream<? extends R>> mapper) {
            return stream.flatMap(mapper);
        }

        @Override
        public IntStream flatMapToInt(Function<? super ELEMENT_TYPE, ? extends IntStream> mapper) {
            return stream.flatMapToInt(mapper);
        }

        @Override
        public LongStream flatMapToLong(Function<? super ELEMENT_TYPE, ? extends LongStream> mapper) {
            return stream.flatMapToLong(mapper);
        }

        @Override
        public DoubleStream flatMapToDouble(Function<? super ELEMENT_TYPE, ? extends DoubleStream> mapper) {
            return stream.flatMapToDouble(mapper);
        }

        @Override
        public Stream<ELEMENT_TYPE> distinct() {
            return stream.distinct();
        }

        @Override
        public Stream<ELEMENT_TYPE> sorted() {
            return stream.sorted();
        }

        @Override
        public Stream<ELEMENT_TYPE> sorted(Comparator<? super ELEMENT_TYPE> comparator) {
            return stream.sorted(comparator);
        }

        @Override
        public Stream<ELEMENT_TYPE> peek(Consumer<? super ELEMENT_TYPE> action) {
            return stream.peek(action);
        }

        @Override
        public Stream<ELEMENT_TYPE> limit(long maxSize) {
            return stream.limit(maxSize);
        }

        @Override
        public Stream<ELEMENT_TYPE> skip(long n) {
            return stream.skip(n);
        }

        @Override
        public void forEach(Consumer<? super ELEMENT_TYPE> action) {
            stream.forEach(action);
        }

        @Override
        public void forEachOrdered(Consumer<? super ELEMENT_TYPE> action) {
            stream.forEachOrdered(action);
        }

        @Override
        public Object[] toArray() {
            return stream.toArray();
        }

        @Override
        public <A> A[] toArray(IntFunction<A[]> generator) {
            return stream.toArray(generator);
        }

        @Override
        public ELEMENT_TYPE reduce(ELEMENT_TYPE identity, BinaryOperator<ELEMENT_TYPE> accumulator) {
            return stream.reduce(identity, accumulator);
        }

        @Override
        public Optional<ELEMENT_TYPE> reduce(BinaryOperator<ELEMENT_TYPE> accumulator) {
            return stream.reduce(accumulator);
        }

        @Override
        public <U> U reduce(U identity, BiFunction<U, ? super ELEMENT_TYPE, U> accumulator, BinaryOperator<U> combiner) {
            return stream.reduce(identity, accumulator, combiner);
        }

        @Override
        public <R> R collect(Supplier<R> supplier, BiConsumer<R, ? super ELEMENT_TYPE> accumulator, BiConsumer<R, R> combiner) {
            return stream.collect(supplier, accumulator, combiner);
        }

        @Override
        public <R, A> R collect(Collector<? super ELEMENT_TYPE, A, R> collector) {
            return stream.collect(collector);
        }

        @Override
        public List<ELEMENT_TYPE> toList() {
            return stream.toList();
        }

        @Override
        public Optional<ELEMENT_TYPE> min(Comparator<? super ELEMENT_TYPE> comparator) {
            return stream.min(comparator);
        }

        @Override
        public Optional<ELEMENT_TYPE> max(Comparator<? super ELEMENT_TYPE> comparator) {
            return stream.max(comparator);
        }

        @Override
        public long count() {
            return stream.count();
        }

        @Override
        public boolean anyMatch(Predicate<? super ELEMENT_TYPE> predicate) {
            return stream.anyMatch(predicate);
        }

        @Override
        public boolean allMatch(Predicate<? super ELEMENT_TYPE> predicate) {
            return stream.allMatch(predicate);
        }

        @Override
        public boolean noneMatch(Predicate<? super ELEMENT_TYPE> predicate) {
            return stream.noneMatch(predicate);
        }

        @Override
        public Optional<ELEMENT_TYPE> findFirst() {
            return stream.findFirst();
        }

        @Override
        public Optional<ELEMENT_TYPE> findAny() {
            return stream.findAny();
        }

        @Override
        public <LIST extends List<ELEMENT_TYPE>> LIST toList(LIST list) {
            List<ELEMENT_TYPE> streamList = stream.toList();
            list.addAll(streamList);
            return list;
        }

        @Override
        public Set<ELEMENT_TYPE> toSet() {
            List<ELEMENT_TYPE> streamList = stream.toList();
            return new HashSet<>(streamList);
        }

        @Override
        public <SET extends Set<ELEMENT_TYPE>> SET toSet(SET set) {
            List<ELEMENT_TYPE> streamList = stream.toList();
            set.addAll(streamList);
            return set;
        }

        @Override
        public <ASSOCIATED_TYPE> ExtendedStream<Pair<ELEMENT_TYPE, ASSOCIATED_TYPE>> associate(Supplier<ASSOCIATED_TYPE> supplier) {
            List<Pair<ELEMENT_TYPE, ASSOCIATED_TYPE>> newList = new ArrayList<>();
            stream.forEach(e -> newList.add(new Pair<>(e, supplier.get())));

            return new SubSeq<>(newList.stream());
        }

        @Override
        public <ASSOCIATED_TYPE> ExtendedStream<Pair<ELEMENT_TYPE, ASSOCIATED_TYPE>> associate(Stream<ASSOCIATED_TYPE> supplier) {
            List<Pair<ELEMENT_TYPE, ASSOCIATED_TYPE>> newList = new ArrayList<>();
            Iterator<ASSOCIATED_TYPE> iterator = supplier.iterator();
            stream.filter(e -> iterator.hasNext()).forEach(e -> newList.add(new Pair<>(e, iterator.next())));

            return new SubSeq<>(newList.stream());
        }

        @Override
        public ExtendedStream<ELEMENT_TYPE> print() {
            System.out.println(stream.toList());
            return this;
        }

        @Override
        public ExtendedStream<ELEMENT_TYPE> plus(Stream<ELEMENT_TYPE> stream) {
            List<ELEMENT_TYPE> streamList = stream.toList();
            List<ELEMENT_TYPE> thisStreamList = new ArrayList<>(this.stream.toList());
            thisStreamList.addAll(streamList);
            return new SubSeq<>(thisStreamList.stream());
        }

        @Override
        public String join(String delimiter) {
            final String[] res = {""};
            stream.forEach(e -> res[0] += e.toString() + delimiter);
            if (res[0].length() == 0)
                return res[0];
            return res[0].substring(0, res[0].length() - 1);
        }

        @Override
        public String join() {
            final String[] res = {""};
            stream.forEach(e -> res[0] += e.toString());
            return res[0];
        }

        @Override
        public <KEY_TYPE> ExtendedStream<Pair<KEY_TYPE, ExtendedStream<ELEMENT_TYPE>>> partition(Function<ELEMENT_TYPE, KEY_TYPE> pivot) {
            List<ELEMENT_TYPE> streamList = stream.toList();
            Map<KEY_TYPE, List<ELEMENT_TYPE>> map = new HashMap<>();
            for (ELEMENT_TYPE elementType : streamList) {
                KEY_TYPE key = pivot.apply(elementType);
                List<ELEMENT_TYPE> curList = map.get(key);
                if (curList == null) {
                    List<ELEMENT_TYPE> newList = new ArrayList<>();
                    newList.add(elementType);
                    map.put(key, newList);
                }
                else {
                    curList.add(elementType);
                    map.put(key, curList);
                }
            }

            List<Pair<KEY_TYPE, ExtendedStream<ELEMENT_TYPE>>> list = new ArrayList<>();
            for (Map.Entry<KEY_TYPE, List<ELEMENT_TYPE>> keyTypeListEntry : map.entrySet()) {
                SubSeq<ELEMENT_TYPE> seq = new SubSeq<>(keyTypeListEntry.getValue().stream());
                list.add(new Pair<>(keyTypeListEntry.getKey(), seq));
            }

            return new SubSeq<>(list.stream());
        }

        @Override
        public Iterator<ELEMENT_TYPE> iterator() {
            return stream.iterator();
        }

        @Override
        public Spliterator<ELEMENT_TYPE> spliterator() {
            return stream.spliterator();
        }

        @Override
        public boolean isParallel() {
            return stream.isParallel();
        }

        @Override
        public Stream<ELEMENT_TYPE> sequential() {
            return stream.sequential();
        }

        @Override
        public Stream<ELEMENT_TYPE> parallel() {
            return stream.parallel();
        }

        @Override
        public Stream<ELEMENT_TYPE> unordered() {
            return stream.unordered();
        }

        @Override
        public Stream<ELEMENT_TYPE> onClose(Runnable closeHandler) {
            return stream.onClose(closeHandler);
        }

        @Override
        public void close() {
            stream.close();
        }
    }

    static <TYPE> Seq<TYPE> of(final List<TYPE> values) {
        return new SubSeq<>(values.stream());
    }

    static <TYPE> Seq<TYPE> of(final TYPE... values) {
        return of(Arrays.stream(values).toList());
    }

    static <TYPE> Seq<TYPE> of(final Stream<TYPE> values) {
        return of(values.toList());
    }
}
