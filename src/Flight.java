// این کلاس برای اطلاعات پرواز طراحی شده است

public class Flight {
    static final int FIX_DATE_SIZE = 10;
    static final int FIX_TIME_SIZE = 5;
    static final int FIX_NAME_ID_SIZE = 20;
    private int seats;
    private int firstSeatsCount;
    private String destination;
    private String date;
    private String origin;
    private String flightId;
    private String time;
    private int price;

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getFlightId() {
        return flightId;
    }

    public void setFlightId(String flightId) {
        this.flightId = flightId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Flight(int seats, String destination, String date, String origin, String flightId, String time, int price, int firstSeatsCount) {
        this.seats = seats;
        this.firstSeatsCount = firstSeatsCount;
        this.destination = destination;
        this.date = date;
        this.origin = origin;
        this.flightId = flightId;
        this.time = time;
        this.price = price;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getFirstSeatsCount() {
        return firstSeatsCount;
    }

    public void setFirstSeatsCount(int firstSeatsCount) {
        this.firstSeatsCount = firstSeatsCount;
    }
}
