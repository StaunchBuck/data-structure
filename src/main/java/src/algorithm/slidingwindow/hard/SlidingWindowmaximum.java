package src.algorithm.slidingwindow.hard;

/*
* Using Heap
* */
import java.util.ArrayList;
import java.util.List;
public class SlidingWindowmaximum {

    public static void main(String[] args) {
        SlidingWindowmaximum o = new SlidingWindowmaximum();
        for(int i:o.maxSlidingWindow(new int[]{1,3,-1,-3,5,3,6,7},3)){
            System.out.print(i+" ");
        }
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        int start = 0;
        int end = k-1;
        List<Integer> list = new ArrayList<>();
        while(end<nums.length){
            int[] temp = new int[k];
            for(int i=0;i<k;i++){
                temp[i]=nums[start+i];
            }
            maxHeap(temp,0,k-1);
            list.add(temp[0]);
            start++;
            end++;
        }
        int[] res = new int[list.size()];
        for(int i=0;i<list.size();i++){
            res[i]=list.get(i);
        }
        return res;
    }

    private void maxHeap(int[] nums, int start, int end) {
        int length = end-start;
        for(int i = length/2;i>=0;i--){
            downHeapify(nums,i,end+1);
        }
    }

    private void downHeapify(int[] nums, int pos,int len) {
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

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
