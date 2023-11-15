package christmas.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static christmas.domain.DiscountType.*;
import static christmas.fixture.ChristmasFixture.initMap;
import static org.assertj.core.api.Assertions.assertThat;

public class OrderDetailsGeneratorTest {
    private OrderDetails orderDetails;

    @BeforeEach
    void setUp() {
        Order order = new Order("3", "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
        OrderDetailsGenerator generator = new OrderDetailsGenerator();
        orderDetails = generator.generateOrderDetails(order);
    }

    @DisplayName("주문 상세 정보 생성 테스트")
    @Test
    public void getOrderedMenuTest() {
        // Given
        Map<Menu, Integer> expected = initMap(Menu.fromName("티본스테이크"), 1);
        expected.put(Menu.fromName("바비큐립"), 1);
        expected.put(Menu.fromName("초코케이크"), 2);
        expected.put(Menu.fromName("제로콜라"), 1);
        // When
        Map<Menu, Integer> actual = orderDetails.getOrderedMenus();
        // Then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("할인 전 총 주문 금액 테스트")
    @Test
    public void calculateTotalBeforeDiscountTest() {
        // When
        int actual = orderDetails.getTotalOrderAmountBeforeDiscount();
        // Then
        assertThat(actual).isEqualTo(142000);
    }

    @DisplayName("증정 메뉴 테스트")
    @Test
    void isGiftMenuTest() {
        // When
        boolean actual = orderDetails.isGiftMenuIncluded();
        // Then
        assertThat(actual).isTrue();
    }

    @DisplayName("할인 전 총 주문 금액 테스트")
    @Test
    void calculateBenefitsTest() {
        // Given
        Map<String, Integer> expected = initMap(CHRISTMAS.getDiscountName(), 1200);
        expected.put(WEEKDAY.getDiscountName(), 4046);
        expected.put(SPECIAL.getDiscountName(), 1000);
        expected.put(PRESENTATION.getDiscountName(), 25000);
        // When
        Map<String, Integer> actual = orderDetails.getBenefitsReceived();
        //That
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("총 혜택 금액 테스트")
    @Test
    void calculateTotalBenefitAmountTest() {
        // When
        int actual = orderDetails.getTotalBenefitValue();
        // That
        assertThat(actual).isEqualTo(31246);
    }

    @DisplayName("할인 후 예상 결제 금액 테스트")
    @Test
    void calculateFinalPaymentTest() {
        // When
        int actual = orderDetails.getEstimatedPaymentAfterDiscount();
        // That
        assertThat(actual).isEqualTo(135754);
    }

    @DisplayName("12월 이벤트 배지 테스트")
    @Test
    void determineEventBadgeTest() {
        // When
        DecemberEventBadge actual = orderDetails.getDecemberEventBadge();
        // That
        assertThat(actual).isEqualTo(DecemberEventBadge.SANTA);
    }
}
