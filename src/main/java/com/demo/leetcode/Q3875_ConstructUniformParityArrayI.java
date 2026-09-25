package com.demo.leetcode;

public class Q3875_ConstructUniformParityArrayI {
    private boolean isAllEvenOrOdd(int[] nums1){
        boolean isAtLeastOneEven=false, isAtLeastOneOdd=false;
        for(int i=0; i< nums1.length; i++){
            if(nums1[i]%2==0){
                isAtLeastOneEven=true;
            }else{
                isAtLeastOneOdd=true;
            }
            if(isAtLeastOneEven && isAtLeastOneOdd) return false;
        }
        return true;
    }
    public boolean uniformArray(int[] nums1) {

        if(isAllEvenOrOdd(nums1)) return true;

        // check how many even or odd numbers we can make
        int oddCount=0;
        int evenCount=0;

        for(int i=0; i<nums1.length; i++){
            for(int j=i; j<nums1.length; j++){
                if(i==j){
                    if(nums1[i]%2==0){
                        evenCount++;
                    }else {
                        oddCount++;
                    }
                }else{
                    if((nums1[i]-nums1[j])%2==0 || nums1[i]%2==0){
                        evenCount ++;
                    }
                    if((nums1[i]-nums1[j])%2!=0 || nums1[i]%2!=0){
                        oddCount++;
                    }
                }
            }
        }

        if(oddCount>= nums1.length || evenCount>= nums1.length) return true;
        return false;
    }
}
