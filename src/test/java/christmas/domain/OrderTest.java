package christmas.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static christmas.fixture.ChristmasFixture.initMap;
import static christmas.fixture.ChristmasFixture.order;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class OrderTest {

    @DisplayName("Order 생성자 테스트")
    @Test
    void Order_Test() {
        // Given
        Order order = order("1", "초코케이크-1");
        // When
        Map<Menu, Integer> expected = initMap(Menu.CHOCO_CAKE, 1);
        // Then
        assertThat(order.getOrderMenu()).isEqualTo(expected);
    }

    @DisplayName("방문 날짜에 문자에 대한 예외 처리 - IllegalArgumentException")
    @Test
    void validateIsNumberTest_IllegalArgumentException() {
        // When & Then
        assertThatThrownBy(() -> order("Not Number", "초코케이크-1"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("방문 날짜가 1 ~ 31일 사이의 숫자가 아닌 경우에 대한 예외 처리 - IllegalArgumentException")
    @Test
    void validateDayInRangeTest_IllegalArgumentException() {
        // When & Then
        assertThatThrownBy(() -> order("0", "초코케이크-1"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("메뉴와 개수의 공백에 대한 예외 처리 - IllegalArgumentException")
    @Test
    void validateNotEmptyMenu_IllegalArgumentException() {
        // When & Then
        assertThatThrownBy(() -> order("1", ""))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("메뉴에 없는 메뉴를 입력한 경우에 대한 예외 처리 - IllegalArgumentException")
    @Test
    void validateHasMenuTest_IllegalArgumentException() {
        // When & Then
        assertThatThrownBy(() -> order("1", "사과-1"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("메뉴의 개수를 문자로 입력한 경우에 대한 예외 처리 - IllegalArgumentException")
    @Test
    void validateCountTest_IllegalArgumentException() {
        // When & Then
        assertThatThrownBy(() -> order("1", "초코케이크-NotNumber"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("총 주문의 개수가 20이 넘어가는 경우에 대한 예외 처리 - IllegalArgumentException")
    @Test
    void validateNotExceedMaxTest_IllegalArgumentException() {
        // When & Then
        assertThatThrownBy(() -> order("1", "초코케이크-20,바비큐립-1"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("동일한 메뉴를 입력한 경우에 대한 예외 처리 - IllegalArgumentException")
    @Test
    void validateNoDuplicateMenuTest_IllegalArgumentException() {
        // When & Then
        assertThatThrownBy(() -> order("1", "바비큐립-1,바비큐립-1"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("음료만 주문한 경우에 대한 예외 처리 - IllegalArgumentException")
    @Test
    void validateNotOnlyDrinksTest_IllegalArgumentException() {
        // When & Then
        assertThatThrownBy(() -> order("1", "제로콜라-1,레드와인-1"))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
