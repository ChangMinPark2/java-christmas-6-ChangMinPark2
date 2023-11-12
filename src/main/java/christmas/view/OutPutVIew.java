package christmas.view;

import christmas.domain.Menu;
import christmas.domain.OrderDetails;

import java.text.NumberFormat;

public class OutPutVIew {
    public final String GAME_GUID = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";

    public void printEventPreview(String visitDay) {
        System.out.println("12월 " + visitDay + "일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n");
    }

    public void printOrderedMenuList(OrderDetails orderDetails) {
        System.out.println("<주문 메뉴>");

        for (Menu menu : orderDetails.getOrderedMenus().keySet()) {
            System.out.println(menu.getName() + " " + orderDetails.getOrderedMenus().get(menu) + "개");
        }
        System.out.println();
    }

    public void printTotalOrderAmountBeforeDiscount(OrderDetails orderDetails) {
        NumberFormat formatter = NumberFormat.getInstance();

        System.out.println("<할인 전 총주문 금액>");
        System.out.println(formatter.format(orderDetails.getTotalOrderAmountBeforeDiscount()) + "원");
        System.out.println();
    }

    public void printGiftMenu(OrderDetails orderDetails) {
        System.out.println("<증정 메뉴>");

        if (!orderDetails.isGiftMenuIncluded()) {
            System.out.println("없음");
            System.out.println();
            return;
        }
        System.out.println("샴페인 1개");
        System.out.println();
    }

    public void printBenefitList(OrderDetails orderDetails) {
        System.out.println("<혜택 내역>");

        NumberFormat formatter = NumberFormat.getInstance();
        if (!orderDetails.getBenefitsReceived().isEmpty()) {
            for (String name : orderDetails.getBenefitsReceived().keySet()) {
                System.out.println(name + ": -" + formatter.format(orderDetails.getBenefitsReceived().get(name)) + "원");
            }
            System.out.println();
            return;
        }
        System.out.println("없음");
    }

    public void printTotalBenefitAmount(OrderDetails orderDetails) {
        System.out.println("<총혜택 금액>");

        NumberFormat formatter = NumberFormat.getInstance();
        if (orderDetails.getTotalBenefitValue() != 0) {
            System.out.println("-" + formatter.format(orderDetails.getTotalBenefitValue()) + "원");
            System.out.println();
            return;
        }
        System.out.println("0원");
        System.out.println();
    }

    public void printEstimatedPaymentAfterDiscount(OrderDetails orderDetails) {
        System.out.println("<할인 후 예상 결제 금액>");

        NumberFormat formatter = NumberFormat.getInstance();
        System.out.println(formatter.format(orderDetails.getEstimatedPaymentAfterDiscount()) + "원");
        System.out.println();
    }

    public void printDecemberEventBadge(OrderDetails orderDetails) {
        System.out.println("<12월 이벤트 배지>");
        System.out.println(orderDetails.getDecemberEventBadge().getBadgeName());
    }
}
