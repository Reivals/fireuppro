package pl.karkowski.server.model;

/**
 * @author Michal on 11.05.2021
 * Either X or O sign which will be used to mark fields in Tic Tac Toe board.
 */
public enum PlayerMark {
    X,
    O;

    public String getSign() {
        return this.name().toUpperCase();
    }
}
