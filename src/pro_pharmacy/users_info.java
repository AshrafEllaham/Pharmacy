
package pro_pharmacy;


public class users_info {
    private String First_name;
    private String Last_name;
    private String Gender;
    private String phone;
    private String state;
    private String username;
    private String password;

    public users_info(String First_name, String Last_name, String Gender, String phone, String state, String username, String password) {
        this.First_name = First_name;
        this.Last_name = Last_name;
        this.Gender = Gender;
        this.phone = phone;
        this.state = state;
        this.username = username;
        this.password = password;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirst_name() {
        return First_name;
    }

    public void setFirst_name(String First_name) {
        this.First_name = First_name;
    }

    public String getLast_name() {
        return Last_name;
    }

    public void setLast_name(String Last_name) {
        this.Last_name = Last_name;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String Gender) {
        this.Gender = Gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    
    
}
