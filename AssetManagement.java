package AssetManagement;

import java.text.SimpleDateFormat;
import java.util.*;

public class AssetManagement {

    HashMap<Integer, Vendor> vendorList = new HashMap<>();
    HashMap<Integer, Employee> employeeList = new HashMap<>();
    HashMap<Integer, Device> deviceList = new HashMap<>();
    HashMap<Integer, Software> softwareList = new HashMap<>();

    int totalAmountSpent = 0;

    public void menu() {

        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("Enter your choice: ");
            System.out.println(
                            "1. Add Employee" +
                            "\n2. Add Vendor" +
                            "\n3. Add Software" +
                            "\n4. Add Device" +
                            "\n5. Install Software" +
                            "\n6. Generate Report" +
                                    "\n7. Vendor List" +
                                    "\n8. Software List" +
                                    "\n9. Employee List" +
                                    "\n10. Device List" +
                            "\n11. Exit"
            );

            int choice = sc.nextInt();

            switch (choice)
            {
                case 1: {
                    System.out.println("Enter employee name: ");
                    String name = sc.next();

                    Employee employee = new Employee(name);
                    employeeList.put(employee.empId, employee);

                    System.out.println("Employee Added...");
                    System.out.println("Employee Id: " + employee.empId);
                    System.out.println("Employee Name: " + employee.name);
                    System.out.println("Employee Device List: " + employee.deviceList);
                    printBreak();
                    break;
                }
                case 2: {
                    System.out.println("Enter vendor name: ");
                    String name = sc.next();

                    Vendor vendor = new Vendor(name);
                    vendorList.put(vendor.vendorId, vendor);

                    System.out.println("Vendor Added...");
                    System.out.println("Vendor Id: " + vendor.vendorId);
                    System.out.println("Vendor Name: " + vendor.vendorName);
                    System.out.println("Vendor Software List: " + vendor.softwareList);
                    printBreak();
                    break;
                }
                case 3: {
                    System.out.println("Enter vendor Id:");
                    int vendorId = sc.nextInt();

                    Vendor vendor = vendorList.get(vendorId);
                    if (vendor == null)
                    {
                        System.out.println("Vendor Not Available! Add Vendor First!");
                        printBreak();
                        break;
                    }

                    System.out.println("Enter software name: ");
                    String name = sc.next();
                    System.out.println("Enter expiration date: (yyyy-MM-dd)");
                    String date = sc.next();

                    Date expDate = dateFormatter(date);
                    if (expDate == null)
                    {
                        printBreak();
                        break;
                    }
                    System.out.println("Enter price: ");
                    int price = sc.nextInt();

                    Software software = new Software(name, expDate, price, vendor);
                    vendor.softwareList.add(software);

                    System.out.println("Software id: " + software.softwareId);
                    System.out.println("Software Added Successfully...");
                    softwareList.put(software.softwareId, software);

                    printBreak();
                    break;
                }
                case 4: {
                    System.out.println("Enter Employee Id:");
                    int empId = sc.nextInt();

                    if (!employeeList.containsKey(empId))
                    {
                        System.out.println("Employee Id not found!");
                        printBreak();
                        break;
                    }

                    Employee employee = employeeList.get(empId);
                    Device device = new Device(new ArrayList<Installation>());

                    employee.deviceList.add(device);
                    System.out.println("Device Id: " + device.deviceId);

                    System.out.println("Device Added Successfully...");
                    deviceList.put(device.deviceId, device);
                    printBreak();
                    break;
                }
                case 5: {
                    System.out.println("Enter employee id: ");
                    int empId = sc.nextInt();

                    if (!employeeList.containsKey(empId))
                    {
                        System.out.println("Employee not found!");
                        printBreak();
                        break;
                    }

                    Employee employee = employeeList.get(empId);

                    System.out.println("Enter device id: ");
                    int deviceId = sc.nextInt();

                    List<Device> deviceList = employee.deviceList;

                    Device deviceToInstall = null;

                    for (Device d : deviceList)
                    {
                        if (d.deviceId == deviceId)
                        {
                            deviceToInstall = d;
                            break;
                        }
                    }

                    if (deviceToInstall == null)
                    {
                        System.out.println("Device Not Found!");
                        printBreak();
                        break;
                    }


                    System.out.println("Enter current date: (yyyy-MM-dd)");
                    String date = sc.next();
                    Date currDate = dateFormatter(date);

                    if (currDate == null)
                    {
                        printBreak();
                        break;
                    }

                    System.out.println("Enter Vendor id: ");
                    int vendorId = sc.nextInt();

                    if (!vendorList.containsKey(vendorId))
                    {
                        System.out.println("Vendor not found!");
                        printBreak();
                        break;
                    }

                    Vendor vendor = vendorList.get(vendorId);

                    if (vendor.softwareList.isEmpty())
                    {
                        System.out.println("No Software available");
                        printBreak();
                        break;
                    }
                    System.out.println("Software List: ");
                    for (Software s : vendor.softwareList)
                    {
                        System.out.println(s.softwareId + "->" + s.name);
                    }

                    System.out.println("Enter software id: ");
                    int softwareId = sc.nextInt();

                    List<Software> softwareList = vendor.softwareList;

                    Software softwareToInstall = null;

                    for (Software s : softwareList)
                    {
                        if (s.softwareId == softwareId)
                        {
                            softwareToInstall = s;
                            break;
                        }
                    }

                    if (softwareToInstall == null)
                    {
                        System.out.println("Software Not Found!");
                        printBreak();
                        break;
                    }

                    Installation installation = new Installation(currDate, softwareToInstall);

                    deviceToInstall.installationList.add(installation);
                    totalAmountSpent += softwareToInstall.price;

                    vendor.amountReceived += softwareToInstall.price;

                    System.out.println("Device Installed Successfully...");
                    printBreak();
                    break;
                }
                case 6: {
                    generateReport();
                    break;
                }
                case 7: {
                    System.out.println("Vendor List: ");
                    for (int key : vendorList.keySet())
                    {
                        System.out.println("Vendor id: " + key);
                        List<Software> list = vendorList.get(key).softwareList;

                        System.out.println("Software List: ");
                        for (Software s : list)
                        {
                            System.out.println(s.softwareId + " -> " + s.name);
                        }
                        printBreak();
                    }
                    printBreak();
                    break;
                }
                case 8: {
                    System.out.println("Software List: ");
                    for (int key : softwareList.keySet())
                    {
                        System.out.println(key + " -> " + softwareList.get(key).getName());
                    }
                    printBreak();
                    break;
                }
                case 9: {
                    System.out.println("Employee List: ");
                    for (int key : employeeList.keySet())
                    {
                        System.out.println(key + " -> " + employeeList.get(key).name);
                        System.out.println("Device Count: " + employeeList.get(key).deviceList.size());

                        System.out.print("Device Id: [");
                        List<Device> list = employeeList.get(key).deviceList;
                        for (Device d : list)
                        {
                            System.out.print(d.deviceId + ", ");
                        }
                        System.out.println("]");
                        printBreak();
                    }
                    printBreak();
                    break;
                }
                case 10: {
                    System.out.println("Device List: ");
                    for (int key : deviceList.keySet())
                    {
                        System.out.println("Device Id: " + key);
                        System.out.println("Installed software: ");
                        for (Installation i : deviceList.get(key).installationList)
                        {
                            System.out.println(i.software.name);
                        }
                        printBreak();
                    }
                    printBreak();
                    break;
                }
                case 11: {
                    System.out.println("Exiting...");
                    System.exit(0);
                }
                default: {
                    System.out.println("Enter valid choice!");
                    printBreak();
                }
            }
        }
    }

    private void generateReport() {

        Scanner sc = new Scanner(System.in);
        while (true)
        {
            System.out.println("Enter your choice: ");
            System.out.println(
                            "1. No. of installations of a particular software" +
                            "\n2. No. of software installed in a device" +
                            "\n3. No. of software installed for an employee" +
                            "\n4. Amount spent for a software" +
                            "\n5. Amount spent for an employee" +
                            "\n6. Amount spent on a vendor" +
                            "\n7. No. of installations of software from a vendor" +
                            "\n8. Devices that have an expired software" +
                            "\n9. Return to main menu"
            );
            int choice = sc.nextInt();

            switch (choice) {
                case 1: {
                    System.out.println("Enter software id: ");
                    int softwareId = sc.nextInt();

                    if (!softwareList.containsKey(softwareId))
                    {
                        System.out.println("Software not found!");
                        printBreak();
                        break;
                    }

                    int count = 0;
                    for (int dId : deviceList.keySet())
                    {
                        Device d = deviceList.get(dId);
                        for (Installation i : d.installationList)
                        {
                            if (i.software.softwareId == softwareId)
                            {
                                count++;
                            }
                        }
                    }

                    System.out.println("Count: " + count);
                    printBreak();
                    break;
                }
                case 2: {
                    System.out.println("Enter device id:");
                    int id = sc.nextInt();

                    if (!deviceList.containsKey(id))
                    {
                        System.out.println("Device not found!");
                        printBreak();
                        break;
                    }

                    System.out.println("No. of software installed in this device: " + deviceList.get(id).installationList.size());
                    printBreak();
                    break;
                }
                case 3: {
                    System.out.println("Enter employee id: ");
                    int id = sc.nextInt();

                    if (!employeeList.containsKey(id))
                    {
                        System.out.println("Employee not found!");
                        printBreak();
                        break;
                    }

                    int count = 0;
                    Employee e = employeeList.get(id);

                    for (Device d : e.deviceList)
                    {
                        count += d.installationList.size();
                    }

                    System.out.println("No. of software installed for the employee: " + count);
                    printBreak();
                    break;
                }
                case 4: {
                    System.out.println("Enter software id:");
                    int id = sc.nextInt();

                    if (!softwareList.containsKey(id))
                    {
                        System.out.println("Software not found!");
                        printBreak();
                        break;
                    }

                    int price = 0;

                    for (int key : deviceList.keySet())
                    {
                        Device d = deviceList.get(key);
                        List<Installation> list = d.installationList;

                        for (Installation i : list)
                        {
                            if (i.software.softwareId == id)
                            {
                                price += i.software.price;
                            }
                        }
                    }

                    System.out.println("Total Amount Spent: " + price);
                    printBreak();
                    break;
                }
                case 5: {
                    System.out.println("Enter employee id: ");
                    int id = sc.nextInt();

                    if (!employeeList.containsKey(id))
                    {
                        System.out.println("Employee not found!");
                        printBreak();
                        break;
                    }

                    int price = 0;

                    Employee e = employeeList.get(id);
                    List<Device> list = e.deviceList;

                    for (Device d : list)
                    {
                        List<Installation> installationList = d.installationList;
                        for (Installation i : installationList)
                        {
                            price += i.software.price;
                        }
                    }

                    System.out.println("Total Price: " + price);
                    printBreak();
                    break;
                }
                case 6: {
                    System.out.println("Enter vendor id: ");
                    int id = sc.nextInt();

                    if (!vendorList.containsKey(id))
                    {
                        System.out.println("Vendor not found!");
                        printBreak();
                        break;
                    }

                    System.out.println("Amount spent on the vendor: " + vendorList.get(id).amountReceived);
                    printBreak();
                    break;
                }
                case 7: {
                    System.out.println("Enter vendor id: ");
                    int id = sc.nextInt();

                    if (!vendorList.containsKey(id))
                    {
                        System.out.println("Vendor not found!");
                        printBreak();
                        break;
                    }

                    int count = 0;

                    for (int key : deviceList.keySet())
                    {
                        Device d = deviceList.get(key);
                        List<Installation> installationList = d.installationList;

                        for (Installation installation : installationList)
                        {
                            if (installation.software.vendor.vendorId == id)
                            {
                                count++;
                            }
                        }
                    }

                    System.out.println("Total Buy from vendor: " + count);
                    printBreak();
                    break;
                }
                case 8: {
                    System.out.println("Enter current date: (yyyy-MM-dd)");
                    String date = sc.next();

                    Date currentDate = dateFormatter(date);

                    for (Integer key : deviceList.keySet())
                    {
                        Device d = deviceList.get(key);
                        List<Installation> list = d.installationList;

                        for (Installation i : list)
                        {
                            if (i.software.expirationDate.before(currentDate))
                            {
                                System.out.println("Device id: " + d.deviceId);
                                printBreak();
                                break;
                            }
                        }
                    }
                    printBreak();
                    break;
                }
                case 9: {
                    System.out.println("Returning to main menu...");
                    printBreak();
                    return;
                }
                default: {
                    System.out.println("Enter valid choice!");
                    printBreak();
                }
            }
        }

    }

    private Date dateFormatter(String date)
    {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        Date expDate = null;
        try
        {
           expDate = formatter.parse(date);
        }
        catch (Exception e)
        {
            System.out.println("Invalid Date format!");
        }

        return expDate;
    }

    private void printBreak()
    {
        System.out.println("---------------------------");
    }
}
