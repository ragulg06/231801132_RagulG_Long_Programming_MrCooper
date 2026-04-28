import java.util.ArrayList;
import java.util.List;

class Doctor {

    private final String doctorId;
    private final String name;
    private final String speciality;
    private final List<Slot> slots = new ArrayList<>();

    public Doctor(String doctorId, String name, String speciality) {
        this.doctorId   = doctorId;
        this.name       = name;
        this.speciality = speciality;
    }

    public void addSlot(Slot slot) { slots.add(slot); }

    public List<Slot> getAvailableSlots() {
        List<Slot> available = new ArrayList<>();
        for (Slot s : slots) {
            if (s.isAvailable()){
                available.add(s);
            }
        }
        return available;
    }

    public Slot findSlot(String slotId) {
        for (Slot s : slots){
            if (s.getSlotId().equals(slotId)){
                return s;
            }
        }
        return null;
    }

    public String getDoctorId()  { return doctorId; }
    public String getName()      { return name; }
    public String getSpeciality(){ return speciality; }

    public String toString(){
        return  "\nDoctor ID     :"+doctorId+
                "\nDoctor Name   :"+name+
                "\nDoctor Speciality : "+speciality+"\n";
    }
}