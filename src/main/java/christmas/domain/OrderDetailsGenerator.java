package christmas.domain;

import christmas.view.InputView;

import java.util.HashMap;

public class OrderDetailsGenerator {

    public OrderDetails generateOrderDetails(Order order) {
        order = new Order(InputView.getExpectedVisitDate(), InputView.getOrderDetails());

        HashMap<Menu, Integer> orderMenus = orderMenu(order);
        int beforeDistCountAmount = beforDiscountAmount(orderMenus);
        boolean giftMenu = isPresent(beforDiscountAmount(orderMenus));
        HashMap<String, Integer> benefitList = benefitList(order, beforDiscountAmount(orderMenus));
        int totalBenefitAmount = totalBenefitAmount(benefitList);
        int afterAbleAmount = beforeDistCountAmount - totalBenefitAmount;
        String event = totalBenefitEvent(totalBenefitAmount);
        OrderDetails orderDetails = new OrderDetails(orderMenus, beforeDistCountAmount, giftMenu, benefitList, totalBenefitAmount, afterAbleAmount, event);

        return orderDetails;
    }

    private HashMap<Menu, Integer> orderMenu(Order order) {
        HashMap<Menu, Integer> orderMenu = new HashMap<>();

        for (Menu menu : order.getOrderMenu().keySet()) {
            int menuCount = order.getOrderMenu().get(menu);
            orderMenu.put(menu, menuCount);
        }

        return orderMenu;
    }

    private int beforDiscountAmount(HashMap<Menu, Integer> orderMenus) {
        int beforeDistCountAmount = 0;

        for (Menu menu : orderMenus.keySet()) {
            int menuCount = orderMenus.get(menu);
            beforeDistCountAmount += menu.getPrice() * menuCount;
        }

        return beforeDistCountAmount;
    }

    private boolean isPresent(int beforeDiscountAmount) {
        if (beforeDiscountAmount >= DiscountCalCulator.BENEFITCONDITION) {
            return true;
        }

        return false;
    }

    private HashMap<String, Integer> benefitList(Order order, int beforeDistCountAmount) {
        DiscountCalCulator discountCalCulator = new DiscountCalCulator();
        HashMap<String, Integer> benefitList = discountCalCulator.totalDiscount(order.getVisitDay(), beforeDistCountAmount, order);

        return benefitList;
    }

    private int totalBenefitAmount(HashMap<String, Integer> benefitList) {
        int totalBenefitAmount = 0;

        for (String key : benefitList.keySet()) {
            totalBenefitAmount += benefitList.get(key);
        }

        return totalBenefitAmount;
    }

    private String totalBenefitEvent(int totalBenefitAmount) {

        if (totalBenefitAmount >= 10000) {
            return "트리";
        }
        if (totalBenefitAmount >= 20000) {
            return "산타";
        }
        return "없음";
    }
}
