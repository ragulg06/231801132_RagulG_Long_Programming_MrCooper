class Slot {
    private final String slotId;
    private final String startTime;
    private final String endTime;
    private boolean isBooked;

    public Slot(String slotId, String startTime, String endTime) {
        this.slotId    = slotId;
        this.startTime = startTime;
        this.endTime   = endTime;
        this.isBooked  = false;
    }

    public boolean isAvailable()  { return !isBooked; }
    public void book(){ isBooked = true; }
    public void release(){ isBooked = false; }
    public String getSlotId(){ return slotId; }


    public String toString(){
        return  "\nSlotId        :"+slotId+
                "\nStart Time    :"+startTime+
                "\nEnd Time      :"+endTime+
                "\nBooked Stats  :"+(isBooked?"Booked": "Available")+"\n";
    }
}
