package christmas.domain;

import java.util.HashMap;

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
        OrderDetails orderDetails = new OrderDetails(orderMenus, beforeDistCountAmount, giftMenu, benefitList, totalBenefitAmount, afterAbleAmount, event);

        return orderDetails;
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
        if (beforeDiscountAmount >= DiscountType.GIFT_EVENT_CONDITION) {
            return true;
        }

        return false;
    }

    private HashMap<String, Integer> calculateBenefits(Order order, int beforeDistCountAmount) {
        DiscountCalCulator discountCalCulator = new DiscountCalCulator();
        HashMap<String, Integer> benefitList = discountCalCulator.calculateTotalDiscount(order.getVisitDay(), beforeDistCountAmount, order);

        return benefitList;
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
        if (totalBenefitAmount >= 5000 && totalBenefitAmount < 10000) {
            return DecemberEventBadge.STAR;
        }
        if (totalBenefitAmount >= 10000 && totalBenefitAmount < 20000) {
            return DecemberEventBadge.TREE;
        }
        if (totalBenefitAmount >= 20000) {
            return DecemberEventBadge.SANTA;
        }

        return DecemberEventBadge.NO_BADGE;
    }
}
