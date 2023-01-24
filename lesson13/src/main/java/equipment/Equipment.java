package equipment;

public abstract class Equipment {

    protected long id;
    protected String name;
    protected String location;

    protected boolean requiresMaintenance;

    public abstract boolean requiresMaintenance();
    public abstract void performMaintenance();
    public void requestMaintenance(){
        this.requiresMaintenance = true;
    }
    public void maintenanceDone(){
        this.requiresMaintenance = false;
    }

    public abstract String store();

}
