package christmas.domain;

import java.util.HashMap;

public class OrderDetails {
    private final HashMap<Menu, Integer> orderedMenus;
    private final int totalOrderAmountBeforeDiscount;
    private final boolean isGiftMenuIncluded;
    private final HashMap<String, Integer> benefitsReceived;
    private final int totalBenefitValue;
    private final int estimatedPaymentAfterDiscount;
    private final DecemberEventBadge decemberEventBadge;

    public OrderDetails(
            HashMap<Menu, Integer> orderedMenus,
            int totalOrderAmountBeforeDiscount,
            boolean isGiftMenuIncluded,
            HashMap<String, Integer> benefitsReceived,
            int totalBenefitValue,
            int estimatedPaymentAfterDiscount,
            DecemberEventBadge decemberEventBadge
    ) {
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
