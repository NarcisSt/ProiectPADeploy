package com.project.deploy.algorithm;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.ArrayUtils;

import java.text.Normalizer;
import java.util.*;

@Getter
@Setter
public class Algorithm extends Graph {
    private String[] field1 = {};
    private String[] field2 = {};
    private String[] field3 = {};
    private String[] field4 = {};
    private String[] field5 = {};
    private LinkedList<String> correctAddress = new LinkedList<>();
    private HashMap<String, ArrayList<String>> cityMap = new HashMap<>();

    public Algorithm() {
        super();
    }

    public void start() {
        findCountry();
        findCity();
    }

    private void findCountry() {
        boolean find = false;
        Set<String> countries = terra.keySet();
        for (String words : field1) {
            String stripped = normalize(words);
            if (countries.contains(stripped)) {
                correctAddress.add(words.trim());
                cityMap = terra.get(stripped);
                int index = ArrayUtils.indexOf(field1, words);
                field1 = ArrayUtils.remove(field1, index);
                find = true;
                break;
            }
        }
        if (!find) {
            for (String words : field2) {
                String stripped = normalize(words);
                if (countries.contains(stripped)) {
                    correctAddress.add(words.trim());
                    cityMap = terra.get(stripped);
                    int index = ArrayUtils.indexOf(field2, words);
                    field2 = ArrayUtils.remove(field2, index);
                    find = true;
                    break;
                }
            }
        }
        if (!find) {
            for (String words : field3) {
                String stripped = normalize(words);
                if (countries.contains(stripped)) {
                    correctAddress.add(words.trim());
                    cityMap = terra.get(stripped);
                    int index = ArrayUtils.indexOf(field3, words);
                    field3 = ArrayUtils.remove(field3, index);
                    find = true;
                    break;
                }
            }
        }
        if (!find) {
            for (String words : field4) {
                String stripped = normalize(words);
                if (countries.contains(stripped)) {
                    correctAddress.add(words.trim());
                    cityMap = terra.get(stripped);
                    int index = ArrayUtils.indexOf(field4, words);
                    field4 = ArrayUtils.remove(field4, index);
                    find = true;
                    break;
                }
            }
        }
        if (!find) {
            for (String words : field5) {
                String stripped = normalize(words);
                if (countries.contains(stripped)) {
                    correctAddress.add(words.trim());
                    cityMap = terra.get(stripped);
                    int index = ArrayUtils.indexOf(field5, words);
                    field5 = ArrayUtils.remove(field5, index);
                    find = true;
                    break;
                }
            }
        }
        if (!find) {
            correctAddress.add("unknown");
        }

    }

    private void findCity() {
        Set<String> keySet = cityMap.keySet();
        List<String> posibleCity = new ArrayList<>();
        List<String> lowerPosibleCity = new ArrayList<>();

        for (String words : field1) {
            String stripped = normalize(words);
            if (keySet.contains(stripped) && !lowerPosibleCity.contains(stripped)) {
                posibleCity.add(words.trim());
                lowerPosibleCity.add(stripped);
                int index = ArrayUtils.indexOf(field1, words);
                field1 = ArrayUtils.remove(field1, index);

            }
        }

        for (String words : field2) {
            String stripped = normalize(words);
            if (keySet.contains(stripped) && !lowerPosibleCity.contains(stripped)) {
                posibleCity.add(words.trim());
                lowerPosibleCity.add(stripped);
                int index = ArrayUtils.indexOf(field2, words);
                field2 = ArrayUtils.remove(field2, index);
            }
        }


        for (String words : field3) {
            String stripped = normalize(words);
            if (keySet.contains(stripped) && !lowerPosibleCity.contains(stripped)) {
                posibleCity.add(words.trim());
                lowerPosibleCity.add(stripped);
                int index = ArrayUtils.indexOf(field3, words);
                field3 = ArrayUtils.remove(field3, index);

            }
        }

        for (String words : field4) {
            String stripped = normalize(words);
            if (keySet.contains(stripped) && !lowerPosibleCity.contains(stripped)) {
                posibleCity.add(words.trim());
                lowerPosibleCity.add(stripped);
                int index = ArrayUtils.indexOf(field4, words);
                field4 = ArrayUtils.remove(field4, index);

            }
        }

        for (String words : field5) {
            String stripped = normalize(words);
            if (keySet.contains(stripped) && !lowerPosibleCity.contains(stripped)) {
                posibleCity.add(words.trim());
                lowerPosibleCity.add(stripped);
                int index = ArrayUtils.indexOf(field5, words);
                field5 = ArrayUtils.remove(field5, index);
            }
        }
        if (posibleCity.size() == 0) {
            correctAddress.add("unknown"); //for city
            correctAddress.add("unknown"); //for locality
        } else {
            boolean findLoc = false;
            findLoc = findLocality(findLoc, posibleCity, lowerPosibleCity);
            if (!findLoc) {
                correctAddress.add(posibleCity.get(0));
                correctAddress.add("unknown");
            }
        }
    }

    private boolean findLocality(boolean findLoc, List<String> posibleCity, List<String> lowerPosibleCity){
        for(String city: lowerPosibleCity){
            ArrayList<String> localities = cityMap.get(city);
            findLoc = findInFieldLocality(findLoc,field1, city, localities, posibleCity, lowerPosibleCity);
            findLoc = findInFieldLocality(findLoc,field2, city, localities, posibleCity, lowerPosibleCity);
            findLoc = findInFieldLocality(findLoc,field3, city, localities, posibleCity, lowerPosibleCity);
            findLoc = findInFieldLocality(findLoc,field4, city, localities, posibleCity, lowerPosibleCity);
            findLoc = findInFieldLocality(findLoc,field5, city, localities, posibleCity, lowerPosibleCity);

        }

        return findLoc;
    }

    public String normalize(String words){
        String stripped;
        stripped = words.trim();
        stripped = Normalizer.normalize(stripped, Normalizer.Form.NFD);
        stripped = stripped.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
        stripped = stripped.toLowerCase();

        return stripped;
    }

    private boolean findInFieldLocality(boolean findLoc, String[] field, String city, ArrayList<String> localities, List<String> posibleCity, List<String> lowerPosibleCity ){
        if (!findLoc) {
            for (String words : field) {
                String stripped = normalize(words);
                if (localities.contains(stripped)) {
                    correctAddress.add(posibleCity.get(lowerPosibleCity.indexOf(city)));
                    correctAddress.add(words.trim());
                    findLoc = true;
                    break;
                }
            }
        }
        return findLoc;
    }

    @Override
    public String toString() {
        return "Algorithm{" +
                "field1=" + Arrays.toString(field1) +
                ", field2=" + Arrays.toString(field2) +
                ", field3=" + Arrays.toString(field3) +
                ", field4=" + Arrays.toString(field4) +
                ", field5=" + Arrays.toString(field5) +
                '}';
    }
}
