import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;

// این کلاس برای متدهای مربوط به ادمین می باشد
public class Admin {
    // این متد برای "منوی اصلی" قسمت ادمین طراحی شده است.
    public void adminMenu (ArrayList<Flight> flightsArrayList) {
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.print("\033[H\033[2J");
            System.out.flush();

            System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::");
            System.out.println("               ADMIN MENU OPTIONS               ");
            System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::");
            System.out.println(" .............................................. ");
            System.out.println("    <1> Add");
            System.out.println("    <2> Update");
            System.out.println("    <3> Remove");
            System.out.println("    <4> Flight schedules");
            System.out.println("    <0> Sign out");
            System.out.print(" >> ");
            String choose = input.nextLine();

            switch (choose) {
                case "0":
                    return ;
                case "1":
                    addFlights(flightsArrayList);
                    break;
                case "2":
                    updateFlights(flightsArrayList);
                    break;
                case "3":
                    removeFlights(flightsArrayList);
                    break;
                case "4":
                    flightSchedules(flightsArrayList);
                    break;
                default:
                    System.out.println("Please check your command!");
                    System.out.println("Press Enter To Continue...");
                    input.nextLine();
                    break;
            }
        }
    }

    CheckingEnteredData checkingEnteredData = new CheckingEnteredData();
    Scanner input = new Scanner(System.in);

    //    این متد برای قسمت اول منوی ادمین (اضافه کردن پرواز ها) زراحی شده است.
    public void addFlights (ArrayList<Flight> flightsArrayList) {
        addFlightMenuLoop: while (true) {
            System.out.print("\033[H\033[2J");
            System.out.flush();

            System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
            System.out.println("                          Add Flights                         ");
            System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\n");
            System.out.println("NOTE: If you want to return to the previous menu, you can type \"return\" as all of the properties and then press enter.");
            System.out.println("\nPlease enter the flightId : ");
            System.out.print(">> ");
            String flightId = input.nextLine();

            if (Objects.equals(flightId, "return")) {
                return ;
            }

            for (Flight flight : flightsArrayList) {
                if (Objects.equals(flightId, flight.getFlightId())) {
                    System.out.println("\nThis flightId already exists. Please enter another flightId!");
                    System.out.println("Press Enter To Continue...");
                    input.nextLine();
                    continue addFlightMenuLoop;
                }
            }

            System.out.println("Please enter the origin : ");
            System.out.println("NOTE: Origins are stored with capital letters.");
            System.out.print(">> ");
            String origin = input.nextLine();

            if (Objects.equals(origin, "return")) {
                return ;
            }

            System.out.println("Please enter the destination : ");
            System.out.println("NOTE: Origin and destination cannot be equals.");
            System.out.println("NOTE: Destinations are stored with capital letters.");
            System.out.print(">> ");
            String destination = input.nextLine();

            if (Objects.equals(destination, "return")) {
                return ;
            }

            if (origin.equalsIgnoreCase(destination)) {
                System.out.println("The origin and the destination are same. Please add flight again!");
                System.out.println("Press Enter To Continue...");
                input.nextLine();
                continue;
            }

            System.out.println("Please enter the date : ");
            System.out.println("NOTE: The entered date should be like the example : 0000-00-00 or 0000/00/00");
            System.out.print(">> ");
            String date = input.nextLine();

            if (Objects.equals(date, "return")) {
                return ;
            }

            if (!checkingEnteredData.isEnteredDateRight(date)) {
                System.out.println("\nYour entered date is wrong! Please add flight again!");
                System.out.println("Press Enter To Continue...");
                input.nextLine();
                continue;
            }

            System.out.println("Please enter the time : ");
            System.out.println("NOTE: The entered time should be like the example : 22:22");
            System.out.print(">> ");
            String time = input.nextLine();

            if (Objects.equals(time, "return")) {
                return ;
            }

            if (!checkingEnteredData.isEnteredTimeRight(time)) {
                System.out.println("\nYour entered time is wrong! Please add flight again!");
                System.out.println("Press Enter To Continue...");
                input.nextLine();
                continue;
            }

            System.out.println("Please enter the price : ");
            System.out.print(">> ");
            String price = input.nextLine();

            if (Objects.equals(price, "return")) {
                return ;
            }

            if (!checkingEnteredData.isEnteredNumberRight(price)) {
                System.out.println("\nYour entered price is wrong! Please add flight again!");
                System.out.println("Press Enter To Continue...");
                input.nextLine();
                continue;
            }

            System.out.println("Please enter the count of the seats : ");
            System.out.print(">> ");
            String seats = input.nextLine();

            if (Objects.equals(seats, "return")) {
                return ;
            }

            if (!checkingEnteredData.isEnteredNumberRight(seats)) {
                System.out.println("\nYour entered count of seats is wrong! Please add flight again!");
                System.out.println("Press Enter To Continue...");
                input.nextLine();
                continue;
            }

            Flight flight = new Flight(Integer.parseInt(seats), destination.toUpperCase(), date, origin.toUpperCase(), flightId, time, Integer.parseInt(price), Integer.parseInt(seats));
            flightsArrayList.add(flight);
            System.out.println("Flight added!");
            System.out.println("Press Enter To Return...");
            input.nextLine();
            return ;
        }
    }

    //    این متد برای تغییر دادن اطلاعات پرواز(ها) در منوی ادمین می باشد.
//    به ظاهر در این متد، ایدی پروازی که ادمین وارد می کند بررسی می شود و اگر این ایدی درست بود به متد پایینی منتقل می شود
//    ولی چون مدیریت قسمت بروزرسانی اطلاعات پرواز در این متد صورت می گیرد و متد پایینی هم زیرمجموعه این متد هست،
//    من این متد را متد اصلی برای قسمت بروزرسانی اطلاعات پرواز در نظر گرفتم.
    public void updateFlights (ArrayList<Flight> flightsArrayList) {
        while (true) {
            System.out.print("\033[H\033[2J");
            System.out.flush();

            System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
            System.out.println("                        Update Flights                        ");
            System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\n");
            if (flightsArrayList.isEmpty()) {
                System.out.println("There is no added flights!");
                System.out.println("Press Enter To Return...");
                input.nextLine();
                return ;
            }

            else {
                System.out.println("Please enter the flightId : ");
                System.out.println("NOTE: If you want to return to the previous page, please type \"return\" then press enter.");
                System.out.print(">> ");
                String flightId = input.nextLine();

                if (Objects.equals(flightId, "return")) {
                    return ;
                }

                boolean flag = false;
                int flightIndex = 0;
                for (int i = 0; i < flightsArrayList.size(); i++) {
                    if (Objects.equals(flightId, flightsArrayList.get(i).getFlightId())) {
                        flag = true;
                        flightIndex = i;
                    }
                }

                if (!flag) {
                    System.out.println("This flightId not exists! Please enter another flightID!");
                    System.out.println("Press Enter To Continue...");
                    input.nextLine();
                }

                else {
                    updateFlightsChangeOptionsMenu(flightsArrayList, flightIndex);
                }
            }
        }
    }

    //    این متد برای منوی انتخاب و تغییر اطلاعات پرواز است.
    public void updateFlightsChangeOptionsMenu (ArrayList<Flight> flightsArrayList, int flightIndex) {
        UpdateFlightsChangeOptionsMenuMethods updateFlightsChangeOptionsMenuMethods = new UpdateFlightsChangeOptionsMenuMethods();

        while (true) {
            System.out.print("\033[H\033[2J");
            System.out.flush();

            System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
            System.out.println("                        Update Flights                        ");
            System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\n");
            System.out.println("Please choose that which of the following chooses you want to update :\n");
            System.out.println("    <1> FlightId");
            System.out.println("    <2> Origin");
            System.out.println("    <3> Destination");
            System.out.println("    <4> Date");
            System.out.println("    <5> Time");
            System.out.println("    <6> Price");
            System.out.println("    <7> Seats");
            System.out.println("    <0> Return");
            System.out.print("\n>> ");
            String select = input.nextLine();

            switch (select) {
                case "1":
                    updateFlightsChangeOptionsMenuMethods.updateFlightId(flightsArrayList, flightIndex);
                    break;

                case "2":
                    updateFlightsChangeOptionsMenuMethods.updateOrigin(flightsArrayList, flightIndex);
                    break;

                case "3":
                    updateFlightsChangeOptionsMenuMethods.updateDestination(flightsArrayList, flightIndex);
                    break;

                case "4":
                    updateFlightsChangeOptionsMenuMethods.updateDate(flightsArrayList, flightIndex);
                    break;

                case "5":
                    updateFlightsChangeOptionsMenuMethods.updateTIme(flightsArrayList, flightIndex);
                    break;

                case "6":
                    updateFlightsChangeOptionsMenuMethods.updatePrice(flightsArrayList, flightIndex);
                    break;

                case "7":
                    updateFlightsChangeOptionsMenuMethods.updateSeats(flightsArrayList, flightIndex);
                    break;

                case "0":
                    return ;

                default:
                    System.out.println("Please check your command!");
                    System.out.println("Press Enter To Continue...");
                    input.nextLine();
                    break;
            }
        }
    }

    //    این متد برای حذف پروازها می باشد.
    public void removeFlights (ArrayList<Flight> flightsArrayList) {
        removeFlightsLoop: while (true) {
            System.out.print("\033[H\033[2J");
            System.out.flush();

            System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
            System.out.println("                         Remove Flights                       ");
            System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\n");
            if (flightsArrayList.isEmpty()) {
                System.out.println("There is no added flights!");
            }

            else {
                System.out.println("Please enter the Id of the flight that you want to remove : ");
                System.out.println("NOTE: If you want to return to the passengers menu, please type \"return\" and then press enter.");
                System.out.print(">> ");
                String flightId = input.nextLine();
                if (Objects.equals(flightId, "return")) {
                    return ;
                }

                boolean flag = false;
                for (int i = 0; i < flightsArrayList.size(); i++) {
                    if (Objects.equals(flightId, flightsArrayList.get(i).getFlightId())) {
                        if (flightsArrayList.get(i).getFirstSeatsCount() != flightsArrayList.get(i).getSeats()) {
                            System.out.println("Sorry! You cannot delete this flight!");
                            System.out.println("Press Enter To Continue...");
                            input.nextLine();
                            continue removeFlightsLoop;
                        }

                        flag = true;
                        flightsArrayList.remove(i);
                        break;
                    }
                }

                if (!flag) {
                    System.out.println("There is no flightIds like this, please try another flightId!");
                    System.out.println("Press Enter To Continue...");
                    input.nextLine();
                    continue;
                }

                System.out.println("Flight removed!");
            }

            System.out.println("Press Enter To Return...");
            input.nextLine();
            return ;
        }
    }


    // این متد برای نمایش جدول پروازها می باشد.
    public void flightSchedules (ArrayList<Flight> flightsArrayList) {
        System.out.print("\033[H\033[2J");
        System.out.flush();

        System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
        System.out.println("                                        Flight Schedules                                    ");
        System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\n");

        if (flightsArrayList.isEmpty()) {
            System.out.println("There is nothing to see!");
        }

        else {
            System.out.printf("|%-20s|%-13s|%-13s|%-13s|%-9s|%-13s|%-7s|", "FlightId", "Origin", "Destination", "Date", "Time", "Price", "Seats");
            System.out.println("\n................................................................................................");
            for (Flight flight : flightsArrayList) {
                System.out.printf(Locale.US, "|%-20s|%-13s|%-13s|%-13s|%-9s|%,-13d|%-7d|", flight.getFlightId(), flight.getOrigin(), flight.getDestination(), flight.getDate(), flight.getTime(), flight.getPrice(), flight.getSeats());
                System.out.println("\n................................................................................................");
            }
        }

        System.out.println("Press Enter To Return...");
        input.nextLine();
    }


}
