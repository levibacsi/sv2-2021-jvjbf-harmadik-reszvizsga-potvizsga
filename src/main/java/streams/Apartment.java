package streams;

import java.util.List;

public class Apartment {
    private String location;
    private int size;
    private BathRoomType bathroom;
    private List<String> extras;

    public Apartment(String location, int size, BathRoomType bathroom, List<String> extras) {
        this.location = location;
        this.size = size;
        this.bathroom = bathroom;
        this.extras = extras;
    }

    public String getLocation() {
        return location;
    }

    public int getSize() {
        return size;
    }

    public BathRoomType getBathRoomType() {
        return bathroom;
    }

    public List<String> getExtras() {
        return extras;
    }
}
