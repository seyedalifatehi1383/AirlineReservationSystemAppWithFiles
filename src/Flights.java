import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class Flights {
    //    این متد برای مهیا کردن نام و ایدی برای رایت کردن در فایل می باشد
    public static String fixStringForWritingName(String str){
        while (str.length() < Flight.FIX_NAME_ID_SIZE)
            str += " ";
        return str.substring(0, Flight.FIX_NAME_ID_SIZE);
    }

    //    این متد برای خواندن نام و ایدی از فایل می باشد
    public static String readName(RandomAccessFile rfile) throws IOException {
        String str = "";
        while (str.length() < Flight.FIX_NAME_ID_SIZE)
            str += rfile.readChar();
        return str.trim();
    }

    //    این متد برای مهیا کردن استرینگ تاریخ برای رایت کردن در فایل می باشد
    public static String fixStringForWritingDate(String str){
        while (str.length() < Flight.FIX_DATE_SIZE)
            str += " ";
        return str.substring(0, Flight.FIX_DATE_SIZE);
    }

    //    این متد برای خواندن رشته تاریخ از فایل می باشد
    public static String readDate(RandomAccessFile rfile) throws IOException {
        String str = "";
        while (str.length() < Flight.FIX_DATE_SIZE)
            str += rfile.readChar();
        return str.trim();
    }

    //    این متد برای مهیا کردن استرینگ زمان برای رایت کردن در فایل می باشد
    public static String fixStringForWritingTime(String str){
        while (str.length() < Flight.FIX_TIME_SIZE)
            str += " ";
        return str.substring(0, Flight.FIX_TIME_SIZE);
    }

    //    این متد برای خواندن رشته زمان از فایل می باشد
    public static String readTime(RandomAccessFile rfile) throws IOException {
        String str = "";
        while (str.length() < Flight.FIX_TIME_SIZE)
            str += rfile.readChar();
        return str.trim();
    }

    // این متد برای ثبت اطلاعات در فایل می باشد
    public void writeFlightInfos(ArrayList<Flight> flightsArrayList, RandomAccessFile flightsFile) throws IOException {
        for (Flight flight : flightsArrayList) {
            flightsFile.writeInt(flight.getSeats()); // 4 bytes
            flightsFile.writeInt(flight.getFirstSeatsCount()); // 4 bytes
            flightsFile.writeInt(flight.getPrice()); // 4 bytes
            flightsFile.writeChars(fixStringForWritingName(flight.getFlightId())); // 40 bytes
            flightsFile.writeChars(fixStringForWritingName(flight.getOrigin())); // 40 bytes
            flightsFile.writeChars(fixStringForWritingName(flight.getDestination())); // 40 bytes
            flightsFile.writeChars(fixStringForWritingName(flight.getDate())); // 20 bytes
            flightsFile.writeChars(fixStringForWritingName(flight.getTime())); // 10 bytes
//            162 bytes
        }
    }

    // این متد برای پر کردن ارایه از اطلاعات فایل ها می باشد
    public void readFlightInfos(ArrayList<Flight> flightsArrayList, RandomAccessFile flightsFile) throws IOException {
        String flightId;
        String origin;
        String destination;
        String date;
        String time;
        int price;
        int seats;
        int firstSeatsCount;

        for (int i = 0; i < flightsFile.length() / 162; i++) {
            seats = flightsFile.readInt();
            firstSeatsCount = flightsFile.readInt();
            price = flightsFile.readInt();
            flightId = readName(flightsFile);
            origin = readName(flightsFile);
            destination = readName(flightsFile);
            date = readName(flightsFile);
            time = readName(flightsFile);
            Flight flight = new Flight(seats, destination, date, origin, flightId, time, price, firstSeatsCount);
            flightsArrayList.set(i, flight);
        }
    }
}
