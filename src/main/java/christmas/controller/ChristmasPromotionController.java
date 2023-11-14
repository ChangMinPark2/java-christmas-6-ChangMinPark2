package christmas.controller;

import christmas.domain.Order;
import christmas.domain.OrderDetails;
import christmas.domain.OrderDetailsGenerator;
import christmas.view.InputView;
import christmas.view.OutputView;

import static christmas.util.Validator.*;

public class ChristmasPromotionController {
    public void start() {
        System.out.println(OutputView.GAME_GUID);

        String visitDay = inputVisitDay();
        String orderInput = inputMenuAndCount();

        Order orders = new Order(visitDay, orderInput);
        OrderDetailsGenerator orderDetailsGenerators = new OrderDetailsGenerator();
        OrderDetails orderDetails = orderDetailsGenerators.generateOrderDetails(orders);
        orderList(visitDay, orderDetails);
    }

    private void orderList(String visitDay, OrderDetails orderDetails) {
        OutputView.printEventPreview(visitDay);
        OutputView.printOrderedMenuList(orderDetails);
        OutputView.printTotalOrderAmountBeforeDiscount(orderDetails);
        OutputView.printGiftMenu(orderDetails);
        OutputView.printBenefitList(orderDetails);
        OutputView.printTotalBenefitAmount(orderDetails);
        OutputView.printEstimatedPaymentAfterDiscount(orderDetails);
        OutputView.printDecemberEventBadge(orderDetails);
    }

    private String inputVisitDay() {
        try {
            String visitDay = InputView.getExpectedVisitDate();
            validateDayInRange(validateIsNumber(visitDay));
            return visitDay;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return inputVisitDay();
        }
    }

    private String inputMenuAndCount() {
        try {
            String orderInput = InputView.getOrderDetails();
            validateNotEmptyMenu(orderInput);
            validateHasMenu(orderInput);
            validateNoDuplicateMenu(orderInput);
            validateCount(orderInput);
            validateNotExceedMax(orderInput);
            validateNotOnlyDrinks(orderInput);
            return orderInput;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return inputMenuAndCount();
        }
    }
}
