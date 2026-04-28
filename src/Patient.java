class Patient {
    private final String patientId;
    private final String name;
    private final int    age;
    private final String phone;

    public Patient(String patientId, String name, int age, String phone) {
        this.patientId = patientId;
        this.name      = name;
        this.age       = age;
        this.phone     = phone;
    }

    public String getPatientId() { return patientId; }
    public String getName()      { return name; }

    @Override
    public String toString() {
        return patientId + " | " + name + " | Age: " + age + " | Phone: " + phone;
    }
}