package com.project.deploy.algorithm;

import lombok.EqualsAndHashCode;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

@EqualsAndHashCode
/**
 * this class is creating the map of countries from world
 *
 * i take from file every line and i put it in map
 * one line is this kind: Country//(RO/US/... )//City//Locality
 * because i want to accept and the lower format of country
 * the map is of type: Map /<Country, Map/<City,Locality/> />
 */
public class Graph {
    protected HashMap<String, HashMap<String, ArrayList<String>>> terra = new HashMap<>();

    public Graph(){
        readFile();
    }

    /**
     * i made it public to call it different from the class constructor
     * i read file and every line and pass it to next function to addToMap
     */
    public void readFile(){
        try{
            File database = new File(System.getProperty("user.dir") + "//src//main//resources//world.txt");
            Scanner in = new Scanner(database);
            while(in.hasNextLine()){
                String data = in.nextLine();
                addToMap(data);
            }
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    /**
     * for every line i read ->
     * i split after //
     * the first two words are the country -> Romania, RO
     * then is the city from country
     * and the last one is the locality from city
     *
     * ->first i normalize every word in lower and no accents
     * ->then i put every word in his place:
     *       --> the country as key for terra Map
     *       --> the city as key on country's map
     *       ->> locality as value on country's map
     *
     * @param line --> the line read from file
     */
    private void addToMap(String line) {
        String[] info = line.split("//");
        for(int i=0; i< info.length;i++)
        {
            String elements = info[i];
            elements = Normalizer.normalize(elements, Normalizer.Form.NFD);
            elements = elements.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
            elements = elements.toLowerCase();
            info[i] = elements;
        }
        HashMap<String, ArrayList<String>> cityMap;
        ArrayList<String> locality;
        //romania ro  bacau   comanesti  => bacau -> {comanesti}
        if (terra.containsKey(info[0])) {
            cityMap = terra.get(info[0]);

            if (!cityMap.containsKey(info[2])) {
                locality = new ArrayList<>();
                cityMap.put(info[2], locality);
            } else {
                locality = cityMap.get(info[2]);
            }
            locality.add(info[3]);
            cityMap.replace(info[2], locality);

            terra.replace(info[0], cityMap);
            terra.replace(info[1], cityMap);
        }
        else{
            cityMap = new HashMap<>();
            terra.put(info[0], cityMap);
            terra.put(info[1], cityMap);
        }
    }
}


