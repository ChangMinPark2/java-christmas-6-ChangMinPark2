package christmas.domain;

import java.util.HashMap;

import static christmas.domain.DiscountType.*;

public class DiscountCalculator {
    public HashMap<String, Integer> calculateTotalDiscount(int visitDay, int beforeDistCountAmount, Order order) {
        HashMap<String, Integer> discountList = new HashMap<>();

        if (isDiscount(beforeDistCountAmount)) {
            applyChristmasDiscount(discountList, visitDay);
            applyStarDiscount(discountList, visitDay);
            applyWeekendDiscount(discountList, visitDay, order);
            applyWeekdayDiscount(discountList, visitDay, order);
            applyPresentationDiscount(discountList, beforeDistCountAmount);
        }

        return discountList;
    }

    private boolean isDiscount(int beforePrice) {
        return beforePrice >= ALL_DISCOUNT_CONDITION;
    }

    private void applyChristmasDiscount(HashMap<String, Integer> discountList, int visitDay) {
        if (visitDay <= CHRISTMAS_CONDITION) {
            discountList.put(CHRISTMAS.getDiscountName(), christmasDiscountCalculator(visitDay));
        }
    }

    private void applyStarDiscount(HashMap<String, Integer> discountList, int visitDay) {
        if (STAR_CONDITION.contains(visitDay)) {
            discountList.put(SPECIAL.getDiscountName(), SPECIAL.getDiscountPrice());
        }
    }

    private void applyWeekendDiscount(HashMap<String, Integer> discountList, int visitDay, Order order) {
        if (WEEKEND_CONDITION.contains(visitDay)) {
            calculateWeekendDiscount(discountList, order);
        }
    }

    private void applyWeekdayDiscount(HashMap<String, Integer> discountList, int visitDay, Order order) {
        if (WEEKDAY_CONDITION.contains(visitDay)) {
            calculateWeekdayDiscount(discountList, order);
        }
    }

    private void applyPresentationDiscount(HashMap<String, Integer> discountList, int beforeDistCountAmount) {
        if (beforeDistCountAmount >= GIFT_EVENT_CONDITION) {
            discountList.put(PRESENTATION.getDiscountName(), PRESENTATION.getDiscountPrice());
        }
    }

    private void calculateWeekendDiscount(HashMap<String, Integer> discountList, Order order) {
        int count = 0;

        for (Menu menu : order.getOrderMenu().keySet()) {
            if (menu.getCategory() == MenuCategory.MAIN) {
                count += order.getOrderMenu().get(menu) * WEEKEND.getDiscountPrice();
            }
        }

        if (count != 0) {
            discountList.put(WEEKEND.getDiscountName(), count);
        }
    }


    private void calculateWeekdayDiscount(HashMap<String, Integer> discountList, Order order) {
        int count = 0;

        for (Menu menu : order.getOrderMenu().keySet()) {
            if (menu.getCategory() == MenuCategory.DESSERT) {
                count += order.getOrderMenu().get(menu) * WEEKDAY.getDiscountPrice();
            }
        }

        if (count != 0) {
            discountList.put(WEEKDAY.getDiscountName(), count);
        }
    }

    private int christmasDiscountCalculator(int visitDay) {
        return CHRISTMAS.getDiscountPrice() + (100 * (visitDay - 1));
    }
}
