package pwa.mensaapp.response;




public class RespondeOpeningStatus {
    private String date;
    private boolean closed;


    public RespondeOpeningStatus() {
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
