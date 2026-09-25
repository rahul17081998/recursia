package com.demo.hashmap.case2_only_equals;

/**
 * Case 2: ONLY equals() overridden — hashCode() is still default (memory-based).
 * equals() returns true for same values, but different hashCode() puts objects
 * in different buckets → equals() never called → DUPLICATE added.
 * Breaks the contract: equal objects MUST have same hashCode.
 */
public class PointOnlyEquals {
    public int x, y;

    public PointOnlyEquals(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof PointOnlyEquals)) return false;
        PointOnlyEquals other = (PointOnlyEquals) o;
        return x == other.x && y == other.y;
    }

    @Override
    public String toString() {
        return "Point(" + x + ", " + y + ") @" + Integer.toHexString(System.identityHashCode(this));
    }
}
