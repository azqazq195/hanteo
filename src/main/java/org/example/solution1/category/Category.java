package org.example.solution1.category;

public class Category {
    private final int id;
    private String name;

    public Category(String name) {
        this.id = CategoryIdGenerator.getInstance().nextIndex();
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

