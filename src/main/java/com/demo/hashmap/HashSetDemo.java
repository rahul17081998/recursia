package com.demo.hashmap;

import com.demo.hashmap.case1_no_override.PointWithout;
import com.demo.hashmap.case2_only_equals.PointOnlyEquals;
import com.demo.hashmap.case3_both_overridden.PointWith;
import com.demo.hashmap.case4_only_hashcode.PointOnlyHashCode;

import java.util.HashSet;
import java.util.Set;

/**
 * Single main class for all HashSet / HashMap duplicate examples.
 *
 * Run: mvn compile exec:java -Dexec.mainClass="com.demo.hashmap.HashSetDemo"
 *
 * To add a new example:
 *   1. Create a new sub-folder inside hashmap/ (e.g. case4_null_key/)
 *   2. Add your model class there
 *   3. Add a new runCaseX() method here and call it from main()
 */
public class HashSetDemo {

    public static void main(String[] args) {
        System.out.println("==========================================");
        System.out.println(" HashSet Duplicate Behavior — All Cases");
        System.out.println("==========================================\n");

        runCase1();
        runCase2();
        runCase3();
        runCase4();
        printSummary();
    }

    // ------------------------------------------------------------------
    // Case 1: No hashCode/equals override → DUPLICATE added
    // Location: hashmap/case1_no_override/PointWithout.java
    // ------------------------------------------------------------------
    private static void runCase1() {
        System.out.println("--- Case 1: WITHOUT hashCode() & equals() ---");
        Set<PointWithout> set = new HashSet<>();
        PointWithout p1 = new PointWithout(1, 2);
        PointWithout p2 = new PointWithout(1, 2);

        System.out.println("p1             : " + p1);
        System.out.println("p2             : " + p2);
        System.out.println("p1.equals(p2)  : " + p1.equals(p2));        // false
        System.out.println("p1.hashCode()  : " + p1.hashCode());
        System.out.println("p2.hashCode()  : " + p2.hashCode());
        set.add(p1);
        set.add(p2);
        System.out.println("Set size       : " + set.size() + "  <-- DUPLICATE\n");
    }

    // ------------------------------------------------------------------
    // Case 2: Only equals() overridden → DUPLICATE added (broken contract)
    // Location: hashmap/case2_only_equals/PointOnlyEquals.java
    // ------------------------------------------------------------------
    private static void runCase2() {
        System.out.println("--- Case 2: ONLY equals() overridden (broken) ---");
        Set<PointOnlyEquals> set = new HashSet<>();
        PointOnlyEquals p1 = new PointOnlyEquals(1, 2);
        PointOnlyEquals p2 = new PointOnlyEquals(1, 2);

        System.out.println("p1             : " + p1);
        System.out.println("p2             : " + p2);
        System.out.println("p1.equals(p2)  : " + p1.equals(p2));        // true — but...
        System.out.println("p1.hashCode()  : " + p1.hashCode());        // different!
        System.out.println("p2.hashCode()  : " + p2.hashCode());        // different!
        set.add(p1);
        set.add(p2);
        System.out.println("Set size       : " + set.size() + "  <-- DUPLICATE (equals() never called)\n");
    }

    // ------------------------------------------------------------------
    // Case 3: Both hashCode() + equals() overridden → NO duplicate
    // Location: hashmap/case3_both_overridden/PointWith.java
    // ------------------------------------------------------------------
    private static void runCase3() {
        System.out.println("--- Case 3: BOTH hashCode() & equals() overridden ---");
        Set<PointWith> set = new HashSet<>();
        PointWith p1 = new PointWith(1, 2);
        PointWith p2 = new PointWith(1, 2);

        System.out.println("p1             : " + p1);
        System.out.println("p2             : " + p2);
        System.out.println("p1.equals(p2)  : " + p1.equals(p2));        // true
        System.out.println("p1.hashCode()  : " + p1.hashCode());        // same!
        System.out.println("p2.hashCode()  : " + p2.hashCode());        // same!
        boolean added1 = set.add(p1);
        boolean added2 = set.add(p2);
        System.out.println("p1 added?      : " + added1);
        System.out.println("p2 added?      : " + added2 + "  <-- rejected");
        System.out.println("Set size       : " + set.size() + "  <-- NO DUPLICATE\n");
    }

    // ------------------------------------------------------------------
    // Case 4: Only hashCode() overridden → SAME bucket, but DUPLICATE added
    // Location: hashmap/case4_only_hashcode/PointOnlyHashCode.java
    // ------------------------------------------------------------------
    private static void runCase4() {
        System.out.println("--- Case 4: ONLY hashCode() overridden (broken) ---");
        Set<PointOnlyHashCode> set = new HashSet<>();
        PointOnlyHashCode p1 = new PointOnlyHashCode(1, 2);
        PointOnlyHashCode p2 = new PointOnlyHashCode(1, 2);

        System.out.println("p1             : " + p1);
        System.out.println("p2             : " + p2);
        System.out.println("p1.hashCode()  : " + p1.hashCode() + "  <-- same!");
        System.out.println("p2.hashCode()  : " + p2.hashCode() + "  <-- same!");
        System.out.println("p1.equals(p2)  : " + p1.equals(p2) + "  <-- false (uses ==, different refs)");
        System.out.println("p1 == p2       : " + (p1 == p2));

        boolean added1 = set.add(p1);
        boolean added2 = set.add(p2);
        System.out.println("p1 added?      : " + added1);
        System.out.println("p2 added?      : " + added2 + "  <-- also added! (equals() said different)");
        System.out.println("Set size       : " + set.size() + "  <-- DUPLICATE (same bucket, collision chain)\n");
    }

    // ------------------------------------------------------------------
    private static void printSummary() {
        System.out.println("==========================================");
        System.out.printf(" %-44s | Result%n", "Scenario");
        System.out.println("------------------------------------------");
        System.out.printf(" %-44s | DUPLICATE%n", "Case 1: No override");
        System.out.printf(" %-44s | DUPLICATE%n", "Case 2: Only equals() overridden");
        System.out.printf(" %-44s | OK%n",         "Case 3: Both hashCode()+equals() overridden");
        System.out.printf(" %-44s | DUPLICATE%n", "Case 4: Only hashCode() overridden");
        System.out.println("==========================================");
    }
}
