package src.algorithm.dp.hard;

public class WildCardMatching {

    public boolean isMatch(String s, String p) {
        if(p.length() == 0){
            if(s.length() == 0)
                return true;
            return false;
        }
        // remove consecutive *. eg ab**c -> ab*c
        StringBuilder pattern = new StringBuilder();
        char curr = p.charAt(0);
        pattern.append(curr);
        for(int i=1;i<p.length();i++){
            if(curr == '*' && p.charAt(i) == curr){
                continue;
            }else{
                curr = p.charAt(i);
                pattern.append(curr);
            }
        }
        boolean[][] res = new boolean[s.length()+1][pattern.length()+1];
        res[0][0] = true;
        if(pattern.charAt(0) == '*')
            res[0][1] = true;
        for(int i=1;i<res.length;i++){
            for(int j=1;j<res[0].length;j++){
                if(s.charAt(i-1) == pattern.charAt(j-1) || pattern.charAt(j-1) == '?'){
                    res[i][j] = res[i-1][j-1];
                }else if(pattern.charAt(j-1) == '*'){
                    res[i][j] = res[i][j-1] || res[i-1][j];
                }
            }
        }
        return res[res.length-1][res[0].length-1];
    }
}