package src.algorithm.slidingwindow.hard;

import java.util.HashMap;
import java.util.Map;

public class MinimumWindowSubstring {

    public static void main(String[] args) {
        MinimumWindowSubstring m = new MinimumWindowSubstring();
        m.minWindow("ADOBECODEBANC","ABC");
    }
    public String minWindow(String s, String t) {

        // make a count map for each element in t. This will denote how many count you would need to reach t from s
        Map<Character,Integer> need = new HashMap<>();
        for(int i=0;i<t.length();i++){
            int count = need.getOrDefault(t.charAt(i),0);
            need.put(t.charAt(i),++count);
        }
        //denotes how many elements from t are missing in the start and end range. Initially all are missing
        int missing = t.length();
        int minStart = 0;
        int minEnd = s.length()+1;
        int start = 0;
        int end = 0;

        while(end<s.length()){
            char c = s.charAt(end);
            //if the current element is part of t, then reduce the count from map and decrease the missing count
            if(need.containsKey(c)){
                need.put(c,need.get(c)-1);
                if(need.get(c)>=0)
                    missing -=1;

                //keep on incresing start. if element is not part of t then simply increase or else
                // increase missing and need map and check if the start and end length is less than the
                // current minimum length.
                while(missing == 0){
                    if(end-start+1<minEnd-minStart+1){
                        minStart = start;
                        minEnd = end;
                    }
                    char left_c = s.charAt(start);
                    if(need.containsKey(left_c)){
                        need.put(left_c,need.get(left_c)+1);
                        if(need.get(left_c)>0)
                            missing +=1;
                    }
                    start++;
                }
            }
            end++;
        }
        return minEnd == s.length()+1 ? "" : s.substring(minStart,minEnd+1);
    }
}
