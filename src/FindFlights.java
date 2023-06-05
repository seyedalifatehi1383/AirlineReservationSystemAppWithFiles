import java.util.ArrayList;
import java.util.Objects;

// این کلاس برای متدهای پیدا کردن پرواز ها در منوی مسافران می باشد.

public class FindFlights {
//    نحوه کارکرد تمام متد ها بدین صورت است که اول در متد searchFlightTickets که در کلاس مخصوص متدهای منوی مسافران قرار دارد ما اول تمام پروازها را در لیست ارایه findFlights کپی کردیم.
//    بعدش در هریک ازاین متدها اگر ان ویژگی مدنظر کاربر در ان پرواز دیده نشود، ان پرواز از لیست ارایه مخصوص پیدا کردن پروازها حذف می شود.

    public void findOrigin(ArrayList<Flight> foundFlightsArrayList, String origin) {
        if (!foundFlightsArrayList.isEmpty()) {
            for (int i = 0; i < foundFlightsArrayList.size(); i++) {
                if (!Objects.equals(foundFlightsArrayList.get(i).getOrigin(), origin)) {
                    foundFlightsArrayList.remove(i);
                }
            }
        }
    }

    public void findDestination(ArrayList<Flight> foundFlightsArrayList, String destination) {
        if (!foundFlightsArrayList.isEmpty()) {
            for (int i = 0; i < foundFlightsArrayList.size(); i++) {
                if (!Objects.equals(foundFlightsArrayList.get(i).getDestination(), destination)) {
                    foundFlightsArrayList.remove(i);
                }
            }
        }
    }

    public void findDate(ArrayList<Flight> foundFlightsArrayList, String date) {
        if (!foundFlightsArrayList.isEmpty()) {
            for (int i = 0; i < foundFlightsArrayList.size(); i++) {
                if (!Objects.equals(foundFlightsArrayList.get(i).getDate(), date)) {
                    foundFlightsArrayList.remove(i);
                }
            }
        }
    }

    public void findTime(ArrayList<Flight> foundFlightsArrayList, String time) {
        if (!foundFlightsArrayList.isEmpty()) {
            for (int i = 0; i < foundFlightsArrayList.size(); i++) {
                if (!Objects.equals(foundFlightsArrayList.get(i).getTime(), time)) {
                    foundFlightsArrayList.remove(i);
                }
            }
        }
    }

    public void findPrice(ArrayList<Flight> foundFlightsArrayList, int minPrice, int maxPrice) {
        if (!foundFlightsArrayList.isEmpty()) {
            for (int i = 0; i < foundFlightsArrayList.size(); i++) {
                if (foundFlightsArrayList.get(i).getPrice() > maxPrice || foundFlightsArrayList.get(i).getPrice() < minPrice) {
                    foundFlightsArrayList.remove(i);
                }
            }
        }
    }
}
