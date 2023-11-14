package christmas.domain;

import java.util.HashMap;

import static christmas.domain.DecemberEventBadge.*;

public class OrderDetailsGenerator {
    public OrderDetailsGenerator() {
    }

    public OrderDetails generateOrderDetails(Order order) {
        HashMap<Menu, Integer> orderMenus = getOrderedMenu(order);
        int beforeDistCountAmount = calculateTotalBeforeDiscount(orderMenus);
        boolean giftMenu = isGiftMenu(calculateTotalBeforeDiscount(orderMenus));
        HashMap<String, Integer> benefitList = calculateBenefits(order, calculateTotalBeforeDiscount(orderMenus));
        int totalBenefitAmount = calculateTotalBenefitAmount(benefitList);
        int afterAbleAmount = calculateFinalPayment(beforeDistCountAmount, totalBenefitAmount, giftMenu);
        DecemberEventBadge event = determineEventBadge(totalBenefitAmount);

        return new OrderDetails(orderMenus, beforeDistCountAmount, giftMenu, benefitList, totalBenefitAmount,
                afterAbleAmount, event);
    }

    private HashMap<Menu, Integer> getOrderedMenu(Order order) {
        HashMap<Menu, Integer> orderMenu = new HashMap<>();

        for (Menu menu : order.getOrderMenu().keySet()) {
            int menuCount = order.getOrderMenu().get(menu);
            orderMenu.put(menu, menuCount);
        }

        return orderMenu;
    }

    private int calculateTotalBeforeDiscount(HashMap<Menu, Integer> orderMenus) {
        int beforeDistCountAmount = 0;

        for (Menu menu : orderMenus.keySet()) {
            int menuCount = orderMenus.get(menu);
            beforeDistCountAmount += menu.getPrice() * menuCount;
        }

        return beforeDistCountAmount;
    }

    private boolean isGiftMenu(int beforeDiscountAmount) {
        return beforeDiscountAmount >= DiscountType.GIFT_EVENT_CONDITION;
    }

    private HashMap<String, Integer> calculateBenefits(Order order, int beforeDistCountAmount) {
        DiscountCalculator discountCalCulator = new DiscountCalculator();
        return discountCalCulator.calculateTotalDiscount(order.getVisitDay(), beforeDistCountAmount, order);
    }

    private int calculateTotalBenefitAmount(HashMap<String, Integer> benefitList) {
        int totalBenefitAmount = 0;

        for (String key : benefitList.keySet()) {
            totalBenefitAmount += benefitList.get(key);
        }

        return totalBenefitAmount;
    }

    private int calculateFinalPayment(int beforeDistCountAmount, int totalBenefitAmount, boolean giftMenu) {
        if (giftMenu) {
            return beforeDistCountAmount - totalBenefitAmount + Menu.CHAMPAGNE.getPrice();
        }

        return beforeDistCountAmount - totalBenefitAmount;
    }

    private DecemberEventBadge determineEventBadge(int totalBenefitAmount) {
        if (isSanta(totalBenefitAmount)) {
            return DecemberEventBadge.SANTA;
        }
        if (isTree(totalBenefitAmount)) {
            return DecemberEventBadge.TREE;
        }
        if (isStar(totalBenefitAmount)) {
            return DecemberEventBadge.STAR;
        }

        return DecemberEventBadge.NO_BADGE;
    }

    private boolean isStar(int totalBenefitAmount) {
        return totalBenefitAmount >= STAR_MIN_AMOUNT;
    }

    private boolean isTree(int totalBenefitAmount) {
        return totalBenefitAmount >= TREE_MIN_AMOUNT;
    }

    private boolean isSanta(int totalBenefitAmount) {
        return totalBenefitAmount >= SANTA_MIN_AMOUNT;
    }
}
