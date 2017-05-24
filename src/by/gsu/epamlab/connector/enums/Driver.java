package by.gsu.epamlab.connector.enums;

public enum Driver {
    MY_SQL("org.gjt.mm.mysql.Driver");

    private final String driverName;

    Driver(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverName() {
        return driverName;
    }
}
