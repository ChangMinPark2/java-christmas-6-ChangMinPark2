package christmas.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static christmas.fixture.ChristmasFixture.initMap;
import static christmas.fixture.ChristmasFixture.order;
import static org.assertj.core.api.Assertions.assertThat;

public class DiscountCalculatorTest {

    @DisplayName("크리스마스 할인 혜택이 들어오는지 확인")
    @Test
    void christmasDiscountTest() {
        // Given
        Order order = order("1", "초코케이크-1");
        DiscountCalculator discountCalculator = new DiscountCalculator();
        Map<String, Integer> expected = initMap("크리스마스 디데이 할인", 1100);
        // When
        Map<String, Integer> actual = discountCalculator
                .calculateTotalDiscount(2, 10000, order);
        // Then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("특별 할인 혜택이 들어오는지 확인")
    @Test
    void starDiscountTest() {
        // Given
        Order order = order("31", "해산물파스타-1");
        DiscountCalculator discountCalculator = new DiscountCalculator();
        Map<String, Integer> expected = initMap("특별 할인", 1000);
        // When
        Map<String, Integer> actual = discountCalculator
                .calculateTotalDiscount(31, 10000, order);
        // Then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("주말 할인 혜택이 들어오는지 확인")
    @Test
    void weekendDiscountTest() {
        // Given
        Order order = order("29", "해산물파스타-1");
        DiscountCalculator discountCalculator = new DiscountCalculator();
        Map<String, Integer> expected = initMap("주말 할인", 2023);
        // When
        Map<String, Integer> actual = discountCalculator
                .calculateTotalDiscount(29, 10000, order);
        // Then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("평일 할인 혜택이 들어오는지 확인")
    @Test
    void weekdayDiscountTest() {
        // Given
        Order order = order("28", "초코케이크-1");
        DiscountCalculator discountCalculator = new DiscountCalculator();
        Map<String, Integer> expected = initMap("평일 할인", 2023);
        // When
        Map<String, Integer> actual = discountCalculator
                .calculateTotalDiscount(28, 10000, order);
        // Then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("증정 이벤트 혜택이 들어오는지 확인")
    @Test
    void presentationDiscountTest() {
        // Given
        Order order = order("27", "티본스테이크-5");
        DiscountCalculator discountCalculator = new DiscountCalculator();
        Map<String, Integer> expected = initMap("증정 이벤트", 25000);
        // When
        Map<String, Integer> actual = discountCalculator
                .calculateTotalDiscount(27, 120000, order);
        // Then
        assertThat(actual).isEqualTo(expected);
    }
}
