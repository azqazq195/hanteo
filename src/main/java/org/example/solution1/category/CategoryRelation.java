package org.example.solution1.category;

public class CategoryRelation {
    private final int parent_idx;
    private final int child_id;

    public CategoryRelation(int parent_idx, int child_id) {
        this.parent_idx = parent_idx;
        this.child_id = child_id;
    }

    public int getParent_idx() {
        return parent_idx;
    }

    public int getChild_id() {
        return child_id;
    }
}
