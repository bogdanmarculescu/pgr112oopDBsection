package equipment;

public class Volleyball extends Equipment{


    public Volleyball(){
        new Volleyball("RandomVolleyball", new Locker());
    }
    public Volleyball(String name, Locker location){
        this.name = name;
        this.requiresMaintenance = false;
        this.location = location;

    }
    @Override
    public boolean requiresMaintenance() {
        return this.requiresMaintenance;
    }

    @Override
    public void performMaintenance() {
        this.requiresMaintenance = false;
        System.out.println("Inflate Volleyball!");
    }

    @Override
    public String store() {
        return location.getLocation();
    }
}
