package equipment;

public class Volleyball extends Equipment{
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
        return null;
    }
}
