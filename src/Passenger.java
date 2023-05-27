import java.io.FileNotFoundException;
import java.io.RandomAccessFile;

//این کلاس برای مسافر می باشد.
public class Passenger {
    private String username;
    private String password;
    private long charge;

    //    این ویژگی برای شمردن تعداد دفعات کنسل کردن بلیت ها می باشد.
    //    این ویژگی کاربرد زیادی ندارد و صرفا بدین منظور به وجود امده که بتوان تیکت ایدی منحصر به فرد برای هر بلیت طراحی شود (توضیحات بیشتر در کامنت های موجود در متد booking ticket در کلاس مخصوص مسافران)
    private int countCancelledTickets;

    private RandomAccessFile tickets;

    public long getCharge() {
        return charge;
    }

    public void setCharge(long charge) {
        this.charge = charge;
    }

    public String getUsername() {
        return username;
    }

    public int getCountCancelledTickets() {
        return countCancelledTickets;
    }

    public void setCountCancelledTickets(int countCancelledTickets) {
        this.countCancelledTickets = countCancelledTickets;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RandomAccessFile getTickets() {
        return tickets;
    }

    public void setTickets(RandomAccessFile tickets) {
        this.tickets = tickets;
    }

    public Passenger(String username, String password, long charge, int countCancelledTickets) throws FileNotFoundException {
        this.username = username;
        this.password = password;
        this.charge = charge;
        this.countCancelledTickets = countCancelledTickets;
        this.tickets = new RandomAccessFile("tickets.dat", "rw");
    }
}