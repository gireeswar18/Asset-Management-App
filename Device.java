package AssetManagement;

import java.util.List;

public class Device {
    static int id = 0;

    int deviceId = ++id;
    List<Installation> installationList;

    public Device(List<Installation> installationList) {
        this.installationList = installationList;
    }

    public int getDeviceId() {
        return deviceId;
    }

    public List<Installation> getInstallationList() {
        return installationList;
    }
}
