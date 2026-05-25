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

import static java.nio.charset.StandardCharsets.UTF_8;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;

@SuppressWarnings({ "java:S107", "java:S1119", "java:S2583", "java:S3740", "java:S3776", "java:S4276" })
public final class Xml {

    private Xml() {
    }

    private static final String NULL = "null";

    private static final String ELEMENT_TEXT = "element";

    private static final String CDATA = "#cdata-section";

    private static final String COMMENT = "#comment";

    private static final String ENCODING = "#encoding";

    private static final String STANDALONE = "#standalone";

    private static final String OMITXMLDECLARATION = "#omit-xml-declaration";

    private static final String YES = "yes";

    private static final String TEXT = "#text";

    private static final String NUMBER = "-number";

    private static final String ELEMENT = "<" + ELEMENT_TEXT + ">";

    private static final String CLOSED_ELEMENT = "</" + ELEMENT_TEXT + ">";

    private static final String EMPTY_ELEMENT = ELEMENT + CLOSED_ELEMENT;

    private static final String NULL_TRUE = " " + NULL + "=\"true\"/>";

    private static final String NUMBER_TEXT = " number=\"true\"";

    private static final String NUMBER_TRUE = NUMBER_TEXT + ">";

    private static final String ARRAY = "-array";

    private static final String ARRAY_TRUE = " array=\"true\"";

    private static final String NULL_ELEMENT = "<" + ELEMENT_TEXT + NULL_TRUE;

    private static final String BOOLEAN = "-boolean";

    private static final String TRUE = "true";

    private static final String SELF_CLOSING = "-self-closing";

    private static final String STRING = "-string";

    private static final String NULL_ATTR = "-null";

    private static final String EMPTY_ARRAY = "-empty-array";

    private static final String QUOT = "&quot;";

    private static final String XML_HEADER = "<?xml ";

    private static final String DOCTYPE_TEXT = "!DOCTYPE";

    private static final String ROOT = "root";

    private static final String DOCTYPE_HEADER = "<" + DOCTYPE_TEXT + " ";

    private static final Set<Character> SKIPPED_CHARS = Set.of(' ', '\n', '\r');

    private static final Map<String, String> XML_UNESCAPE = new HashMap<>();

    private static final org.w3c.dom.Document DOCUMENT = Document.createDocument();

    static {
        XML_UNESCAPE.put(QUOT, "\"");
        XML_UNESCAPE.put("&amp;", "&");
        XML_UNESCAPE.put("&lt;", "<");
        XML_UNESCAPE.put("&gt;", ">");
        XML_UNESCAPE.put("&apos;", "'");
    }

    public enum ArrayTrue {

        ADD, SKIP
    }

    public static class XmlStringBuilder {

        public enum Step {

            TWO_SPACES(2), THREE_SPACES(3), FOUR_SPACES(4), COMPACT(0), TABS(1);

            private final int ident;

            Step(int ident) {
                this.ident = ident;
            }

            public int getIdent() {
                throw new UnsupportedOperationException("STUB: not implemented");
            }
        }

        protected final StringBuilder builder;

        private final Step identStep;

        private int ident;

        public XmlStringBuilder() {
            builder = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<root>\n");
            identStep = Step.TWO_SPACES;
            ident = 2;
        }

        public XmlStringBuilder(StringBuilder builder, Step identStep, int ident) {
            this.builder = builder;
            this.identStep = identStep;
            this.ident = ident;
        }

        public XmlStringBuilder append(final String string) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public XmlStringBuilder fillSpaces() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public XmlStringBuilder incIdent() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public XmlStringBuilder decIdent() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public XmlStringBuilder newLine() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public int getIdent() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Step getIdentStep() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public String toString() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    public static class XmlStringBuilderWithoutRoot extends XmlStringBuilder {

        public XmlStringBuilderWithoutRoot(XmlStringBuilder.Step identStep, String encoding, String standalone) {
            super(new StringBuilder("<?xml version=\"1.0\" encoding=\"" + XmlValue.escape(encoding).replace("\"", QUOT) + "\"" + standalone + "?>" + (identStep == Step.COMPACT ? "" : "\n")), identStep, 0);
        }

        @Override
        public String toString() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    public static class XmlStringBuilderWithoutHeader extends XmlStringBuilder {

        public XmlStringBuilderWithoutHeader(XmlStringBuilder.Step identStep, int ident) {
            super(new StringBuilder(), identStep, ident);
        }

        @Override
        public String toString() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    public static class XmlStringBuilderText extends XmlStringBuilderWithoutHeader {

        public XmlStringBuilderText(XmlStringBuilder.Step identStep, int ident) {
            super(identStep, ident);
        }
    }

    public static class XmlArray {

        private XmlArray() {
        }

        public static void writeXml(Collection<?> collection, String name, XmlStringBuilder builder, boolean parentTextFound, Set<String> namespaces, boolean addArray, String arrayTrue) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        private static void writeXml(Collection<?> collection, XmlStringBuilder builder, String name, final boolean parentTextFound, Set<String> namespaces, String arrayTrue) {
            boolean localParentTextFound = parentTextFound;
            final List<?> entries = new ArrayList<>(collection);
            for (int index = 0; index < entries.size(); index += 1) {
                final Object value = entries.get(index);
                final boolean addNewLine = index < entries.size() - 1 && !XmlValue.getMapKey(XmlValue.getMapValue(entries.get(index + 1))).startsWith(TEXT);
                if (value == null) {
                    builder.fillSpaces().append("<" + (name == null ? ELEMENT_TEXT : XmlValue.escapeName(name, namespaces)) + (collection.size() == 1 ? arrayTrue : "") + NULL_TRUE);
                } else {
                    if (value instanceof Map && ((Map) value).size() == 1 && XmlValue.getMapKey(value).equals("#item") && XmlValue.getMapValue(value) instanceof Map) {
                        XmlObject.writeXml((Map) XmlValue.getMapValue(value), null, builder, localParentTextFound, namespaces, true, arrayTrue);
                        if (XmlValue.getMapKey(XmlValue.getMapValue(value)).startsWith(TEXT)) {
                            localParentTextFound = true;
                            continue;
                        }
                    } else {
                        XmlValue.writeXml(value, name == null ? ELEMENT_TEXT : name, builder, localParentTextFound, namespaces, collection.size() == 1 || value instanceof Collection, arrayTrue);
                    }
                    localParentTextFound = false;
                }
                if (addNewLine) {
                    builder.newLine();
                }
            }
        }

        public static void writeXml(byte[] array, XmlStringBuilder builder) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public static void writeXml(short[] array, XmlStringBuilder builder) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public static void writeXml(int[] array, XmlStringBuilder builder) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public static void writeXml(long[] array, XmlStringBuilder builder) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public static void writeXml(float[] array, XmlStringBuilder builder) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public static void writeXml(double[] array, XmlStringBuilder builder) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public static void writeXml(boolean[] array, XmlStringBuilder builder) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public static void writeXml(char[] array, XmlStringBuilder builder) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public static void writeXml(Object[] array, String name, XmlStringBuilder builder, boolean parentTextFound, Set<String> namespaces, String arrayTrue) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    public static class XmlObject {

        private XmlObject() {
        }

        @SuppressWarnings("unchecked")
        public static void writeXml(final Map map, final String name, final XmlStringBuilder builder, final boolean parentTextFound, final Set<String> namespaces, final boolean addArray, final String arrayTrue) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @SuppressWarnings("unchecked")
        private static void fillNamespacesAndAttrs(final Map map, final Set<String> namespaces, final Set<String> attrKeys) {
            for (Map.Entry entry : (Set<Map.Entry>) map.entrySet()) {
                if (String.valueOf(entry.getKey()).startsWith("-") && !(entry.getValue() instanceof Map) && !(entry.getValue() instanceof List)) {
                    if (String.valueOf(entry.getKey()).startsWith("-xmlns:")) {
                        namespaces.add(String.valueOf(entry.getKey()).substring(7));
                    }
                    attrKeys.add(String.valueOf(entry.getKey()));
                }
            }
        }

        private static void addToBuilder(final String name, final boolean parentTextFound, final XmlStringBuilder builder, final Set<String> namespaces, final List<String> attrs, final List<XmlStringBuilder> elems) {
            final boolean selfClosing = attrs.remove(" self-closing=\"true\"");
            addOpenElement(name, parentTextFound, builder, namespaces, selfClosing, attrs, elems);
            if (!selfClosing) {
                for (XmlStringBuilder localBuilder1 : elems) {
                    builder.append(localBuilder1.toString());
                }
            }
            if (name != null) {
                builder.decIdent();
                if (!elems.isEmpty() && !(elems.get(elems.size() - 1) instanceof XmlStringBuilderText)) {
                    builder.newLine().fillSpaces();
                }
                if (!selfClosing) {
                    builder.append("</").append(XmlValue.escapeName(name, namespaces)).append(">");
                }
            }
        }

        private static void addOpenElement(final String name, final boolean parentTextFound, final XmlStringBuilder builder, final Set<String> namespaces, final boolean selfClosing, final List<String> attrs, final List<XmlStringBuilder> elems) {
            if (name != null) {
                if (!parentTextFound) {
                    builder.fillSpaces();
                }
                builder.append("<").append(XmlValue.escapeName(name, namespaces)).append(String.join("", attrs));
                if (selfClosing) {
                    builder.append("/");
                }
                builder.append(">").incIdent();
                if (!elems.isEmpty() && !(elems.get(0) instanceof XmlStringBuilderText)) {
                    builder.newLine();
                }
            }
        }

        private static void processElements(final Map.Entry entry, final XmlStringBuilder.Step identStep, final int ident, final boolean addNewLine, final List<XmlStringBuilder> elems, final Set<String> namespaces, final boolean parentTextFound, final String arrayTrue) {
            if (String.valueOf(entry.getKey()).startsWith(COMMENT)) {
                addComment(entry, identStep, ident, parentTextFound, addNewLine, elems);
            } else if (String.valueOf(entry.getKey()).startsWith(CDATA)) {
                addCdata(entry, identStep, ident, addNewLine, elems);
            } else if (entry.getValue() instanceof List && !((List) entry.getValue()).isEmpty()) {
                addElements(identStep, ident, entry, namespaces, elems, addNewLine, arrayTrue);
            } else {
                addElement(identStep, ident, entry, namespaces, elems, addNewLine, arrayTrue);
            }
        }

        private static void addText(final Map.Entry entry, final List<XmlStringBuilder> elems, final XmlStringBuilder.Step identStep, final int ident, final Set<String> attrKeys, final List<String> attrs) {
            if (entry.getValue() instanceof List) {
                for (Object value : (List) entry.getValue()) {
                    elems.add(new XmlStringBuilderText(identStep, ident).append(XmlValue.escape(String.valueOf(value))));
                }
            } else {
                if (entry.getValue() instanceof Number && !attrKeys.contains(NUMBER)) {
                    attrs.add(NUMBER_TEXT);
                } else if (entry.getValue() instanceof Boolean && !attrKeys.contains(BOOLEAN)) {
                    attrs.add(" boolean=\"true\"");
                } else if (entry.getValue() == null && !attrKeys.contains(NULL_ATTR)) {
                    attrs.add(" null=\"true\"");
                    return;
                } else if ("".equals(entry.getValue()) && !attrKeys.contains(STRING)) {
                    attrs.add(" string=\"true\"");
                    return;
                }
                elems.add(new XmlStringBuilderText(identStep, ident).append(XmlValue.escape(String.valueOf(entry.getValue()))));
            }
        }

        private static void addElements(final XmlStringBuilder.Step identStep, final int ident, Map.Entry entry, Set<String> namespaces, final List<XmlStringBuilder> elems, final boolean addNewLine, final String arrayTrue) {
            boolean parentTextFound = !elems.isEmpty() && elems.get(elems.size() - 1) instanceof XmlStringBuilderText;
            final XmlStringBuilder localBuilder = new XmlStringBuilderWithoutHeader(identStep, ident);
            XmlArray.writeXml((List) entry.getValue(), localBuilder, String.valueOf(entry.getKey()), parentTextFound, namespaces, arrayTrue);
            if (addNewLine) {
                localBuilder.newLine();
            }
            elems.add(localBuilder);
        }

        private static void addElement(final XmlStringBuilder.Step identStep, final int ident, Map.Entry entry, Set<String> namespaces, final List<XmlStringBuilder> elems, final boolean addNewLine, final String arrayTrue) {
            boolean parentTextFound = !elems.isEmpty() && elems.get(elems.size() - 1) instanceof XmlStringBuilderText;
            XmlStringBuilder localBuilder = new XmlStringBuilderWithoutHeader(identStep, ident);
            XmlValue.writeXml(entry.getValue(), String.valueOf(entry.getKey()), localBuilder, parentTextFound, namespaces, false, arrayTrue);
            if (addNewLine) {
                localBuilder.newLine();
            }
            elems.add(localBuilder);
        }

        private static void addComment(Map.Entry entry, XmlStringBuilder.Step identStep, int ident, boolean parentTextFound, boolean addNewLine, List<XmlStringBuilder> elems) {
            if (entry.getValue() instanceof List) {
                for (Iterator iterator = ((List) entry.getValue()).iterator(); iterator.hasNext(); ) {
                    elems.add(addCommentValue(identStep, ident, String.valueOf(iterator.next()), parentTextFound, iterator.hasNext() || addNewLine));
                }
            } else {
                elems.add(addCommentValue(identStep, ident, String.valueOf(entry.getValue()), parentTextFound, addNewLine));
            }
        }

        private static XmlStringBuilder addCommentValue(XmlStringBuilder.Step identStep, int ident, String value, boolean parentTextFound, boolean addNewLine) {
            XmlStringBuilder localBuilder = new XmlStringBuilderWithoutHeader(identStep, ident);
            if (!parentTextFound) {
                localBuilder.fillSpaces();
            }
            localBuilder.append("<!--").append(value).append("-->");
            if (addNewLine) {
                localBuilder.newLine();
            }
            return localBuilder;
        }

        private static void addCdata(Map.Entry entry, XmlStringBuilder.Step identStep, int ident, boolean addNewLine, List<XmlStringBuilder> elems) {
            if (entry.getValue() instanceof List) {
                for (Iterator iterator = ((List) entry.getValue()).iterator(); iterator.hasNext(); ) {
                    elems.add(addCdataValue(identStep, ident, String.valueOf(iterator.next()), iterator.hasNext() || addNewLine));
                }
            } else {
                elems.add(addCdataValue(identStep, ident, String.valueOf(entry.getValue()), addNewLine));
            }
        }

        private static XmlStringBuilder addCdataValue(XmlStringBuilder.Step identStep, int ident, String value, boolean addNewLine) {
            XmlStringBuilder localBuilder = new XmlStringBuilderText(identStep, ident);
            localBuilder.append("<![CDATA[").append(value).append("]]>");
            if (addNewLine) {
                localBuilder.newLine();
            }
            return localBuilder;
        }
    }

    public static class XmlValue {

        private XmlValue() {
        }

        public static void writeXml(Object value, String name, XmlStringBuilder builder, boolean parentTextFound, Set<String> namespaces, boolean addArray, String arrayTrue) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        private static void processArrays(Object value, XmlStringBuilder builder, String name, boolean parentTextFound, Set<String> namespaces, boolean addArray, String arrayTrue) {
            if (value instanceof Double) {
                if (((Double) value).isInfinite() || ((Double) value).isNaN()) {
                    builder.append(NULL_ELEMENT);
                } else {
                    builder.append("<" + XmlValue.escapeName(name, namespaces) + (addArray ? arrayTrue : "") + NUMBER_TRUE);
                    builder.append(value.toString());
                    builder.append("</" + XmlValue.escapeName(name, namespaces) + ">");
                }
            } else if (value instanceof Float) {
                if (((Float) value).isInfinite() || ((Float) value).isNaN()) {
                    builder.append(NULL_ELEMENT);
                } else {
                    builder.append("<" + XmlValue.escapeName(name, namespaces) + NUMBER_TRUE);
                    builder.append(value.toString());
                    builder.append("</" + XmlValue.escapeName(name, namespaces) + ">");
                }
            } else if (value instanceof Number) {
                builder.append("<" + XmlValue.escapeName(name, namespaces) + (addArray ? arrayTrue : "") + NUMBER_TRUE);
                builder.append(value.toString());
                builder.append("</" + XmlValue.escapeName(name, namespaces) + ">");
            } else if (value instanceof Boolean) {
                builder.append("<" + XmlValue.escapeName(name, namespaces) + (addArray ? arrayTrue : "") + " boolean=\"true\">");
                builder.append(value.toString());
                builder.append("</" + XmlValue.escapeName(name, namespaces) + ">");
            } else {
                builder.append("<" + XmlValue.escapeName(name, namespaces) + ">");
                if (value instanceof byte[]) {
                    builder.newLine().incIdent();
                    XmlArray.writeXml((byte[]) value, builder);
                    builder.decIdent().newLine().fillSpaces();
                } else if (value instanceof short[]) {
                    builder.newLine().incIdent();
                    XmlArray.writeXml((short[]) value, builder);
                    builder.decIdent().newLine().fillSpaces();
                } else {
                    processArrays2(value, builder, name, parentTextFound, namespaces, arrayTrue);
                }
                builder.append("</" + XmlValue.escapeName(name, namespaces) + ">");
            }
        }

        private static void processArrays2(Object value, XmlStringBuilder builder, String name, boolean parentTextFound, Set<String> namespaces, String arrayTrue) {
            if (value instanceof int[]) {
                builder.newLine().incIdent();
                XmlArray.writeXml((int[]) value, builder);
                builder.decIdent().newLine().fillSpaces();
            } else if (value instanceof long[]) {
                builder.newLine().incIdent();
                XmlArray.writeXml((long[]) value, builder);
                builder.decIdent().newLine().fillSpaces();
            } else if (value instanceof float[]) {
                builder.newLine().incIdent();
                XmlArray.writeXml((float[]) value, builder);
                builder.decIdent().newLine().fillSpaces();
            } else if (value instanceof double[]) {
                builder.newLine().incIdent();
                XmlArray.writeXml((double[]) value, builder);
                builder.decIdent().newLine().fillSpaces();
            } else if (value instanceof boolean[]) {
                builder.newLine().incIdent();
                XmlArray.writeXml((boolean[]) value, builder);
                builder.decIdent().newLine().fillSpaces();
            } else if (value instanceof char[]) {
                builder.newLine().incIdent();
                XmlArray.writeXml((char[]) value, builder);
                builder.decIdent().newLine().fillSpaces();
            } else if (value instanceof Object[]) {
                builder.newLine().incIdent();
                XmlArray.writeXml((Object[]) value, name, builder, parentTextFound, namespaces, arrayTrue);
                builder.decIdent().newLine().fillSpaces();
            } else {
                builder.append(value.toString());
            }
        }

        public static String escapeName(String name, Set<String> namespaces) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public static String escape(String s) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        private static void escape(String s, StringBuilder sb) {
            final int len = s.length();
            for (int i = 0; i < len; i++) {
                char ch = s.charAt(i);
                switch(ch) {
                    case '\'':
                        sb.append("'");
                        break;
                    case '&':
                        sb.append("&amp;");
                        break;
                    case '<':
                        sb.append("&lt;");
                        break;
                    case '>':
                        sb.append("&gt;");
                        break;
                    case '\b':
                        sb.append("\\b");
                        break;
                    case '\f':
                        sb.append("\\f");
                        break;
                    case '\n':
                        sb.append("\n");
                        break;
                    case '\r':
                        sb.append("&#xD;");
                        break;
                    case '\t':
                        sb.append("\t");
                        break;
                    case '€':
                        sb.append("€");
                        break;
                    default:
                        if (ch <= '\u001F' || ch >= '\u007F' && ch <= '\u009F' || ch >= '\u2000' && ch <= '\u20FF') {
                            String ss = Integer.toHexString(ch);
                            sb.append("&#x");
                            sb.append("0".repeat(4 - ss.length()));
                            sb.append(ss.toUpperCase()).append(";");
                        } else {
                            sb.append(ch);
                        }
                        break;
                }
            }
        }

        public static String unescape(String s) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        private static void unescape(String s, StringBuilder sb) {
            final int len = s.length();
            final StringBuilder localSb = new StringBuilder();
            int index = 0;
            while (index < len) {
                final int skipChars = translate(s, index, localSb);
                if (skipChars > 0) {
                    sb.append(localSb);
                    localSb.setLength(0);
                    index += skipChars;
                } else {
                    sb.append(s.charAt(index));
                    index += 1;
                }
            }
        }

        private static int translate(final CharSequence input, final int index, final StringBuilder builder) {
            final int shortest = 4;
            final int longest = 6;
            if ('&' == input.charAt(index)) {
                int max = longest;
                if (index + longest > input.length()) {
                    max = input.length() - index;
                }
                for (int i = max; i >= shortest; i--) {
                    final CharSequence subSeq = input.subSequence(index, index + i);
                    final String result = XML_UNESCAPE.get(subSeq.toString());
                    if (result != null) {
                        builder.append(result);
                        return i;
                    }
                }
            }
            return 0;
        }

        public static String getMapKey(Object map) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public static Object getMapValue(Object map) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    public static String toXml(Collection collection, XmlStringBuilder.Step identStep) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static String toXmlWithoutRoot(Collection collection, XmlStringBuilder.Step identStep) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static String toXml(Collection collection) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static String toXml(Map map, XmlStringBuilder.Step identStep) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static String toXml(Map map, XmlStringBuilder.Step identStep, String newRootName) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static String toXml(Map map, XmlStringBuilder.Step identStep, String newRootName, ArrayTrue arrayTrue) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private static void checkLocalMap(final XmlStringBuilder builder, final Map localMap, final String newRootName, final String arrayTrue) {
        final Map localMap2;
        if (localMap != null && localMap.containsKey(DOCTYPE_TEXT)) {
            localMap2 = (Map) ((LinkedHashMap) localMap).clone();
            localMap2.remove(DOCTYPE_TEXT);
            builder.append(DOCTYPE_HEADER).append(String.valueOf(localMap.get(DOCTYPE_TEXT))).append(">").newLine();
        } else {
            localMap2 = localMap;
        }
        if (localMap2 != null && localMap2.size() == 1 && ROOT.equals(XmlValue.getMapKey(localMap2)) && XmlValue.getMapValue(localMap2) instanceof List) {
            writeArray((List) XmlValue.getMapValue(localMap2), builder, arrayTrue);
        } else {
            XmlObject.writeXml(localMap2, getRootName(localMap2, newRootName), builder, false, new LinkedHashSet<>(), false, arrayTrue);
        }
    }

    private static void writeArray(final Collection collection, final XmlStringBuilder builder, final String arrayTrue) {
        builder.append("<root");
        if (collection != null && collection.isEmpty()) {
            builder.append(" empty-array=\"true\"");
        }
        builder.append(">").incIdent();
        if (collection != null && !collection.isEmpty()) {
            builder.newLine();
        }
        XmlArray.writeXml(collection, null, builder, false, new LinkedHashSet<>(), false, arrayTrue);
        if (collection != null && !collection.isEmpty()) {
            builder.newLine();
        }
        builder.append("</root>");
    }

    private static XmlStringBuilder checkStandalone(String encoding, XmlStringBuilder.Step identStep, final Map localMap) {
        final XmlStringBuilder builder;
        if (localMap.containsKey(STANDALONE)) {
            builder = new XmlStringBuilderWithoutRoot(identStep, encoding, " standalone=\"" + (YES.equals(localMap.get(STANDALONE)) ? YES : "no") + "\"");
            localMap.remove(STANDALONE);
        } else {
            builder = new XmlStringBuilderWithoutRoot(identStep, encoding, "");
        }
        return builder;
    }

    @SuppressWarnings("unchecked")
    private static String getRootName(final Map localMap, final String newRootName) {
        int foundAttrs = 0;
        int foundElements = 0;
        int foundListElements = 0;
        if (localMap != null) {
            for (Map.Entry entry : (Set<Map.Entry>) localMap.entrySet()) {
                if (String.valueOf(entry.getKey()).startsWith("-")) {
                    foundAttrs += 1;
                } else if (!String.valueOf(entry.getKey()).startsWith(COMMENT) && !String.valueOf(entry.getKey()).startsWith(CDATA) && !String.valueOf(entry.getKey()).startsWith("?")) {
                    if (entry.getValue() instanceof List && ((List) entry.getValue()).size() > 1) {
                        foundListElements += 1;
                    }
                    foundElements += 1;
                }
            }
        }
        return foundAttrs == 0 && foundElements == 1 && foundListElements == 0 ? null : newRootName;
    }

    public static String toXml(Map map) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    private static Object getValue(final String name, final Object value, final FromType fromType) {
        final Object localValue;
        if (value instanceof Map && ((Map<String, Object>) value).entrySet().size() == 1) {
            final Map.Entry<String, Object> entry = ((Map<String, Object>) value).entrySet().iterator().next();
            if (TEXT.equals(entry.getKey()) || (fromType == FromType.FOR_CONVERT && ELEMENT_TEXT.equals(entry.getKey()))) {
                localValue = entry.getValue();
            } else {
                localValue = value;
            }
        } else {
            localValue = value;
        }
        return localValue instanceof String && name.startsWith("-") ? XmlValue.unescape((String) localValue) : localValue;
    }

    public static Object stringToNumber(String number) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private static Object createMap(final org.w3c.dom.Node node, final BiFunction<Object, Set<String>, String> elementMapper, final Function<Object, Object> nodeMapper, final Map<String, Object> attrMap, final int[] uniqueIds, final String source, final int[] sourceIndex, final Set<String> namespaces, final FromType fromType) {
        final Map<String, Object> map = new LinkedHashMap<>(attrMap);
        final org.w3c.dom.NodeList nodeList = node.getChildNodes();
        for (int index = 0; index < nodeList.getLength(); index++) {
            final org.w3c.dom.Node currentNode = nodeList.item(index);
            final String name;
            if (currentNode.getNodeType() == org.w3c.dom.Node.PROCESSING_INSTRUCTION_NODE) {
                name = "?" + currentNode.getNodeName();
            } else {
                name = currentNode.getNodeName();
            }
            final Object value;
            if (currentNode.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
                sourceIndex[0] = source.indexOf("<" + name, sourceIndex[0]) + name.length() + 1;
                value = addElement(sourceIndex, source, elementMapper, nodeMapper, uniqueIds, currentNode, namespaces, fromType);
            } else {
                if (COMMENT.equals(name)) {
                    sourceIndex[0] = source.indexOf("-->", sourceIndex[0]) + 3;
                } else if (CDATA.equals(name)) {
                    sourceIndex[0] = source.indexOf("]]>", sourceIndex[0]) + 3;
                }
                value = currentNode.getTextContent();
            }
            if (TEXT.equals(name) && node.getChildNodes().getLength() > 1 && String.valueOf(value).trim().isEmpty()) {
                continue;
            }
            if (currentNode.getNodeType() == org.w3c.dom.Node.DOCUMENT_TYPE_NODE) {
                addNodeValue(map, DOCTYPE_TEXT, getDoctypeValue(source), elementMapper, nodeMapper, uniqueIds, namespaces, fromType);
            } else {
                addNodeValue(map, name, value, elementMapper, nodeMapper, uniqueIds, namespaces, fromType);
            }
        }
        return checkNumberAndBoolean(map, node.getNodeName());
    }

    @SuppressWarnings("unchecked")
    private static Object checkNumberAndBoolean(final Map<String, Object> map, final String name) {
        final Map<String, Object> localMap;
        if (map.containsKey(NUMBER) && TRUE.equals(map.get(NUMBER)) && map.containsKey(TEXT)) {
            localMap = (Map) ((LinkedHashMap) map).clone();
            localMap.remove(NUMBER);
            localMap.put(TEXT, stringToNumber(String.valueOf(localMap.get(TEXT))));
        } else {
            localMap = map;
        }
        final Map<String, Object> localMap2;
        if (map.containsKey(BOOLEAN) && TRUE.equals(map.get(BOOLEAN)) && map.containsKey(TEXT)) {
            localMap2 = (Map) ((LinkedHashMap) localMap).clone();
            localMap2.remove(BOOLEAN);
            localMap2.put(TEXT, Boolean.valueOf(String.valueOf(localMap.get(TEXT))));
        } else {
            localMap2 = localMap;
        }
        return checkArray(localMap2, name);
    }

    @SuppressWarnings("unchecked")
    private static Object checkArray(final Map<String, Object> map, final String name) {
        final Map<String, Object> localMap = checkNullAndString(map);
        final Object object;
        if (map.containsKey(ARRAY) && TRUE.equals(map.get(ARRAY))) {
            final Map<String, Object> localMap4 = (Map) ((LinkedHashMap) localMap).clone();
            localMap4.remove(ARRAY);
            localMap4.remove(SELF_CLOSING);
            object = name.equals(XmlValue.getMapKey(localMap4)) ? new ArrayList<>(Collections.singletonList(getValue(name, XmlValue.getMapValue(localMap4), FromType.FOR_CONVERT))) : new ArrayList<>(Collections.singletonList(getValue(name, localMap4, FromType.FOR_CONVERT)));
        } else {
            object = localMap;
        }
        final Object object2;
        if (map.containsKey(EMPTY_ARRAY) && TRUE.equals(map.get(EMPTY_ARRAY))) {
            final Map<String, Object> localMap4 = (Map) ((LinkedHashMap) map).clone();
            localMap4.remove(EMPTY_ARRAY);
            if (localMap4.containsKey(ARRAY) && TRUE.equals(localMap4.get(ARRAY)) && localMap4.size() == 1) {
                object2 = new ArrayList<>();
                ((List) object2).add(new ArrayList<>());
            } else {
                object2 = localMap4.isEmpty() ? new ArrayList<>() : localMap4;
            }
        } else {
            object2 = object;
        }
        return object2;
    }

    @SuppressWarnings("unchecked")
    private static Map<String, Object> checkNullAndString(final Map<String, Object> map) {
        final Map<String, Object> localMap;
        if (map.containsKey(NULL_ATTR) && TRUE.equals(map.get(NULL_ATTR))) {
            localMap = (Map) ((LinkedHashMap) map).clone();
            localMap.remove(NULL_ATTR);
            if (!map.containsKey(TEXT)) {
                localMap.put(TEXT, null);
            }
        } else {
            localMap = map;
        }
        final Map<String, Object> localMap2;
        if (map.containsKey(STRING) && TRUE.equals(map.get(STRING))) {
            localMap2 = (Map) ((LinkedHashMap) localMap).clone();
            localMap2.remove(STRING);
            if (!map.containsKey(TEXT)) {
                localMap2.put(TEXT, "");
            }
        } else {
            localMap2 = localMap;
        }
        return localMap2;
    }

    private static Object addElement(final int[] sourceIndex, final String source, final BiFunction<Object, Set<String>, String> elementMapper, final Function<Object, Object> nodeMapper, final int[] uniqueIds, final org.w3c.dom.Node currentNode, final Set<String> namespaces, final FromType fromType) {
        final Map<String, Object> attrMapLocal = new LinkedHashMap<>();
        if (currentNode.getAttributes().getLength() > 0) {
            final Map<String, String> attributes = parseAttributes(getAttributes(sourceIndex[0], source));
            for (Map.Entry<String, String> attribute : attributes.entrySet()) {
                if (attribute.getKey().startsWith("xmlns:")) {
                    namespaces.add(attribute.getKey().substring(6));
                }
            }
            for (Map.Entry<String, String> attribute : attributes.entrySet()) {
                addNodeValue(attrMapLocal, '-' + attribute.getKey(), attribute.getValue(), elementMapper, nodeMapper, uniqueIds, namespaces, fromType);
            }
        }
        if (getAttributes(sourceIndex[0], source).endsWith("/") && !attrMapLocal.containsKey(SELF_CLOSING) && (attrMapLocal.size() != 1 || ((!attrMapLocal.containsKey(STRING) || !TRUE.equals(attrMapLocal.get(STRING))) && (!attrMapLocal.containsKey(NULL_ATTR) || !TRUE.equals(attrMapLocal.get(NULL_ATTR)))))) {
            attrMapLocal.put(SELF_CLOSING, TRUE);
        }
        return createMap(currentNode, elementMapper, nodeMapper, attrMapLocal, uniqueIds, source, sourceIndex, namespaces, fromType);
    }

    static Map<String, String> parseAttributes(final String source) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    static String getAttributes(final int sourceIndex, final String source) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private static String unescapeName(final String name) {
        if (name == null) {
            return null;
        }
        final int length = name.length();
        if ("__EE__EMPTY__EE__".equals(name)) {
            return "";
        }
        if ("-__EE__EMPTY__EE__".equals(name)) {
            return "-";
        }
        if (!name.contains("__")) {
            return name;
        }
        StringBuilder result = new StringBuilder();
        int underlineCount = 0;
        StringBuilder lastChars = new StringBuilder();
        int i = 0;
        outer: while (i < length) {
            char ch = name.charAt(i);
            if (ch == '_') {
                lastChars.append(ch);
            } else {
                if (lastChars.length() == 2) {
                    StringBuilder nameToDecode = new StringBuilder();
                    for (int j = i; j < length; ++j) {
                        if (name.charAt(j) == '_') {
                            underlineCount += 1;
                            if (underlineCount == 2) {
                                try {
                                    result.append(Base32.decode(nameToDecode.toString()));
                                } catch (Base32.DecodingException ex) {
                                    result.append("__").append(nameToDecode).append(lastChars);
                                }
                                i = j;
                                underlineCount = 0;
                                lastChars.setLength(0);
                                i++;
                                continue outer;
                            }
                        } else {
                            nameToDecode.append(name.charAt(j));
                            underlineCount = 0;
                        }
                    }
                }
                result.append(lastChars).append(ch);
                lastChars.setLength(0);
            }
            i++;
        }
        return result.append(lastChars).toString();
    }

    @SuppressWarnings("unchecked")
    private static void addNodeValue(final Map<String, Object> map, final String name, final Object value, final BiFunction<Object, Set<String>, String> elementMapper, final Function<Object, Object> nodeMapper, final int[] uniqueIds, final Set<String> namespaces, final FromType fromType) {
        final String elementName = unescapeName(elementMapper.apply(name, namespaces));
        if (map.containsKey(elementName)) {
            if (TEXT.equals(elementName)) {
                map.put(elementName + uniqueIds[0], nodeMapper.apply(getValue(name, value, fromType)));
                uniqueIds[0] += 1;
            } else if (COMMENT.equals(elementName)) {
                map.put(elementName + uniqueIds[1], nodeMapper.apply(getValue(name, value, fromType)));
                uniqueIds[1] += 1;
            } else if (CDATA.equals(elementName)) {
                map.put(elementName + uniqueIds[2], nodeMapper.apply(getValue(name, value, fromType)));
                uniqueIds[2] += 1;
            } else {
                final Object object = map.get(elementName);
                if (object instanceof List) {
                    addText(map, elementName, (List<Object>) object, value, fromType);
                } else {
                    final List<Object> objects = new ArrayList<>();
                    objects.add(object);
                    addText(map, elementName, objects, value, fromType);
                    map.put(elementName, objects);
                }
            }
        } else {
            if (elementName != null) {
                map.put(elementName, nodeMapper.apply(getValue(name, value, fromType)));
            }
        }
    }

    private static void addText(final Map<String, Object> map, final String name, final List<Object> objects, final Object value, final FromType fromType) {
        int lastIndex = map.size() - 1;
        final int index = objects.size();
        while (true) {
            final Map.Entry lastElement = (Map.Entry) map.entrySet().toArray()[lastIndex];
            if (name.equals(String.valueOf(lastElement.getKey()))) {
                break;
            }
            final Map<String, Object> item = new LinkedHashMap<>();
            final Map<String, Object> text = new LinkedHashMap<>();
            text.put(String.valueOf(lastElement.getKey()), map.remove(lastElement.getKey()));
            item.put("#item", text);
            objects.add(index, item);
            lastIndex -= 1;
        }
        final Object newValue = getValue(name, value, fromType);
        if (newValue instanceof List) {
            objects.add(((List) newValue).get(0));
        } else {
            objects.add(newValue);
        }
    }

    public static Object fromXml(final String xml) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public enum FromType {

        FOR_CONVERT, FOR_FORMAT
    }

    public static Object fromXml(final String xml, final FromType fromType) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    private static boolean checkResult(final String xml, org.w3c.dom.Document document, final Object result, final FromType fromType) {
        final Map<String, String> headerAttributes = getHeaderAttributes(xml);
        if (document.getXmlEncoding() != null && !"UTF-8".equalsIgnoreCase(document.getXmlEncoding())) {
            ((Map) result).put(ENCODING, document.getXmlEncoding());
            if (headerAttributes.containsKey(STANDALONE.substring(1))) {
                ((Map) result).put(STANDALONE, headerAttributes.get(STANDALONE.substring(1)));
            }
        } else if (headerAttributes.containsKey(STANDALONE.substring(1))) {
            ((Map) result).put(STANDALONE, headerAttributes.get(STANDALONE.substring(1)));
        } else if (fromType == FromType.FOR_CONVERT && Xml.XmlValue.getMapKey(result).equals(ROOT) && (Xml.XmlValue.getMapValue(result) instanceof List || Xml.XmlValue.getMapValue(result) instanceof Map)) {
            if (xml.startsWith(XML_HEADER)) {
                return true;
            } else {
                ((Map) result).put(OMITXMLDECLARATION, YES);
            }
        } else if (!xml.startsWith(XML_HEADER)) {
            ((Map) result).put(OMITXMLDECLARATION, YES);
        }
        return false;
    }

    private static Map<String, String> getHeaderAttributes(final String xml) {
        final Map<String, String> result = new LinkedHashMap<>();
        if (xml.startsWith(XML_HEADER)) {
            final String xmlLocal = xml.substring(XML_HEADER.length(), Math.max(XML_HEADER.length(), xml.indexOf("?>", XML_HEADER.length())));
            final Map<String, String> attributes = parseAttributes(xmlLocal);
            result.putAll(attributes);
        }
        return result;
    }

    static String getDoctypeValue(final String xml) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private static class MyEntityResolver implements org.xml.sax.EntityResolver {

        public org.xml.sax.InputSource resolveEntity(String publicId, String systemId) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    protected static class Document {

        private Document() {
        }

        public static org.w3c.dom.Document createDocument(final String xml) throws java.io.IOException, javax.xml.parsers.ParserConfigurationException, org.xml.sax.SAXException {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        private static org.w3c.dom.Document createDocument() {
            try {
                final javax.xml.parsers.DocumentBuilderFactory factory = javax.xml.parsers.DocumentBuilderFactory.newInstance();
                factory.setNamespaceAware(true);
                setupFactory(factory);
                final javax.xml.parsers.DocumentBuilder builder = factory.newDocumentBuilder();
                return builder.newDocument();
            } catch (javax.xml.parsers.ParserConfigurationException ex) {
                throw new IllegalArgumentException(ex);
            }
        }

        private static void setupFactory(javax.xml.parsers.DocumentBuilderFactory factory) {
            try {
                factory.setFeature(javax.xml.XMLConstants.FEATURE_SECURE_PROCESSING, true);
            } catch (Exception ignored) {
                // ignored
            }
        }
    }

    public static Object fromXmlMakeArrays(final String xml) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static Object fromXmlWithElementMapper(final String xml, final BiFunction<Object, Set<String>, String> elementMapper) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static Object fromXmlWithoutNamespaces(final String xml) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static Object fromXmlWithoutAttributes(final String xml) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static Object fromXmlWithoutNamespacesAndAttributes(final String xml) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static String formatXml(String xml, XmlStringBuilder.Step identStep) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static String formatXml(String xml) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    public static String changeXmlEncoding(String xml, XmlStringBuilder.Step identStep, String encoding) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static String changeXmlEncoding(String xml, String encoding) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
