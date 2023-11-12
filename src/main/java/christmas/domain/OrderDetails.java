package christmas.domain;

import java.util.HashMap;

public class OrderDetails {
    private HashMap<Menu, Integer> orderedMenus;
    private int totalOrderAmountBeforeDiscount; //할인 전 총 주문 금액
    private boolean isGiftMenuIncluded;
    private HashMap<String, Integer> benefitsReceived;
    private int totalBenefitValue;
    private int estimatedPaymentAfterDiscount;
    private DecemberEventBadge decemberEventBadge;

    public OrderDetails(HashMap<Menu, Integer> orderedMenus,
                        int totalOrderAmountBeforeDiscount,
                        boolean isGiftMenuIncluded,
                        HashMap<String, Integer> benefitsReceived,
                        int totalBenefitValue,
                        int estimatedPaymentAfterDiscount,
                        DecemberEventBadge decemberEventBadge) {

        this.orderedMenus = orderedMenus;
        this.totalOrderAmountBeforeDiscount = totalOrderAmountBeforeDiscount;
        this.isGiftMenuIncluded = isGiftMenuIncluded;
        this.benefitsReceived = benefitsReceived;
        this.totalBenefitValue = totalBenefitValue;
        this.estimatedPaymentAfterDiscount = estimatedPaymentAfterDiscount;
        this.decemberEventBadge = decemberEventBadge;
    }

    public HashMap<Menu, Integer> getOrderedMenus() {
        return orderedMenus;
    }

    public int getTotalOrderAmountBeforeDiscount() {
        return totalOrderAmountBeforeDiscount;
    }

    public boolean isGiftMenuIncluded() {
        return isGiftMenuIncluded;
    }

    public HashMap<String, Integer> getBenefitsReceived() {
        return benefitsReceived;
    }

    public int getTotalBenefitValue() {
        return totalBenefitValue;
    }

    public int getEstimatedPaymentAfterDiscount() {
        return estimatedPaymentAfterDiscount;
    }

    public DecemberEventBadge getDecemberEventBadge() {
        return decemberEventBadge;
    }
}
