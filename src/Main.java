import java.util.List;
import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);
    static Hospital hospital = Hospital.getInstance();

    public static void main(String[] args) {
        sampleData();
        boolean running = true;
        while (running) {
            printMenu();
            System.out.print("  Choose: ");
            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1"  -> registerPatient();
                case "2"  -> listDoctors();
                case "3"  -> searchBySpeciality();
                case "4"  -> viewAvailableSlots();
                case "5"  -> bookAppointment();
                case "6"  -> cancelAppointment();
                case "8"  -> hospital.showAllAppointments();
                case "9"  -> hospital.showConsultations();
                case "0"  -> { System.out.println("\n  Goodbye!\n"); running = false; }
                default   -> System.out.println("  ✗ Invalid option.");
            }
        }
    }

    static void printMenu() {
        System.out.println("===============CHOOSE THE OPTIONS=================");
        System.out.println("""
            \n
             1. Register Patient           \s
             2. List All Doctors           \s
             3. Search Doctor by Speciality\s
             4. View Available Slots       \s
             5. Book Appointment           \s
             6. Cancel Appointment         \s
             8. View All Appointments      \s
             9. View Consultation Records  \s
             0. Exit                       \s
           \s""");
        System.out.println("===============================");
    }

    // ── Actions ──────────────────────────

    static void registerPatient() {
        System.out.println("\n  -- Register Patient --");
        System.out.print("  Patient ID : "); String id   = sc.nextLine().trim();
        System.out.print("  Name       : "); String name = sc.nextLine().trim();
        System.out.print("  Age        : "); int age     = Integer.parseInt(sc.nextLine().trim());
        System.out.print("  Phone      : "); String phone= sc.nextLine().trim();
        hospital.registerPatient(new Patient(id, name, age, phone));
        System.out.println("Patient registered: " + id);
    }

    static void listDoctors() {
        System.out.println("\n  -- All Doctors --");
        hospital.getAllDoctors().forEach(d -> System.out.println("  " + d));
    }

    static void searchBySpeciality() {
        System.out.print("\nEnter Speciality: ");
        String spec = sc.nextLine().trim();
        List<Doctor> result = hospital.searchBySpeciality(spec);
        if (result.isEmpty()){
            System.out.println("No doctors found for: " + spec);
        }else{
            result.forEach(d -> System.out.println("  " + d));
        }
    }

    static void viewAvailableSlots() {
        System.out.print("\nEnter Doctor ID: ");
        String dId = sc.nextLine().trim();
        Doctor d = hospital.findDoctor(dId);
        if (d == null) { System.out.println("Doctor not found."); return; }
        List<Slot> slots = d.getAvailableSlots();
        if (slots.isEmpty()) System.out.println("  No available slots.");
        else slots.forEach(s -> System.out.println("  " + s));
    }

    static void bookAppointment() {
        System.out.println("\n  -- Book Appointment --");
        System.out.print("  Patient ID : "); String pid = sc.nextLine().trim();
        if(!hospital.patients.containsKey(pid)){
            System.out.println("Patient Not found with this ID: "+pid);
        }
        System.out.print("  Doctor ID  : "); String did = sc.nextLine().trim();

        Doctor d = hospital.findDoctor(did);
        if (d != null) {
            System.out.println("  Available slots:");
            d.getAvailableSlots().forEach(s -> System.out.println("    " + s));
        }

        System.out.print("  Slot ID    : ");
        String sid = sc.nextLine().trim();
        hospital.bookAppointment(pid, did, sid);
    }

    static void cancelAppointment() {
        System.out.print("\n  Appointment ID: ");
        hospital.cancelAppointment(sc.nextLine().trim());
    }
    // ── Sample Data ────────────────────────
    static void sampleData() {
        Doctor d1 = new Doctor("D001", "Dhoni",  "Cardiology");
        d1.addSlot(new Slot("S1", "09:00", "09:30"));
        d1.addSlot(new Slot("S2", "09:30", "10:00"));
        d1.addSlot(new Slot("S3", "10:00", "10:30"));

        Doctor d2 = new Doctor("D002", "Virat Kholi",    "Neurology");
        d2.addSlot(new Slot("S4", "11:00", "11:30"));
        d2.addSlot(new Slot("S5", "11:30", "12:00"));

        Doctor d3 = new Doctor("D003", "sachin",   "Cardiology");
        d3.addSlot(new Slot("S6", "14:00", "14:30"));

        hospital.addDoctor(d1);
        hospital.addDoctor(d2);
        hospital.addDoctor(d3);

        hospital.registerPatient(new Patient("P001", "Ragul G",  30, "9876543210"));
        hospital.registerPatient(new Patient("P002", "David", 25, "9123456780"));
    }
}