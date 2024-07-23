package AssetManagement;

import java.util.Date;

public class Installation {

    static int id = 0;

    int installId = ++id;
    Date installDate;
    Software software;

    public Installation(Date installDate, Software software) {
        this.installDate = installDate;
        this.software = software;
    }

    public int getInstallId() {
        return installId;
    }

    public Date getInstallDate() {
        return installDate;
    }

    public Software getSoftware() {
        return software;
    }
}
