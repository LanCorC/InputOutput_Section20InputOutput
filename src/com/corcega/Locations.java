package com.corcega;

import java.io.*;
import java.util.*;

public class Locations implements Map<Integer, Location>  {
    private static Map<Integer, Location> locations = new LinkedHashMap<>();

    public static void main(String[] args)  throws IOException {
//        try(FileWriter locFile = new FileWriter("locations.txt");
//        FileWriter dirFile = new FileWriter("directions.txt")) {
//            for(Location location : locations.values()) {
//                locFile.write(location.getLocationID() + "," + location.getDescription() + "\n");
//                for (String direction : location.getExits().keySet()) {
//                    dirFile.write(location.getLocationID() + ","
//                            + direction + "," + location.getExits().get(direction) + "\n");
//                }
//             }
//        }

//        part1:
//        modify program, make it BufferedReader to read in the locations + run program
//        Part2:
//        modify main method - use BufferedWriter
//        open locations.txt and directions.txt to check correct data
//        another change: allow the 0 (quit location) exits before using newly created files
//        HINT change the 3x instances of hashMap -> linkedHashMap in Location class + the 1x in Locations in order to compare more easily

        try(BufferedWriter locWriter = new BufferedWriter(new FileWriter("locations.txt"));
        BufferedWriter dirWriter = new BufferedWriter(new FileWriter("directions.txt"))) {
            for(Location location : locations.values()) {
                locWriter.write(location.getLocationID() + "," + location.getDescription());
                for(String direction : location.getExits().keySet()) {
                    //temp -- to remove the exit, for comparison purposes. removing this "if" block will not cause issues
                    if (direction.equals("Q")) {
                        continue;
                    }
                    //temp
                    dirWriter.write(location.getLocationID() +","+ direction +"," + location.getExits().get(direction));
                    dirWriter.newLine();
                }
                locWriter.newLine();
            }

        }

    }

    static {
        try(BufferedReader locRead = new BufferedReader(new FileReader("locations_big.txt"));
            BufferedReader dirRead = new BufferedReader(new FileReader("directions_big.txt"))) {
            String input;
            //Read locations
            while((input = locRead.readLine()) != null) {
                String[] data = input.split(",");
                int loc = Integer.parseInt(data[0]);
                String des = data[1];
                Map<String, Integer> tempExit = new LinkedHashMap<>();
                locations.put(loc, new Location(loc, des, tempExit));
            }
            //Read exits
            while((input = dirRead.readLine()) != null) {
                String[] data = input.split(",");
                int loc = Integer.parseInt(data[0]);
                String dir = data[1];
                int des = Integer.parseInt(data[2]);
                Location location = locations.get(loc);
                location.addExit(dir, des);
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public int size() {
        return locations.size();
    }

    @Override
    public boolean isEmpty() {
        return locations.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return locations.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return locations.containsValue(value);
    }

    @Override
    public Location get(Object key) {
        return locations.get(key);
    }

    @Override
    public Location put(Integer key, Location value) {
        return locations.put(key, value);
    }

    @Override
    public Location remove(Object key) {
        return locations.remove(key);
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends Location> m) {

    }

    @Override
    public void clear() {
        locations.clear();
    }

    @Override
    public Set<Integer> keySet() {
        return locations.keySet();
    }

    @Override
    public Collection<Location> values() {
        return locations.values();
    }

    @Override
    public Set<Entry<Integer, Location>> entrySet() {
        return locations.entrySet();
    }
}
