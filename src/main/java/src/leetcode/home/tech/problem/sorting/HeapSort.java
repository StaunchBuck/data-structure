package src.leetcode.home.tech.problem.sorting;

import java.util.List;

public class HeapSort {

    public static void main(String[] args) {
        Integer[] nums = new Integer[]{4,8,3,7,0,2,5,3};
        HeapSort obj = new HeapSort();
        obj.maxHeap(nums,0,nums.length-1);
        //we have a maxheap at this point
        int counter = nums.length-1;
        while(counter>=0){
            swap(nums,0,counter);
            downHeapify(nums,0,counter);
            counter--;
        }
        List.of(nums).forEach(System.out::print);
    }

    private void maxHeap(Integer[] nums, int start, int end) {
        int length = end-start;
        for(int i = length/2;i>=0;i--){
            downHeapify(nums,i,nums.length);
        }
    }

    private static void downHeapify(Integer[] nums, int pos,int len) {
        //pos is leaf node
        if(2*pos+1>=len)
            return;
        if(2*pos+2<len){
           //both nodes are present
            int max_pos = nums[2*pos+1] > nums[2*pos+2]  ? 2*pos+1 : 2*pos+2;
            if(nums[max_pos]>nums[pos]){
                swap(nums,pos,max_pos);
                downHeapify(nums,max_pos,len);
            }
        }else{
            //only left node is present
            if(nums[2*pos+1]>nums[pos]){
                swap(nums,pos,2*pos+1);
                downHeapify(nums,2*pos+1,len);
            }
        }
    }

    private static void swap(Integer[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
