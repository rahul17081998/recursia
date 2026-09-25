package com.demo.hashmap.case3_both_overridden;

import java.util.Objects;

/**
 * Case 3: BOTH hashCode() and equals() correctly overridden.
 * Same values → same hashCode → same bucket → equals() called → duplicate rejected.
 */
public class PointWith {
    public int x, y;

    public PointWith(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PointWith)) return false;
        PointWith other = (PointWith) o;
        return x == other.x && y == other.y;
    }

    @Override
    public String toString() {
        return "Point(" + x + ", " + y + ")";
    }
}
