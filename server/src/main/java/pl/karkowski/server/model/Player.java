package pl.karkowski.server.model;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

/**
 * @author Michal on 11.05.2021
 * Class represents a player which can participate in game.
 */
public class Player {
    private final PlayerMark playerMark;
    private final Collection<Integer> positions;

    public Player(final PlayerMark playerMark) {
        this.positions = new HashSet<>(5);
        this.playerMark = playerMark;
    }

    public Collection<Integer> getPositions() {
        return Collections.unmodifiableCollection(positions);
    }

    public void addPosition(final Integer position) {
        this.positions.add(position);
    }

    public PlayerMark getPlayerMark() {
        return playerMark;
    }
}
