package christmas.domain;

import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Collectors;

import static christmas.util.GlobalConstant.*;
import static christmas.util.Validator.*;

public class Order {
    private final int visitDay;
    private final HashMap<Menu, Integer> orderMenu;

    public Order(String visitDay, String inputMenuAndCount) {
        int day = validateIsNumber(visitDay);
        validateNotEmptyMenu(inputMenuAndCount);
        validateHasMenu(inputMenuAndCount);
        validateCount(inputMenuAndCount);
        validateNotExceedMax(inputMenuAndCount);
        validateNoDuplicateMenu(inputMenuAndCount);
        validateNotOnlyDrinks(inputMenuAndCount);
        this.visitDay = validateDayInRange(day);
        this.orderMenu = createMenu(inputMenuAndCount);
    }

    public int getVisitDay() {
        return visitDay;
    }

    public HashMap<Menu, Integer> getOrderMenu() {
        return orderMenu;
    }

    private HashMap<Menu, Integer> createMenu(String inputMenuAndCount) {
        return Arrays.stream(inputMenuAndCount.split(COMMA))
                .map(menuAndCount -> menuAndCount.split(MINUS))
                .collect(Collectors.toMap(
                        menuAndCountArr -> Menu.fromName(menuAndCountArr[MENU_NAME]),
                        menuAndCountArr -> Integer.parseInt(menuAndCountArr[MENU_COUNT]),
                        (oldValue, newValue) -> oldValue,
                        HashMap::new
                ));
    }
}