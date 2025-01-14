package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PlayerNames {

    private static final int MAX_PLAYERS_SIZE = 5;
    private static final String SIZE_ERROR_GUIDE_MESSAGE = "[ERROR] 플레이어는 5명까지 참가 가능합니다.";
    private static final String DUPLICATE_ERROR_GUIDE_MESSAGE = "[ERROR] 플레이어 이름은 중복일 수 없습니다.";

    private List<PlayerName> playerNames;

    public PlayerNames(List<String> playerNamesInput) {
        validatePlayerNames(playerNamesInput);
        this.playerNames = createPlayerNames(playerNamesInput);
    }

    private void validatePlayerNames(List<String> playerNamesInput) {
        validateSameName(playerNamesInput);
        validateSize(playerNamesInput);
    }

    private void validateSameName(List<String> playerNamesInput) {
        int removeDuplicateSize = playerNamesInput.stream().distinct().collect(Collectors.toList()).size();
        if (playerNamesInput.size() != removeDuplicateSize) {
            throw new IllegalArgumentException(DUPLICATE_ERROR_GUIDE_MESSAGE);
        }
    }

    private void validateSize(List<String> playerNamesInput) {
        if (playerNamesInput.size() > MAX_PLAYERS_SIZE) {
            throw new IllegalArgumentException(SIZE_ERROR_GUIDE_MESSAGE);
        }
    }

    private List<PlayerName> createPlayerNames(List<String> playerNamesInput) {
        List<PlayerName> playerNames = new ArrayList<>();
        for (String playerName : playerNamesInput) {
            playerNames.add(new PlayerName(playerName));
        }

        return playerNames;
    }

    public List<String> getStringPlayerNames() {
        List<String> playerNames = new ArrayList<>();
        for (PlayerName playerName : this.playerNames) {
            playerNames.add(playerName.getName());
        }

        return playerNames;
    }
}
