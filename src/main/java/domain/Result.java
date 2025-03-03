package domain;

import java.util.LinkedHashMap;
import java.util.Map;

public class Result {

    private final Map<Gambler, Integer> result;
    private final Map<Gambler, Integer> benefits;
    private final Referee referee;
    private final Players players;
    private final Dealer dealer;

    public Result(Players players, Dealer dealer, Bettings bettings) {
        this.players = players;
        this.dealer = dealer;
        referee = new Referee(dealer, bettings);
        result = createResult();
        benefits = createBenefits();
    }

    private Map<Gambler, Integer> createBenefits() {
        Map<Gambler, Integer> benefits = new LinkedHashMap<>();

        benefits.put(dealer, 0);
        for (Player player : players.getPlayers()) {
            referee.decideBenefits(player, players.getPlayers(), benefits);
        }

        return benefits;
    }

    private Map<Gambler, Integer> createResult() {
        Map<Gambler, Integer> result = new LinkedHashMap<>();
        result.put(dealer, 0);
        for (Player player : players.getPlayers()) {
            referee.decideWinner(player, result);
        }

        return result;
    }

    public Map<Gambler, Integer> getResult() {
        return result;
    }

    public Map<Gambler, Integer> getBenefits() {
        return benefits;
    }
}
