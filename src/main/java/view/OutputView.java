package view;

import domain.*;

import java.util.Map;

public class OutputView {

    private static final String DELIMITER = ", ";
    private static final String NAME_FORMAT = "카드: ";
    private static final String INIT = "";
    private static final String WIN = "승";
    private static final String LOSE = "패";
    private static final String DEALER_RESULT_FORMAT = "딜러: %d승 %d패";
    private static final String RESULT_GUIDE_MESSAGE = "## 최종 승패";
    private static final String SCORE_GUIDE_MESSAGE = " - 결과: ";

    private OutputView() {
    }

    public static void printPlayersCards(Players players) {
        for (Player player : players.getPlayers()) {
            printPlayerName(player);
            printPlayerCards(player);
            System.out.println();
        }
    }

    public static void printPlayerName(Gambler gambler) {
        String name = gambler.getName();
        System.out.print(name + NAME_FORMAT);
    }

    public static void printPlayerCards(Gambler gambler) {
        String output = getPlayerCards(gambler);
        System.out.print(String.join(DELIMITER, output));
    }

    public static String getPlayerCards(Gambler gambler) {
        String output = INIT;
        for (Card card : gambler.getCards()) {
            output += card.getSuit() + card.getName();
        }
        return output;
    }

    public static void printScore(Gambler gambler) {
        printPlayerName(gambler);
        printPlayerCards(gambler);
        System.out.print(SCORE_GUIDE_MESSAGE);
        System.out.println(gambler.getScore());
    }

    public static void printResult(Map<Gambler, Integer> result) {
        System.out.println(RESULT_GUIDE_MESSAGE);


        for (Map.Entry<Gambler, Integer> resultEntry : result.entrySet()) {
            printDealerResult(resultEntry, result.size());
            printPlayersResult(resultEntry);
        }
    }

    private static void printPlayersResult(Map.Entry<Gambler, Integer> resultEntry) {
        if (resultEntry.getKey().getClass().isInstance(Player.class)) {
            int winCount = resultEntry.getValue();
            System.out.print(resultEntry.getKey().getName() + ": ");
            System.out.println(resolveOutcome(winCount));
        }
    }

    private static String resolveOutcome(int winCount) {
        if (winCount == 1) {
            return WIN;
        }
        return LOSE;
    }

    private static void printDealerResult(Map.Entry<Gambler, Integer> gamblerEntry, int size) {
        if (gamblerEntry.getKey().getClass().isInstance(Dealer.class)) {
            int winCount = gamblerEntry.getValue();
            int loseCount = size - winCount - 1;
            System.out.printf(DEALER_RESULT_FORMAT, winCount, loseCount);
        }
    }
}