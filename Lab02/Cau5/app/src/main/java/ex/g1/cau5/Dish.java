package ex.g1.cau5;

public class Dish {
    private final String name;
    private final Thumbnail thumbnail;
    private final boolean isPromotion;

    public Dish(String name, Thumbnail thumbnail, boolean isPromotion) {
        this.name = name;
        this.thumbnail = thumbnail;
        this.isPromotion = isPromotion;
    }

    public String getName() {
        return name;
    }

    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    public boolean isPromotion() {
        return isPromotion;
    }
}

