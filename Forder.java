public class Forder {
    String Onumber;
    String quantity;
    String date;
    String price;
    String customer_id;
    String taken = "true";

    public String getTaken() {
        return taken;
    }

    public void setTaken(String taken) {
        taken = taken;
    }

    public String getOnumber() {
        return Onumber;
    }

    public void setOnumber(String onumber) {
        Onumber = onumber;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

}
