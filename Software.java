package AssetManagement;

import java.util.Date;

public class Software {
    static int id = 0;

    int softwareId = ++id;
    Vendor vendor;
    String name;
    Date expirationDate;
    int price;

    public Software(String name, Date expirationDate, int price, Vendor vendor) {
        this.name = name;
        this.expirationDate = expirationDate;
        this.price = price;
        this.vendor = vendor;
    }

    public int getSoftwareId() {
        return softwareId;
    }

    public String getName() {
        return name;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public int getPrice() {
        return price;
    }
}
