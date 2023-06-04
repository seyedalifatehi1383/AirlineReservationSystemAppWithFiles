import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

/**
 * Advanced Programming
 * @Teacher Dr.Bostan
 * @author Seyed Ali Fatehi
 * Second Project (HW3) :
 * Airline Reservation System With Files
 */

public class Main {
    public static void main(String[] args) throws IOException {
        MainMenus mainMenus = new MainMenus();
        RandomAccessFile flightsFile = new RandomAccessFile("flightsFile.dat", "rw");
        RandomAccessFile passengersFile = new RandomAccessFile("passengersFile.dat", "rw");
        RandomAccessFile ticketFile = new RandomAccessFile("ticketsFile.dat", "rw");
        Passengers passengers = new Passengers();
        ArrayList<Flight> flightsArrayList = new ArrayList<>();
        ArrayList<Passenger> passengersArrayList  = new ArrayList<>();

        passengersFile.seek(0);
        passengers.readPassengerInfos(passengersArrayList, passengersFile);

        mainMenus.mainMenu(flightsArrayList, passengersArrayList);

        flightsFile.close();
        passengersFile.close();
    }
}