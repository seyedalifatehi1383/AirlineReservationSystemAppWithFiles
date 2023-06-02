import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Advanced Programming
 * @Teacher Dr.Bostan
 * @author Seyed Ali Fatehi
 * Second Project (HW2) :
 * Airline Reservation System
 */

public class Main {
    public static void main(String[] args) throws IOException {
        MainMenus mainMenus = new MainMenus();
        RandomAccessFile flightsFile = new RandomAccessFile("flightsFile.dat", "rw");
        RandomAccessFile passengersFile = new RandomAccessFile("passengersFile.dat", "rw");
        RandomAccessFile ticketFile = new RandomAccessFile("ticketsFile.dat", "rw");

        mainMenus.mainMenu(flightsFile, passengersFile);

        flightsFile.close();
        passengersFile.close();
    }
}