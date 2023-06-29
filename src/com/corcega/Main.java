package com.corcega;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    //Challenge: allow full words or phrases, then move
    //e.g. Go West, run South, East, N should all be acceptable


    private static Locations locations = new Locations();

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        Location currentLocation = locations.getLocation(1);
        while (true) {
            System.out.println(currentLocation.getDescription());
            if (currentLocation.getLocationID() == 0) {
                break;
            }

            Map<String, Integer> exits = currentLocation.getExits();
            System.out.print("Available exits are: ");
            for (String exit : exits.keySet()) {
                System.out.print(exit + ", ");
            }
            System.out.println();

//            my implementation below:
//            Limitations: trust that the user will appropriately enter correct words, else..
//            No, I wanna stay -> read as “N” north if exit is valid
//            String[] direction = scanner.nextLine().
//                    toUpperCase().split(" ");
//            int size = direction.length;
//            for (String input : direction){
//                size--;
//                input = input.substring(0, 1);
//                if (exits.containsKey(input)) {
//                    loc = exits.get(input);
//                    System.out.println("Valid exit selected: " + input);
//                } else if(size == 0) {
//                    System.out.println("You cannot go in that direction");
//                }
//            }

            //His implementation:
            Map<String, String> vocabulary = new HashMap<>();
            vocabulary.put("NORTH", "N");
            vocabulary.put("WEST", "W");
            vocabulary.put("SOUTH", "S");
            vocabulary.put("EAST", "E");


            String direction = scanner.nextLine().toUpperCase();
            if (direction.length() > 1) {
                String[] words = direction.split(" ");
                for (String word : words) {
                    if (vocabulary.containsKey(word)) {
                        direction = vocabulary.get(word);
                    }
                }
            }

            if (exits.containsKey(direction)) {
                currentLocation = locations.getLocation(currentLocation.getExits().get(direction));
            } else {
                System.out.println("Invalid direction.");
            }
        }

        locations.close();
        //Limitations:
        //trusts users use strictly sole input, N, Q, S etc or
        //trusts users with phrases strictly uses word e.g. “please go north” not “please go n”
    }
}