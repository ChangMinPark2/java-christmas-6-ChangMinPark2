package christmas.domain;

import java.util.Arrays;
import java.util.List;

public enum DiscountType {
    CHRISTMAS(1000, "크리스마스 디데이 할인"),
    WEEKDAY(2023, "평일 할인"),
    WEEKEND(2023, "주말 할인"),
    SPECIAL(1000, "특별 할인"),
    PRESENTATION(25000, "증정 이벤트");

    public static final List<Integer> STAR_CONDITION = Arrays.asList(3, 10, 17, 24, 25, 31);
    public static final List<Integer> WEEKEND_CONDITION = Arrays.asList(1, 2, 8, 9, 15, 16, 22, 23, 29, 30);
    public static final List<Integer> WEEKDAY_CONDITION =
            Arrays.asList(3, 4, 5, 6, 7, 10, 11, 12, 13, 14, 17, 18, 19, 20, 21, 24, 25, 26, 27, 28, 31);
    public static final int CHRISTMAS_CONDITION = 25;
    public static final int ALL_DISCOUNT_CONDITION = 10000;
    public static final int GIFT_EVENT_CONDITION = 120000;

    private final int discountPrice;
    private final String discountName;

    DiscountType(int discountPrice, String discountName) {
        this.discountPrice = discountPrice;
        this.discountName = discountName;
    }

    public int getDiscountPrice() {
        return discountPrice;
    }

    public String getDiscountName() {
        return discountName;
    }
}
