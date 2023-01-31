package equipment;

public class Locker {
    private int id;
    private String location;
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String printout(){
        return "Locker: \n" +
                "Id: " + this.id + "\n" +
                "Location: " + this.location + "\n" +
                "Address: " + this.address + "\n"
                ;
    }
}
