package src.algorithm.arrayAndstring.hard;

import java.util.Arrays;
import java.util.List;
//https://leetcode.com/problems/count-of-smaller-numbers-after-self

public class CountOfSmallerNumbers {

    public static void main(String[] args) {
        CountOfSmallerNumbers obj = new CountOfSmallerNumbers();
        System.out.println(obj.countSmaller(new int[]{0,2,1}));
    }

    public List<Integer> countSmaller(int[] nums) {

        Integer[] res = new Integer[nums.length];
        int start = 0;
        int end = nums.length-1;
        int[] merged = mergeSort(start,end,nums,res);
        Arrays.toString(merged);
        Arrays.toString(res);
        return Arrays.asList(res);
    }

    public int[] mergeSort(int start,int end,int[] nums,Integer[] res){
        if(start==end)
            return new int[]{start};
        int mid = (start+end)/2;
        int[] left = mergeSort(start,mid,nums,res);
        int[] right = mergeSort(mid+1,end,nums,res);
        return merge(left,right,nums,res);
    }

    public int[] merge(int[] left,int[] right,int[] nums,Integer[] res){
        int[] merged = new int[left.length+right.length];
        int l = 0;
        int r = 0;
        int pos = 0;
        while(l<left.length && r<right.length){
            if(nums[right[r]] < nums[left[l]]){
                for(int i=l;i<left.length;i++){
                    res[left[i]]+=1;
                }
                merged[pos++] = right[r++];
            }else{
                merged[pos++] = left[l++];
            }
        }
        if(l<left.length){
            while(l<left.length){
                merged[pos++] = left[l++];
            }
        }
        if(r<right.length){
            while(r<right.length){
                merged[pos++] = right[r++];
            }
        }
        return merged;
    }
}