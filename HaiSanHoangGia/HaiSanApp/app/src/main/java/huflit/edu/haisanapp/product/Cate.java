package huflit.edu.haisanapp.product;

public class Cate {
        int id;
        String nameCate;
        String imageCate;

        public Cate(int id, String nameCate, String imageCate) {
                this.id = id;
                this.nameCate = nameCate;
                this.imageCate = imageCate;
        }

        public int getId() {
                return id;
        }

        public void setId(int id) {
                this.id = id;
        }

        public String getNameCate() {
                return nameCate;
        }

        public void setNameCate(String nameCate) {
                this.nameCate = nameCate;
        }

        public String getImageCate() {
                return imageCate;
        }

        public void setImageCate(String imageCate) {
                this.imageCate = imageCate;
        }

        public String toString() {
                return nameCate + " + " + imageCate;
        }

}
