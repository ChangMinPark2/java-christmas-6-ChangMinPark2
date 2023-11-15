package christmas.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MenuTest {

    @DisplayName("메뉴 이름을 입력하면 enum 값으로 받는지 확인")
    @Test
    void fromNameTest() {
        // Given
        String menuName = "초코케이크";
        // When
        Menu expected = Menu.fromName(menuName);
        // Then
        assertThat(Menu.CHOCO_CAKE).isEqualTo(expected);
    }
}
