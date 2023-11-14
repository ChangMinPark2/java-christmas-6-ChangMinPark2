package christmas.domain;

public enum DecemberEventBadge {
    STAR("별"),
    SANTA("산타"),
    TREE("트리"),
    NO_BADGE("없음");

    public static final int STAR_MIN_AMOUNT = 5000;
    public static final int TREE_MIN_AMOUNT = 10000;
    public static final int SANTA_MIN_AMOUNT = 20000;

    private final String badgeName;

    DecemberEventBadge(String badgeName) {
        this.badgeName = badgeName;
    }

    public String getBadgeName() {
        return badgeName;
    }
}
