package essences;

public class Ticket {

    private int ticketId;
    private String ticketCode;
    private String pasName;
    private String pasSurname;
    private String flightDate;
    private String dispachPoint;
    private String destinationPoint;
    private String plane;

    public Ticket(int ticketId, String ticketCode, String pasName, String pasSurname, String flightDate, String dispachPoint, String destinationPoint, String plane) {
        this.ticketId = ticketId;
        this.ticketCode = ticketCode;
        this.pasName = pasName;
        this.pasSurname = pasSurname;
        this.flightDate = flightDate;
        this.dispachPoint = dispachPoint;
        this.destinationPoint = destinationPoint;
        this.plane = plane;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public String getTicketCode() {
        return ticketCode;
    }

    public void setTicketCode(String ticketCode) {
        this.ticketCode = ticketCode;
    }

    public String getPasName() {
        return pasName;
    }

    public void setPasName(String pasName) {
        this.pasName = pasName;
    }

    public String getPasSurname() {
        return pasSurname;
    }

    public void setPasSurname(String pasSurname) {
        this.pasSurname = pasSurname;
    }

    public String getFlightDate() {
        return flightDate;
    }

    public void setFlightDate(String flightDate) {
        this.flightDate = flightDate;
    }

    public String getDispachPoint() {
        return dispachPoint;
    }

    public void setDispachPoint(String dispachPoint) {
        this.dispachPoint = dispachPoint;
    }

    public String getDestinationPoint() {
        return destinationPoint;
    }

    public void setDestinationPoint(String destinationPoint) {
        this.destinationPoint = destinationPoint;
    }

    public String getPlane() {
        return plane;
    }

    public void setPlane(String plane) {
        plane = plane;
    }
}
