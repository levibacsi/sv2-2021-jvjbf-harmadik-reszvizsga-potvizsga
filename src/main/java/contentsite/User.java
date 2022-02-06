package contentsite;

public class User {
    private String userName;
    private int password;
    private boolean premium;
    private boolean loggedIn;

    public User(String userName, String password) {
        this.userName = userName;
        this.password = (userName + password).hashCode();
    }

    public String getUserName() {
        return userName;
    }

    public int getPassword() {
        return password;
    }

    public boolean isPremiumMember() {
        return premium;
    }

    public boolean isLogIn() {
        return loggedIn;
    }

    public void setLogIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public void upgradeForPremium(){
        premium = true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return userName != null ? userName.equals(user.userName) : user.userName == null;
    }

    @Override
    public int hashCode() {
        return userName != null ? userName.hashCode() : 0;
    }
}
