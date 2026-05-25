/*
 * The MIT License (MIT)
 *
 * Copyright 2015-2026 Valentyn Kolesnikov <0009-0003-9608-3364@orcid.org>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.github.underscore;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

/**
 * Underscore-java is a java port of Underscore.js.
 *
 * @author Valentyn Kolesnikov
 */
@SuppressWarnings({ "java:S106", "java:S2119", "java:S2189", "java:S2272", "java:S2789", "java:S3740", "java:S5852" })
public class Underscore<T> {

    private static final Map<String, Function<String, String>> FUNCTIONS = new LinkedHashMap<>();

    private static final Map<String, String> TEMPLATE_SETTINGS = new HashMap<>();

    private static final int MIN_PASSWORD_LENGTH_8 = 8;

    private static final long CAPACITY_SIZE_5 = 5L;

    private static final long CAPACITY_COEFF_2 = 2L;

    private static final long CAPACITY_SIZE_16 = 16L;

    private static final java.util.concurrent.atomic.AtomicInteger UNIQUE_ID = new java.util.concurrent.atomic.AtomicInteger(0);

    private static final String ALL_SYMBOLS = "([\\s\\S]+?)";

    private static final String EVALUATE = "evaluate";

    private static final String INTERPOLATE = "interpolate";

    private static final String ESCAPE = "escape";

    private static final String S_Q = "\\s*\\Q";

    private static final String E_S = "\\E\\s*";

    private static final java.util.regex.Pattern FORMAT_PATTERN = java.util.regex.Pattern.compile("\\{\\s*(\\d*)\\s*\\}");

    private static final Map<Character, String> ESCAPES = new HashMap<>();

    private final Iterable<T> iterable;

    private final Optional<String> string;

    static {
        TEMPLATE_SETTINGS.put(EVALUATE, "<%([\\s\\S]+?)%>");
        TEMPLATE_SETTINGS.put(INTERPOLATE, "<%=([\\s\\S]+?)%>");
        TEMPLATE_SETTINGS.put(ESCAPE, "<%-([\\s\\S]+?)%>");
        ESCAPES.put('&', "&amp;");
        ESCAPES.put('<', "&lt;");
        ESCAPES.put('>', "&gt;");
        ESCAPES.put('"', "&quot;");
        ESCAPES.put('\'', "&#x27;");
        ESCAPES.put('`', "&#x60;");
    }

    public Underscore(final Iterable<T> iterable) {
        this.iterable = iterable;
        this.string = Optional.empty();
    }

    public Underscore(final String string) {
        this.iterable = null;
        this.string = Optional.of(string);
    }

    private static void setTemplateKey(final Map<String, String> templateSettings, final String key) {
        if (templateSettings.containsKey(key) && templateSettings.get(key).contains(ALL_SYMBOLS)) {
            TEMPLATE_SETTINGS.put(key, templateSettings.get(key));
        }
    }

    public static void templateSettings(final Map<String, String> templateSettings) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private static final class WherePredicate<E, T> implements Predicate<E> {

        private final List<Map.Entry<String, T>> properties;

        private WherePredicate(List<Map.Entry<String, T>> properties) {
            this.properties = properties;
        }

        @Override
        public boolean test(final E elem) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    private static final class TemplateImpl<K, V> implements Template<Map<K, V>> {

        private final String template;

        private TemplateImpl(String template) {
            this.template = template;
        }

        @Override
        public String apply(Map<K, V> value) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public List<String> check(Map<K, V> value) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    private static final class MyIterable<T> implements Iterable<T> {

        private final UnaryOperator<T> unaryOperator;

        private boolean firstRun = true;

        private T value;

        MyIterable(final T seed, final UnaryOperator<T> unaryOperator) {
            this.value = seed;
            this.unaryOperator = unaryOperator;
        }

        public Iterator<T> iterator() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    public static <K, V> Function<Map<K, V>, V> iteratee(final K key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /*
     * Documented, #each
     */
    public static <T> void each(final Iterable<T> iterable, final Consumer<? super T> func) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> void eachIndexed(final Iterable<T> iterable, final BiConsumer<Integer, ? super T> func) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void each(final Consumer<? super T> func) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> void eachRight(final Iterable<T> iterable, final Consumer<? super T> func) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void eachRight(final Consumer<? super T> func) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> void forEach(final Iterable<T> iterable, final Consumer<? super T> func) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> void forEachIndexed(final Iterable<T> iterable, final BiConsumer<Integer, ? super T> func) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void forEach(final Consumer<? super T> func) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void forEachIndexed(final BiConsumer<Integer, ? super T> func) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> void forEachRight(final Iterable<T> iterable, final Consumer<? super T> func) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void forEachRight(final Consumer<? super T> func) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /*
     * Documented, #map
     */
    public static <T, E> List<T> map(final List<E> list, final Function<? super E, T> func) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T, E> List<T> mapMulti(final List<E> list, final BiConsumer<? super E, ? super Consumer<T>> mapper) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public <F> List<F> map(final Function<? super T, F> func) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> List<T> map(final int[] array, final Function<? super Integer, T> func) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T, E> Set<T> map(final Set<E> set, final Function<? super E, T> func) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T, E> List<T> mapIndexed(final List<E> list, final BiFunction<Integer, ? super E, T> func) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> List<T> replace(final Iterable<T> iter, final Predicate<T> pred, final T value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public List<T> replace(final Predicate<T> pred, final T value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> List<T> replaceIndexed(final Iterable<T> iter, final PredicateIndexed<T> pred, final T value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public List<T> replaceIndexed(final PredicateIndexed<T> pred, final T value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public <F> List<F> mapIndexed(final BiFunction<Integer, ? super T, F> func) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T, E> List<T> collect(final List<E> list, final Function<? super E, T> func) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T, E> Set<T> collect(final Set<E> set, final Function<? super E, T> func) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /*
     * Documented, #reduce
     */
    public static <T, E> E reduce(final Iterable<T> iterable, final BiFunction<E, T, E> func, final E zeroElem) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> Optional<T> reduce(final Iterable<T> iterable, final BinaryOperator<T> func) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> E reduce(final int[] array, final BiFunction<E, ? super Integer, E> func, final E zeroElem) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T, E> E reduce(final T[] array, final BiFunction<E, T, E> func, final E zeroElem) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T, E> E foldl(final Iterable<T> iterable, final BiFunction<E, T, E> func, final E zeroElem) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T, E> E inject(final Iterable<T> iterable, final BiFunction<E, T, E> func, final E zeroElem) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /*
     * Documented, #reduceRight
     */
    public static <T, E> E reduceRight(final Iterable<T> iterable, final BiFunction<E, T, E> func, final E zeroElem) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> Optional<T> reduceRight(final Iterable<T> iterable, final BinaryOperator<T> func) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> E reduceRight(final int[] array, final BiFunction<E, ? super Integer, E> func, final E zeroElem) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T, E> E reduceRight(final T[] array, final BiFunction<E, T, E> func, final E zeroElem) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T, E> E foldr(final Iterable<T> iterable, final BiFunction<E, T, E> func, final E zeroElem) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /*
     * Documented, #find
     */
    public static <E> Optional<E> find(final Iterable<E> iterable, final Predicate<E> pred) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> Optional<E> detect(final Iterable<E> iterable, final Predicate<E> pred) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> Optional<E> findLast(final Iterable<E> iterable, final Predicate<E> pred) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /*
     * Documented, #filter
     */
    public static <E> List<E> filter(final Iterable<E> iterable, final Predicate<E> pred) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> List<E> filter(final List<E> list, final Predicate<E> pred) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public List<T> filter(final Predicate<T> pred) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> List<E> filterIndexed(final List<E> list, final PredicateIndexed<E> pred) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> Set<E> filter(final Set<E> set, final Predicate<E> pred) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> List<E> select(final List<E> list, final Predicate<E> pred) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> Set<E> select(final Set<E> set, final Predicate<E> pred) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /*
     * Documented, #reject
     */
    public static <E> List<E> reject(final List<E> list, final Predicate<E> pred) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public List<T> reject(final Predicate<T> pred) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> List<E> rejectIndexed(final List<E> list, final PredicateIndexed<E> pred) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> Set<E> reject(final Set<E> set, final Predicate<E> pred) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> List<E> filterFalse(final List<E> list, final Predicate<E> pred) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public List<T> filterFalse(final Predicate<T> pred) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> Set<E> filterFalse(final Set<E> set, final Predicate<E> pred) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> boolean every(final Iterable<E> iterable, final Predicate<E> pred) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean every(final Predicate<T> pred) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /*
     * Documented, #all
     */
    public static <E> boolean all(final Iterable<E> iterable, final Predicate<E> pred) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean all(final Predicate<T> pred) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> boolean some(final Iterable<E> iterable, final Predicate<E> pred) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean some(final Predicate<T> pred) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /*
     * Documented, #any
     */
    public static <E> boolean any(final Iterable<E> iterable, final Predicate<E> pred) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean any(final Predicate<T> pred) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> int count(final Iterable<E> iterable, final Predicate<E> pred) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public int count(final Predicate<T> pred) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> boolean contains(final Iterable<E> iterable, final E elem) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean contains(final T elem) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> boolean containsWith(final Iterable<E> iterable, final E elem) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean containsWith(final T elem) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> boolean contains(final Iterable<E> iterable, final E elem, final int fromIndex) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean containsAtLeast(final T value, final int count) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean containsAtMost(final T value, final int count) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> boolean containsAtLeast(final Iterable<E> iterable, final E value, final int count) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> boolean containsAtMost(final Iterable<E> iterable, final E value, final int count) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /*
     * Documented, #include
     */
    public static <E> boolean include(final Iterable<E> iterable, final E elem) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /*
     * Documented, #invoke
     */
    @SuppressWarnings("unchecked")
    public static <E> List<E> invoke(final Iterable<E> iterable, final String methodName, final List<Object> args) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    private static <E> void doInvoke(List<Object> args, List<E> result, Method method, E arg) {
        try {
            result.add((E) method.invoke(arg, args.toArray(new Object[0])));
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

    public List<T> invoke(final String methodName, final List<Object> args) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> List<E> invoke(final Iterable<E> iterable, final String methodName) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public List<T> invoke(final String methodName) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /*
     * Documented, #pluck
     */
    public static <E> List<Object> pluck(final List<E> list, final String propertyName) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public List<Object> pluck(final String propertyName) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> Set<Object> pluck(final Set<E> set, final String propertyName) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /*
     * Documented, #where
     */
    public static <T, E> List<E> where(final List<E> list, final List<Map.Entry<String, T>> properties) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public <E> List<T> where(final List<Map.Entry<String, E>> properties) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T, E> Set<E> where(final Set<E> set, final List<Map.Entry<String, T>> properties) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /*
     * Documented, #findWhere
     */
    public static <T, E> Optional<E> findWhere(final Iterable<E> iterable, final List<Map.Entry<String, T>> properties) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public <E> Optional<T> findWhere(final List<Map.Entry<String, E>> properties) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /*
     * Documented, #max
     */
    public static <E extends Comparable<? super E>> E max(final Collection<E> collection) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    public T max() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    public static <E, F extends Comparable> E max(final Collection<E> collection, final Function<E, F> func) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    public <F extends Comparable<? super F>> T max(final Function<T, F> func) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /*
     * Documented, #min
     */
    public static <E extends Comparable<? super E>> E min(final Collection<E> collection) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    public T min() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    public static <E, F extends Comparable> E min(final Collection<E> collection, final Function<E, F> func) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    public <F extends Comparable<? super F>> T min(final Function<T, F> func) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /*
     * Documented, #shuffle
     */
    public static <E> List<E> shuffle(final Iterable<E> iterable) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public List<T> shuffle() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /*
     * Documented, #sample
     */
    public static <E> E sample(final Iterable<E> iterable) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public T sample() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> Set<E> sample(final List<E> list, final int howMany) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T extends Comparable<? super T>> List<T> sortWith(final Iterable<T> iterable, final Comparator<T> comparator) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    public <E extends Comparable<? super E>> List<E> sortWith(final Comparator<E> comparator) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /*
     * Documented, #sortBy
     */
    public static <E, T extends Comparable<? super T>> List<E> sortBy(final Iterable<E> iterable, final Function<E, T> func) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    public <E, V extends Comparable<? super V>> List<E> sortBy(final Function<E, V> func) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <K, V extends Comparable<? super V>> List<Map<K, V>> sortBy(final Iterable<Map<K, V>> iterable, final K key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /*
     * Documented, #groupBy
     */
    public static <K, E> Map<K, List<E>> groupBy(final Iterable<E> iterable, final Function<E, K> func) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    public <K, E> Map<K, List<E>> groupBy(final Function<E, K> func) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <K, E> Map<K, Optional<E>> groupBy(final Iterable<E> iterable, final Function<E, K> func, final BinaryOperator<E> binaryOperator) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    public <K, E> Map<K, Optional<E>> groupBy(final Function<E, K> func, final BinaryOperator<E> binaryOperator) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <K, E> Map<K, E> associateBy(final Iterable<E> iterable, final Function<E, K> func) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    public <K, E> Map<K, E> associateBy(final Function<E, K> func) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    public static <K, E> Map<K, List<E>> indexBy(final Iterable<E> iterable, final String property) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    public <K, E> Map<K, List<E>> indexBy(final String property) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /*
     * Documented, #countBy
     */
    public static <K, E> Map<K, Integer> countBy(final Iterable<E> iterable, Function<E, K> func) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <K> Map<K, Integer> countBy(final Iterable<K> iterable) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    public <K, E> Map<K, Integer> countBy(Function<E, K> func) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    public <K> Map<K, Integer> countBy() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /*
     * Documented, #toArray
     */
    @SuppressWarnings("unchecked")
    public static <E> E[] toArray(final Iterable<E> iterable) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    public <E> E[] toArray() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /*
     * Documented, #toMap
     */
    public static <K, V> Map<K, V> toMap(final Iterable<Map.Entry<K, V>> iterable) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    public <K, V> Map<K, V> toMap() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <K, V> Map<K, V> toMap(final List<Map.Entry<K, V>> tuples) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public Map<T, Integer> toCardinalityMap() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <K> Map<K, Integer> toCardinalityMap(final Iterable<K> iterable) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /*
     * Documented, #size
     */
    public static int size(final Iterable<?> iterable) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public int size() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    public static <E> int size(final E... array) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> List<List<E>> partition(final Iterable<E> iterable, final Predicate<E> pred) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    public static <E> List<E>[] partition(final E[] iterable, final Predicate<E> pred) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public T singleOrNull() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public T singleOrNull(Predicate<T> pred) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> E singleOrNull(final Iterable<E> iterable) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> E singleOrNull(final Iterable<E> iterable, Predicate<E> pred) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /*
     * Documented, #first
     */
    public static <E> E first(final Iterable<E> iterable) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    public static <E> E first(final E... array) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> List<E> first(final List<E> list, final int n) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public T first() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public List<T> first(final int n) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> E first(final Iterable<E> iterable, final Predicate<E> pred) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> List<E> first(final Iterable<E> iterable, final Predicate<E> pred, final int n) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public T first(final Predicate<T> pred) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public List<T> first(final Predicate<T> pred, final int n) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> E firstOrNull(final Iterable<E> iterable) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public T firstOrNull() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> E firstOrNull(final Iterable<E> iterable, final Predicate<E> pred) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public T firstOrNull(final Predicate<T> pred) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> E head(final Iterable<E> iterable) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    public static <E> E head(final E... array) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> List<E> head(final List<E> list, final int n) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public T head() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public List<T> head(final int n) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /*
     * Documented, #initial
     */
    public static <E> List<E> initial(final List<E> list) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> List<E> initial(final List<E> list, final int n) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    public static <E> E[] initial(final E... array) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> E[] initial(final E[] array, final int n) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public List<T> initial() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public List<T> initial(final int n) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    public static <E> E last(final E... array) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /*
     * Documented, #last
     */
    public static <E> E last(final List<E> list) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> List<E> last(final List<E> list, final int n) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public T last() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public List<T> last(final int n) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> E last(final List<E> list, final Predicate<E> pred) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public T last(final Predicate<T> pred) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> E lastOrNull(final List<E> list) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public T lastOrNull() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> E lastOrNull(final List<E> list, final Predicate<E> pred) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public T lastOrNull(final Predicate<T> pred) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /*
     * Documented, #rest
     */
    public static <E> List<E> rest(final List<E> list) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> List<E> rest(final List<E> list, int n) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    public static <E> E[] rest(final E... array) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    public static <E> E[] rest(final E[] array, final int n) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public List<T> rest() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public List<T> rest(int n) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> List<E> tail(final List<E> list) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> List<E> tail(final List<E> list, final int n) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    public static <E> E[] tail(final E... array) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> E[] tail(final E[] array, final int n) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public List<T> tail() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public List<T> tail(final int n) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> List<E> drop(final List<E> list) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> List<E> drop(final List<E> list, final int n) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    public static <E> E[] drop(final E... array) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> E[] drop(final E[] array, final int n) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /*
     * Documented, #compact
     */
    public static <E> List<E> compact(final List<E> list) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    public static <E> E[] compact(final E... array) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> List<E> compactList(final List<E> list, final E falsyValue) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    public static <E> E[] compact(final E[] array, final E falsyValue) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public List<T> compact() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public List<T> compact(final T falsyValue) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /*
     * Documented, #flatten
     */
    public static <E> List<E> flatten(final List<?> list) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> List<E> flatten(final List<?> list, final boolean shallow) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    private static <E> void flatten(final List<?> fromTreeList, final List<E> toFlatList, final int shallowLevel) {
        for (Object item : fromTreeList) {
            if (item instanceof List<?> && shallowLevel != 0) {
                flatten((List<?>) item, toFlatList, shallowLevel - 1);
            } else {
                toFlatList.add((E) item);
            }
        }
    }

    public List<T> flatten() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public List<T> flatten(final boolean shallow) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /*
     * Documented, #without
     */
    @SuppressWarnings("unchecked")
    public static <E> List<E> without(final List<E> list, E... values) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    public static <E> E[] without(final E[] array, final E... values) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /*
     * Documented, #uniq
     */
    public static <E> List<E> uniq(final List<E> list) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    public static <E> E[] uniq(final E... array) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <K, E> Collection<E> uniq(final Iterable<E> iterable, final Function<E, K> func) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    public static <K, E> E[] uniq(final E[] array, final Function<E, K> func) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> List<E> distinct(final List<E> list) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    public static <E> E[] distinct(final E... array) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <K, E> Collection<E> distinctBy(final Iterable<E> iterable, final Function<E, K> func) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <K, E> E[] distinctBy(final E[] array, final Function<E, K> func) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /*
     * Documented, #union
     */
    @SuppressWarnings("unchecked")
    public static <E> List<E> union(final List<E> list, final List<E>... lists) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    public List<T> unionWith(final List<T>... lists) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    public static <E> E[] union(final E[]... arrays) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /*
     * Documented, #intersection
     */
    public static <E> List<E> intersection(final List<E> list1, final List<E> list2) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    public static <E> List<E> intersection(final List<E> list, final List<E>... lists) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    public List<T> intersectionWith(final List<T>... lists) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    public static <E> E[] intersection(final E[]... arrays) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /*
     * Documented, #difference
     */
    public static <E> List<E> difference(final List<E> list1, final List<E> list2) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    public static <E> List<E> difference(final List<E> list, final List<E>... lists) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    public List<T> differenceWith(final List<T>... lists) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    public static <E> E[] difference(final E[]... arrays) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /*
     * Documented, #zip
     */
    @SuppressWarnings("unchecked")
    public static <T> List<List<T>> zip(final List<T>... lists) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    public static <T> List<List<T>> unzip(final List<T>... lists) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /*
     * Documented, #object
     */
    public static <K, V> List<Map.Entry<K, V>> object(final List<K> keys, final List<V> values) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> int findIndex(final List<E> list, final Predicate<E> pred) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> int findIndex(final E[] array, final Predicate<E> pred) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> int findLastIndex(final List<E> list, final Predicate<E> pred) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> int findLastIndex(final E[] array, final Predicate<E> pred) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E extends Comparable<E>> int binarySearch(final Iterable<E> iterable, final E key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E extends Comparable<E>> int binarySearch(final E[] array, final E key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /*
     * Documented, #sortedIndex
     */
    public static <E extends Comparable<E>> int sortedIndex(final List<E> list, final E value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E extends Comparable<E>> int sortedIndex(final E[] array, final E value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    public static <E extends Comparable<E>> int sortedIndex(final List<E> list, final E value, final String propertyName) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E extends Comparable<E>> int sortedIndex(final E[] array, final E value, final String propertyName) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /*
     * Documented, #indexOf
     */
    public static <E> int indexOf(final List<E> list, final E value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> int indexOf(final E[] array, final E value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /*
     * Documented, #lastIndexOf
     */
    public static <E> int lastIndexOf(final List<E> list, final E value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> int lastIndexOf(final E[] array, final E value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /*
     * Documented, #range
     */
    public static List<Integer> range(int stop) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static List<Integer> range(int start, int stop) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static List<Integer> range(int start, int stop, int step) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static List<Character> range(char stop) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static List<Character> range(char start, char stop) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static List<Character> range(char start, char stop, int step) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> List<List<T>> chunk(final Iterable<T> iterable, final int size) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> List<List<T>> chunk(final Iterable<T> iterable, final int size, final int step) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> List<List<T>> chunkFill(final Iterable<T> iterable, final int size, final T fillValue) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> List<List<T>> chunkFill(final Iterable<T> iterable, final int size, final int step, final T fillValue) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public List<List<T>> chunk(final int size) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public List<List<T>> chunk(final int size, final int step) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public List<List<T>> chunkFill(final int size, final T fillvalue) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public List<List<T>> chunkFill(final int size, final int step, T fillvalue) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> List<T> cycle(final Iterable<T> iterable, final int times) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public List<T> cycle(final int times) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> List<T> repeat(final T element, final int times) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> List<T> interpose(final Iterable<T> iterable, final T interElement) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> List<T> interposeByList(final Iterable<T> iterable, final Iterable<T> interIter) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public List<T> interpose(final T element) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public List<T> interposeByList(final Iterable<T> interIter) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /*
     * Documented, #bind
     */
    public static <T, F> Function<F, T> bind(final Function<F, T> function) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /*
     * Documented, #memoize
     */
    public static <T, F> Function<F, T> memoize(final Function<F, T> function) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /*
     * Documented, #delay
     */
    public static <T> java.util.concurrent.ScheduledFuture<T> delay(final Supplier<T> function, final int delayMilliseconds) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> java.util.concurrent.ScheduledFuture<T> defer(final Supplier<T> function) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static java.util.concurrent.ScheduledFuture<Void> defer(final Runnable runnable) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> Supplier<T> throttle(final Supplier<T> function, final int waitMilliseconds) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /*
     * Documented, #debounce
     */
    public static <T> Supplier<T> debounce(final Supplier<T> function, final int delayMilliseconds) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /*
     * Documented, #wrap
     */
    public static <T> Function<Void, T> wrap(final UnaryOperator<T> function, final Function<UnaryOperator<T>, T> wrapper) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> Predicate<E> negate(final Predicate<E> pred) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /*
     * Documented, #compose
     */
    @SuppressWarnings("unchecked")
    public static <T> Function<T, T> compose(final Function<T, T>... func) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /*
     * Documented, #after
     */
    public static <E> Supplier<E> after(final int count, final Supplier<E> function) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /*
     * Documented, #before
     */
    public static <E> Supplier<E> before(final int count, final Supplier<E> function) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /*
     * Documented, #once
     */
    public static <T> Supplier<T> once(final Supplier<T> function) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /*
     * Documented, #keys
     */
    public static <K, V> Set<K> keys(final Map<K, V> object) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /*
     * Documented, #values
     */
    public static <K, V> Collection<V> values(final Map<K, V> object) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <K, V> List<Map.Entry<K, V>> mapObject(final Map<K, V> object, final Function<? super V, V> func) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /*
     * Documented, #pairs
     */
    public static <K, V> List<Map.Entry<K, V>> pairs(final Map<K, V> object) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /*
     * Documented, #invert
     */
    public static <K, V> List<Map.Entry<V, K>> invert(final Map<K, V> object) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /*
     * Documented, #functions
     */
    public static List<String> functions(final Object object) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static List<String> methods(final Object object) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /*
     * Documented, #extend
     */
    @SuppressWarnings("unchecked")
    public static <K, V> Map<K, V> extend(final Map<K, V> destination, final Map<K, V>... sources) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> E findKey(final List<E> list, final Predicate<E> pred) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> E findKey(final E[] array, final Predicate<E> pred) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> E findLastKey(final List<E> list, final Predicate<E> pred) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> E findLastKey(final E[] array, final Predicate<E> pred) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /*
     * Documented, #pick
     */
    @SuppressWarnings("unchecked")
    public static <K, V> List<Map.Entry<K, V>> pick(final Map<K, V> object, final K... keys) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    public static <K, V> List<Map.Entry<K, V>> pick(final Map<K, V> object, final Predicate<V> pred) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /*
     * Documented, #omit
     */
    @SuppressWarnings("unchecked")
    public static <K, V> List<Map.Entry<K, V>> omit(final Map<K, V> object, final K... keys) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    public static <K, V> List<Map.Entry<K, V>> omit(final Map<K, V> object, final Predicate<V> pred) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /*
     * Documented, #defaults
     */
    public static <K, V> Map<K, V> defaults(final Map<K, V> object, final Map<K, V> defaults) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /*
     * Documented, #clone
     */
    public static Object clone(final Object obj) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    public static <E> E[] clone(final E... iterable) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> void tap(final Iterable<T> iterable, final Consumer<? super T> func) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <K, V> boolean isMatch(final Map<K, V> object, final Map<K, V> properties) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /*
     * Documented, #isEqual
     */
    public static boolean isEqual(final Object object, final Object other) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <K, V> boolean isEmpty(final Map<K, V> object) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /*
     * Documented, #isEmpty
     */
    public static <T> boolean isEmpty(final Iterable<T> iterable) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean isEmpty() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <K, V> boolean isNotEmpty(final Map<K, V> object) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> boolean isNotEmpty(final Iterable<T> iterable) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean isNotEmpty() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /*
     * Documented, #isArray
     */
    public static boolean isArray(final Object object) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /*
     * Documented, #isObject
     */
    public static boolean isObject(final Object object) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /*
     * Documented, #isFunction
     */
    public static boolean isFunction(final Object object) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /*
     * Documented, #isString
     */
    public static boolean isString(final Object object) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /*
     * Documented, #isNumber
     */
    public static boolean isNumber(final Object object) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /*
     * Documented, #isDate
     */
    public static boolean isDate(final Object object) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static boolean isRegExp(final Object object) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static boolean isError(final Object object) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /*
     * Documented, #isBoolean
     */
    public static boolean isBoolean(final Object object) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static boolean isNull(final Object object) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /*
     * Documented, #has
     */
    public static <K, V> boolean has(final Map<K, V> object, final K key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> E identity(final E value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> Supplier<E> constant(final E value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <K, V> Function<Map<K, V>, V> property(final K key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <K, V> Function<K, V> propertyOf(final Map<K, V> object) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <K, V> Predicate<Map<K, V>> matcher(final Map<K, V> object) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /*
     * Documented, #times
     */
    public static void times(final int count, final Runnable runnable) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /*
     * Documented, #random
     */
    public static int random(final int min, final int max) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static int random(final int max) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static long now() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /*
     * Documented, #escape
     */
    public static String escape(final String value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static String unescape(final String value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /*
     * Documented, #result
     */
    public static <E> Object result(final Iterable<E> iterable, final Predicate<E> pred) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /*
     * Documented, #uniqueId
     */
    public static String uniqueId(final String prefix) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /*
     * Documented, #uniquePassword
     */
    public static String uniquePassword() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <K, V> Template<Map<K, V>> template(final String template) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static String format(final String template, final Object... params) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> Iterable<T> iterate(final T seed, final UnaryOperator<T> unaryOperator) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /*
     * Documented, #chain
     */
    public static <T> Chain<T> chain(final List<T> list) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static Chain<Map<String, Object>> chain(final Map<String, Object> map) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> Chain<T> chain(final Iterable<T> iterable) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> Chain<T> chain(final Iterable<T> iterable, int size) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    public static <T> Chain<T> chain(final T... array) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static Chain<Integer> chain(final int[] array) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public Chain<T> chain() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> Chain<T> of(final List<T> list) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> Chain<T> of(final Iterable<T> iterable) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> Chain<T> of(final Iterable<T> iterable, int size) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    public static <T> Chain<T> of(final T... array) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static Chain<Integer> of(final int[] array) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public Chain<T> of() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static class Chain<T> {

        private final T item;

        private final List<T> list;

        private final Map<String, Object> map;

        public Chain(final T item) {
            this.item = item;
            this.list = null;
            this.map = null;
        }

        public Chain(final List<T> list) {
            this.item = null;
            this.list = list;
            this.map = null;
        }

        public Chain(final Map<String, Object> map) {
            this.item = null;
            this.list = null;
            this.map = map;
        }

        public Chain<T> first() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Chain<T> first(int n) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Chain<T> first(final Predicate<T> pred) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Chain<T> first(final Predicate<T> pred, int n) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Chain<T> firstOrNull() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Chain<T> firstOrNull(final Predicate<T> pred) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Chain<T> initial() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Chain<T> initial(int n) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Chain<T> last() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Chain<T> last(int n) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Chain<T> lastOrNull() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Chain<T> lastOrNull(final Predicate<T> pred) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Chain<T> rest() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Chain<T> rest(int n) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Chain<T> compact() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Chain<T> compact(final T falsyValue) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @SuppressWarnings("unchecked")
        public Chain flatten() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public <F> Chain<F> map(final Function<? super T, F> func) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public <F> Chain<F> mapMulti(final BiConsumer<? super T, ? super Consumer<F>> mapper) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public <F> Chain<F> mapIndexed(final BiFunction<Integer, ? super T, F> func) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Chain<T> replace(final Predicate<T> pred, final T value) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Chain<T> replaceIndexed(final PredicateIndexed<T> pred, final T value) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Chain<T> filter(final Predicate<T> pred) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Chain<T> filterIndexed(final PredicateIndexed<T> pred) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Chain<T> reject(final Predicate<T> pred) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Chain<T> rejectIndexed(final PredicateIndexed<T> pred) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Chain<T> filterFalse(final Predicate<T> pred) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public <F> Chain<F> reduce(final BiFunction<F, T, F> func, final F zeroElem) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Chain<Optional<T>> reduce(final BinaryOperator<T> func) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public <F> Chain<F> reduceRight(final BiFunction<F, T, F> func, final F zeroElem) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Chain<Optional<T>> reduceRight(final BinaryOperator<T> func) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Chain<Optional<T>> find(final Predicate<T> pred) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Chain<Optional<T>> findLast(final Predicate<T> pred) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @SuppressWarnings("unchecked")
        public Chain<Comparable> max() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public <F extends Comparable<? super F>> Chain<T> max(final Function<T, F> func) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @SuppressWarnings("unchecked")
        public Chain<Comparable> min() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public <F extends Comparable<? super F>> Chain<T> min(final Function<T, F> func) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @SuppressWarnings("unchecked")
        public Chain<Comparable> sort() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @SuppressWarnings("unchecked")
        public <F extends Comparable<? super F>> Chain<F> sortWith(final Comparator<F> comparator) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public <F extends Comparable<? super F>> Chain<T> sortBy(final Function<T, F> func) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @SuppressWarnings("unchecked")
        public <K> Chain<Map<K, Comparable>> sortBy(final K key) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public <F> Chain<Map<F, List<T>>> groupBy(final Function<T, F> func) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public <F> Chain<Map<F, T>> associateBy(final Function<T, F> func) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public <F> Chain<Map<F, Optional<T>>> groupBy(final Function<T, F> func, final BinaryOperator<T> binaryOperator) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Chain<Map<Object, List<T>>> indexBy(final String property) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public <F> Chain<Map<F, Integer>> countBy(final Function<T, F> func) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Chain<Map<T, Integer>> countBy() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Chain<T> shuffle() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Chain<T> sample() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Chain<T> sample(final int howMany) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Chain<T> tap(final Consumer<T> func) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Chain<T> forEach(final Consumer<T> func) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Chain<T> forEachRight(final Consumer<T> func) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Chain<Boolean> every(final Predicate<T> pred) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Chain<Boolean> some(final Predicate<T> pred) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Chain<Integer> count(final Predicate<T> pred) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Chain<Boolean> contains(final T elem) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Chain<Boolean> containsWith(final T elem) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Chain<T> invoke(final String methodName, final List<Object> args) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Chain<T> invoke(final String methodName) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Chain<Object> pluck(final String propertyName) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public <E> Chain<T> where(final List<Map.Entry<String, E>> properties) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public <E> Chain<Optional<T>> findWhere(final List<Map.Entry<String, E>> properties) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Chain<T> uniq() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public <F> Chain<T> uniq(final Function<T, F> func) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Chain<T> distinct() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @SuppressWarnings("unchecked")
        public <F> Chain<F> distinctBy(final Function<T, F> func) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @SuppressWarnings("unchecked")
        public Chain<T> union(final List<T>... lists) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @SuppressWarnings("unchecked")
        public Chain<T> intersection(final List<T>... lists) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @SuppressWarnings("unchecked")
        public Chain<T> difference(final List<T>... lists) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Chain<Integer> range(final int stop) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Chain<Integer> range(final int start, final int stop) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Chain<Integer> range(final int start, final int stop, final int step) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Chain<List<T>> chunk(final int size) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Chain<List<T>> chunk(final int size, final int step) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Chain<List<T>> chunkFill(final int size, final T fillValue) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Chain<List<T>> chunkFill(final int size, final int step, final T fillValue) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Chain<T> cycle(final int times) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Chain<T> interpose(final T element) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Chain<T> interposeByList(final Iterable<T> interIter) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @SuppressWarnings("unchecked")
        public Chain<T> concat(final List<T>... lists) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Chain<T> slice(final int start) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Chain<T> slice(final int start, final int end) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Chain<List<T>> splitAt(final int position) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Chain<T> takeSkipping(final int stepSize) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Chain<T> reverse() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Chain<String> join() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Chain<String> join(final String separator) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @SuppressWarnings("unchecked")
        public Chain<T> push(final T... values) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Chain<Map.Entry<T, List<T>>> pop() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Chain<Map.Entry<T, List<T>>> shift() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @SuppressWarnings("unchecked")
        public Chain<T> unshift(final T... values) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Chain<T> skip(final int numberToSkip) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Chain<T> limit(final int size) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @SuppressWarnings("unchecked")
        public <K, V> Chain<Map<K, V>> toMap() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public boolean isEmpty() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public boolean isNotEmpty() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public int size() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public T item() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /*
         * Documented, #value
         */
        public List<T> value() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Map<String, Object> map() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public List<T> toList() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public String toString() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /*
     * Documented, #mixin
     */
    public static void mixin(final String funcName, final UnaryOperator<String> func) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public Optional<String> call(final String funcName) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T extends Comparable<T>> List<T> sort(final Iterable<T> iterable) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    public static <T extends Comparable<T>> T[] sort(final T... array) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    public List<Comparable> sort() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /*
     * Documented, #join
     */
    public static <T> String join(final Iterable<T> iterable, final String separator) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> String joinToString(final Iterable<T> iterable, final String separator, final String prefix, final String postfix, final int limit, final String truncated, final Function<T, String> transform) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private static void joinToStringPostfix(String postfix, int limit, String truncated, int index, StringBuilder sb) {
        if (limit >= 0 && index > limit) {
            sb.append(truncated == null ? "..." : truncated);
        }
        if (postfix != null) {
            sb.append(postfix);
        }
    }

    public static <T> String join(final Iterable<T> iterable) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> String join(final T[] array, final String separator) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> String join(final T[] array) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public String join(final String separator) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public String join() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    public static <T> List<T> push(final List<T> list, final T... values) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    public List<T> push(final T... values) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> Map.Entry<T, List<T>> pop(final List<T> list) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public Map.Entry<T, List<T>> pop() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    public static <T> List<T> unshift(final List<T> list, final T... values) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    public List<T> unshift(final T... values) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> Map.Entry<T, List<T>> shift(final List<T> list) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public Map.Entry<T, List<T>> shift() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    public static <T> T[] concat(final T[] first, final T[]... other) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /*
     * Documented, #concat
     */
    @SuppressWarnings("unchecked")
    public static <T> List<T> concat(final Iterable<T> first, final Iterable<T>... other) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    public List<T> concatWith(final Iterable<T>... other) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /*
     * Documented, #slice
     */
    public static <T> List<T> slice(final Iterable<T> iterable, final int start) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> T[] slice(final T[] array, final int start) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public List<T> slice(final int start) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> List<T> slice(final Iterable<T> iterable, final int start, final int end) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> T[] slice(final T[] array, final int start, final int end) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public List<T> slice(final int start, final int end) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> List<List<T>> splitAt(final Iterable<T> iterable, final int position) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> List<List<T>> splitAt(final T[] array, final int position) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public List<List<T>> splitAt(final int position) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> List<T> takeSkipping(final Iterable<T> iterable, final int stepSize) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> List<T> takeSkipping(final T[] array, final int stepSize) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public List<T> takeSkipping(final int stepSize) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /*
     * Documented, #reverse
     */
    public static <T> List<T> reverse(final Iterable<T> iterable) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    public static <T> T[] reverse(final T... array) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static List<Integer> reverse(final int[] array) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public List<T> reverse() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public Iterable<T> getIterable() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public Iterable<T> value() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public Optional<String> getString() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> java.util.concurrent.ScheduledFuture<T> setTimeout(final Supplier<T> function, final int delayMilliseconds) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static void clearTimeout(java.util.concurrent.ScheduledFuture<?> scheduledFuture) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> java.util.concurrent.ScheduledFuture setInterval(final Supplier<T> function, final int delayMilliseconds) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static void clearInterval(java.util.concurrent.ScheduledFuture scheduledFuture) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> List<T> copyOf(final Iterable<T> iterable) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public List<T> copyOf() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> List<T> copyOfRange(final Iterable<T> iterable, final int start, final int end) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public List<T> copyOfRange(final int start, final int end) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> T elementAt(final List<T> list, final int index) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public T elementAt(final int index) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> T get(final List<T> list, final int index) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public T get(final int index) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> Map.Entry<T, List<T>> set(final List<T> list, final int index, final T value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public Map.Entry<T, List<T>> set(final int index, final T value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> T elementAtOrElse(final List<T> list, final int index, T defaultValue) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public T elementAtOrElse(final int index, T defaultValue) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> T elementAtOrNull(final List<T> list, final int index) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public T elementAtOrNull(final int index) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> int lastIndex(final Iterable<T> iterable) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> int lastIndex(final T[] array) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static int lastIndex(final int[] array) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> T checkNotNull(T reference) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> List<T> checkNotNullElements(List<T> references) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> T checkNotNull(T reference, Object errorMessage) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static boolean nonNull(Object obj) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> T defaultTo(T value, T defaultValue) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected static <T> List<T> newArrayList(final Iterable<T> iterable) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected static <T> List<T> newArrayList(final T object) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected static <T> List<T> newArrayList(final Iterable<T> iterable, final int size) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected static List<Integer> newIntegerList(int... array) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected static <T> List<T> newArrayListWithExpectedSize(int size) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected static <T> Set<T> newLinkedHashSet(Iterable<T> iterable) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected static <T> Set<T> newLinkedHashSetWithExpectedSize(int size) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    public static <T> Predicate<T> and(final Predicate<? super T> pred1, final Predicate<? super T> pred2, final Predicate<? super T>... rest) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    public static <T> Predicate<T> or(final Predicate<? super T> pred1, final Predicate<? super T> pred2, final Predicate<? super T>... rest) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static void main(String... args) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public interface Function3<F1, F2, F3, T> {

        T apply(F1 arg1, F2 arg2, F3 arg3);
    }

    public abstract static class MemoizeFunction<F, T> implements Function<F, T> {

        private final Map<F, T> cache = new LinkedHashMap<>();

        public abstract T calc(final F n);

        public T apply(final F key) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    public interface PredicateIndexed<T> {

        boolean test(int index, T arg);
    }

    public interface Template<T> extends Function<T, String> {

        List<String> check(T arg);
    }
}
