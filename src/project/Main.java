package project;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Random choice = new Random();
        Scanner keyboard = new Scanner(System.in);

        // Defaults
        int sizeX = 800;
        int sizeY = 800;
        int numOfPeople = 1000;
        int incubationPeriod = 100;
        int sickPeriod = 100;
        int infectionArea = 10;
        int infectionChance = 5;

        boolean isDefaultSettings = true;

        System.out.print("Change or Default Settings? > ");
        String input = keyboard.nextLine();
        if (input.length() > 0 && input.charAt(0) == 'c') {
            isDefaultSettings = false;
            System.out.print("Screen Width (recommend > 400) > ");
            sizeX = keyboard.nextInt();
            System.out.print("Screen Height (recommended > 200) > ");
            sizeY = keyboard.nextInt();
            System.out.print("Number of People > ");
            numOfPeople = keyboard.nextInt();
            System.out.print("Virus' Incubation Period > ");
            incubationPeriod = keyboard.nextInt();
            System.out.print("Virus' Sick Period (higher will cause performance drops) > ");
            sickPeriod = keyboard.nextInt();
            System.out.print("Average Person's Precautions (lower = less range to be infected) > ");
            infectionArea = keyboard.nextInt();
            System.out.print("Average Person's Hygiene Level (1-10) > ");
            infectionChance = keyboard.nextInt();
        }

        ArrayList<Person> people = new ArrayList<>();
        for (int idx = 0; idx < numOfPeople; idx++) {
            people.add(new Person(
                    '0',
                    choice.nextInt(sizeX - 1),
                    choice.nextInt(sizeY - 1),
                    incubationPeriod,
                    sickPeriod
            ));
        }

        // Person generation
        people.get(0).status = '8';

        // JFrame Setup
        new DisplayFrame(sizeX, sizeY, people, infectionArea, infectionChance, isDefaultSettings);
    }
}
