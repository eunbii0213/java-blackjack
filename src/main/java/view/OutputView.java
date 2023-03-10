package view;

import domain.Card;
import domain.Dealer;
import domain.Gambler;
import domain.Player;
import domain.Players;
import domain.Result;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OutputView {

    private static final String DELIMITER = ", ";
    private static final String NAME_FORMAT = "카드: ";
    private static final String WIN = "승";
    private static final String LOSE = "패";
    private static final String DEALER_RESULT_FORMAT = "딜러: %d승 %d패";
    private static final String RESULT_GUIDE_MESSAGE = "\n## 최종 승패";
    private static final String SCORE_GUIDE_MESSAGE = " - 결과: ";
    private static final String POSTFIX_INITIAL_PICK_GUIDE_MESSAGE = "에게 2장을 나누었습니다.";
    private static final String PREFIX_INITIAL_PICK_GUIDE_MESSAGE = "\n딜러와 ";
    private static final String COLON = ": ";
    private static final String NEW_LINE = "\n";
    private static final String DEALER_HIT = "\n딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final int PLAYER_WIN = 1;

    private OutputView() {
    }

    public static void printInitialPickGuideMessage(Players players) {
        System.out.print(PREFIX_INITIAL_PICK_GUIDE_MESSAGE);
        List<String> playerNames = getPlayerNames(players);
        System.out.println(String.join(DELIMITER, playerNames) + POSTFIX_INITIAL_PICK_GUIDE_MESSAGE);
    }

    private static List<String> getPlayerNames(Players players) {
        List<String> playerNames = new ArrayList<>();
        for (Player player : players.getPlayers()) {
            playerNames.add(player.getName());
        }
        return playerNames;
    }

    public static void printGamblersCards(Players players, Dealer dealer) {
        printDealerCards(dealer);
        printPlayersCards(players);
    }

    private static void printDealerCards(Dealer dealer) {
        printGamblerName(dealer);
        printGamblerCards(dealer);
        printNewLine();
    }

    private static void printGamblerName(Gambler gambler) {
        String name = gambler.getName();
        System.out.print(name + NAME_FORMAT);
    }

    private static void printGamblerCards(Gambler gambler) {
        List<String> output = getPlayerCards(gambler);
        System.out.print(String.join(DELIMITER, output));
    }

    public static List<String> getPlayerCards(Gambler gambler) {
        List<String> output = new ArrayList<>();
        for (Card card : gambler.getCards()) {
            output.add(card.getName() + card.getSuit());
        }
        return output;
    }

    private static void printPlayersCards(Players players) {
        for (Player player : players.getPlayers()) {
            printSingleGambler(player);
        }
    }

    public static void printSingleGambler(Gambler gambler) {
        printGamblerName(gambler);
        printGamblerCards(gambler);
        printNewLine();
    }

    public static void printScore(Gambler gambler) {
        printNewLine();
        printGamblerName(gambler);
        printGamblerCards(gambler);
        System.out.print(SCORE_GUIDE_MESSAGE + gambler.getScore());
    }

    public static void printResult(Result result) {
        System.out.println(NEW_LINE + RESULT_GUIDE_MESSAGE);
        Map<Gambler, Integer> map = result.getResult();

        for (Map.Entry<Gambler, Integer> resultEntry : map.entrySet()) {
            printDealerResult(resultEntry, map.size());
            printPlayersResult(resultEntry);
        }
    }

    public static void printDealerResult(Map.Entry<Gambler, Integer> gamblerEntry, int size) {
        if (isDealer(gamblerEntry)) {
            int winCount = gamblerEntry.getValue();
            int loseCount = size - winCount - 1;
            System.out.printf(DEALER_RESULT_FORMAT + NEW_LINE, winCount, loseCount);
        }
    }

    private static boolean isDealer(Map.Entry<Gambler, Integer> gamblerEntry) {
        return gamblerEntry.getKey().getName().equals("딜러");
    }

    public static void printPlayersResult(Map.Entry<Gambler, Integer> gamblerEntry) {
        if (!isDealer(gamblerEntry)) {
            int winCount = gamblerEntry.getValue();
            System.out.print(gamblerEntry.getKey().getName() + COLON);
            System.out.println(resolveOutcome(winCount));
        }
    }

    public static String resolveOutcome(int winCount) {
        if (winCount == PLAYER_WIN) {
            return WIN;
        }
        return LOSE;
    }

    public static void printNewLine() {
        System.out.println();
    }

    public static void printScores(Players players, Dealer dealer) {
        printScore(dealer);
        for (Player player : players.getPlayers()) {
            printScore(player);
        }
    }

    public static void printDealerHitMessage() {
        System.out.println(DEALER_HIT);
    }

}
