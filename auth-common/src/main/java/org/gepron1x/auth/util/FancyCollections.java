package org.gepron1x.auth.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public final class FancyCollections {
    private FancyCollections() { throw new UnsupportedOperationException(); }


    public static <K, V> Map<K, V> toMap(Function<V, K> keyMapper, Collection<V> values) {
        HashMap<K, V> map = new HashMap<>(values.size());
        for(V value : values) {
            map.put(keyMapper.apply(value), value);
        }
        return map;
    }
    @SafeVarargs
    public static <K, V> Map<K, V> toMap(Function<V, K> keyMapper, V... values) {
        return toMap(keyMapper, Arrays.asList(values));
    }
}
