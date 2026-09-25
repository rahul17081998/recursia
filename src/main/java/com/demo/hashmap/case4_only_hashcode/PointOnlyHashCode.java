package com.demo.hashmap.case4_only_hashcode;

import java.util.Objects;

/**
 * Case 4: ONLY hashCode() overridden — equals() is still default (reference equality ==).
 *
 * What happens:
 *   Step 1 → hashCode(): same values → same hash → SAME bucket ✅
 *   Step 2 → equals():   a == b → FALSE (different object references) ❌
 *
 * Result: both objects land in the same bucket as a collision chain,
 * but equals() never confirms they are logically equal → DUPLICATE added.
 *
 * Bucket state after adding both:
 *   bucket[X] → Node(p1, Point(1,2)) → Node(p2, Point(1,2))
 *               ↑ collision chain — equals() said they are different objects
 */
public class PointOnlyHashCode {
    public int x, y;

    public PointOnlyHashCode(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y); // ✅ overridden — same values → same hash → same bucket
    }

    // equals() NOT overridden → uses Object's == (reference equality)
    // Two separate instances are never == even if x and y are identical

    @Override
    public String toString() {
        return "Point(" + x + ", " + y + ") hashCode=" + hashCode()
                + " @" + Integer.toHexString(System.identityHashCode(this));
    }
}
