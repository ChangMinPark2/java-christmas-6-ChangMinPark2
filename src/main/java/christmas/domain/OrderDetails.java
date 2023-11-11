package christmas.domain;

import java.util.HashMap;

public class OrderDetails {
    private HashMap<Menu, Integer> orderedMenus;
    private int totalOrderAmountBeforeDiscount; //할인 전 총 주문 금액
    private boolean isGiftMenuIncluded;
    private HashMap<String, Integer> benefitsReceived;
    private int totalBenefitValue;
    private int estimatedPaymentAfterDiscount;
    private String decemberEventBadge;

    public OrderDetails(HashMap<Menu, Integer> orderedMenus,
                        int totalOrderAmountBeforeDiscount,
                        boolean isGiftMenuIncluded,
                        HashMap<String, Integer> benefitsReceived,
                        int totalBenefitValue,
                        int estimatedPaymentAfterDiscount,
                        String decemberEventBadge) {

        this.orderedMenus = orderedMenus;
        this.totalOrderAmountBeforeDiscount = totalOrderAmountBeforeDiscount;
        this.isGiftMenuIncluded = isGiftMenuIncluded;
        this.benefitsReceived = benefitsReceived;
        this.totalBenefitValue = totalBenefitValue;
        this.estimatedPaymentAfterDiscount = estimatedPaymentAfterDiscount;
        this.decemberEventBadge = decemberEventBadge;
    }

}
