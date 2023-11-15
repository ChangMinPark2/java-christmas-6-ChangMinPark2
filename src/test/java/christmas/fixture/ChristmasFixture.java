package christmas.fixture;

import christmas.domain.Order;

import java.util.HashMap;
import java.util.Map;

public final class ChristmasFixture {

    public static Order order(String visitDay, String menuAndCount) {
        return new Order(visitDay, menuAndCount);
    }

    public static <T, R> Map<T, R> initMap(T key, R value) {
        Map<T, R> map = new HashMap<>();
        map.put(key, value);

        return map;
    }
}
