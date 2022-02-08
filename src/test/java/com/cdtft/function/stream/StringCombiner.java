package com.cdtft.function.stream;


/**
 * @author: wangcheng
 * @date: 2022年02月08 11:11
 */
public class StringCombiner {

    private final static String prefix = "[";

    private final static String delim = ", ";

    public final StringBuilder builder = new StringBuilder();

    public StringCombiner add(String element) {
        if (areAtStart()) {
            builder.append(prefix);
        } else {
            builder.append(delim);
        }
        builder.append(element);
        return this;
    }

    private boolean areAtStart() {
        return builder.length() == 0;
    }

    public StringCombiner merge(StringCombiner other) {
        builder.append(other.builder);
        return this;
    }
}
