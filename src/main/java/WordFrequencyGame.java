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

                String[] arr = sentence.split(BLANK_REGEX);

                List<Input> inputList = new ArrayList<>();
                for (String s : arr) {
                    Input input = new Input(s, 1);
                    inputList.add(input);
                }

                Map<String, List<Input>> map = getListMap(inputList);

                List<Input> list = new ArrayList<>();
                for (Map.Entry<String, List<Input>> entry : map.entrySet()) {
                    Input input = new Input(entry.getKey(), entry.getValue().size());
                    list.add(input);
                }
                inputList = list;

                inputList.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount());

                StringJoiner joiner = new StringJoiner(DELIMITER);
                for (Input w : inputList) {
                    String s = w.getValue() + STRING + w.getWordCount();
                    joiner.add(s);
                }
                return joiner.toString();
            } catch (Exception e) {
                return CALCULATE_ERROR;
            }
        }
    }

    private Map<String, List<Input>> getListMap(List<Input> inputList) {
        Map<String, List<Input>> map = new HashMap<>();
        for (Input input : inputList) {
            if (!map.containsKey(input.getValue())) {
                ArrayList arr = new ArrayList<>();
                arr.add(input);
                map.put(input.getValue(), arr);
            } else {
                map.get(input.getValue()).add(input);
            }
        }
        return map;
    }
}
