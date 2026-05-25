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

import org.w3c.dom.NodeList;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.zip.GZIPInputStream;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

@SuppressWarnings({ "java:S135", "java:S1168", "java:S3655", "java:S3740", "java:S3776", "java:S4423", "java:S4830", "java:S5843", "java:S5996", "java:S5998" })
public class U<T> extends Underscore<T> {

    private static final int DEFAULT_TRUNC_LENGTH = 30;

    private static final String DEFAULT_TRUNC_OMISSION = "...";

    private static final java.util.regex.Pattern RE_LATIN_1 = java.util.regex.Pattern.compile("[\\xc0-\\xd6\\xd8-\\xde\\xdf-\\xf6\\xf8-\\xff]");

    private static final java.util.regex.Pattern RE_PROP_NAME = java.util.regex.Pattern.compile("[^.\\[\\]]+|\\[(?:(-?\\d+(?:\\.\\d+)?)|([\"'])((?:(?!\2)\\[^\\]|\\.)*?)\2)\\]|" + "(?=(\\.|\\[\\])(?:\4|$))");

    private static final Map<String, String> DEBURRED_LETTERS = new LinkedHashMap<>();

    private static final Map<String, List<String>> DEFAULT_HEADER_FIELDS = new HashMap<>();

    private static final Set<String> SUPPORTED_HTTP_METHODS = new HashSet<>(Arrays.asList("GET", "POST", "PUT", "DELETE"));

    private static final int BUFFER_LENGTH_1024 = 1024;

    private static final int RESPONSE_CODE_400 = 400;

    private static final String ROOT = "root";

    private static final String UPPER = "[A-Z\\xc0-\\xd6\\xd8-\\xde\\u0400-\\u04FF]";

    private static final String LOWER = "[a-z\\xdf-\\xf6\\xf8-\\xff]+";

    private static final String SELF_CLOSING = "-self-closing";

    private static final String NIL_KEY = "-nil";

    private static final String OMIT_XML_DECL = "#omit-xml-declaration";

    private static final String YES = "yes";

    private static final java.util.regex.Pattern RE_WORDS = java.util.regex.Pattern.compile(UPPER + "+(?=" + UPPER + LOWER + ")|" + UPPER + "?" + LOWER + "|" + UPPER + "+|\\d+");

    private static final String ENCODING = "#encoding";

    static {
        String[] deburredLetters = new String[] { "\u00c0", "A", "\u00c1", "A", "\u00c2", "A", "\u00c3", "A", "\u00c4", "A", "\u00c5", "A", "\u00e0", "a", "\u00e1", "a", "\u00e2", "a", "\u00e3", "a", "\u00e4", "a", "\u00e5", "a", "\u00c7", "C", "\u00e7", "c", "\u00d0", "D", "\u00f0", "d", "\u00c8", "E", "\u00c9", "E", "\u00ca", "E", "\u00cb", "E", "\u00e8", "e", "\u00e9", "e", "\u00ea", "e", "\u00eb", "e", "\u00cC", "I", "\u00cd", "I", "\u00ce", "I", "\u00cf", "I", "\u00eC", "i", "\u00ed", "i", "\u00ee", "i", "\u00ef", "i", "\u00d1", "N", "\u00f1", "n", "\u00d2", "O", "\u00d3", "O", "\u00d4", "O", "\u00d5", "O", "\u00d6", "O", "\u00d8", "O", "\u00f2", "o", "\u00f3", "o", "\u00f4", "o", "\u00f5", "o", "\u00f6", "o", "\u00f8", "o", "\u00d9", "U", "\u00da", "U", "\u00db", "U", "\u00dc", "U", "\u00f9", "u", "\u00fa", "u", "\u00fb", "u", "\u00fc", "u", "\u00dd", "Y", "\u00fd", "y", "\u00ff", "y", "\u00c6", "Ae", "\u00e6", "ae", "\u00de", "Th", "\u00fe", "th", "\u00df", "ss" };
        for (int index = 0; index < deburredLetters.length; index += 2) {
            DEBURRED_LETTERS.put(deburredLetters[index], deburredLetters[index + 1]);
        }
        DEFAULT_HEADER_FIELDS.put("Content-Type", Arrays.asList("application/json", "charset=utf-8"));
    }

    public enum XmlToJsonMode {

        REPLACE_SELF_CLOSING_WITH_NULL,
        REPLACE_SELF_CLOSING_WITH_STRING,
        REPLACE_EMPTY_VALUE_WITH_NULL,
        REPLACE_EMPTY_TAG_WITH_NULL,
        REPLACE_EMPTY_TAG_WITH_STRING,
        REMOVE_FIRST_LEVEL,
        WITHOUT_NAMESPACES,
        REPLACE_MINUS_WITH_AT,
        REPLACE_EMPTY_TAG_WITH_NULL_AND_MINUS_WITH_AT
    }

    public enum JsonToXmlMode {

        FORCE_ATTRIBUTE_USAGE,
        DEFINE_ROOT_NAME,
        REPLACE_NULL_WITH_EMPTY_VALUE,
        REPLACE_EMPTY_STRING_WITH_EMPTY_VALUE,
        ADD_ROOT,
        REMOVE_ARRAY_ATTRIBUTE,
        REMOVE_ATTRIBUTES
    }

    public U(final Iterable<T> iterable) {
        super(iterable);
    }

    public U(final String string) {
        super(string);
    }

    public static class Chain<T> extends Underscore.Chain<T> {

        public Chain(final T item) {
            super(item);
        }

        public Chain(final List<T> list) {
            super(list);
        }

        public Chain(final Map<String, Object> map) {
            super(map);
        }

        @Override
        public Chain<T> first() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public Chain<T> first(int n) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public Chain<T> firstOrNull() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public Chain<T> firstOrNull(final Predicate<T> pred) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public Chain<T> initial() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public Chain<T> initial(int n) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public Chain<T> last() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public Chain<T> last(int n) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public Chain<T> lastOrNull() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public Chain<T> lastOrNull(final Predicate<T> pred) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public Chain<T> rest() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public Chain<T> rest(int n) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public Chain<T> compact() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public Chain<T> compact(final T falsyValue) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public Chain flatten() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public <F> Chain<F> map(final Function<? super T, F> func) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public <F> Chain<F> mapMulti(final BiConsumer<? super T, ? super Consumer<F>> mapper) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public <F> Chain<F> mapIndexed(final BiFunction<Integer, ? super T, F> func) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public Chain<T> filter(final Predicate<T> pred) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public Chain<T> filterIndexed(final PredicateIndexed<T> pred) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public Chain<T> rejectIndexed(final PredicateIndexed<T> pred) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public Chain<T> reject(final Predicate<T> pred) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public Chain<T> filterFalse(final Predicate<T> pred) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public <F> Chain<F> reduce(final BiFunction<F, T, F> func, final F zeroElem) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public Chain<Optional<T>> reduce(final BinaryOperator<T> func) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public <F> Chain<F> reduceRight(final BiFunction<F, T, F> func, final F zeroElem) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public Chain<Optional<T>> reduceRight(final BinaryOperator<T> func) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public Chain<Optional<T>> find(final Predicate<T> pred) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public Chain<Optional<T>> findLast(final Predicate<T> pred) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        @SuppressWarnings("unchecked")
        public Chain<Comparable> max() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public <F extends Comparable<? super F>> Chain<T> max(final Function<T, F> func) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        @SuppressWarnings("unchecked")
        public Chain<Comparable> min() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public <F extends Comparable<? super F>> Chain<T> min(final Function<T, F> func) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        @SuppressWarnings("unchecked")
        public Chain<Comparable> sort() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        @SuppressWarnings("unchecked")
        public <F extends Comparable<? super F>> Chain<F> sortWith(final Comparator<F> comparator) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public <F extends Comparable<? super F>> Chain<T> sortBy(final Function<T, F> func) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        @SuppressWarnings("unchecked")
        public <K> Chain<Map<K, Comparable>> sortBy(final K key) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public <F> Chain<Map<F, List<T>>> groupBy(final Function<T, F> func) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public <F> Chain<Map<F, T>> associateBy(final Function<T, F> func) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public <F> Chain<Map<F, Optional<T>>> groupBy(final Function<T, F> func, final BinaryOperator<T> binaryOperator) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public Chain<Map<Object, List<T>>> indexBy(final String property) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public <F> Chain<Map<F, Integer>> countBy(final Function<T, F> func) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public Chain<Map<T, Integer>> countBy() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public Chain<T> shuffle() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public Chain<T> sample() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public Chain<T> sample(final int howMany) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public Chain<T> tap(final Consumer<T> func) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public Chain<T> forEach(final Consumer<T> func) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public Chain<T> forEachRight(final Consumer<T> func) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public Chain<Boolean> every(final Predicate<T> pred) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public Chain<Boolean> some(final Predicate<T> pred) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public Chain<Integer> count(final Predicate<T> pred) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public Chain<Boolean> contains(final T elem) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public Chain<Boolean> containsWith(final T elem) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public Chain<T> invoke(final String methodName, final List<Object> args) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public Chain<T> invoke(final String methodName) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public Chain<Object> pluck(final String propertyName) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public <E> Chain<T> where(final List<Map.Entry<String, E>> properties) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public <E> Chain<Optional<T>> findWhere(final List<Map.Entry<String, E>> properties) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public Chain<T> uniq() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public <F> Chain<T> uniq(final Function<T, F> func) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public Chain<T> distinct() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        @SuppressWarnings("unchecked")
        public <F> Chain<F> distinctBy(final Function<T, F> func) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        @SuppressWarnings("unchecked")
        public Chain<T> union(final List<T>... lists) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        @SuppressWarnings("unchecked")
        public Chain<T> intersection(final List<T>... lists) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        @SuppressWarnings("unchecked")
        public Chain<T> difference(final List<T>... lists) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public Chain<Integer> range(final int stop) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public Chain<Integer> range(final int start, final int stop) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public Chain<Integer> range(final int start, final int stop, final int step) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public Chain<List<T>> chunk(final int size) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public Chain<List<T>> chunk(final int size, final int step) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public Chain<List<T>> chunkFill(final int size, final T fillValue) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public Chain<List<T>> chunkFill(final int size, final int step, final T fillValue) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public Chain<T> cycle(final int times) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public Chain<T> interpose(final T element) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public Chain<T> interposeByList(final Iterable<T> interIter) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        @SuppressWarnings("unchecked")
        public Chain<T> concat(final List<T>... lists) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public Chain<T> slice(final int start) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public Chain<T> slice(final int start, final int end) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Chain<Map<String, Object>> set(final String path, Object value) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Chain<Map<String, Object>> set(final List<String> paths, Object value) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public Chain<T> reverse() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public Chain<String> join() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public Chain<String> join(final String separator) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public Chain<T> skip(final int numberToSkip) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public Chain<T> limit(final int size) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        @SuppressWarnings("unchecked")
        public <K, V> Chain<Map<K, V>> toMap() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Chain<T> drop() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Chain<T> drop(final Integer n) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Chain<T> dropRight() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Chain<T> dropRight(final Integer n) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Chain<T> dropWhile(final Predicate<T> pred) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Chain<T> dropRightWhile(final Predicate<T> pred) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @SuppressWarnings("unchecked")
        public Chain<Object> fill(final Object value) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @SuppressWarnings("unchecked")
        public Chain<Object> fill(final Object value, final Integer start, final Integer end) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Chain<Object> flattenDeep() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @SuppressWarnings("unchecked")
        public Chain<Object> pull(final Object... values) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @SuppressWarnings("unchecked")
        public Chain<Object> pullAt(final Integer... indexes) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Chain<T> remove(final Predicate<T> pred) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Chain<T> take() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Chain<T> takeRight() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Chain<T> take(final Integer n) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Chain<T> takeRight(final Integer n) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Chain<T> takeWhile(final Predicate<T> pred) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Chain<T> takeRightWhile(final Predicate<T> pred) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @SuppressWarnings("unchecked")
        public Chain<T> xor(final List<T> list) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Chain<T> at(final Integer... indexes) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @SuppressWarnings("unchecked")
        public <F extends Number> Chain<F> sum() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public <F extends Number> Chain<F> sum(final Function<T, F> func) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @SuppressWarnings("unchecked")
        public Chain<Double> mean() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @SuppressWarnings("unchecked")
        public Chain<Double> median() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Chain<String> camelCase() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Chain<String> lowerFirst() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Chain<String> upperFirst() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Chain<String> capitalize() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Chain<String> deburr() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Chain<Boolean> endsWith(final String target) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Chain<Boolean> endsWith(final String target, final Integer position) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Chain<String> kebabCase() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Chain<String> repeat(final int length) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Chain<String> pad(final int length) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Chain<String> pad(final int length, final String chars) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Chain<String> padStart(final int length) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Chain<String> padStart(final int length, final String chars) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Chain<String> padEnd(final int length) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Chain<String> padEnd(final int length, final String chars) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Chain<String> snakeCase() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Chain<String> startCase() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Chain<Boolean> startsWith(final String target) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Chain<Boolean> startsWith(final String target, final Integer position) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Chain<String> trim() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Chain<String> trim(final String chars) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Chain<String> trimStart() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Chain<String> trimStart(final String chars) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Chain<String> trimEnd() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Chain<String> trunc() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Chain<String> trunc(final int length) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Chain<String> trimEnd(final String chars) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Chain<String> uncapitalize() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Chain<String> words() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Chain<String> toJson() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Chain<Object> fromJson() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Chain<String> toXml() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Chain<Object> fromXml() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Chain<String> fetch() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Chain<String> fetch(final String method, final String body) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Chain<List<T>> createPermutationWithRepetition(final int permutationLength) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Chain<String> xmlToJson() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Chain<String> jsonToXml() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    public static Chain<String> chain(final String item) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

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
    public static <T> Chain<T> chain(final T... list) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static Chain<Integer> chain(final int[] array) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Chain<T> chain() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static Chain<String> of(final String item) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> Chain<T> of(final List<T> list) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static Chain<Map<String, Object>> of(final Map<String, Object> map) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> Chain<T> of(final Iterable<T> iterable) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> Chain<T> of(final Iterable<T> iterable, int size) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    public static <T> Chain<T> of(final T... list) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static Chain<Integer> of(final int[] array) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Chain<T> of() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> List<T> drop(final Iterable<T> iterable) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public List<T> drop() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> List<T> drop(final Iterable<T> iterable, final Integer n) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public List<T> drop(final Integer n) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> List<T> dropRight(final Iterable<T> iterable) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public List<T> dropRight() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> List<T> dropRight(final Iterable<T> iterable, final Integer n) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public List<T> dropRight(final Integer n) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> List<T> dropWhile(final Iterable<T> iterable, final Predicate<T> pred) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public List<T> dropWhile(final Predicate<T> pred) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> List<T> dropRightWhile(final Iterable<T> iterable, final Predicate<T> pred) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public List<T> dropRightWhile(final Predicate<T> pred) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> List<T> fill(List<T> list, T item) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> T[] fill(T[] array, T item) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    public List<Object> fill(Object value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static List<Object> fill(final List<Object> list, Object value, Integer start, Integer end) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    public List<Object> fill(Object value, Integer start, Integer end) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> List<E> flattenDeep(final List<?> list) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public List<T> flattenDeep() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static List<Object> pull(final List<Object> list, Object... values) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    public List<Object> pull(Object... values) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static List<Object> pullAt(final List<Object> list, final Integer... indexes) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    public List<Object> pullAt(final Integer... indexes) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> List<T> remove(final List<T> list, final Predicate<T> pred) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public List<T> remove(final Predicate<T> pred) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> List<T> take(final Iterable<T> iterable) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public List<T> take() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> List<T> takeRight(final Iterable<T> iterable) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public List<T> takeRight() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> List<T> take(final Iterable<T> iterable, final Integer n) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public List<T> take(final Integer n) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> List<T> takeRight(final Iterable<T> iterable, final Integer n) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public List<T> takeRight(final Integer n) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> List<T> takeWhile(final Iterable<T> iterable, final Predicate<T> pred) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public List<T> takeWhile(final Predicate<T> pred) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> List<T> takeRightWhile(final Iterable<T> iterable, final Predicate<T> pred) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public List<T> takeRightWhile(final Predicate<T> pred) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    public static <T> List<T> xor(final List<T>... lists) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    public List<T> xor(final List<T> list) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> List<T> at(final List<T> list, final Integer... indexes) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public List<T> at(final Integer... indexes) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T extends Number> Double average(final Iterable<T> iterable) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E, F extends Number> Double average(final Iterable<E> iterable, final Function<E, F> func) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <N extends Number> Double average(N[] array) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static Double average(java.math.BigDecimal first, java.math.BigDecimal second) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static Double average(java.math.BigInteger first, java.math.BigInteger second) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static Double average(Byte first, Byte second) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static Double average(Double first, Double second) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static Double average(Float first, Float second) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static Double average(Integer first, Integer second) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static Double average(Long first, Long second) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T extends Number> T sum(final Iterable<T> iterable) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E, F extends Number> F sum(final Iterable<E> iterable, final Function<E, F> func) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <N extends Number> N sum(N[] array) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    public <F extends Number> F sum() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    public <E, F extends Number> F sum(final Function<E, F> func) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    public static <T extends Number> T add(final T first, final T second) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private static java.math.BigDecimal sum(java.math.BigDecimal first, java.math.BigDecimal second) {
        return first.add(second);
    }

    private static java.math.BigInteger sum(java.math.BigInteger first, java.math.BigInteger second) {
        return first.add(second);
    }

    private static Byte sum(Byte first, Byte second) {
        return (byte) (first + second);
    }

    private static Double sum(Double first, Double second) {
        return first + second;
    }

    private static Float sum(Float first, Float second) {
        return first + second;
    }

    private static Integer sum(Integer first, Integer second) {
        return first + second;
    }

    private static Long sum(Long first, Long second) {
        return first + second;
    }

    private static Short sum(Short first, Short second) {
        return (short) (first + second);
    }

    @SuppressWarnings("unchecked")
    public static <T extends Number> T subtract(final T... values) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T extends Number> double mean(final Iterable<T> iterable) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    public double mean() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    public static <T extends Number> double median(final Iterable<T> iterable) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    public double median() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static String camelCase(final String string) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static String lowerFirst(final String string) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static String upperFirst(final String string) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static String capitalize(final String string) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static String uncapitalize(final String string) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private static String baseToString(String value) {
        return value == null ? "" : value;
    }

    public static String deburr(final String string) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static List<String> words(final String string) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private static Function<String, String> createCompounder(final Function3<String, String, Integer, String> callback) {
        return string -> {
            int index = -1;
            List<String> array = words(deburr(string));
            int length = array.size();
            String result = "";
            while (++index < length) {
                result = callback.apply(result, array.get(index), index);
            }
            return result;
        };
    }

    private static Function<String, String> createCaseFirst(final String methodName) {
        return string -> {
            final String localString = baseToString(string);
            final String chr = localString.isEmpty() ? "" : localString.substring(0, 1);
            final String trailing = localString.length() > 1 ? localString.substring(1) : "";
            return Underscore.invoke(Collections.singletonList(chr), methodName).get(0) + trailing;
        };
    }

    public static boolean endsWith(final String string, final String target) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static boolean endsWith(final String string, final String target, final Integer position) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static String kebabCase(final String string) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static String repeat(final String string, final int length) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private static String createPadding(final String string, final int length, final String chars) {
        final int strLength = string.length();
        final int padLength = length - strLength;
        final String localChars = chars == null ? " " : chars;
        return repeat(localChars, (int) Math.ceil(padLength / (double) localChars.length())).substring(0, padLength);
    }

    public static String pad(final String string, final int length) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static String pad(final String string, final int length, final String chars) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private static Function3<String, Integer, String, String> createPadDir(final boolean fromRight) {
        return (string, length, chars) -> {
            final String localString = baseToString(string);
            return (fromRight ? localString : "") + createPadding(localString, length, chars) + (fromRight ? "" : localString);
        };
    }

    public static String padStart(final String string, final Integer length) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static String padStart(final String string, final Integer length, final String chars) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static String padEnd(final String string, final Integer length) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static String padEnd(final String string, final Integer length, final String chars) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static String snakeCase(final String string) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static String startCase(final String string) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static boolean startsWith(final String string, final String target) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static boolean startsWith(final String string, final String target, final Integer position) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private static int charsLeftIndex(final String string, final String chars) {
        int index = 0;
        final int length = string.length();
        while (index < length && chars.indexOf(string.charAt(index)) > -1) {
            index += 1;
        }
        return index == length ? -1 : index;
    }

    private static int charsRightIndex(final String string, final String chars) {
        int index = string.length() - 1;
        while (index >= 0 && chars.indexOf(string.charAt(index)) > -1) {
            index -= 1;
        }
        return index;
    }

    public static String trim(final String string) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static String trim(final String string, final String chars) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static String trimStart(final String string) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static String trimStart(final String string, final String chars) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static String trimEnd(final String string) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static String trimEnd(final String string, final String chars) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static String trunc(final String string) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static String trunc(final String string, final Integer length) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static List<String> stringToPath(final String string) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private enum OperationType {

        GET, SET, UPDATE, REMOVE
    }

    @SuppressWarnings("unchecked")
    private static <T> T baseGetOrSetOrRemove(final Map<String, Object> object, final List<String> paths, final Object value, OperationType operationType) {
        int index = 0;
        final int length = paths.size();
        Object localObject = object;
        Object savedLocalObject = null;
        String savedPath = null;
        while (localObject != null && index < length) {
            if (localObject instanceof Map) {
                Map.Entry mapEntry = getMapEntry((Map) localObject);
                if (mapEntry != null && "#item".equals(mapEntry.getKey())) {
                    localObject = mapEntry.getValue();
                    continue;
                }
                savedLocalObject = localObject;
                savedPath = paths.get(index);
                localObject = ((Map) localObject).get(paths.get(index));
            } else if (localObject instanceof List) {
                savedLocalObject = localObject;
                savedPath = paths.get(index);
                localObject = ((List) localObject).get(Integer.parseInt(paths.get(index)));
            } else {
                break;
            }
            index += 1;
        }
        if (index > 0 && index == length) {
            checkSetAndRemove(value, operationType, savedLocalObject, savedPath);
            return (T) localObject;
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    private static void checkSetAndRemove(Object value, OperationType operationType, Object savedLocalObject, String savedPath) {
        if (operationType == OperationType.SET || operationType == OperationType.UPDATE) {
            if (savedLocalObject instanceof Map) {
                checkSetOrUpdate(value, operationType, (Map<String, Object>) savedLocalObject, savedPath);
            } else {
                ((List) savedLocalObject).set(Integer.parseInt(savedPath), value);
            }
        } else if (operationType == OperationType.REMOVE) {
            if (savedLocalObject instanceof Map) {
                ((Map) savedLocalObject).remove(savedPath);
            } else {
                ((List) savedLocalObject).remove(Integer.parseInt(savedPath));
            }
        }
    }

    private static void checkSetOrUpdate(Object value, OperationType operationType, Map<String, Object> savedLocalObject, String savedPath) {
        if (operationType == OperationType.UPDATE && savedLocalObject.containsKey(savedPath)) {
            savedLocalObject.put(Underscore.uniqueId(savedPath), value);
        } else {
            savedLocalObject.put(savedPath, value);
        }
    }

    private static Map.Entry getMapEntry(Map map) {
        return map.isEmpty() ? null : (Map.Entry) map.entrySet().iterator().next();
    }

    public static <T> T get(final Map<String, Object> object, final String path) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> T get(final Map<String, Object> object, final List<String> paths) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static String selectToken(final Map<String, Object> object, final String expression) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static List<String> selectTokens(final Map<String, Object> object, final String expression) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> T set(final Map<String, Object> object, final String path, Object value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> T set(final Map<String, Object> object, final List<String> paths, Object value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> T update(final Map<String, Object> object, final String path, Object value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> T update(final Map<String, Object> object, final List<String> paths, Object value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> T remove(final Map<String, Object> object, final String path) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> T remove(final Map<String, Object> object, final List<String> paths) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static Map<String, Object> rename(final Map<String, Object> map, final String oldKey, final String newKey) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    private static Object makeObjectForRename(Object value, final String oldKey, final String newKey) {
        final Object result;
        if (value instanceof List) {
            List<Object> values = new ArrayList<>();
            for (Object item : (List) value) {
                values.add(item instanceof Map ? rename((Map<String, Object>) item, oldKey, newKey) : item);
            }
            result = values;
        } else if (value instanceof Map) {
            result = rename((Map<String, Object>) value, oldKey, newKey);
        } else {
            result = value;
        }
        return result;
    }

    public static Map<String, Object> setValue(final Map<String, Object> map, final String key, final Object newValue) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static Map<String, Object> setValue(final Map<String, Object> map, final String key, final BiFunction<String, Object, Object> newValue) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    private static Object makeObjectForSetValue(Object value, final String key, final BiFunction<String, Object, Object> newValue) {
        final Object result;
        if (value instanceof List) {
            List<Object> values = new ArrayList<>();
            for (Object item : (List) value) {
                values.add(item instanceof Map ? setValue((Map<String, Object>) item, key, newValue) : item);
            }
            result = values;
        } else if (value instanceof Map) {
            result = setValue((Map<String, Object>) value, key, newValue);
        } else {
            result = value;
        }
        return result;
    }

    public static Map<String, Object> update(final Map<String, Object> map1, final Map<String, Object> map2) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    private static void createKey(final Map<String, Object> map, String key, Object value2, Map<String, Object> outMap) {
        Object value1 = map.get(key);
        if (value1 instanceof Map && value2 instanceof Map) {
            outMap.put(key, update((Map<String, Object>) value1, (Map<String, Object>) value2));
        } else if (value1 instanceof List && value2 instanceof List) {
            outMap.put(key, merge((List<Object>) value1, (List<Object>) value2));
        } else if (value1 instanceof List) {
            outMap.put(key, merge((List<Object>) value1, newArrayList(value2)));
        } else if (value2 instanceof List) {
            outMap.put(key, merge(newArrayList(value1), (List<Object>) value2));
        } else {
            outMap.put(key, value2);
        }
    }

    public static List<Object> merge(List<Object> list1, List<Object> list2) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static class FetchResponse {

        private final boolean ok;

        private final int status;

        private final Map<String, List<String>> headerFields;

        private final java.io.ByteArrayOutputStream stream;

        public FetchResponse(final boolean ok, final int status, final Map<String, List<String>> headerFields, final java.io.ByteArrayOutputStream stream) {
            this.ok = ok;
            this.status = status;
            this.stream = stream;
            this.headerFields = headerFields;
        }

        public boolean isOk() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public int getStatus() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Map<String, List<String>> getHeaderFields() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public byte[] blob() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public String text() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Object json() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Map<String, Object> jsonMap() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Object xml() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Map<String, Object> xmlMap() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    public static long downloadUrl(final String url, final String fileName) throws IOException, URISyntaxException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static void decompressGzip(final String sourceFileName, final String targetFileName) throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static FetchResponse fetch(final String url) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static FetchResponse fetch(final String url, final Integer connectTimeout, final Integer readTimeout) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static FetchResponse fetch(final String url, final Integer connectTimeout, final Integer readTimeout, final Integer retryCount, final Integer timeBetweenRetry) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static FetchResponse fetch(final String url, final String method, final String body) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static class BaseHttpSslSocketFactory extends javax.net.ssl.SSLSocketFactory {

        private javax.net.ssl.SSLContext getSslContext() {
            return createEasySslContext();
        }

        @Override
        public java.net.Socket createSocket(java.net.InetAddress arg0, int arg1, java.net.InetAddress arg2, int arg3) throws java.io.IOException {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public java.net.Socket createSocket(String arg0, int arg1, java.net.InetAddress arg2, int arg3) throws java.io.IOException {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public java.net.Socket createSocket(java.net.InetAddress arg0, int arg1) throws java.io.IOException {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public java.net.Socket createSocket(String arg0, int arg1) throws java.io.IOException {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public String[] getSupportedCipherSuites() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public String[] getDefaultCipherSuites() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public java.net.Socket createSocket(java.net.Socket arg0, String arg1, int arg2, boolean arg3) throws java.io.IOException {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        private javax.net.ssl.SSLContext createEasySslContext() {
            try {
                javax.net.ssl.SSLContext context = javax.net.ssl.SSLContext.getInstance("SSL");
                context.init(null, new javax.net.ssl.TrustManager[] { MyX509TrustManager.manger }, null);
                return context;
            } catch (Exception ex) {
                throw new UnsupportedOperationException(ex);
            }
        }

        public static class MyX509TrustManager implements javax.net.ssl.X509TrustManager {

            static MyX509TrustManager manger = new MyX509TrustManager();

            public MyX509TrustManager() {
                // ignore MyX509TrustManager
            }

            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                throw new UnsupportedOperationException("STUB: not implemented");
            }

            public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                throw new UnsupportedOperationException("STUB: not implemented");
            }

            public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                throw new UnsupportedOperationException("STUB: not implemented");
            }
        }
    }

    public static void setupConnection(final java.net.HttpURLConnection connection, final String method, final Map<String, List<String>> headerFields, final Integer connectTimeout, final Integer readTimeout) throws java.io.IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static FetchResponse fetch(final String url, final String method, final String body, final Map<String, List<String>> headerFields, final Integer connectTimeout, final Integer readTimeout) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static int readWithRetry(java.io.InputStream inputStream, byte[] buffer) throws java.io.IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static class Fetch {

        private Fetch() {
        }

        @SuppressWarnings("java:S107")
        public static FetchResponse fetch(final String url, final String method, final String body, final Map<String, List<String>> headerFields, final Integer connectTimeout, final Integer readTimeout, final Integer retryCount, final Integer timeBetweenRetry) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    public static List<String> explode(final String input) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static String implode(final String[] input) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static String implode(final Iterable<String> input) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public String camelCase() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public String lowerFirst() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public String upperFirst() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public String capitalize() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public String deburr() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean endsWith(final String target) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean endsWith(final String target, final Integer position) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public String kebabCase() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public String repeat(final int length) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public String pad(final int length) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public String pad(final int length, final String chars) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public String padStart(final int length) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public String padStart(final int length, final String chars) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public String padEnd(final int length) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public String padEnd(final int length, final String chars) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public String snakeCase() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public String startCase() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean startsWith(final String target) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean startsWith(final String target, final Integer position) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public String trim() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public String trimWith(final String chars) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public String trimStart() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public String trimStartWith(final String chars) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public String trimEnd() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public String trimEndWith(final String chars) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public String trunc() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public String trunc(final int length) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public String uncapitalize() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public List<String> words() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static class LruCache<K, V> {

        private static final boolean SORT_BY_ACCESS = true;

        private static final float LOAD_FACTOR = 0.75F;

        private final Map<K, V> lruCacheMap;

        private final int capacity;

        public LruCache(int capacity) {
            this.capacity = capacity;
            this.lruCacheMap = new LinkedHashMap<>(capacity, LOAD_FACTOR, SORT_BY_ACCESS);
        }

        public V get(K key) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public void put(K key, V value) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    public static <K, V> LruCache<K, V> createLruCache(final int capacity) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> List<List<T>> createPermutationWithRepetition(final List<T> list, final int permutationLength) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public List<List<T>> createPermutationWithRepetition(final int permutationLength) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected static <T> List<T> newArrayList(final Iterable<T> iterable) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> String join(final Iterable<T> iterable, final String separator) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> String joinToString(final Iterable<T> iterable, final String separator, final String prefix, final String postfix, final int limit, final String truncated, final Function<T, String> transform) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static String toJson(Collection collection) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static String toJson(Map map) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public String toJson() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    public static <T> T fromXml(final String xml) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static Map<String, Object> fromXmlMap(final String xml) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static Map<String, Object> fromXmlMap(final String xml, final Xml.FromType fromType) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    public static <T> T fromXml(final String xml, final Xml.FromType fromType) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    public static <T> T fromXmlMakeArrays(final String xml) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    public static <T> T fromXmlWithoutNamespaces(final String xml) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static Map<String, Object> fromXmlWithoutNamespacesMap(final String xml) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    public static <T> T fromXmlWithoutAttributes(final String xml) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    public static <T> T fromXmlWithoutNamespacesAndAttributes(final String xml) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static String toXml(Collection collection) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static String toXml(Map map) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    public static <T> T fromJson(String string) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public Object fromJson() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static Map<String, Object> fromJsonMap(final String string) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static Map<String, Object> fromJsonMap(final String string, final int maxDepth) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    private static Map<String, Object> getStringObjectMap(Object object) {
        final Map<String, Object> result;
        if (object instanceof Map) {
            result = (Map<String, Object>) object;
        } else {
            result = new LinkedHashMap<>();
            result.put("value", object);
        }
        return result;
    }

    public String toXml() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public Object fromXml() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    public static String jsonToXml(String json, Xml.XmlStringBuilder.Step identStep, JsonToXmlMode mode, String newRootName) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static String jsonToXml(String json, Xml.XmlStringBuilder.Step identStep) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static String jsonToXml(String json, JsonToXmlMode mode) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static String jsonToXml(String json, JsonToXmlMode mode, String newRootName) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static String jsonToXml(String json, String newRootName) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static String jsonToXml(String json) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    public static String jsonToXmlMinimum(String json, Xml.XmlStringBuilder.Step identStep) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static String jsonToXmlMinimum(String json) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    public static String xmlToJson(String xml, Json.JsonStringBuilder.Step identStep, XmlToJsonMode mode) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static String xmlToJson(String xml) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    public static String xmlToJsonMinimum(String xml, Json.JsonStringBuilder.Step identStep) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static String xmlToJsonMinimum(String xml) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static String xmlToJson(String xml, Json.JsonStringBuilder.Step identStep) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static String xmlToJson(String xml, XmlToJsonMode mode) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static void fileXmlToJson(String xmlFileName, String jsonFileName, Json.JsonStringBuilder.Step identStep) throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static void fileXmlToJson(String xmlFileName, String jsonFileName) throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static void streamXmlToJson(InputStream xmlInputStream, OutputStream jsonOutputStream, Json.JsonStringBuilder.Step indentStep) throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static void streamXmlToJson(InputStream xmlInputStream, OutputStream jsonOutputStream) throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static void fileJsonToXml(String jsonFileName, String xmlFileName, Xml.XmlStringBuilder.Step identStep) throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static void fileJsonToXml(String jsonFileName, String xmlFileName) throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static void jsonFolderToXml(String jsonFolder, String xmlFolder, Xml.XmlStringBuilder.Step identStep) throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static void jsonFolderToXml(String jsonFolder, String xmlFolder) throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static void covertJsonToXml(Path path, Path sourceRoot, Path targetRoot, Xml.XmlStringBuilder.Step identStep) throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static void xmlFolderToJson(String xmlFolder, String jsonFolder, Json.JsonStringBuilder.Step identStep) throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static void xmlFolderToJson(String xmlFolder, String jsonFolder) throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static void covertXmlToJson(Path path, Path sourceRoot, Path targetRoot, Json.JsonStringBuilder.Step identStep) throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static void streamJsonToXml(InputStream jsonInputStream, OutputStream xmlOutputStream, Xml.XmlStringBuilder.Step identStep) throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static void streamJsonToXml(InputStream jsonInputStream, OutputStream xmlOutputStream) throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static byte[] removeBom(byte[] bytes) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static String detectEncoding(byte[] buffer) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static String formatString(String data, String lineSeparator) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static String xmlOrJsonToJson(String xmlOrJson, Json.JsonStringBuilder.Step identStep) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static String xmlOrJsonToJson(String xmlOrJson) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static String mergeXmlsOrJsonsToJson(List<String> xmlsOrJsons, Json.JsonStringBuilder.Step identStep) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static String mergeXmlsOrJsonsToJson(List<String> xmlsOrJsons) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static String mergeXmlsOrJsonsToXml(List<String> xmlsOrJsons, Xml.XmlStringBuilder.Step identStep) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static String mergeXmlsOrJsonsToXml(List<String> xmlsOrJsons) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    private static String getJsonString(Json.JsonStringBuilder.Step identStep, Object object) {
        final String result;
        if (object instanceof Map) {
            result = Json.toJson((Map) object, identStep);
        } else {
            result = Json.toJson((List) object, identStep);
        }
        return result;
    }

    public static String xmlOrJsonToXml(String xmlOrJson, Xml.XmlStringBuilder.Step identStep) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static String xmlOrJsonToXml(String xmlOrJson) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    private static String getXmlString(Xml.XmlStringBuilder.Step identStep, Object object) {
        final String result;
        if (object instanceof Map) {
            result = Xml.toXml((Map) object, identStep);
        } else {
            result = Xml.toXml((List) object, identStep);
        }
        return result;
    }

    public enum TextType {

        JSON, XML, OTHER
    }

    public static TextType getTextType(String text) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static String formatJsonOrXml(String jsonOrXml, String identStep) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static String formatJsonOrXml(String jsonOrXml) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static String formatJson(String json, Json.JsonStringBuilder.Step identStep) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static String formatJson(String json) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static String formatXml(String xml, Xml.XmlStringBuilder.Step identStep) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static String formatXml(String xml) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static String changeXmlEncoding(String xml, Xml.XmlStringBuilder.Step identStep, String encoding) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static String changeXmlEncoding(String xml, String encoding) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static Map<String, Object> removeMinusesAndConvertNumbers(Map<String, Object> map) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    private static Object makeObject(Object value) {
        final Object result;
        if (value instanceof List) {
            List<Object> values = new ArrayList<>();
            for (Object item : (List) value) {
                values.add(item instanceof Map ? removeMinusesAndConvertNumbers((Map<String, Object>) item) : item);
            }
            result = values;
        } else if (value instanceof Map) {
            result = removeMinusesAndConvertNumbers((Map) value);
        } else {
            String stringValue = String.valueOf(value);
            result = isJsonNumber(stringValue) ? Xml.stringToNumber(stringValue) : value;
        }
        return result;
    }

    public static boolean isJsonNumber(final String string) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    public static Map<String, Object> replaceSelfClosingWithNull(Map<String, Object> map) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    public static Map<String, Object> replaceSelfClosingWithEmpty(Map<String, Object> map) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    public static Object replaceSelfClosingWithValue(Map<String, Object> map, String value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    private static Object makeObjectSelfClose(Object value, String newValue) {
        final Object result;
        if (value instanceof List) {
            List<Object> values = new ArrayList<>();
            for (Object item : (List) value) {
                values.add(item instanceof Map ? replaceSelfClosingWithValue((Map) item, newValue) : item);
            }
            result = values;
        } else if (value instanceof Map) {
            result = replaceSelfClosingWithValue((Map) value, newValue);
        } else {
            result = value;
        }
        return result;
    }

    public static Map<String, Object> replaceMinusWithAt(Map<String, Object> map) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    private static Object replaceMinusWithAtValue(Object value) {
        final Object result;
        if (value instanceof List) {
            List<Object> values = new ArrayList<>();
            for (Object item : (List) value) {
                values.add(item instanceof Map ? replaceMinusWithAt((Map) item) : item);
            }
            result = values;
        } else if (value instanceof Map) {
            result = replaceMinusWithAt((Map) value);
        } else {
            result = value;
        }
        return result;
    }

    public static Map<String, Object> replaceEmptyValueWithNull(Map<String, Object> map) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    private static Object makeObjectEmptyValue(Object value) {
        final Object result;
        if (value instanceof List) {
            List<Object> values = new ArrayList<>();
            for (Object item : (List) value) {
                values.add(item instanceof Map ? replaceEmptyValueWithNull((Map) item) : item);
            }
            result = values;
        } else if (value instanceof Map) {
            result = replaceEmptyValueWithNull((Map) value);
        } else {
            result = value;
        }
        return result;
    }

    public static Object replaceEmptyValueWithEmptyString(Map<String, Object> map) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    private static Object makeObjectEmptyString(Object value) {
        final Object result;
        if (value instanceof List) {
            List<Object> values = new ArrayList<>();
            for (Object item : (List) value) {
                values.add(item instanceof Map ? replaceEmptyValueWithEmptyString((Map) item) : item);
            }
            result = values;
        } else if (value instanceof Map) {
            result = replaceEmptyValueWithEmptyString((Map) value);
        } else {
            result = value;
        }
        return result;
    }

    public static Map<String, Object> forceAttributeUsage(Map<String, Object> map) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    private static Object makeAttributeUsage(Object value) {
        final Object result;
        if (value instanceof List) {
            List<Object> values = new ArrayList<>();
            for (Object item : (List) value) {
                values.add(item instanceof Map ? forceAttributeUsage((Map) item) : item);
            }
            result = values;
        } else if (value instanceof Map) {
            result = forceAttributeUsage((Map) value);
        } else {
            result = value;
        }
        return result;
    }

    public static Map<String, Object> replaceNullWithEmptyValue(Map<String, Object> map) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    private static Object makeReplaceNullValue(Object value) {
        final Object result;
        if (value instanceof List) {
            List<Object> values = new ArrayList<>();
            for (Object item : (List) value) {
                values.add(item instanceof Map ? replaceNullWithEmptyValue((Map) item) : item);
            }
            result = values;
        } else if (value instanceof Map) {
            result = replaceNullWithEmptyValue((Map) value);
        } else {
            result = value;
        }
        return result;
    }

    public static Map<String, Object> replaceEmptyStringWithEmptyValue(Map<String, Object> map) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    private static Object makeReplaceEmptyString(Object value) {
        final Object result;
        if (value instanceof List) {
            List<Object> values = new ArrayList<>();
            for (Object item : (List) value) {
                values.add(item instanceof Map ? replaceEmptyStringWithEmptyValue((Map) item) : item);
            }
            result = values;
        } else if (value instanceof Map) {
            result = replaceEmptyStringWithEmptyValue((Map) value);
        } else {
            result = value;
        }
        return result;
    }

    public static Map<String, Object> replaceNumberAndBooleanWithString(Map<String, Object> map) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    private static Object makeReplaceNumberAndBoolean(Object value) {
        final Object result;
        if (value instanceof List) {
            List<Object> values = new ArrayList<>();
            for (Object item : (List) value) {
                if (item instanceof Map) {
                    values.add(replaceNumberAndBooleanWithString((Map) item));
                } else if (item instanceof Number || item instanceof Boolean || isNull(item)) {
                    values.add(String.valueOf(item));
                } else {
                    values.add(item);
                }
            }
            result = values;
        } else if (value instanceof Map) {
            result = replaceNumberAndBooleanWithString((Map) value);
        } else if (isNull(value)) {
            result = "null";
        } else {
            result = value;
        }
        return result;
    }

    public static Map<String, Object> replaceFirstLevel(Map<String, Object> map) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    public static Map<String, Object> replaceFirstLevel(Map<String, Object> map, int level) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    private static Object makeReplaceFirstLevel(Object value, int level) {
        final Object result;
        if (value instanceof List) {
            List<Object> values = new ArrayList<>();
            for (Object item : (List) value) {
                values.add(item instanceof Map ? replaceFirstLevel((Map) item, level + 1) : item);
            }
            result = values;
        } else if (value instanceof Map) {
            result = replaceFirstLevel((Map) value, level + 1);
        } else {
            result = value;
        }
        return result;
    }

    public static Map<String, Object> replaceNilWithNull(Map<String, Object> map) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    private static Object makeReplaceNilWithNull(Object value) {
        final Object result;
        if (value instanceof List) {
            List<Object> values = new ArrayList<>();
            for (Object item : (List) value) {
                values.add(item instanceof Map ? replaceNilWithNull((Map) item) : item);
            }
            result = values;
        } else if (value instanceof Map) {
            result = replaceNilWithNull((Map) value);
        } else {
            result = value;
        }
        return result;
    }

    public static Map<String, Object> deepCopyMap(Map<String, Object> map) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    private static Object makeDeepCopyMap(Object value) {
        final Object result;
        if (value instanceof List) {
            List<Object> values = new ArrayList<>();
            for (Object item : (List) value) {
                values.add(item instanceof Map ? deepCopyMap((Map) item) : item);
            }
            result = values;
        } else if (value instanceof Map) {
            result = deepCopyMap((Map) value);
        } else {
            result = value;
        }
        return result;
    }

    public static Builder objectBuilder() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static class Builder {

        private final Map<String, Object> data;

        public Builder() {
            data = new LinkedHashMap<>();
        }

        public Builder add(final String key, final Object value) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder add(final Object value) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public <T> T get(final String path) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public <T> T get(final List<String> paths) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder set(final String path, final Object value) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder set(final List<String> paths, final Object value) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder remove(final String key) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder remove(final List<String> keys) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder clear() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public boolean isEmpty() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public int size() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder add(final Builder builder) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder add(final String key, final ArrayBuilder builder) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder add(final String key, final Builder builder) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder add(final Map<String, Object> map) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder update(final Map<String, Object> map) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder addNull(final String key) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @SuppressWarnings("unchecked")
        public Map<String, Object> build() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public String toXml() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public static Builder fromXml(final String xml) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public static Builder fromMap(final Map<String, Object> map) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public String toJson() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public static Builder fromJson(final String json) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Chain<Object> toChain() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public String toString() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    public static ArrayBuilder arrayBuilder() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static class ArrayBuilder {

        private final List<Object> data;

        public ArrayBuilder() {
            data = new ArrayList<>();
        }

        public ArrayBuilder add(final Object value) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public ArrayBuilder addNull() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public <T> T get(final String path) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public <T> T get(final List<String> paths) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public ArrayBuilder set(final int index, final Object value) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public ArrayBuilder remove(final int index) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public ArrayBuilder clear() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public boolean isEmpty() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public int size() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public ArrayBuilder add(final ArrayBuilder builder) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public ArrayBuilder add(final Builder builder) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @SuppressWarnings("unchecked")
        public ArrayBuilder merge(final List<Object> list) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @SuppressWarnings("unchecked")
        public List<Object> build() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public String toXml() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public static ArrayBuilder fromXml(final String xml) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public String toJson() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public static ArrayBuilder fromJson(final String json) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Chain<Object> toChain() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public String toString() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    public static Map<String, Object> propertiesToMap(Properties properties) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static Properties mapToProperties(Map<String, Object> map) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
