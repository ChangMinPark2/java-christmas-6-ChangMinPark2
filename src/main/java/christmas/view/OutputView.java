package christmas.view;

import christmas.domain.Menu;
import christmas.domain.OrderDetails;

import java.text.NumberFormat;

import static christmas.util.GlobalConstant.MINUS;

public final class OutputView {
    public static final String GAME_GUID = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";

    private OutputView() {
    }

    public static void printEventPreview(String visitDay) {
        System.out.println("12월 " + visitDay + "일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n");
    }

    public static void printOrderedMenuList(OrderDetails orderDetails) {
        System.out.println("<주문 메뉴>");

        for (Menu menu : orderDetails.getOrderedMenus().keySet()) {
            System.out.println(menu.getName() + " " + orderDetails.getOrderedMenus().get(menu) + "개");
        }

        System.out.println();
    }

    public static void printTotalOrderAmountBeforeDiscount(OrderDetails orderDetails) {
        NumberFormat formatter = NumberFormat.getInstance();

        System.out.println("<할인 전 총주문 금액>");
        System.out.printf("%s원\n\n", formatter.format(orderDetails.getTotalOrderAmountBeforeDiscount()));
    }

    public static void printGiftMenu(OrderDetails orderDetails) {
        System.out.println("<증정 메뉴>");

        if (!orderDetails.isGiftMenuIncluded()) {
            System.out.println("없음\n");
            return;
        }

        System.out.println("샴페인 1개\n");
    }

    public static void printBenefitList(OrderDetails orderDetails) {
        System.out.println("<혜택 내역>");

        if (!orderDetails.getBenefitsReceived().isEmpty()) {
            printBenefits(orderDetails);
            return;
        }

        System.out.println("없음\n");
    }

    public static void printTotalBenefitAmount(OrderDetails orderDetails) {
        System.out.println("<총혜택 금액>");
        NumberFormat formatter = NumberFormat.getInstance();

        if (orderDetails.getTotalBenefitValue() != 0) {
            System.out.printf("%s%s원\n\n", MINUS, formatter.format(orderDetails.getTotalBenefitValue()));
            return;
        }

        System.out.println("0원\n");
    }

    public static void printEstimatedPaymentAfterDiscount(OrderDetails orderDetails) {
        System.out.println("<할인 후 예상 결제 금액>");
        NumberFormat formatter = NumberFormat.getInstance();
        System.out.printf("%s원\n\n", formatter.format(orderDetails.getEstimatedPaymentAfterDiscount()));
    }

    public static void printDecemberEventBadge(OrderDetails orderDetails) {
        System.out.println("<12월 이벤트 배지>");
        System.out.print(orderDetails.getDecemberEventBadge().getBadgeName());
    }

    private static void printBenefits(OrderDetails orderDetails) {
        NumberFormat formatter = NumberFormat.getInstance();
        for (String name : orderDetails.getBenefitsReceived().keySet()) {
            System.out.printf(
                    "%s: %s%s원\n",
                    name,
                    MINUS,
                    formatter.format(orderDetails.getBenefitsReceived().get(name))
            );
        }

        System.out.println();
    }
}
