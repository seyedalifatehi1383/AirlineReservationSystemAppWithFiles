import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

// این کلاس برای منوهای اصلی برنامه می باشد
public class MainMenus {
    Scanner input = new Scanner(System.in);

    //    این متد برای صفحه اول برنامه طراحی شده است
    public void mainMenu(RandomAccessFile flightsFile, RandomAccessFile passengersFile) {
        while (true) {
            System.out.print("\033[H\033[2J");
            System.out.flush();

            System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::");
            System.out.println("     WELCOME TO AIRLINE RESERVATION SYSTEM     ");
            System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::");
            System.out.println(" .................MENU OPTIONS................ ");
            System.out.println();
            System.out.println("    <1> Sign in");
            System.out.println("    <2> Sign up");
            System.out.println("    <3> Exit");
            System.out.print(  " >> "  );
            String choose = input.nextLine();
            switch (choose) {
                case "1":
                    signInMenu(flightsArrayList, passengersArrayList);
                    break;
                case "2":
                    signUpMenu(passengersArrayList);
                    break;
                case "3":
                    return ;
                default:
                    System.out.println("Please check your command!");
                    System.out.println("Press Enter To Continue...");
                    input.nextLine();
                    break;
            }
        }
    }


    //    این متد برای منوی ورود به سیستم طراحی شده است
    public void signInMenu (ArrayList<Flights> flightsArrayList, ArrayList<Passengers> passengersArrayList) {
        Scanner input = new Scanner(System.in);
        int passengerIndex = 0;

        while (true) {
            System.out.print("\033[H\033[2J");
            System.out.flush();

            System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
            System.out.println(".......................................................SIGN IN MENU......................................................");
            System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
            System.out.println(" NOTE : IF YOU WANT TO RETURN TO THE MAIN MENU, PLEASE TYPE \"return\" AS THE USERNAME OR THE PASSWORD AND THEN PRESS ENTER.\n");
            System.out.print(  "    Please enter your username : ");
            String username = input.nextLine();
            if (Objects.equals(username, "return")) {
                return ;
            }

            boolean flag = false;
            for (int i = 0; i < passengersArrayList.size(); i++) {
                if (Objects.equals(passengersArrayList.get(i).getUsername(), username)) {
                    flag = true;
                    passengerIndex = i;
                    break;
                }
            }

            if (!flag && !Objects.equals(username, "Admin")) {
                System.out.println("\nThis username not exists. Please sign up first and then try again!");
                System.out.println("Press Enter To Continue...");
                input.nextLine();
                continue;
            }

            System.out.print("\n    Please enter your password : ");
            String password = input.nextLine();

            if (Objects.equals(password, "return")) {
                return ;
            }

            if (Objects.equals(username, "Admin") && Objects.equals(password, "Admin")) {
                adminMenu(flightsArrayList);
            }

            else {
                if (!passengersArrayList.isEmpty() && !Objects.equals(username, "Admin")) {
                    if (Objects.equals(passengersArrayList.get(passengerIndex).getPassword(), password)) {
                        passengersMenu(flightsArrayList, passengersArrayList, passengerIndex);
                    }

                    else {
                        System.out.println("\nWrong password! Please sign in again!");
                        System.out.println("Press Enter To Continue...");
                        input.nextLine();
                    }
                }

                else {
                    System.out.println("\nWrong password! Please sign in again!");
                    System.out.println("Press Enter To Continue...");
                    input.nextLine();
                }
            }
        }
    }


    //    این متد برای منوی ثبتنام در سیستم طراحی شده است و کاربر نمیتواند با نام کاربری مخصوص ادمین (Admin) و نام های کاربری قبلی در سایت ثبتنام نماید
    public void signUpMenu (ArrayList<Passengers> passengersArrayList) {
        signUpMenuLoop: while (true) {
            System.out.print("\033[H\033[2J");
            System.out.flush();

            System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
            System.out.println(".......................................................SIGN UP MENU......................................................");
            System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
            System.out.println(" NOTE : IF YOU WANT TO RETURN TO THE MAIN MENU, PLEASE TYPE \"return\" AS THE USERNAME OR THE PASSWORD AND THEN PRESS ENTER.\n");
            System.out.print(  "    Please enter your username : ");
            String username = input.nextLine();

            if (Objects.equals(username, "return")) {
                return ;
            }

            for (Passengers passengers : passengersArrayList) {
                if (Objects.equals(username, passengers.getUsername())) {
                    System.out.println("\nThis username already exists. Please try another username.");
                    System.out.println("Press Enter To Continue...");
                    input.nextLine();
                    continue signUpMenuLoop;
                }
            }

            if (Objects.equals(username, "Admin")) {
                System.out.println("\nYou are not allowed to use this username. Please try another username.");
                System.out.println("Press Enter To Continue...");
                input.nextLine();
                continue;
            }

            System.out.print("\n    Please enter your password : ");
            String password = input.nextLine();
            if (Objects.equals(password, "return")) {
                return ;
            }

            System.out.print("\n    Please confirm your password : ");
            String confirmPassword = input.nextLine();
            if (!Objects.equals(password, confirmPassword)) {
                System.out.println("\nYou didn't confirm your password. Please sign up again!");
                System.out.println("Press Enter To Continue...");
                input.nextLine();
                continue;
            }

            Passengers passenger = new Passengers(username, password, 0, 0) ;
            passengersArrayList.add(passenger);
            System.out.println("\nSigning up Done!!!");
            System.out.println("Press Enter To Return...");
            input.nextLine();
            return ;
        }
    }
}
