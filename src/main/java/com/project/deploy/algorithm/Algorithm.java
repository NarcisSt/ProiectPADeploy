package com.project.deploy.algorithm;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.ArrayUtils;

import java.text.Normalizer;
import java.util.*;

@Getter
@Setter
/**
 * the algorithm class is the main class that find the correct address
 * it extends the graph class because we need the map that is created every time we call the constructor of this class
 * 
 * -> the private fields 1 - 5 means the fields from form page html, the country, city, locality, postal_code, rest of address
 * there are String[] to make the parse after ','
 * -> the correctAddress list is the final result after look after Strings
 * -> first time i look after country, which can be all name or the short name(Romania / RO ) , upper or lower cases
 * if i find the country, i  save his map of cities and localities in cityMap HashMap
 *
 */
public class Algorithm extends Graph {
    private String[] field1 = {};
    private String[] field2 = {};
    private String[] field3 = {};
    private String[] field4 = {};
    private String[] field5 = {};
    private LinkedList<String> correctAddress = new LinkedList<>();
    private HashMap<String, ArrayList<String>> cityMap = new HashMap<>();

    /**
     * here is called the super class constructor where is created the map of countries
     */
    public Algorithm() {
        super();
    }

    /**
     * this is the start of creating correct address
     * 
     * first I look after country
     * if I find it I save the country's cityMap 
     * else i save in correctAddress "unknown" because i did not find it
     * 
     * even if i did not find the country, i still call the findCity() function
     * where i look after city and then if it's possible, after locality
     */
    public void start() {
        findCountry();
        findCity();
    }

    /**
     * ! the idea of all future methods and why I have so much copy code is:
     *  -> i look in every field for matching words
     * if i did not find in first one, I look in other, and so on to the last one
     * because are String[], i can't put that for in some function and call with every field
     * because will be passed-by-value, and if I remove some word from it, the actual field from this class, will not be modified
     * and the future calling of that array will be in the same form
     *  -> i use the boolean find / findLoc.. because, if I find what i need in one of that field, i did not need to look in the next ones
     * 
     * ! in the other hand:
     * the map from Graph class is this type : Map \<Country, Map\<City, Locality\> \>
     *     so when I find the country -> i get his map with cities
     *     
     */
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

    /**
     * this method is looking after city, if i find it i look after locality, if i did not find, i put unknown
     * 
     * the keySet is for taking the country's cities as Set to iterate through them i find a match
     * but, the client can write a word like this: MeHEdinTi, so this word is not in our set, but i can understand what he want to say
     * so i save the word in possibleCity List and in lowerPossibleCity the lower word
     * 
     * but this time i did not use a boolean value to stop after first match because I look after all possible address match in all fields
     * if this lists are empty, i can't find possible city so i put on correctAddress unknown for both city and locality
     *    else i look for every city i found, for locality
     */
    private void findCity() {
        Set<String> keySet = cityMap.keySet();
        List<String> possibleCity = new ArrayList<>();
        List<String> lowerPossibleCity = new ArrayList<>();

        for (String words : field1) {
            String stripped = normalize(words);
            if (keySet.contains(stripped) && !lowerPossibleCity.contains(stripped)) {
                possibleCity.add(words.trim());
                lowerPossibleCity.add(stripped);
                int index = ArrayUtils.indexOf(field1, words);
                field1 = ArrayUtils.remove(field1, index);

            }
        }

        for (String words : field2) {
            String stripped = normalize(words);
            if (keySet.contains(stripped) && !lowerPossibleCity.contains(stripped)) {
                possibleCity.add(words.trim());
                lowerPossibleCity.add(stripped);
                int index = ArrayUtils.indexOf(field2, words);
                field2 = ArrayUtils.remove(field2, index);
            }
        }


        for (String words : field3) {
            String stripped = normalize(words);
            if (keySet.contains(stripped) && !lowerPossibleCity.contains(stripped)) {
                possibleCity.add(words.trim());
                lowerPossibleCity.add(stripped);
                int index = ArrayUtils.indexOf(field3, words);
                field3 = ArrayUtils.remove(field3, index);

            }
        }

        for (String words : field4) {
            String stripped = normalize(words);
            if (keySet.contains(stripped) && !lowerPossibleCity.contains(stripped)) {
                possibleCity.add(words.trim());
                lowerPossibleCity.add(stripped);
                int index = ArrayUtils.indexOf(field4, words);
                field4 = ArrayUtils.remove(field4, index);

            }
        }

        for (String words : field5) {
            String stripped = normalize(words);
            if (keySet.contains(stripped) && !lowerPossibleCity.contains(stripped)) {
                possibleCity.add(words.trim());
                lowerPossibleCity.add(stripped);
                int index = ArrayUtils.indexOf(field5, words);
                field5 = ArrayUtils.remove(field5, index);
            }
        }
        if (possibleCity.size() == 0) {
            correctAddress.add("unknown"); //for city
            correctAddress.add("unknown"); //for locality
        } else {
            boolean findLoc = false;
            findLoc = findLocality(findLoc, possibleCity, lowerPossibleCity);
            if (!findLoc) {
                correctAddress.add(possibleCity.get(0));
                correctAddress.add("unknown");
            }
        }
    }

    /**
     * if i found possible cities, i look after locality
     * i use this function because it is the last one called so i did not reuse the private fields other time and them will be unchanged
     *  i look for every city i found as possible(in lower case to match the graph's map), when i find the first locality then this is the correct address
     *  (i did not look for all possible match because then i need to look after postal_code or other field and it is not in our need, now
     *
     *  if i did not find, then unknown, but i put for city the first one from my list
     *
     * @param findLoc      to pass it back in find city function
     * @param posibleCity  the list of possible cities i found
     * @param lowerPosibleCity the list of possible cities i found but in lower case
     *
     * @return  true if i find the correct address, or false if i didn't
     */
    private boolean findLocality(boolean findLoc, List<String> posibleCity, List<String> lowerPosibleCity){
        for(String city: lowerPosibleCity){
            ArrayList<String> localities = cityMap.get(city);
            String cityAsClientWrite = posibleCity.get(lowerPosibleCity.indexOf(city));
            findLoc = findInFieldLocality(findLoc,field1, cityAsClientWrite ,localities);
            findLoc = findInFieldLocality(findLoc,field2, cityAsClientWrite, localities);
            findLoc = findInFieldLocality(findLoc,field3, cityAsClientWrite, localities);
            findLoc = findInFieldLocality(findLoc,field4, cityAsClientWrite, localities);
            findLoc = findInFieldLocality(findLoc,field5, cityAsClientWrite, localities);

        }

        return findLoc;
    }

    /**
     * this method i use to format the word
     * first i trim it -- erase the white spaces from beginning and end
     * then i get rid of accent letters -> ă, î , â, ș , ț and others
     * and for final i transform it in lowercase -> MeHEdinTi -> mehedinti
     *
     * @param words   -> the word to apply the rules
     * @return        -> lower world without accents
     */
    public String normalize(String words){
        String stripped;
        stripped = words.trim();
        stripped = Normalizer.normalize(stripped, Normalizer.Form.NFD);
        stripped = stripped.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
        stripped = stripped.toLowerCase();

        return stripped;
    }

    /**
     * same as in the cityFind but it is individually for every field i pass as parameter
     *
     * @param findLoc      -- if i found the correct address or not
     * @param field        -- in which filed i look after matching words
     * @param city         -- the city in client format to add to correctAddress
     * @param localities   -- all city's localities
     *
     * @return
     */
    private boolean findInFieldLocality(boolean findLoc, String[] field, String city, ArrayList<String> localities ){
        if (!findLoc) {
            for (String words : field) {
                String stripped = normalize(words);
                if (localities.contains(stripped)) {
                    correctAddress.add(city);
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
