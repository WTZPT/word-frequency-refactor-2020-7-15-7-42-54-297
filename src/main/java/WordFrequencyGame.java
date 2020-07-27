import java.util.*;

public class WordFrequencyGame {

    public static final String BLANK_REGEX = "\\s+";
    public static final String DELIMITER = "\n";
    public static final String CALCULATE_ERROR = "Calculate Error";
    public static final String STRING = " ";

    public String getResult(String sentence) {


        if (sentence.split(BLANK_REGEX).length == 1) {
            return sentence + " 1";
        } else {

            try {

                List<WordInfo> wordInfoList = getWordInfoList(sentence);

                return generatorResult(wordInfoList);
            } catch (Exception e) {
                return CALCULATE_ERROR;
            }
        }
    }

    private String generatorResult(List<WordInfo> wordInfoList) {
        StringJoiner joiner = new StringJoiner(DELIMITER);
        for (WordInfo w : wordInfoList) {
            String s = w.getValue() + STRING + w.getWordCount();
            joiner.add(s);
        }
        return joiner.toString();
    }


    private List<WordInfo> getWordInfoList(String sentence) {
        String[] words = sentence.split(BLANK_REGEX);

        List<WordInfo> wordInfoList = new ArrayList<>();
        for (String s : words) {
            WordInfo wordInfo = new WordInfo(s, 1);
            wordInfoList.add(wordInfo);
        }

        Map<String, List<WordInfo>> wordToWordInfo = getListMap(wordInfoList);

        List<WordInfo> list = new ArrayList<>();
        for (Map.Entry<String, List<WordInfo>> entry : wordToWordInfo.entrySet()) {
            WordInfo wordInfo = new WordInfo(entry.getKey(), entry.getValue().size());
            list.add(wordInfo);
        }
        wordInfoList = list;
        wordInfoList.sort((firstWordInfo, secondWordInfo) -> secondWordInfo.getWordCount() - firstWordInfo.getWordCount());
        return wordInfoList;
    }

    private Map<String, List<WordInfo>> getListMap(List<WordInfo> wordInfoList) {
        Map<String, List<WordInfo>> map = new HashMap<>();
        for (WordInfo wordInfo : wordInfoList) {
            if (!map.containsKey(wordInfo.getValue())) {
                ArrayList arr = new ArrayList<>();
                arr.add(wordInfo);
                map.put(wordInfo.getValue(), arr);
            } else {
                map.get(wordInfo.getValue()).add(wordInfo);
            }
        }
        return map;
    }
}
