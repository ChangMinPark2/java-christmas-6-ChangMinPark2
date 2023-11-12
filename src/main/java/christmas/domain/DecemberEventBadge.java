package christmas.domain;

public enum DecemberEventBadge {
    STAR("별"),
    SANTA("산타"),
    TREE("트리"),
    NO_BADGE("없음");

    private String badgeName;

    DecemberEventBadge(String badgeName) {
        this.badgeName = badgeName;
    }
}
