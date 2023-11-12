package christmas.domain;

import java.util.HashMap;

import static christmas.domain.DiscountType.*;

public class DiscountCalCulator {

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

    private boolean isDiscount(int beforeprice) {
        if (beforeprice >= ALL_DISCOUNT_CONDITION) {
            return true;
        }
        return false;
    }

    private HashMap<String, Integer> applyChristmasDiscount(HashMap<String, Integer> discountList, int visitDay) {
        if (visitDay <= CHRISTMAS_CONDITION) {
            discountList.put(CHRISTMAS.getDiscountName(), christmasDiscountCalculator(visitDay));
        }
        return discountList;
    }

    private HashMap<String, Integer> applyStarDiscount(HashMap<String, Integer> discountList, int visitDay) {
        if (STAR_CONDITION.contains(visitDay)) {
            discountList.put(SPECIAL.getDiscountName(), SPECIAL.getDiscountPrice());
        }
        return discountList;
    }

    private HashMap<String, Integer> applyWeekendDiscount(HashMap<String, Integer> discountList, int visitDay, Order order) {
        if (WEEKEND_CONDITION.contains(visitDay)) {
            calculateWeekendDiscount(discountList, order);
        }
        return discountList;
    }

    private HashMap<String, Integer> applyWeekdayDiscount(HashMap<String, Integer> discountList, int visitDay, Order order) {
        if (WEEKDAY_CONDITION.contains(visitDay)) {
            calculateWeekdayDiscount(discountList, order);
        }
        return discountList;
    }

    private HashMap<String, Integer> applyPresentationDiscount(HashMap<String, Integer> discountList, int beforeDistCountAmount) {
        if (beforeDistCountAmount >= GIFT_EVENT_CONDITION) {
            discountList.put(PRESENTATION.getDiscountName(), PRESENTATION.getDiscountPrice());
        }
        return discountList;
    }

    private void calculateWeekendDiscount(HashMap<String, Integer> discountList, Order order) {
        int count = 0;
        for (Menu menu : order.getOrderMenu().keySet()) {
            if (menu.getCategory() == MenuCategory.MAIN) {
                count += order.getOrderMenu().get(menu) * WEEKEND.getDiscountPrice();
            }
        }
        if (count != 0) {
            discountList.put(WEEKEND.getDiscountName(), count * WEEKEND.getDiscountPrice());
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
            discountList.put(WEEKEND.getDiscountName(), count);
        }
    }

    private int christmasDiscountCalculator(int visitDay) {
        return CHRISTMAS.getDiscountPrice() + (100 * (visitDay - 1));
    }
}
