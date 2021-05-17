package project;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import javax.swing.JFrame;

public class DisplayFrame extends JFrame {

    public DisplayFrame(int sizeX, int sizeY, ArrayList<Person> people, int infectionArea, int infectionChance) {
        this.setSize(sizeX,sizeY);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        HashSet<Person> contagiousHumans = new HashSet<>();

        ArrayList<Person> removeQueue = new ArrayList<>();
        ArrayList<Person> deadPeople = new ArrayList<>();

        Random chance = new Random();

        while (true) {
            int startTime = (int) System.currentTimeMillis();

            // Stats
            int healthy = 0;
            int infected = 0;
            int sick = 0;
            int dead;
            int recovered = 0;

            for (Person p : people) {
                p.move(sizeX, sizeY);

                switch (p.status) {
                    case '0' -> healthy++;
                    case '8' -> infected++;
                    case '!' -> sick++;
                    case 'Y' -> recovered++;
                }

                if (p.status == 'X') {
                    removeQueue.add(p);
                }

                if (p.status == '!') {
                    contagiousHumans.add(p);
                } else {
                    contagiousHumans.remove(p);
                }

                for (Person contHuman : contagiousHumans) {
                    if (coordInRange(contHuman.x, contHuman.y, p.x, p.y, infectionArea)) {
                        if (p.status == '0' && chance.nextInt(10) < infectionChance) {
                            p.infect();
                            break;
                        }
                    }
                }
            }

            for (Person p : removeQueue) {
                people.remove(p);
                deadPeople.add(p);
            }
            removeQueue.clear();

            dead = deadPeople.size();

            int endTime = (int) System.currentTimeMillis();
            int timeDelta = (endTime - startTime);
            int waitTime = Math.max(25 - timeDelta, 0);
            wait(waitTime);
            this.add(new GraphicsProcessing(people, deadPeople, sizeY, sizeX, healthy, infected, sick, dead, recovered));
            this.setVisible(true);
        }
    }

    public static void wait(int ms) {
        try {
            Thread.sleep(ms);
        }
        catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    public static boolean coordInRange(int x1, int y1, int x2, int y2, int range) {
        // d =√(x2-x1)^2 + (y2-y1)^2
        return Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2)) < range;
    }
}
