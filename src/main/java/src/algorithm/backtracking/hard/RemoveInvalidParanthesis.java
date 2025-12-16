package src.algorithm.backtracking.hard;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RemoveInvalidParanthesis {
    int minRemoved = Integer.MAX_VALUE;
    public static void main(String[] args) {
        RemoveInvalidParanthesis o = new RemoveInvalidParanthesis();
        System.out.println(o.removeInvalidParentheses(")(f"));
    }
    public List<String> removeInvalidParentheses(String s) {
        Set<String> res = new HashSet<>();
        calculate(s,0,0,0,0,new StringBuilder(),res);
        return new ArrayList<String>(res);
    }

    public void calculate(String s,int pos,int oCount,int cCount,int remCount,StringBuilder tempPar,Set<String> res){
        if(oCount<cCount || remCount>minRemoved)
            return;
        if(pos == s.length()){
            if(oCount == cCount){
                if(remCount<minRemoved){
                    res.clear();
                    minRemoved = remCount;
                }
                res.add(tempPar.toString());
            }
        }
        else{
            if(s.charAt(pos) != '(' && s.charAt(pos) != ')'){
                tempPar.append(s.charAt(pos));
                calculate(s,pos+1,oCount,cCount,remCount,tempPar,res);
                tempPar.deleteCharAt(tempPar.length()-1);
            }else{
                int newOcount = s.charAt(pos) == '(' ? oCount+1 : oCount;
                int newCcount = s.charAt(pos) == ')' ? cCount+1 : cCount;
                tempPar.append(s.charAt(pos));
                calculate(s,pos+1,newOcount,newCcount,remCount,tempPar,res);
                tempPar.deleteCharAt(tempPar.length()-1);
                calculate(s,pos+1,oCount,cCount,remCount+1,tempPar,res);
            }
        }
    }
}