package domain;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class PlayerNameTest {

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"", " "})
    void 올바른_playerName이_아니라면_예외처리(String nameInput) {
        assertThatThrownBy(() -> new PlayerName(nameInput))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
