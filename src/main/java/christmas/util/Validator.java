package christmas.util;

import christmas.domain.Menu;
import christmas.domain.MenuCategory;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static christmas.util.GlobalConstant.*;

public final class Validator {
    private static final String ERROR = "[ERROR]";
    private static final String TRY_AGAIN_MASSAGE = " 다시 입력해 주세요.";
    private static final String VALIDATE_ORDER = ERROR + " 유효하지 않은 주문입니다." + TRY_AGAIN_MASSAGE;
    private static final String VALIDATE_VISIT_DAY = ERROR + " 유효하지 않은 날짜입니다." + TRY_AGAIN_MASSAGE;

    private Validator() {
    }

    public static int validateIsNumber(String visitDay) {
        if (!visitDay.matches("\\d+")) {
            throw new IllegalArgumentException(VALIDATE_VISIT_DAY);
        }

        return Integer.parseInt(visitDay);
    }

    public static int validateDayInRange(int visitDay) {
        if (visitDay < MIN_DAY || visitDay > MAX_DAY) {
            throw new IllegalArgumentException(VALIDATE_VISIT_DAY);
        }

        return visitDay;
    }

    public static void validateNotEmptyMenu(String input) {
        if (input.contains(" ")) {
            throw new IllegalArgumentException(VALIDATE_ORDER);
        }
    }

    public static void validateHasMenu(String input) {
        Arrays.stream(input.split(COMMA))
                .map(condition -> condition.split(MINUS)[MENU_NAME])
                .forEach(menuName -> {
                    if (!Menu.contains(menuName)) {
                        throw new IllegalArgumentException(VALIDATE_ORDER);
                    }
                });
    }

    public static void validateCount(String input) {
        Arrays.stream(input.split(COMMA))
                .map(condition -> condition.split(MINUS)[MENU_COUNT])
                .forEach(count -> {
                    if (!count.matches("\\d+")) {
                        throw new IllegalArgumentException(VALIDATE_ORDER);
                    }
                });
    }

    public static void validateNotExceedMax(String input) {
        int total = Arrays.stream(input.split(COMMA))
                .map(condition -> condition.split(MINUS)[MENU_COUNT])
                .mapToInt(Integer::parseInt)
                .sum();

        if (total > ORDER_MAX_COUNT) {
            throw new IllegalArgumentException(VALIDATE_ORDER);
        }
    }

    public static void validateNoDuplicateMenu(String input) {
        List<String> menuNames = Arrays.stream(input.split(COMMA))
                .map(condition -> condition.split(MINUS)[MENU_NAME])
                .toList();

        Set<String> uniqueMenuNames = new HashSet<>(menuNames);

        if (menuNames.size() != uniqueMenuNames.size()) {
            throw new IllegalArgumentException(VALIDATE_ORDER);
        }
    }

    public static void validateNotOnlyDrinks(String input) {
        if (isAllDrink(input)) {
            throw new IllegalArgumentException(VALIDATE_ORDER);
        }
    }

    private static boolean isAllDrink(String input) {
        return Arrays.stream(input.split(COMMA))
                .map(condition -> condition.split(MINUS)[MENU_NAME])
                .allMatch(menuName -> Menu.fromName(menuName).getCategory().equals(MenuCategory.DRINK));
    }

}
