package pwa.mensaapp.response;



public class ResponseMeals {
    private int id;
    private String name;
    private String[] notes;
    private String category;
    private ResponsePrice prices;

    public ResponseMeals() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getNotes() {
        return notes;
    }

    public void setNotes(String[] notes) {
        this.notes = notes;
    }

    public ResponsePrice getPrices() { return prices; }

    public void setPrices(ResponsePrice prices) {
        this.prices = prices;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
