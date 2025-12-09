package src.algorithm.backtracking;

import java.util.ArrayList;
import java.util.List;

public class PalindromPartitioning {

    public static void main(String[] args) {
        PalindromPartitioning p = new PalindromPartitioning();
        System.out.println(p.partition("aab"));
    }

    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        List<String> subList = new ArrayList<>();

        check(0,s,s.length(),subList,res);
        return res;
    }

    public void check(int start,String s,int len,List<String> subList,List<List<String>> res){
        if(start>=len){
            List<String> copy = subList.stream().toList();
//            System.out.println(copy);
            res.add(copy);
        }else{
            for(int i=start;i<len;i++){
                if(isPalindrom(s,start,i)){
                    subList.add(s.substring(start,i+1));
                    check(i+1,s,len,subList,res);
                    subList.remove(subList.size()-1);
                }
            }
        }
    }

    public boolean isPalindrom(String s,int i,int j){
        while(i<=j){
            if(s.charAt(i++) != s.charAt(j--))
                return false;
        }
        return true;
    }
}