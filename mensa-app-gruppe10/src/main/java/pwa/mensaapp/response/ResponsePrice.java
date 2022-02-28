package pwa.mensaapp.response;

public class ResponsePrice {
    private float student;
    private float employees;
    private float others;
    private float pupils;

    public ResponsePrice() {
    }

    public float getStudent() {
        return student;
    }

    public void setStudent(float student) {
        this.student = student;
    }

    public float getEmployees() {
        return employees;
    }

    public void setEmployees(float employees) {
        this.employees = employees;
    }

    public float getOthers() {
        return others;
    }

    public void setOthers(float others) {
        this.others = others;
    }

    public float getPupils() {
        return pupils;
    }

    public void setPupils(float pupils) {
        this.pupils = pupils;
    }
}
