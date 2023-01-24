package equipment;

public class Football extends Equipment implements Kickable{
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
        return null;
    }

    @Override
    public boolean kick() {
        System.out.println("Kicked!");
        return true;
    }
}
