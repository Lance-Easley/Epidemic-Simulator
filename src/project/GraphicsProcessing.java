package project;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class GraphicsProcessing extends JPanel {

    ArrayList<Person> people;
    ArrayList<Person> removedPeople;
    int sizeY;
    int sizeX;
    int healthy;
    int infected;
    int sick;
    int dead;
    int recovered;

    public GraphicsProcessing(ArrayList<Person> people, ArrayList<Person> removedPeople, int sizeY, int sizeX, int healthy, int infected, int sick, int dead, int recovered) {
        this.people = people;
        this.removedPeople = removedPeople;
        this.sizeY = sizeY;
        this.sizeX = sizeX;
        this.healthy = healthy;
        this.infected = infected;
        this.sick = sick;
        this.dead = dead;
        this.recovered = recovered;
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        ArrayList<Person> drawQueue;

        drawQueue = (ArrayList<Person>) people.clone();

        drawQueue.addAll(removedPeople);

        for (Person p : drawQueue) {
            Graphics2D g2d = (Graphics2D) g;

            Color c = Color.green;

            switch (p.status) { // 0=healthy, 8=infected, !=sick, X=dead
                case '8' -> c = Color.yellow;
                case '!' -> c = Color.red;
                case 'X' -> c = Color.gray;
                case 'Y' -> c = Color.pink;
            }

            g2d.setColor(c);
            g2d.fillOval(p.x, p.y, 5, 5);
        }

        // Stats
        Graphics2D statsG2d = (Graphics2D) g;

        Color c = Color.black;

        statsG2d.setColor(c);
        statsG2d.drawLine(0, sizeY - 74, sizeX, sizeY - 74);

        statsG2d.drawString("Healthy: " + healthy, 10, sizeY - 50);
        statsG2d.drawString("Infected: " + infected, 10, sizeY - 30);
        statsG2d.drawString("Sick: " + sick, 110, sizeY - 50);
        statsG2d.drawString("Dead: " + dead, 110, sizeY - 30);
        statsG2d.drawString("Recovered: " + recovered, 210, sizeY - 30);

        if (infected + sick == 0) {
            statsG2d.setColor(Color.red);
            statsG2d.drawString("Virus is Eliminated", 225, sizeY - 50);
        }
    }

}
