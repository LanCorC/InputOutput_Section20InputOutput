package com.corcega;

import java.io.*;
import java.util.*;

public class Locations implements Map<Integer, Location>  {
    private static Map<Integer, Location> locations = new HashMap<>();

    public static void main(String[] args)  throws IOException {
        try(FileWriter locFile = new FileWriter("locations.txt");
        FileWriter dirFile = new FileWriter("directions.txt")) {
            for(Location location : locations.values()) {
                locFile.write(location.getLocationID() + "," + location.getDescription() + "\n");
                for (String direction : location.getExits().keySet()) {
                    dirFile.write(location.getLocationID() + ","
                            + direction + "," + location.getExits().get(direction) + "\n");
                }
             }
        }
//        FileWriter locFile = null;
//        try {
//            locFile = new FileWriter("locations.txt");
//            for(Location location : locations.values()) {
//                locFile.write(location.getLocationID() + "," + location.getDescription() + "\n");
//            }
//        } finally {
//            System.out.println("in finally block");
//                if(locFile != null) {
//                    System.out.println("Attempting to close locfile");
//                    locFile.close();
//                }
//        }

    }

    static {
//        Scanner scanner = null;
//        try {
//            scanner = new Scanner(new FileReader("locations_big.txt"));
//            scanner.useDelimiter(",");
//            while(scanner.hasNextLine()) {
//                int loc = scanner.nextInt();
//                scanner.skip(scanner.delimiter());
//                String description = scanner.nextLine();
//                System.out.println("Imported loc: " + loc + ": " + description);
//                Map<String, Integer> tempExit = new HashMap<>();
//                locations.put(loc, new Location(loc, description, tempExit));
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (scanner != null) {
//                scanner.close();
//            }
//        }
//
//        //Now read the exits
//        try {
//            scanner = new Scanner(new BufferedReader(
//                    new FileReader("directions_big.txt")));
//            scanner.useDelimiter(",");
//            while(scanner.hasNextLine()) {
//                String input = scanner.nextLine();
//                String[] data = input.split(",");
//                int loc = Integer.parseInt(data[0]);
//                String direction = data[1];
//                int destination = Integer.parseInt(data[2]);
//                System.out.println(loc + ": " + direction + ": " + destination);
//                Location location = locations.get(loc);
//                location.addExit(direction, destination);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if(scanner != null) {
//                scanner.close();
//            }
//        }

        try(Scanner locRead = new Scanner(new BufferedReader(new FileReader("locations_big.txt")));
            Scanner dirRead = new Scanner(new BufferedReader(new FileReader("directions_big.txt")))) {
            //Read locations
            while(locRead.hasNextLine()) {
                String input = locRead.nextLine();
                String[] data = input.split(",");
                int loc = Integer.parseInt(data[0]);
                String des = data[1];
                Map<String, Integer> tempExit = new HashMap<>();
                locations.put(loc, new Location(loc, des, tempExit));
            }
            //Read exits
            while(dirRead.hasNextLine()) {
                String input = dirRead.nextLine();
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
