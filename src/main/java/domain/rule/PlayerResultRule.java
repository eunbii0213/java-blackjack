package domain.rule;

import java.util.function.BiFunction;

import domain.result.ResultType;
import domain.user.User;

public enum PlayerResultRule implements Rule {

    PLAYER_BUST((player, dealer) -> player.isBust(), ResultType.LOSE),
    DEALER_BUST((player, dealer) -> dealer.isBust(), ResultType.WIN),
    PLAYER_GREATER((player, dealer) -> player.calculatePoint() > dealer.calculatePoint(), ResultType.WIN),
    DEALER_GREATER((player, dealer) -> player.calculatePoint() < dealer.calculatePoint(), ResultType.LOSE),
    PLAYER_ONLY_BLACK_JACK((player, dealer) -> player.isBlackJack() && !dealer.isBlackJack(), ResultType.WIN),
    DEALER_ONLY_BLACK_JACK((player, dealer) -> !player.isBlackJack() && dealer.isBlackJack(), ResultType.LOSE),
    EQUALS((player, dealer) -> player.calculatePoint() == dealer.calculatePoint(), ResultType.DRAW);

    private final BiFunction<User, User, Boolean> condition;
    private final ResultType resultType;

    PlayerResultRule(BiFunction<User, User, Boolean> condition, ResultType resultType) {
        this.condition = condition;
        this.resultType = resultType;
    }

    @Override
    public Boolean condition(User first, User second) {
        return condition.apply(first, second);
    }

    @Override
    public ResultType getResultType() {
        return resultType;
    }
}