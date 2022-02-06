package contentsite;

import java.util.HashSet;
import java.util.Set;

public class ContentService {
    private Set<User> users = new HashSet<>();
    private Set<Content> contents = new HashSet<>();

    public Set<User> getAllUsers() {
        return Set.copyOf(users);
    }

    public Set<Content> getAllContent() {
        return Set.copyOf(contents);
    }

    public void registerUser(String name, String password){
       for (User u: users){
           if (u.getUserName().equals(name)){
               throw new IllegalArgumentException("Username is already taken: " + name);
           }
       }
        users.add(new User(name, password));
   }


    public void addContent(Content content){
        for (Content c: contents){
            if (c.getTitle().equals(content.getTitle())){
                throw new IllegalArgumentException("Content name is already taken: " + content.getTitle());
            }
        }
        contents.add(content);
    }

    public void logIn(String userName, String password) {
        findUser(userName, password).setLogIn(true);
    }

    private User findUser(String userName, String password) {
        User user = users.stream().filter(u -> u.getUserName().equals(userName)).findFirst().orElseThrow(() -> new IllegalArgumentException("Username is wrong!"));
        if ((userName + password).hashCode() == user.getPassword()) {
            return user;
        }
        throw new IllegalArgumentException("Password is Invalid!");
    }

    public void clickOnContent(User user, Content content) {
        if (user.isLogIn()) {
            if (content.isPremiumContent() && user.isPremiumMember()) {
                content.click(user);
            } else if (!content.isPremiumContent()) {
                content.click(user);
            } else {
                throw new IllegalStateException("Upgrade for Premium to watch this content!");
            }
        } else {
            throw new IllegalStateException("Log in to watch this content!");
        }
    }
}
