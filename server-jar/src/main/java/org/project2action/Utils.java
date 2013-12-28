package org.project2action;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.*;

public class Utils {

    public static <T> Set<T> returnNotNull(Set<T> set) {
        return set == null? Collections.<T>emptySet():set;
    }

    public static <T> Set<T> initializeIfNull(Set<T> set) {
        return set == null ? new HashSet<T>(): set;
    }

    public static <T> List<T> initializeIfNull(List<T> set) {
        return set == null ? new ArrayList<T>(): set;
    }

    public static List<Class<?>> asList(Class<?>... classes) {
        return Lists.newArrayList(classes);
    }

    public static <T> Set<T> asSet(T... set) {
        return Sets.newHashSet(set);
    }

    public static boolean isNotEmpty(String s){
        return s != null && s.length() != 0 && s.trim().length() != 0;
    }
}
