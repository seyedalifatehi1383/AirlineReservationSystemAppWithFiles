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
        RandomAccessFile ticketIdsFile = new RandomAccessFile("ticketIdsFile.dat", "rw");

        Passengers passengers = new Passengers();
        Flights flights = new Flights();

        ArrayList<Passenger> passengersArrayList  = new ArrayList<>();
        ArrayList<Flight> flightsArrayList = new ArrayList<>();

        passengersFile.seek(0);
        ticketIdsFile.seek(0);
        passengers.readPassengerInfos(passengersArrayList, passengersFile, ticketIdsFile, flightsArrayList);

        flightsFile.seek(0);
        flights.readFlightInfos(flightsArrayList, flightsFile);

        mainMenus.mainMenu(flightsArrayList, passengersArrayList);

        passengersFile.seek(0);
        ticketIdsFile.seek(0);
        passengers.writePassengerInfos(passengersArrayList, passengersFile, ticketIdsFile);

        flightsFile.seek(0);
        flights.writeFlightInfos(flightsArrayList, flightsFile);

        ticketIdsFile.close();
        passengersFile.close();
        flightsFile.close();
    }
}