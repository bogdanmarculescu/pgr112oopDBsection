package equipment;

public abstract class Equipment {

    protected long id;
    protected Locker location;
    protected boolean requiresMaintenance;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    protected String name;

    public Locker getLocation() {
        return location;
    }

    public void setLocation(Locker location) {
        this.location = location;
    }



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
