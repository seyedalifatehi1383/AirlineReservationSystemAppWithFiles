import java.util.ArrayList;

// این کلاس برای متدهای مربوط به ادمین می باشد
public class Admin {
    // این متد برای "منوی اصلی" قسمت ادمین طراحی شده است.
    public void adminMenu (ArrayList<Flight> flightsArrayList) {
        AdminMenuMethods adminMenuMethods = new AdminMenuMethods();

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
                    adminMenuMethods.addFlights(flightsArrayList);
                    break;
                case "2":
                    adminMenuMethods.updateFlights(flightsArrayList);
                    break;
                case "3":
                    adminMenuMethods.removeFlights(flightsArrayList);
                    break;
                case "4":
                    adminMenuMethods.flightSchedules(flightsArrayList);
                    break;
                default:
                    System.out.println("Please check your command!");
                    System.out.println("Press Enter To Continue...");
                    input.nextLine();
                    break;
            }
        }
    }
}
