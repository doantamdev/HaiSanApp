package huflit.edu.haisanapp.product;

public class Product {
    int id;
    String name;
    String imagepro;
    String soluong;

    String mota;
    int cateid;

    boolean isAddtoCart;

    public boolean isAddtoCart() {
        return isAddtoCart;
    }

    public void setAddtoCart(boolean addtoCart) {
        isAddtoCart = addtoCart;
    }

    public int getCateid() {
        return cateid;
    }

    public void setCateid(int cateid) {
        this.cateid = cateid;
    }

    String catename;

    String gia;

    public String getGia() {
        return gia;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }

    public String getCatename() {
        return catename;
    }

    public void setCatename(String catename) {
        this.catename = catename;
    }


    public Product(int id, String name, String imagepro, String soluong, String gia, String mota) {
        this.id = id;
        this.name = name;
        this.imagepro = imagepro;
        this.soluong = soluong;
        this.mota = mota;
        this.gia = gia;
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

    public String getImagepro() {
        return imagepro;
    }

    public void setImagepro(String imagepro) {
        this.imagepro = imagepro;
    }

    public String getSoluong() {
        return soluong;
    }

    public void setSoluong(String soluong) {
        this.soluong = soluong;
    }



}
