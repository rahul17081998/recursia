package com.demo.hashmap.case1_no_override;

/**
 * Case 1: NO hashCode() / equals() override.
 * Default hashCode() is memory-address based → two objects with same values
 * go to different buckets → DUPLICATE added to HashSet.
 */
public class PointWithout {
    public int x, y;

    public PointWithout(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Point(" + x + ", " + y + ") @" + Integer.toHexString(System.identityHashCode(this));
    }
}
