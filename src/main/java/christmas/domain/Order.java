package christmas.domain;

import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Collectors;

public class Order {
    private final int visitDay;
    private final HashMap<Menu, Integer> orderMenu;

    public Order(int visitDay, String inputMenuAndCount) {
        validateNotRange(visitDay);
        this.visitDay = visitDay;
        this.orderMenu = createMenu(inputMenuAndCount);
    }

    private HashMap<Menu, Integer> createMenu(String inputMenuAndCount) {
        return Arrays.stream(inputMenuAndCount.split(","))
                .map(menuAndCount -> menuAndCount.split("-"))
                .collect(Collectors.toMap(
                        menuAndCountArr -> Menu.fromName(menuAndCountArr[0]),
                        menuAndCountArr -> Integer.parseInt(menuAndCountArr[1]),
                        (oldValue, newValue) -> oldValue,
                        HashMap::new
                ));
    }

    private void validateNotRange(int visitDay) {
        if (visitDay < 1 || visitDay > 31) {
            throw new IllegalArgumentException();
        }
    }
}
