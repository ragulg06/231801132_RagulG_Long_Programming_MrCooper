import java.util.*;

class Hospital {
    private static Hospital instance;

    private int aptCounter = 1;
    private int conCounter = 1;

    private final String name;
    private final Map<String, Doctor> doctors =  new LinkedHashMap<>();
    final Map<String, Patient>patients = new LinkedHashMap<>();
    private final Map<String, Appointment> appointments = new LinkedHashMap<>();
    private final List<Consultation> consultations =  new ArrayList<>();


    private Hospital(String name) {
        this.name = name;
    }

    public static Hospital getInstance() {
        if (instance == null) instance = new Hospital("City General Hospital");
        return instance;
    }

    // Doctors
    public void addDoctor(Doctor d) {
        doctors.put(d.getDoctorId(), d);
    }

    public List<Doctor> searchBySpeciality(String speciality) {
        List<Doctor> result = new ArrayList<>();
        for (Doctor d : doctors.values())
            if (d.getSpeciality().equalsIgnoreCase(speciality)){
                result.add(d);
            }
        return result;
    }

    public Doctor findDoctor(String doctorId) { return doctors.get(doctorId); }
    public Collection<Doctor> getAllDoctors()  { return doctors.values(); }





    //Patients
    public void registerPatient(Patient p) {
        patients.put(p.getPatientId(), p);
    }
    public Patient findPatient(String id)  {
        return patients.get(id);
    }
    public Collection<Patient> getAllPatients() {
        return patients.values();
    }




    // Appointment
    public void bookAppointment(String patientId, String doctorId, String slotId) {
        Patient p = patients.get(patientId);
        if (p == null)  {
            System.out.println("Patient not found.");
            return;
        }

        Doctor d = doctors.get(doctorId);
        if (d == null)  {
            System.out.println("Doctor not found.");
            return;
        }

        Slot slot = d.findSlot(slotId);
        if (slot == null) {
            System.out.println("Slot not found.");
            return;
        }
        if (!slot.isAvailable()) {
            System.out.println("Slot already booked.");
            return;
        }

        slot.book();
        String aptId = "APT" + String.format("%03d", aptCounter++);
        Appointment apt = new Appointment(aptId, patientId, doctorId, slot);
        appointments.put(aptId, apt);
        System.out.println("Appointment confirmed: " + aptId);
    }

    public void cancelAppointment(String aptId) {
        Appointment apt = appointments.get(aptId);
        if (apt == null) {
            System.out.println("Appointment not found.");
            return;
        }
        if (apt.getStatus() != AppointmentStatus.BOOKED) {
            System.out.println("Cannot cancel — status is " + apt.getStatus());
            return;
        }
        apt.cancel();
        System.out.println(" Appointment " + aptId + " cancelled.");
    }


    public void showAllAppointments() {
        if (appointments.isEmpty()) {
            System.out.println("  No appointments.");
            return;
        }
        //appointments.values().forEach(a -> System.out.println("  " + a));
        for(Appointment a: appointments.values()){
            System.out.println("Appointments "+a);
        }

    }

    public void showConsultations() {
        if (consultations.isEmpty()) {
            System.out.println("  No consultations.");
            return;
        }
        //consultations.forEach(c -> System.out.println("  " + c));
        for(Consultation c : consultations){
            System.out.println("Consultations\n:"+c);
        }
    }

    public String getName() { return name; }
}
