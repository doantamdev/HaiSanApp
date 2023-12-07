package huflit.edu.haisanapp.product;

public class Checkout {
    int id;
    String nameCusCO;
    String sdtCusCO;
    String diachiCO;

    public Checkout(int id, String nameCusCO, String sdtCusCO, String diachiCO) {
        this.id = id;
        this.nameCusCO = nameCusCO;
        this.sdtCusCO = sdtCusCO;
        this.diachiCO = diachiCO;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameCusCO() {
        return nameCusCO;
    }

    public void setNameCusCO(String nameCusCO) {
        this.nameCusCO = nameCusCO;
    }

    public String getSdtCusCO() {
        return sdtCusCO;
    }

    public void setSdtCusCO(String sdtCusCO) {
        this.sdtCusCO = sdtCusCO;
    }

    public String getDiachiCO() {
        return diachiCO;
    }

    public void setDiachiCO(String diachiCO) {
        this.diachiCO = diachiCO;
    }
}
