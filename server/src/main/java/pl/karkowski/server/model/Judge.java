package pl.karkowski.server.model;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Michal on 11.05.2021
 * Class which represents Judge who checks if game was won
 */
public class Judge {
    /**
     * Contains all possible combinations of indexes in {@link Board#board} which wins.
     */
    private final static List<List<Integer>> WINNING_COMBINATIONS;

    static {
        final List<Integer> topRow = List.of(0, 1, 2);
        final List<Integer> middleRow = List.of(3, 4, 5);
        final List<Integer> bottomRow = List.of(6, 7, 8);
        final List<Integer> leftCol = List.of(0, 3, 6);
        final List<Integer> middleCol = List.of(1, 4, 7);
        final List<Integer> rightCol = List.of(2, 5, 8);
        final List<Integer> leftAxis = List.of(0, 4, 8);
        final List<Integer> rightAxis = List.of(2, 4, 6);
        WINNING_COMBINATIONS = Stream.of(topRow, middleRow, bottomRow, leftCol, middleCol, rightCol, leftAxis, rightAxis)
                .collect(Collectors.toUnmodifiableList());
    }

    // Static helper class. Do not instantiate.
    private Judge() {
    }

    /**
     * Check if given positions guarantees win.
     *
     * @param positions player positions which was taken by player
     * @return if positions guarantees win.
     */
    public static boolean checkIfWin(final Collection<Integer> positions) {
        return WINNING_COMBINATIONS.stream().anyMatch(positions::containsAll);
    }
}
