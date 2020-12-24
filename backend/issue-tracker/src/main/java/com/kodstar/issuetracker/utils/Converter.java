package com.kodstar.issuetracker.utils;


import java.util.List;
import java.util.stream.Collectors;

@FunctionalInterface
public interface Converter<T, R> {

    R convert(T t);

    default List<R> convertAll(List<T> list) {
        return list.stream()
                .map(x -> convert(x))
                .collect(Collectors.toList());
    }
}
