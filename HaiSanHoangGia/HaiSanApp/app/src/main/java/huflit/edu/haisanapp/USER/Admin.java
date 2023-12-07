package huflit.edu.haisanapp.USER;

public class Admin {
    int id;
    String nameAd;
    String usernameAd;
    String passAd;
    String addressAd;
    String emailAd;
    String phoneAd;

    //role
    int roleid;
    String rolename;



    public int getRoleid() {
        return roleid;
    }

    public void setRoleid(int roleid) {
        this.roleid = roleid;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public Admin(String nameAd, String usernameAd, String passAd, String addressAd, String emailAd, String phoneAd) {
        this.nameAd = nameAd;
        this.usernameAd = usernameAd;
        this.passAd = passAd;
        this.addressAd = addressAd;
        this.emailAd = emailAd;
        this.phoneAd = phoneAd;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameAd() {
        return nameAd;
    }

    public void setNameAd(String nameAd) {
        this.nameAd = nameAd;
    }

    public String getUsernameAd() {
        return usernameAd;
    }

    public void setUsernameAd(String usernameAd) {
        this.usernameAd = usernameAd;
    }

    public String getPassAd() {
        return passAd;
    }

    public void setPassAd(String passAd) {
        this.passAd = passAd;
    }

    public String getAddressAd() {
        return addressAd;
    }

    public void setAddressAd(String addressAd) {
        this.addressAd = addressAd;
    }

    public String getEmailAd() {
        return emailAd;
    }

    public void setEmailAd(String emailAd) {
        this.emailAd = emailAd;
    }

    public String getPhoneAd() {
        return phoneAd;
    }

    public void setPhoneAd(String phoneAd) {
        this.phoneAd = phoneAd;
    }


}
