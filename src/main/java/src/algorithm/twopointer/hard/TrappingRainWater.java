package src.algorithm.twopointer.hard;
//https://leetcode.com/problems/trapping-rain-water/
//O(n) time and O(1) space
public class TrappingRainWater {

    public int trap(int[] height) {
        int left = 0;
        int right = height.length-1;
        int maxLeft = height[left];
        int maxRight = height[right];
        int res = 0;
        while(left<right){
            if(maxLeft>maxRight){
                right--;
                maxRight = Math.max(maxRight,height[right]);
                res +=maxRight-height[right];
            }else{
                left++;
                maxLeft = Math.max(maxLeft,height[left]);
                res +=maxLeft-height[left];
            }
        }
        return res;
    }
}

//O(n) time and O(n) space
class TrappingRainWater2 {

    public int trap(int[] height) {
        int trapped = 0;
        int n = height.length;

        int [] leftmax = new int[n];
        int [] rightmax = new int[n];

        leftmax[0] = height[0];
        for (int i = 1; i < n; i++) {
            leftmax[i] = Math.max(leftmax[i-1], height[i]);
        }

        rightmax[n-1] = height[n-1];
        for (int i = n-2; i >= 0 ; i--) {
            rightmax[i] = Math.max(rightmax[i+1], height[i]);
        }
        for (int i = 0; i < n; i++) {
            int water = Math.min(leftmax[i], rightmax[i]) - height[i];

            if(water>0){
                trapped += water;
            }
        }

        return trapped;
    }
}

//O(n sqr) time and O(1) space
class TrappingRainWater3 {

    public int trap(int[] height) {
        if(height.length<3)
            return 0;

        int res = 0;
        for(int i=2;i<height.length;i++){
            if(height[i]>height[i-1]){
                int lastHeighest = i-1;
                int right = i;
                for(int left=right-2;left>=0;left--){
                    if(height[left]>=height[lastHeighest]){
                        int unitsApart = right-left-1;
                        int minHeight = Math.min(height[right],height[left]);
                        int areaCovered = unitsApart*minHeight;
                        int minusArea = unitsApart*height[lastHeighest];
                        res +=(areaCovered-minusArea);
                        lastHeighest = left;
                        if(height[left]>=height[right])
                            break;
                    }
                }
            }
        }
        return res;
    }
}
