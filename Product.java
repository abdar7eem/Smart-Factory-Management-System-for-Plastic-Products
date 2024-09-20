public class Product {
    String Pname;
    String Pprice;
    String Sprice;
    String Pimage;
    String Plimit;
    String Pquantity;
    String Pid;
    double reqMaterial;

    public String getPname() {
        return Pname;
    }

    public void setPname(String pname) {
        Pname = pname;
    }

    public String getPprice() {
        return Pprice;
    }

    public void setPprice(String pprice) {
        Pprice = pprice;
    }

    public String getPimage() {
        return Pimage;
    }

    public void setPimage(String pimage) {
        Pimage = pimage;
    }

    public String getPlimit() {
        return Plimit;
    }

    public void setPlimit(String plimit) {
        Plimit = plimit;
    }

    public String getPquantity() {
        return Pquantity;
    }

    public void setPquantity(String pquantity) {
        Pquantity = pquantity;
    }

    public String getPid() {
        return Pid;
    }

    public void setPid(String pid) {
        Pid = pid;
    }

    public String getSprice() {
        return Sprice;
    }

    public void setSprice(String sprice) {
        Sprice = sprice;
    }

    public double getReqMaterial() {
        return reqMaterial;
    }

    public void setReqMaterial(double reqMaterial) {
        this.reqMaterial = reqMaterial;
    }

    @Override
    public String toString() {
        return "Product [Pname=" + Pname + ", Pprice=" + Pprice + ", Sprice=" + Sprice + ", Pimage=" + Pimage
                + ", Plimit=" + Plimit + ", Pquantity=" + Pquantity + ", Pid=" + Pid + ", Pcoast=";
    }

}
