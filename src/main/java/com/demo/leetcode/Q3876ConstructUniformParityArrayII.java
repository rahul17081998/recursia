package com.demo.leetcode;

import java.util.Arrays;

public class Q3876ConstructUniformParityArrayII {

    public boolean uniformArray(int[] nums1) {

        Arrays.sort(nums1);
        int minValue=Integer.MAX_VALUE;
        boolean isOddNo=false;
        for(int i=0; i< nums1.length; i++){
            minValue=Math.min(minValue, nums1[i]);
            if(i!=0 && nums1[i]%2!=0) isOddNo=true;
        }
        if(nums1.length==1 || minValue%2!=0) return true; // smallest number is odd
//        if first number is not odd but we have other odd numbers
        if(minValue%2==0 && isOddNo) return false;

        return true;


    }
}
