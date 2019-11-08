package at.EliasTrummer.HappyIndicator.BL;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public class HappinessModel {

    private Map<String, List<Integer>> values = new LinkedHashMap<>();

    public HappinessModel() {
        String[] names = new String[]{
                "Homer",
                "Bart",
                "Marge",
                "Lisa"
        };

        List<Integer> amountRandomValues;

        do{
            amountRandomValues = generateNumbers();
        }while(amountRandomValues.contains(0));

        Random rnd = new Random();

        for(int i = 0; i < names.length; i++){
            List<Integer> happinessValues = new ArrayList<>();

            for(int i2 = 0; i2 < amountRandomValues.size(); i2++){
                happinessValues.add(rnd.nextInt(10) + 1);
            }

            values.put(names[i], happinessValues);
        }
    }

    public void addHappinessValue(String name, int value){
        if(!values.containsKey(name)){
            List<Integer> _values = new ArrayList<>();
            _values.add(value);
            values.put(name, _values);
            return;
        }

        values.get(name).add(value);
    }

    public String getTopThreeString(){
        String result = "";

        Map<String, List<String>> averages = new TreeMap<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        });
        int count = 0;

        for(Map.Entry<String, List<Integer>> entry : values.entrySet()){
            double avg = 0;

            for(int val : entry.getValue()){
                avg += val;
            }

            avg /= entry.getValue().size();

            String average = String.format("%.2f", avg);

            if(averages.containsKey(average)){
                averages.get(average).add(entry.getKey());
                continue;
            }

            List<String> names = new ArrayList<>();
            names.add(entry.getKey());
            averages.put(average, names);
        }

        count = 0;

        for(Map.Entry<String, List<String>> entry : averages.entrySet()){
            if(count < 3){
                count++;

                result += entry.getKey() + " - ";

                for(int i = 0; i < entry.getValue().size(); i++){
                    result += entry.getValue().get(i);

                    if(i < (entry.getValue().size() - 1)){
                        result += ", ";
                    }
                }

                result += "\n";
            }
        }

        return result;
    }

    private int getSum(List<Integer> param){
        int sum = 0;
        for(int val : param){
            sum += val;
        }

        return sum;
    }

    private List<Integer> generateNumbers(){
        Random rnd = new Random();
        List<Integer> amountRandomValues = new ArrayList<>();

        while(amountRandomValues.size() < 3){
            int rndNum = rnd.nextInt(17) + 1;

            if(getSum(amountRandomValues) + rndNum < (20 - (3 - amountRandomValues.size()))){
                amountRandomValues.add(rndNum);
            }
        }

        amountRandomValues.add(20 - getSum(amountRandomValues));

        return amountRandomValues;
    }
}
