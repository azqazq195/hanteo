package org.example.solution1.category;

import java.util.concurrent.atomic.AtomicInteger;

public class CategoryIdGenerator {

    private final AtomicInteger index;

    private CategoryIdGenerator() {
        index = new AtomicInteger(0);
    }

    private static class CategoryIdGeneratorHolder {
        private static final CategoryIdGenerator INSTANCE = new CategoryIdGenerator();
    }

    public static CategoryIdGenerator getInstance() {
        return CategoryIdGeneratorHolder.INSTANCE;
    }

    public int nextIndex() {
        return index.incrementAndGet();
    }

    public void clear() {
        index.set(0);
    }
}