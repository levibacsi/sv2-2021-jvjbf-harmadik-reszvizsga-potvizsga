package contentsite;

import java.util.ArrayList;
import java.util.List;

public class Podcast implements Content{
    private String title;
    private List<String> performers = new ArrayList<>();
    protected List<User> clicked = new ArrayList<>();

    public Podcast(String title, List<String> performers) {
        this.title = title;
        this.performers = performers;
    }

    @Override
    public String getTitle() {
        return title;
    }

    public List<String> getSpeakers() {
        return performers;
    }

    @Override
    public boolean isPremiumContent() {
        return false;
    }

    @Override
    public List<User> clickedBy() {
        return new ArrayList<>(clicked);
    }

    @Override
    public void click(User user) {
        clicked.add(user);
    }
}
