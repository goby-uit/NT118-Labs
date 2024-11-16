package ex.g1.cau5;

public enum Thumbnail {
    THUMBNAIL1(R.drawable.thumbnail1, "Thumbnail 1"),
    THUMBNAIL2(R.drawable.thumbnail2, "Thumbnail 2"),
    THUMBNAIL3(R.drawable.thumbnail3, "Thumbnail 3"),
    THUMBNAIL4(R.drawable.thumbnail4, "Thumbnail 4");

    private final int imageResId;
    private final String name;

    Thumbnail(int imageResId, String name) {
        this.imageResId = imageResId;
        this.name = name;
    }

    public int getImageResId() {
        return imageResId;
    }

    public String getName() {
        return name;
    }
}

