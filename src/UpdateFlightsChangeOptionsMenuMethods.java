// ابن کلاس برای متد های منوی بروزرسانی قسمت های مختلف پرواز (updateFlightsChangeOptionsMenu) که در کلاس AdminMenuMethods واقع می باشد، پدید آمده است.

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class UpdateFlightsChangeOptionsMenuMethods {
    Scanner input = new Scanner(System.in);
    CheckingEnteredData checkingEnteredData = new CheckingEnteredData();
    IsEqualNotCaseSensitiveMethod isEqualNotCaseSensitiveMethod = new IsEqualNotCaseSensitiveMethod();
    MakeCapitalStringMethod makeCapitalStringMethod = new MakeCapitalStringMethod();

    public void updateFlightId (ArrayList<Flight> flightsArrayList, int flightIndex) {
        updateFlightIdLoop: while (true) {
            System.out.print("\033[H\033[2J");
            System.out.flush();

            System.out.println("Please enter the new flightId : ");
            System.out.println("NOTE: If you want to return to the previous page, please type \"return\" and then press enter.");
            System.out.print(">> ");
            String newFlightId = input.nextLine();

            if (Objects.equals(newFlightId, "return")) {
                return ;
            }

            for (Flight flights : flightsArrayList) {
                if (Objects.equals(newFlightId, flights.getFlightId())) {
                    System.out.println("This flightId already exists. Please try another flightId!");
                    System.out.println("Press Enter To Continue...");
                    input.nextLine();
                    continue updateFlightIdLoop;
                }
            }

            flightsArrayList.get(flightIndex).setFlightId(newFlightId);

            System.out.println("FlightId changed!");
            System.out.println("Press Enter To Return...");
            input.nextLine();
            return ;
        }
    }


    public void updateOrigin (ArrayList<Flight> flightsArrayList, int flightIndex) {
        while (true) {
            System.out.print("\033[H\033[2J");
            System.out.flush();

            System.out.println("Please enter the new origin : ");
            System.out.println("NOTE: New origin and destination of the flight cannot be equals.");
            System.out.println("NOTE: If you want to return to the previous page, please type \"return\" and then press enter.");
            System.out.println("NOTE: Origins are stored with capital letters.");
            System.out.print(">> ");
            String newOrigin = input.nextLine();

            if (Objects.equals(newOrigin, "return")) {
                return ;
            }

            if (isEqualNotCaseSensitiveMethod.isEqualNotCaseSensitive(flightsArrayList.get(flightIndex).getDestination(), newOrigin)) {
                System.out.println("New origin and destination cannot be equals. Please enter another new origin.");
                System.out.println("Press Enter To Continue...");
                input.nextLine();
                continue;
            }

            if (Objects.equals(newOrigin, flightsArrayList.get(flightIndex).getOrigin())) {
                System.out.println("This origin is the flight's current origin. Please try again!");
                System.out.println("Press Enter To Continue...");
                input.nextLine();
                continue;
            }

            flightsArrayList.get(flightIndex).setOrigin(makeCapitalStringMethod.makeCapitalString(newOrigin));
            System.out.println("Origin changed!");
            System.out.println("Press Enter To Return...");
            input.nextLine();
            return ;
        }
    }


    public void updateDestination (ArrayList<Flight> flightsArrayList, int flightIndex) {
        while (true) {
            System.out.print("\033[H\033[2J");
            System.out.flush();

            System.out.println("Please enter the new destination : ");
            System.out.println("NOTE: New destination and origin of the flight cannot be equals.");
            System.out.println("NOTE: If you want to return to the previous page, please type \"return\" and then press enter.");
            System.out.println("NOTE: Destinations are stored with capital letters.");
            System.out.print(">> ");
            String newDestination = input.nextLine();

            if (Objects.equals(newDestination, "return")) {
                return ;
            }

            if (newDestination.toUpperCase().equals(flightsArrayList.get(flightIndex).getOrigin().toUpperCase())) {
                System.out.println("New destination and origin cannot be equals. Please enter another new destination.");
                System.out.println("Press Enter To Continue...");
                input.nextLine();
                continue;
            }

            if (Objects.equals(newDestination, flightsArrayList.get(flightIndex).getDestination())) {
                System.out.println("This destination is the flight's current destination. Please try again!");
                System.out.println("Press Enter To Continue...");
                input.nextLine();
                continue;
            }

            flightsArrayList.get(flightIndex).setDestination(newDestination.toUpperCase());
            System.out.println("Destination changed!");
            System.out.println("Press Enter To Return...");
            input.nextLine();
            return ;
        }
    }


    public void updateDate (ArrayList<Flight> flightsArrayList, int flightIndex) {
        while (true) {
            System.out.print("\033[H\033[2J");
            System.out.flush();

            System.out.println("Please enter the new date : ");
            System.out.println("NOTE: If you want to return to the previous page, please type \"return\" and then press enter.");
            System.out.println("NOTE: The entered date should be like the example : 1111-11-11 or 1111/11/11 .");
            System.out.print(">> ");
            String newDate = input.nextLine();

            if (Objects.equals(newDate, "return")) {
                return ;
            }

            if (!checkingEnteredData.isEnteredDateRight(newDate)) {
                System.out.println("The entered new date is wrong. Please try again!");
                System.out.println("Press Enter To Continue...");
                input.nextLine();
                continue;
            }

            if (Objects.equals(newDate, flightsArrayList.get(flightIndex).getDate())) {
                System.out.println("This date is the flight's current date. Please try again!");
                System.out.println("Press Enter To Continue...");
                input.nextLine();
                continue;
            }

            flightsArrayList.get(flightIndex).setDate(newDate);
            System.out.println("Date changed!");
            System.out.println("Press Enter To Return...");
            input.nextLine();
            return ;
        }
    }


    public void updateTIme (ArrayList<Flight> flightsArrayList, int flightIndex) {
        while (true) {
            System.out.print("\033[H\033[2J");
            System.out.flush();

            System.out.println("Please enter the new time : ");
            System.out.println("NOTE: If you want to return to the previous page, please type \"return\" and then press enter.");
            System.out.println("NOTE: The entered time should be like the example : 12:12 .");
            System.out.print(">> ");
            String newTime = input.nextLine();

            if (Objects.equals(newTime, "return")) {
                return ;
            }

            if (!checkingEnteredData.isEnteredTimeRight(newTime)) {
                System.out.println("The entered new time is wrong. Please try again!");
                System.out.println("Press Enter To Continue...");
                input.nextLine();
                continue;
            }

            if (Objects.equals(newTime, flightsArrayList.get(flightIndex).getTime())) {
                System.out.println("This time is the flight's current time. Please try again!");
                System.out.println("Press Enter To Continue...");
                input.nextLine();
                continue;
            }

            flightsArrayList.get(flightIndex).setTime(newTime);
            System.out.println("Time changed!");
            System.out.println("Press Enter To Return...");
            input.nextLine();
            return ;
        }
    }


    public void updatePrice (ArrayList<Flight> flightsArrayList, int flightIndex) {
        while (true) {
            System.out.print("\033[H\033[2J");
            System.out.flush();

            System.out.println("Please enter the new price : ");
            System.out.println("NOTE: If you want to return to the previous page, please type \"return\" and then press enter.");
            System.out.print(">> ");
            String newPrice = input.nextLine();

            if (Objects.equals(newPrice, "return")) {
                return ;
            }

            if (!checkingEnteredData.isEnteredNumberRight(newPrice)) {
                System.out.println("The entered new price is not right. Please try again!");
                System.out.println("Press Enter To Continue...");
                input.nextLine();
                continue;
            }

            if (Integer.parseInt(newPrice) == flightsArrayList.get(flightIndex).getPrice()) {
                System.out.println("This price is the flight's current price. Please try again!");
                System.out.println("Press Enter To Continue...");
                input.nextLine();
                continue;
            }

            flightsArrayList.get(flightIndex).setPrice(Integer.parseInt(newPrice));
            System.out.println("Price changed!");
            System.out.println("Press Enter To Return...");
            input.nextLine();
            return ;
        }
    }


    public void updateSeats (ArrayList<Flight> flightsArrayList, int flightIndex) {
        while (true) {
            System.out.print("\033[H\033[2J");
            System.out.flush();

            System.out.println("Please enter the new count of seats : ");
            System.out.println("NOTE: If you want to return to the previous page, please type \"return\" and then press enter.");
            System.out.print(">> ");
            String newSeats = input.nextLine();

            if (Objects.equals(newSeats, "return")) {
                return ;
            }

            if (!checkingEnteredData.isEnteredNumberRight(newSeats)) {
                System.out.println("The entered new count of seats is not right. Please try again!");
                System.out.println("Press Enter To Continue...");
                input.nextLine();
                continue;
            }

            if (Integer.parseInt(newSeats) == flightsArrayList.get(flightIndex).getSeats()) {
                System.out.println("This count of seats is the flight's current count of seats. Please try again!");
                System.out.println("Press Enter To Continue...");
                input.nextLine();
                continue;
            }

            flightsArrayList.get(flightIndex).setSeats(Integer.parseInt(newSeats));
            System.out.println("Count of seats changed!");
            System.out.println("Press Enter To Return...");
            input.nextLine();
            return ;
        }
    }
}
