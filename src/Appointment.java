class Appointment {
    private final String appointmentId;
    private final String patientId;
    private final String doctorId;
    private final Slot slot;
    private AppointmentStatus status;

    public Appointment(String appointmentId, String patientId, String doctorId, Slot slot) {
        this.appointmentId = appointmentId;
        this.patientId     = patientId;
        this.doctorId      = doctorId;
        this.slot          = slot;
        this.status        = AppointmentStatus.BOOKED;
    }

    public void cancel()   {
        status = AppointmentStatus.CANCELLED;
        slot.release();
    }
    public void complete() {
        status = AppointmentStatus.COMPLETED;
    }

    public String getAppointmentId() { return appointmentId; }
    public String getPatientId()     { return patientId; }
    public String getDoctorId()      { return doctorId; }
    public AppointmentStatus getStatus() { return status; }


    public String toString(){
        return  "\nAppointment Id        :"+appointmentId+
                "\nPatient Id            :"+patientId+
                "\nDoctor Id             :"+doctorId+
                "\nSlot                  :"+slot+
                "\nStatus                :"+status+"\n";
    }
}