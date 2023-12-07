package huflit.edu.haisanapp.USER;

public class Customer {
    int id;
    String nameCus;
    String usernameCus;
    String passCus;
    String addressCus;
    String emailCus;
    String phoneCus;

    public Customer(String nameCus, String usernameCus, String passCus, String addressCus, String emailCus, String phoneCus) {
        this.nameCus = nameCus;
        this.usernameCus = usernameCus;
        this.passCus = passCus;
        this.addressCus = addressCus;
        this.emailCus = emailCus;
        this.phoneCus = phoneCus;
    }


    public void setId(int id) {
        this.id = id;
    }

    public String getNameCus() {
        return nameCus;
    }

    public void setNameCus(String nameCus) {
        this.nameCus = nameCus;
    }

    public String getUsernameCus() {
        return usernameCus;
    }

    public void setUsernameCus(String usernameCus) {
        this.usernameCus = usernameCus;
    }

    public String getPassCus() {
        return passCus;
    }

    public void setPassCus(String passCus) {
        this.passCus = passCus;
    }

    public String getAddressCus() {
        return addressCus;
    }

    public void setAddressCus(String addressCus) {
        this.addressCus = addressCus;
    }

    public String getEmailCus() {
        return emailCus;
    }

    public void setEmailCus(String emailCus) {
        this.emailCus = emailCus;
    }

    public String getPhoneCus() {
        return phoneCus;
    }

    public void setPhoneCus(String phoneCus) {
        this.phoneCus = phoneCus;
    }

    @Override
    public String toString() {
        if(id > 0)
        {
            return (nameCus + " - " + emailCus);

        }
        return nameCus;
    }
}
