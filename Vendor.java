package AssetManagement;

import java.util.ArrayList;
import java.util.List;

public class Vendor {
    static int id = 0;

    int vendorId = ++id;
    String vendorName;
    List<Software> softwareList;
    int amountReceived;

    public Vendor(String name) {
        this.vendorName = name;
        this.softwareList = new ArrayList<>();
        this.amountReceived = 0;
    }
}
