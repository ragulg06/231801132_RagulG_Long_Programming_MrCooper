class Consultation {
    private final String consultationId;
    private final String appointmentId;
    private String notes;
    private String prescription;

    public Consultation(String consultationId, String appointmentId) {
        this.consultationId = consultationId;
        this.appointmentId  = appointmentId;
    }

    public void addNotes(String notes)            { this.notes = notes; }
    public void addPrescription(String presc)     { this.prescription = presc; }
    public String getAppointmentId()              { return appointmentId; }

    public String toString(){
        return "\nConsultation Id    :"+consultationId+
                "\nAppointment Id    :"+appointmentId+
                "\nNotes             :"+notes+
                "\nPrescription      :"+prescription+"\n";
    }
}
