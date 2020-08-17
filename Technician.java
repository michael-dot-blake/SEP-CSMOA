public class Technician {

    private int fitterDistrict;
    private int technician_id;


    public Technician(int technician_id, int fitterDistrict) {
        this.technician_id = technician_id;
        this.fitterDistrict = fitterDistrict;

    }

    public String toString() {
        return "GST info: " + technician_id + ", " + fitterDistrict;
    }

    
    
}