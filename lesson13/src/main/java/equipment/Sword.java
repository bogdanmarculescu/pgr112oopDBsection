package equipment;

public class Sword extends Equipment{
    private String type;

    public Sword(){
        this.type = "EPEE";
    }
    public Sword(String type){
        this.type = type;
    }
    @Override
    public boolean requiresMaintenance() {
        return this.requiresMaintenance;
    }

    @Override
    public void performMaintenance() {
        System.out.println("Straighten and check connection");
        this.requiresMaintenance = false;
    }

    @Override
    public String store() {
        return location.getLocation();
    }

    public String getType(){
        return this.type;
    }
    public void setType(String type){
        this.type = type;
    }
}
