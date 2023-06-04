import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class Flights {
    public class Passengers {
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
    }
}
