package equipment;

public class Football extends Equipment implements Kickable{
    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    private int weight;
    public Football(){
        new Football("RandomFootball", new Locker());
    }
    public Football(String name){
        new Football(name, new Locker());
    }

    public Football(String name, Locker locker){
        this.requiresMaintenance = false;
        this.name = name;
        this.location = locker;
        this.weight = 450;
    }

    @Override
    public boolean requiresMaintenance() {
        return this.requiresMaintenance;
    }

    @Override
    public void performMaintenance() {
        this.requiresMaintenance = false;
        System.out.println("Inflate football");
    }

    @Override
    public String store() {
        return location.getLocation();
    }

    @Override
    public String printout() {
         return "Football! (can be kicked) \n"
                + "Name: " + this.name + "\n"
                + "Maintenance: " + this.requiresMaintenance + "\n"
                + "Location: " + this.location.getLocation();
    }

    @Override
    public boolean kick() {
        System.out.println("Kicked!");
        return true;
    }
}
