import java.util.Map;

// این کلاس برای مدیریت بلیت های رزرو شده مسافران طراحی شده است
public class Ticket {
    private Map<String, Flight> ticketInfo;
    public Ticket(Map<String, Flight> ticketInfo) {
        this.ticketInfo = ticketInfo;
    }
}
