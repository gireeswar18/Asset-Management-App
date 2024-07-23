package AssetManagement;

import java.util.ArrayList;
import java.util.List;

public class Employee {

    static int id = 0;

    int empId = ++id;
    String name;
    List<Device> deviceList;

    public Employee(String name) {
        this.name = name;
        deviceList = new ArrayList<>();
    }

    public int getEmpId() {
        return empId;
    }

    public String getName() {
        return name;
    }

    public List<Device> getDeviceList() {
        return deviceList;
    }
}
