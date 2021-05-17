package project;
import java.util.Random;

public class Person {
    char status; // 0=healthy, 8=infected, !=sick, X=dead, Y=recovered
    int x;
    int y;
    int incubationPeriod;
    int sickPeriod;
    int mortalityRate; // 0%-100%
    int direction = 6; // 0=up, 1=up-right, 2=right, etc clockwise
    int incubationHours = -1; // -1 means person was never infected
    int sickHours = -1; //    ^

    public Person(char status, int x, int y, int incubationPeriod, int sickPeriod, int mortalityRate) {
        this.status = status;
        this.x = x;
        this.y = y;
        this.incubationPeriod = incubationPeriod;
        this.sickPeriod = sickPeriod;
        this.mortalityRate = mortalityRate;
    }

    void infect() {
        Random chance = new Random();
        incubationHours = incubationPeriod + (chance.nextInt(incubationPeriod / 3) * ((chance.nextBoolean()) ? 1: -1));
    }

    void move(int sizeX, int sizeY) {
        Random chance = new Random();

        // Hours logic
        if (incubationHours > 0) {
            status = '8';
            incubationHours--;
        } else if (status == '8'){
            status = '!';
            sickHours = sickPeriod;
        }

        if (sickHours > 0) {
            status = '!';
            sickHours--;
        } else if (status == '!') {
            if (chance.nextInt(101) > mortalityRate) {
                status = 'Y';
            } else status = 'X';
        }

        // Movement Logic
        int choice = chance.nextInt(3);

        int checkX = x;
        int checkY = y;

        switch (choice) { // 0=left, 1=straight, 2=right
            case 0 -> {
                if (direction > 0) {
                    direction--;
                } else if (direction == 0) {
                    direction = 7;
                }
            }
            case 2 -> {
                if (direction < 7) {
                    direction++;
                } else if (direction == 7) {
                    direction = 0;
                }
            }
        }

        switch (direction) {
            case 0 -> checkY--; // up
            case 1 -> { // up, right
                checkY--;
                checkX++;
            }
            case 2 -> checkX++; // right
            case 3 ->  { // down, right
                checkY++;
                checkX++;
            }
            case 4 -> checkY++; // down
            case 5 -> { // down, left
                checkY++;
                checkX--;
            }
            case 6 -> checkX--; // left
            case 7 -> { // up, left
                checkX--;
                checkY--;
            }
        }


        // Screen Border Checks
        if (checkX < 0) checkX = 0;
        else if (checkX > sizeX - 5) checkX = sizeX - 5;

        if (checkY < 0) checkY = 0;
        else if (checkY > sizeY - 80) checkY = sizeY - 80;

        x = checkX;
        y = checkY;
    }
}
