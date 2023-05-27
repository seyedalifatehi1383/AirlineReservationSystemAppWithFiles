// این کلاس برای مدیریت بلیت های رزرو شده مسافران طراحی شده است
public class Ticket {
    private String ticketId;
    private Flight flightInfo;

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public Flight getFlightInfo() {
        return flightInfo;
    }

    public void setFlightInfo(Flight flightInfo) {
        this.flightInfo = flightInfo;
    }

    public Ticket(String ticketId, Flight flightInfo) {
        this.ticketId = ticketId;
        this.flightInfo = flightInfo;
    }
}
