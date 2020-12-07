package com.mastercard.cities.cityconnectionservice.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class CityConnectionUtilities {

    @Value("${application.city.file}")
    private String CITY_FILE_PATH ;

    Map<String, List<String>> cityConnectionsMap;
    Map<String, Boolean> cityConnectionCache = new HashMap<>();
    final String PIPE_DELIMITER = "|" ;

    @PostConstruct
    public void init(){
        //Read the CITY_FILE and create cityConnectionsMap
        //cityConnectionsMap is graph representation V, and connected nodes List
        try (Stream<String> stream = Files.lines(new ClassPathResource(CITY_FILE_PATH).getFile().toPath())) {
            cityConnectionsMap = stream.filter(s-> s.contains(","))
                    .map(line -> line.split(","))
                    .filter(str -> str.length==2)
                    .map(str-> new AbstractMap.SimpleEntry<>(str[0].trim(), str[1].trim()))
                    .collect(Collectors.groupingBy(Map.Entry::getKey,
                            Collectors.mapping(Map.Entry::getValue, Collectors.toList())));
        } catch (IOException e) {
            System.err.println("ERROR: Error initiailizing application using file: " + CITY_FILE_PATH);
            System.exit(-1);
        }
    }

    public boolean citiesConnected(String origin, String destination) {
        if(origin ==null || destination ==null)
            return false;
        if(cityConnectionCache.containsKey(origin + PIPE_DELIMITER + destination)){
            return cityConnectionCache.get(origin + PIPE_DELIMITER + destination);
        }else if(origin.equals(destination)){
            return true;
        }else{
            //-- if path exists from (A to B) or (B to A) it means they are connected.
            //-- we will cache our result in cityConnectionCache for the next time.
            boolean connected = pathExists(origin,destination) || pathExists(destination,origin);
            cityConnectionCache.put(origin + PIPE_DELIMITER + destination,     connected);
            cityConnectionCache.put(destination + PIPE_DELIMITER + origin,     connected);
            return connected;
        }
    }

    private boolean pathExists(String source, String destination) {
        //-- Use graph DFS search to see if 2 source and destination are connected.
        if (        source == null
                || destination == null
                || !cityConnectionsMap.containsKey(source)
                || !cityConnectionsMap.containsKey(destination)
                )
            return false;

        List<String> visited = new ArrayList<>();
        Stack<String> stack = new Stack<>();
        stack.push(source);
        while (!stack.empty()) {
            String city = stack.pop();
            List<String> connections = cityConnectionsMap.get(city);
            if(connections !=null) {
                for (String connection : connections) {
                    if (connection.equals(destination)) {
                        return true;
                    }
                    if (!visited.contains(connection)) {
                        stack.push(connection);
                        visited.add(connection);
                    }
                }
            }
        }

        return false;
    }

}