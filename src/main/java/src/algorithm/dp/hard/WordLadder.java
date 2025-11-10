package src.algorithm.dp.hard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordLadder {

    public static void main(String[] args) {
        WordLadder s = new WordLadder();
        System.out.println(s.ladderLength("hit","cog", List.of("hot","dot","dog","lot","log","cog")));
    }
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord))
            return 0;
        List<String> startList = new ArrayList<>();
        startList.add(beginWord);
        Map<String, Integer> dp = new HashMap<>();
        dp.put(endWord, 0);
        int min = calculate(startList, endWord, wordList, new ArrayList<String>(), dp);
        if (min > wordList.size())
            return 0;
        return min;
    }

    public int calculate(List<String> startList, String endWord, List<String> wordList, List<String> block,Map<String, Integer> dp) {

        int localMin = wordList.size();
        if (startList.isEmpty())
            return localMin;
        List<String> possibilities = new ArrayList<>();
        for (String begin : startList) {
            possibilities.clear();
            if (dp.containsKey(begin)) {
                localMin = Math.min(localMin, dp.get(begin));
            } else {
                for (String word : wordList) {
                    if (!block.contains(word)) {
                        int diff = 0;
                        for (int i = 0; i < begin.length(); i++) {
                            if (begin.charAt(i) != word.charAt(i)) {
                                diff++;
                                if (diff > 1)
                                    break;
                            }
                        }
                        if (diff == 1)
                            possibilities.add(word);
                    }
                }
                block.add(begin);
                int min = calculate(possibilities, endWord, wordList, block, dp);
                block.remove(block.size() - 1);
                dp.put(begin, min);
                localMin = Math.min(localMin,min);
            }
        }
        return localMin+1;
    }
}
