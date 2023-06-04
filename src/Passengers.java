import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;

public class Passengers {
    Scanner input = new Scanner(System.in);
//    این متد برای مهیا کردن استرینگ برای رایت کردن در فایل می باشد
    public static String fixStringForWriting(String str){
        while (str.length() < Passenger.FIX_SIZE)
            str += " ";
        return str.substring(0, Passenger.FIX_SIZE);
    }

//    این متد برای خواندن رشته از فایل می باشد
    public static String readString(RandomAccessFile rfile) throws IOException {
        String str = "";
        while (str.length() < Passenger.FIX_SIZE)
            str += rfile.readChar();
        return str.trim();
    }

// این متد برای ثبت اطلاعات در فایل می باشد
    public void writePassengerInfos(ArrayList<Passenger> passengersArrayList, RandomAccessFile passengersFile) throws IOException {
        for (Passenger passenger : passengersArrayList) {
            passengersFile.writeLong(passenger.getCharge()); // 8 byte
            passengersFile.writeChars(fixStringForWriting(passenger.getUsername())); // 40 byte
            passengersFile.writeChars(fixStringForWriting(passenger.getPassword())); // 40 byte
//            88 bytes
        }
    }

// این متد برای پر کردن ارایه از اطلاعات فایل ها می باشد
    public void readPassengerInfos(ArrayList<Passenger> passengersArrayList, RandomAccessFile passengersFile) throws IOException {
        String username;
        String password;
        long charge;

        for (int i = 0; i < passengersFile.length() / 88; i++) {
            charge = passengersFile.readLong();
            username = readString(passengersFile);
            password = readString(passengersFile);
            Passenger passenger = new Passenger(username, password, charge);
            passengersArrayList.set(i, passenger);
        }
    }

    //    این متد برای "منوی اصلی" قسمت مسافران می باشد.
    public void passengersMenu (ArrayList<Flight> flightsArrayList, ArrayList<Passenger> passengersArrayList, int passengerIndex) {
        Scanner input = new Scanner(System.in);
        ArrayList<Ticket> ticketsArrayList = new ArrayList<>();

        if (passengersArrayList.get(passengerIndex).getTickets() != null) {
            ticketsArrayList = passengersArrayList.get(passengerIndex).getTickets();
        }

        while (true) {
            System.out.print("\033[H\033[2J");
            System.out.flush();

            System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::");
            System.out.println("               PASSENGERS MENU OPTIONS               ");
            System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::");
            System.out.println(" ................................................... ");
            System.out.println("    <1> Change password");
            System.out.println("    <2> Search flight tickets");
            System.out.println("    <3> Booking ticket");
            System.out.println("    <4> Ticket cancellation");
            System.out.println("    <5> Booked tickets");
            System.out.println("    <6> Add charge");
            System.out.println("    <0> Sign out");
            System.out.print(  " >> "  );
            String choose = input.nextLine();

            switch (choose) {
                case "0" -> {
                    passengersArrayList.get(passengerIndex).setTickets(ticketsArrayList);

                    return;
                }
                case "1" -> changePassword(passengersArrayList, passengerIndex);
                case "2" -> searchFlightTickets(flightsArrayList);
                case "3" -> bookingTicket(flightsArrayList, passengersArrayList, ticketsArrayList, passengerIndex);
                case "4" -> ticketCancellation(passengersArrayList, ticketsArrayList, passengerIndex);
                case "5" -> bookedTickets(ticketsArrayList);
                case "6" -> addCharge(passengersArrayList, passengerIndex);
                default -> {
                    System.out.println("Please check your command!");
                    System.out.println("Press Enter To Continue...");
                    input.nextLine();
                }
            }
        }
    }

    //    این متد برای قسمت تغییر رمز عبور منوی مسافران طراحی شده است
    public void changePassword (ArrayList<Passengers> passengersArrayList, int passengerIndex) {
        while (true) {
            System.out.print("\033[H\033[2J");
            System.out.flush();

            System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
            System.out.println("                        Change Password                        ");
            System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\n");
            System.out.println("Please enter your previous password : ");
            System.out.println("NOTE: If you want to return to the passengers menu, please type \"return\" and then press enter.");
            System.out.print(">> ");
            String previousPassword = input.nextLine();
            if (Objects.equals(previousPassword, "return")) {
                return ;
            }

            if (!Objects.equals(previousPassword, passengersArrayList.get(passengerIndex).getPassword())) {
                System.out.println("\nThe entered password is wrong! Please try again!");
                System.out.println("Press Enter To Continue...");
                input.nextLine();
                continue;
            }

            System.out.print("Please enter your new password : ");
            String newPassword = input.nextLine();
            System.out.print("Please confirm your new password : ");
            String confirmNewPassword = input.nextLine();
            if (!Objects.equals(newPassword, confirmNewPassword)) {
                System.out.println("\nYou didn't confirm your password. Please try again!");
                System.out.println("Press Enter To Continue...");
                input.nextLine();
            }

            else {
                if (Objects.equals(passengersArrayList.get(passengerIndex).getPassword(), newPassword)) {
                    System.out.println("\nThis is your previous password. Please try another password!");
                    System.out.println("Press Enter To Continue...");
                    input.nextLine();
                }

                else {
                    passengersArrayList.get(passengerIndex).setPassword(newPassword);
                    System.out.println("\nPassword changed!");
                    System.out.println("Press Enter To Return...");
                    input.nextLine();
                    break;
                }
            }
        }
    }

    //    این متد برای قسمت جستجوی بلیت ها در منوی مسافران می باشد.
    public void searchFlightTickets (ArrayList<Flights> flightsArrayList) {

        while (true) {
            FindFlights findFlights = new FindFlights();
            System.out.print("\033[H\033[2J");
            System.out.flush();

            System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
            System.out.println("                          Search Flight Tickets                         ");
            System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\n");

            if (flightsArrayList.isEmpty()) {
                System.out.println("There is no flight added!");
                System.out.println("Press Enter To Return...");
                input.nextLine();
                return ;
            }

            ArrayList<Flights> foundFlightsArrayList = new ArrayList<>(flightsArrayList);

            System.out.println("Please enter the wanted filters : ");
            System.out.println("NOTE: If any filter is not important for you, you can type \"ni\" as the wanted filter.");
            System.out.println("NOTE: If you want to return to the passengers menu, you can type \"return\" as all of the properties then press enter.");
            System.out.println("\nOrigin : ");
            System.out.println("NOTE: Searching origins are not case sensitive.");
            System.out.println("NOTE: Origins and destinations cannot be equals.");
            System.out.print(">> ");
            String origin = input.nextLine();

            if (Objects.equals(origin, "return")) {
                return ;
            }

            System.out.println("Destination : ");
            System.out.println("NOTE: Searching destinations are not case sensitive.");
            System.out.println("NOTE: Origins and destinations cannot be equals.");
            System.out.print(">> ");
            String destination = input.nextLine();

            if (Objects.equals(destination, "return")) {
                return ;
            }

            if (isEqualNotCaseSensitiveMethod.isEqualNotCaseSensitive(origin, destination) && !origin.equals("ni")) {
                System.out.println("Origin and destination cannot be equals. Please search again!");
                System.out.println("Press Enter To Continue...");
                input.nextLine();
                continue;
            }

            System.out.println("Date : ");
            System.out.println("NOTE: The entered date should be like the example: 0000/00/00 or 0000-00-00");
            System.out.print(">> ");
            String date = input.nextLine();

            if (Objects.equals(date, "return")) {
                return ;
            }

            if (!Objects.equals(date, "ni")) {
                if (!(checkingEnteredData.isEnteredDateRight(date))) {
                    System.out.println("The entered date is not correct! Please search again!");
                    System.out.println("Press Enter To Continue...");
                    input.nextLine();
                    continue;
                }
            }

            System.out.println("Time : ");
            System.out.println("NOTE: The entered time should be like the example: 12:10");
            System.out.print(">> ");
            String time = input.nextLine();

            if (Objects.equals(time, "return")) {
                return ;
            }

            if (!Objects.equals(time, "ni")) {
                if (!(checkingEnteredData.isEnteredTimeRight(time))) {
                    System.out.println("The entered time is not correct! Please search again!");
                    System.out.println("Press Enter To Continue...");
                    input.nextLine();
                    continue;
                }
            }

            System.out.print("Minimum price : ");
            String minPrice = input.nextLine();

            if (Objects.equals(minPrice, "return")) {
                return ;
            }

            if (!Objects.equals(minPrice, "ni")) {
                if (!(checkingEnteredData.isEnteredNumberRight(minPrice))) {
                    System.out.println("The entered minimum price is not correct! Please search again!");
                    System.out.println("Press Enter To Continue...");
                    input.nextLine();
                    continue;
                }
            }

            else {
                minPrice = "0";
            }

            System.out.print("Maximum price : ");
            String maxPrice = input.nextLine();

            if (Objects.equals(maxPrice, "return")) {
                return ;
            }

            if (!Objects.equals(maxPrice, "ni")) {
                if (!(checkingEnteredData.isEnteredNumberRight(maxPrice))) {
                    System.out.println("The entered maximum price is not correct! Please search again!");
                    System.out.println("Press Enter To Continue...");
                    input.nextLine();
                    continue;
                }
            }

            else {
                int maxPriceNumber = flightsArrayList.get(0).getPrice();
                for (int i = 1; i < flightsArrayList.size(); i++) {
                    if (maxPriceNumber < flightsArrayList.get(i).getPrice()) {
                        maxPriceNumber = flightsArrayList.get(i).getPrice();
                    }
                }
                maxPrice = String.valueOf(maxPriceNumber);
            }

            if (!Objects.equals(origin, "ni")) {
                findFlights.findOrigin(foundFlightsArrayList, origin);
            }

            if (!Objects.equals(origin, "ni") && foundFlightsArrayList.isEmpty()) {
                System.out.println("No flights were found based on your filters!");
                System.out.println("Press Enter To Return...");
                input.nextLine();
                return ;
            }

            if (!Objects.equals(destination, "ni")) {
                findFlights.findDestination(foundFlightsArrayList, destination);
            }

            if (!Objects.equals(destination, "ni") && foundFlightsArrayList.isEmpty()) {
                System.out.println("No flights were found based on your filters!");
                System.out.println("Press Enter To Return...");
                input.nextLine();
                return ;
            }

            if (!Objects.equals(date, "ni")) {
                findFlights.findDate(foundFlightsArrayList, date);
            }

            if (!Objects.equals(date, "ni") && foundFlightsArrayList.isEmpty()) {
                System.out.println("No flights were found based on your filters!");
                System.out.println("Press Enter To Return...");
                input.nextLine();
                return ;
            }

            if (!Objects.equals(time, "ni")) {
                findFlights.findTime(foundFlightsArrayList, date);
            }

            if (!Objects.equals(time, "ni") && foundFlightsArrayList.isEmpty()) {
                System.out.println("No flights were found based on your filters!");
                System.out.println("Press Enter To Return...");
                input.nextLine();
                return ;
            }

            findFlights.findPrice(foundFlightsArrayList, Integer.parseInt(minPrice), Integer.parseInt(maxPrice));
            if (foundFlightsArrayList.isEmpty()) {
                System.out.println("No flights were found based on your filters!");
            }

            else {
                System.out.println("The info(s) of the flight(s) you are looking for is(are) :");
                System.out.printf("|%-15s|%-13s|%-13s|%-12s|%-8s|%-13s|%-7s|", "FlightId", "Origin", "Destination", "Date", "Time", "Price", "Seats");
                System.out.println("\n..........................................................................................................................");
                for (Flights flights : foundFlightsArrayList) {
                    System.out.printf(Locale.US, "|%-15s|%-13s|%-13s|%-12s|%-8s|%-13d|%-7d|", flights.getFlightId(), flights.getOrigin(), flights.getDestination(), flights.getDate(), flights.getTime(), flights.getPrice(), flights.getSeats());
                    System.out.println("\n..........................................................................................................................");
                }
            }
            System.out.println("Press Enter To Return...");
            input.nextLine();
            return ;
        }
    }


    //    این متد برای رزرو کردن بلیت می باشد.
    public void bookingTicket (ArrayList<Flights> flightsArrayList, ArrayList<Passengers> passengersArrayList, ArrayList<Ticket> ticketsArrayList, int passengerIndex) {
        while (true) {
            System.out.print("\033[H\033[2J");
            System.out.flush();

            System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
            System.out.println("                          Booking Tickets                         ");
            System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\n");

            if (flightsArrayList.isEmpty()) {
                System.out.println("There is no flights for booking!");
                System.out.println("Press Enter To Return...");
                input.nextLine();
                return ;
            }

            System.out.println("Please enter the fightId : ");
            System.out.println("NOTE : If you want to return to the previous page, you can type \"return\" then press enter.");
            System.out.print(  ">> "  );
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
                    break;
                }
            }

            if (!flag) {
                System.out.println("This flightId not exists. Please try another flightID.");
                System.out.println("Press Enter To Continue...");
                input.nextLine();
                continue;
            }

            if (flightsArrayList.get(flightIndex).getSeats() == 0) {
                System.out.println("There is no seats remained for this flight.");
                System.out.println("Press Enter To Continue...");
                input.nextLine();

            } else if (passengersArrayList.get(passengerIndex).getCharge() < flightsArrayList.get(flightIndex).getPrice()) {
                System.out.println("Your charge is not enough to book this ticket. Please charge your account then try again.");
                System.out.println("Press Enter To Return...");
                input.nextLine();
                return ;
            }

            else {
//                اینجا من اومدم تیکت ایدی رو اینجوری ساختم که اول یوزرنیم کاربر بعد ایدی پرواز ، بعد تعداد صندلی های باقی مونده قبل اینکه کاربر بلیت رزرو کنه و در نهایت تعداد دفعات کنسل کردن بلیت و هر کدوم با @ از هم جدا شدن مثال : username@flightId@434@0)
//                درباره تعداد دفعات کنسلی بلیت در کلاس مسافران بالای این ویژگی توضیحاتی داده شده است.
                String ticketId = "";
                ticketId = ticketId.concat(passengersArrayList.get(passengerIndex).getUsername());
                ticketId = ticketId.concat("@");
                ticketId = ticketId.concat(flightsArrayList.get(flightIndex).getFlightId());
                ticketId = ticketId.concat("@");
                ticketId = ticketId.concat(String.valueOf(flightsArrayList.get(flightIndex).getSeats()));

                int countSeats = flightsArrayList.get(flightIndex).getSeats();
                countSeats--;

                long chargeAmount = passengersArrayList.get(passengerIndex).getCharge();
                chargeAmount -= flightsArrayList.get(flightIndex).getPrice();
                passengersArrayList.get(passengerIndex).setCharge(chargeAmount);
                flightsArrayList.get(flightIndex).setSeats(countSeats);

                Tickets ticket = new Tickets(ticketId, flightsArrayList.get(flightIndex));
                ticketsArrayList.add(ticket);

                System.out.println("Ticket booked.");
                System.out.println("Your ticketId is : " + ticketId);
                System.out.println("Press Enter To Return...");
                input.nextLine();
                return ;
            }
        }
    }


    //    این متد برای نمایش بلیت های رزرو شده می باشد.
    public void bookedTickets (ArrayList<Tickets> ticketsArrayList) {
        System.out.print("\033[H\033[2J");
        System.out.flush();

        System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
        System.out.println("                                         Booked Tickets                                      ");
        System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\n");

        if (ticketsArrayList.isEmpty()) {
            System.out.println("There is nothing to see!");
        }

        else {
            System.out.printf("|%-25s|%-15s|%-13s|%-13s|%-12s|%-8s|%-15s|", "TicketId", "FlightId", "Origin", "Destination", "Date", "Time", "Amount Paid");
            System.out.println("\n.......................................................................................................................");
            for (Tickets tickets : ticketsArrayList) {
                System.out.printf(Locale.US, "|%-25s|%-15s|%-13s|%-13s|%-12s|%-8s|%-,15d|", tickets.getTicketId(), tickets.getFlightInfo().getFlightId(), tickets.getFlightInfo().getOrigin(), tickets.getFlightInfo().getDestination(), tickets.getFlightInfo().getDate(), tickets.getFlightInfo().getTime(), tickets.getFlightInfo().getPrice());
                System.out.println("\n.......................................................................................................................");
            }
        }

        System.out.println("Press Enter To Return...");
        input.nextLine();
    }


    //    این متد برای اینه که مسافر یتونه حسابشو شارژ کنه.
    public void addCharge (ArrayList<Passengers> passengersArrayList, int passengerIndex) {
        while (true) {
            System.out.print("\033[H\033[2J");
            System.out.flush();

            System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
            System.out.println("                              Add Charge                             ");
            System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\n");
            System.out.println("Please enter the amount of charge you want to add to your wallet : ");
            System.out.println("NOTE: If you want to return to the passengers menu, please type \"return\" and then press enter.");
            System.out.print(">> ");
            String charge = input.nextLine();

            if (Objects.equals(charge, "return")) {
                return ;
            }

            if (!(checkingEnteredData.isEnteredNumberRight(charge))) {
                System.out.println("\nYour entered charge is wrong! Please try again!");
                System.out.println("Press Enter To Continue...");
                input.nextLine();
                continue;
            }

            long passengerCharge = passengersArrayList.get(passengerIndex).getCharge();
            passengerCharge += Integer.parseInt(charge);
            passengersArrayList.get(passengerIndex).setCharge(passengerCharge);
            System.out.println("\nCharging done!");
            System.out.println("The amount of your charge is : " + passengerCharge);
            System.out.println("Press Enter To Return...");
            input.nextLine();
            break;
        }
    }


    //   این متد برای کنسل کردن بلیت مورد استفاده قرار می گیرد.
    public void ticketCancellation (ArrayList<Passengers> passengersArrayList, ArrayList<Tickets> ticketsArrayList, int passengerIndex) {
        while (true) {
            System.out.print("\033[H\033[2J");
            System.out.flush();

            if (ticketsArrayList.isEmpty()) {
                System.out.println("There is no booked tickets for cancelling!");
                System.out.println("Press Enter To Return...");
                input.nextLine();
                return ;
            }

            System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
            System.out.println("                        Ticket Cancellation                        ");
            System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\n");
            System.out.println("Please enter the ticketId : ");
            System.out.println("NOTE: If you want to return to the passengers menu, please type \"return\" and then press enter.");
            System.out.print(">> ");
            String ticketId = input.nextLine();

            if (Objects.equals(ticketId, "return")) {
                return ;
            }

            for (int i = 0; i < ticketsArrayList.size(); i++) {
                if (ticketId.equals(ticketsArrayList.get(i).getTicketId())) {
                    long passengerCharge = passengersArrayList.get(passengerIndex).getCharge();
                    passengerCharge += ticketsArrayList.get(i).getFlightInfo().getPrice();
                    passengersArrayList.get(passengerIndex).setCharge(passengerCharge);

                    int countSeats = ticketsArrayList.get(i).getFlightInfo().getSeats();
                    countSeats++;
                    ticketsArrayList.get(i).getFlightInfo().setSeats(countSeats);

                    int countCancelledSeats = passengersArrayList.get(passengerIndex).getCountCancelledTickets();
                    countCancelledSeats++;
                    passengersArrayList.get(passengerIndex).setCountCancelledTickets(countCancelledSeats);

                    ticketsArrayList.remove(i);

                    System.out.println("Ticket canceled!");
                    System.out.println("Press Enter To Return...");
                    input.nextLine();
                    return ;
                }
            }

            System.out.println("This ticketId not exists! Please try another ticketId.");
            System.out.println("Press Enter To Continue...");
            input.nextLine();
        }
    }
}
