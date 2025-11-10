package src.algorithm.arrayAndstring;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/basic-calculator-ii/
public class BasicCalculator2 {

    public static void main(String[] args) {
        BasicCalculator2 vc = new BasicCalculator2();
        System.out.println(vc.calculate("4-9"));
    }
    public int calculate(String s) {

        StringBuilder input = new StringBuilder();
        for(int i=0;i<s.length();i++){
            if(s.charAt(i) != ' ')
                input.append(s.charAt(i));
        }
        input.append('&');
        s = input.toString();
        List<Character> operand_seq = new ArrayList<>();
        operand_seq.add('/');
        operand_seq.add('*');
        operand_seq.add('+');
        operand_seq.add('-');
        int pos = 0;
        StringBuilder num1=new StringBuilder();
        StringBuilder num2=new StringBuilder();
        boolean operandFound = false;
        for (char current_operand : operand_seq) {
            pos=0;
            num1=new StringBuilder();
            num2=new StringBuilder();
            operandFound = false;
            while (pos < s.length()) {
                char curr = s.charAt(pos);
                if (curr != '*' && curr != '/' && curr != '+' && curr != '-' && curr != '&') {
                    if (!operandFound) {
                        num1.append(pos);
                    } else {
                        num2.append(pos);
                    }
                } else{
                    if (operandFound) {
                        int n1_start = num1.charAt(0) - '0';
                        int n1_end = num1.charAt(num1.length() - 1) - '0';
                        int n2_start = num2.charAt(0) - '0';
                        int n2_end = num2.charAt(num2.length() - 1) - '0';
                        String n1 = s.substring(n1_start, n1_end + 1);
                        String n2 = s.substring(n2_start, n2_end + 1);
                        String res = "";
                        if (current_operand == '/')
                            res = String.valueOf(Math.abs(Integer.parseInt(n1) / Integer.parseInt(n2)));
                        else if (current_operand == '*')
                            res = String.valueOf(Math.abs(Integer.parseInt(n1) * Integer.parseInt(n2)));
                        else if (current_operand == '+')
                            res = String.valueOf(Math.abs(Integer.parseInt(n1) + Integer.parseInt(n2)));
                        else if (current_operand == '-')
                            res = String.valueOf(Math.abs(Integer.parseInt(n1) - Integer.parseInt(n2)));
                        String s_start = s.substring(0, n1_start);
                        String s_end = pos == s.length() - 1 ? "&" : s.substring(n2_start + 1);
                        s = s_start + res + s_end;
                        pos = n1_start - 1;
                        operandFound = false;
                        num1 = new StringBuilder();
                        num2 = new StringBuilder();
                    } else {
                        if(current_operand == curr)
                            operandFound = true;
                        else{
                            num1 = new StringBuilder();
                            num2 = new StringBuilder();
                        }
                    }
                }
                pos++;
            }
        }
        return Integer.parseInt(s.substring(0,s.length()-1));
    }
}
