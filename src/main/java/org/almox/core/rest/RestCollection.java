package org.almox.core.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.util.Streamable;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RestCollection<T> implements List<T>, Page<T> {

    private Page<T> page;
    private List<T> list;

    private RestCollection() {
    }

    public RestCollection(List<T> list, Pageable pageable) {
        page = list.isEmpty()
                ? Page.empty()
                : new PageImpl<>(list, pageable, list.size());
        this.list = list;
    }

    public static <T> RestCollection<T> fromPage(Page<T> page) {
        return new RestCollection(page.getContent(), page.getPageable());
    }

    public <R> RestCollection<R> mapCollection(Function<? super T, R> converter) {
        List<R> newList = list.stream().map(converter).collect(Collectors.toList());
        Page<R> newPage = page.map(converter);

        RestCollection<R> newRestCollection = new RestCollection<>();
        newRestCollection.list = newList;
        newRestCollection.page = newPage;
        return newRestCollection;
    }

    @JsonValue
    public DTO<T> toDTO() {
        DTO<T> dto = new DTO<>();
        DTO.Meta meta = new DTO.Meta();

        dto.meta = meta;
        dto.data = list;
        meta.page = this.page;
        return dto;
    }

    public static class DTO<T> {
        public Meta<T> meta;
        public List<T> data;

        public static class Meta<T> {
            @JsonIgnoreProperties({"content", "pageable"})
            public Page<T> page;
        }
    }

    // @formatter:off
    @Override public int size() {return list.size();}
    @Override public boolean isEmpty() {return list.isEmpty();}
    @Override public Streamable<T> and(Supplier<? extends Stream<? extends T>> stream) {return page.and(stream);}
    @Override public Streamable<T> and(T... others) {return page.and(others);}
    @Override public Streamable<T> and(Iterable<? extends T> iterable) {return page.and(iterable);}
    @Override public Streamable<T> and(Streamable<? extends T> streamable) {return page.and(streamable);}
    @Override public List<T> toList() {return page.toList();}
    @Override public Set<T> toSet() {return page.toSet();}
    @Override public Stream<T> get() {return page.get();}
    @Override public boolean contains(Object o) {return list.contains(o);}
    @Override public Iterator<T> iterator() {return list.iterator();}
    @Override public void forEach(Consumer<? super T> action) {list.forEach(action);}
    @Override public Object[] toArray() {        return list.toArray();    }
    @Override public <T1> T1[] toArray(T1[] t1s) {return list.toArray(t1s);}
    @Override public <T1> T1[] toArray(IntFunction<T1[]> generator) {return list.toArray(generator);}
    @Override public boolean add(T t) {return list.add(t);}
    @Override public boolean remove(Object o) {return list.remove(o);}
    @Override public boolean containsAll(Collection<?> collection) {return list.containsAll(collection);}
    @Override public boolean addAll(Collection<? extends T> collection) {return list.addAll(collection);}
    @Override public boolean addAll(int i, Collection<? extends T> collection) {return list.addAll(i, collection);}
    @Override public boolean removeAll(Collection<?> collection) {return list.removeAll(collection);}
    @Override public boolean removeIf(Predicate<? super T> filter) {return list.removeIf(filter);}
    @Override public boolean retainAll(Collection<?> collection) {return list.retainAll(collection);}
    @Override public void replaceAll(UnaryOperator<T> operator) {list.replaceAll(operator);}
    @Override public void sort(Comparator<? super T> c) {list.sort(c);}
    @Override public void clear() {list.clear();}
    @Override public T get(int i) {return list.get(i);}
    @Override public T set(int i, T t) {return list.set(i, t);}
    @Override public void add(int i, T t) {list.add(i, t);}
    @Override public T remove(int i) {return list.remove(i);}
    @Override public int indexOf(Object o) {return list.indexOf(o);}
    @Override public int lastIndexOf(Object o) {return list.lastIndexOf(o);}
    @Override public ListIterator<T> listIterator() {return list.listIterator();}
    @Override public ListIterator<T> listIterator(int i) {return list.listIterator(i);}
    @Override public List<T> subList(int i, int i1) {return list.subList(i, i1);}
    @Override public Spliterator<T> spliterator() {return list.spliterator();}
    @Override public Stream<T> stream() {return list.stream();}
    @Override public Stream<T> parallelStream() {return list.parallelStream();}
    @Override public int getTotalPages() {return page.getTotalPages();}
    @Override public long getTotalElements() {return page.getTotalElements() == 0 ? list.size() : page.getTotalElements();}
    @Override public <U> Page<U> map(Function<? super T, ? extends U> converter) {return page.map(converter);}
    @Override public <R> Streamable<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper) {return page.flatMap(mapper);}
    @Override public Streamable<T> filter(Predicate<? super T> predicate) {return page.filter(predicate);}
    @Override public Pageable nextOrLastPageable() {return page.nextOrLastPageable();}
    @Override public Pageable previousOrFirstPageable() {return page.previousOrFirstPageable();}
    @Override public int getNumber() {return page.getNumber();}
    @Override public int getSize() { return page.getSize();}
    @Override public int getNumberOfElements() { return page.getNumberOfElements();}
    @Override public List<T> getContent(){ return page.getContent();}
    @Override public boolean hasContent(){ return page.hasContent();}
    @Override public Sort getSort(){ return page.getSort();}
    @Override public boolean isFirst(){ return page.isFirst();}
    @Override public boolean isLast() { return page.isLast();}
    @Override public boolean hasNext() { return page.hasNext();}
    @Override public boolean hasPrevious() { return page.hasPrevious();}
    @Override public Pageable getPageable() { return page.getPageable();}
    @Override public Pageable nextPageable() {return page.nextPageable();}
    @Override public Pageable previousPageable() {return page.previousPageable();}
    // @formatter:on
}
