package pl.karkowski.server.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.UUID;

/**
 * @author Michal on 11.05.2021
 * Class which represents the Board in Tic Tac Toe.
 */
public class Board {
    /**
     * Fields and their mappings. It maps user choice position to the position in {@link Board#board}
     */
    private static final Map<String, Integer> MARKABLE_FIELDS;
    private static final String PLAYER_1_NAME = "Player1";
    private static final String PLAYER_2_NAME = "Player2";
    private static final String DOT_SIGN = ".";
    private static final int INITIAL_PLAYERS_AT_TABLE = 0;
    private static final int MAX_POSSIBLE_ACTIONS = 9;

    static {
        MARKABLE_FIELDS = Map.of("A1", 0,
                "A2", 1,
                "A3", 2,
                "B1", 3,
                "B2", 4,
                "B3", 5,
                "C1", 6,
                "C2", 7,
                "C3", 8);
    }

    private final String[] board;
    private final String boardId;
    private final Collection<Player> players;
    /**
     * Below values needs to mutable because they contains state of the board.
     */
    private PlayerMark previousPlayer;
    private int playersJoined;
    private int availableMovesLeft;

    public Board() {
        this.board = new String[]{DOT_SIGN, DOT_SIGN, DOT_SIGN, DOT_SIGN, DOT_SIGN, DOT_SIGN, DOT_SIGN, DOT_SIGN, DOT_SIGN};
        this.availableMovesLeft = MAX_POSSIBLE_ACTIONS;
        playersJoined = INITIAL_PLAYERS_AT_TABLE;
        this.boardId = generateId();
        players = new HashSet<>(PlayerMark.values().length);
        players.add(new Player(PlayerMark.O));
        players.add(new Player(PlayerMark.X));
    }

    public void updateGameState(final String position, final Player player) {
        final Integer index = MARKABLE_FIELDS.get(position);
        player.addPosition(index);
        PlayerMark playerMark = player.getPlayerMark();
        this.board[index] = playerMark.getSign();
        availableMovesLeft--;
        previousPlayer = playerMark;
    }

    public boolean areAllFieldsFilled() {
        return availableMovesLeft <= 0;
    }

    public boolean isPositionFree(final String position) {
        final Integer index = MARKABLE_FIELDS.get(position);
        return DOT_SIGN.equals(board[index]);
    }

    protected boolean isMoveValid(final String position) {
        return MARKABLE_FIELDS.containsKey(position);
    }

    public Collection<Player> getPlayers() {
        return players;
    }

    public PlayerMark assignSign() {
        if (playersJoined == INITIAL_PLAYERS_AT_TABLE) {
            playersJoined++;
            return PlayerMark.O;
        } else {
            return PlayerMark.X;
        }
    }

    public String getBoardId() {
        return boardId;
    }

    public String[] getBoard() {
        return board;
    }

    public void makeMove(final String position, final String playerSign) {
        final Player player = findPLayerByPlayerId(playerSign);
        if (isMoveValid(position) && isPositionFree(position) && isPlayerMove(player)) {
            this.updateGameState(position, player);
        } else {
            throw new IllegalArgumentException("Move not valid");
        }
    }

    private Player findPLayerByPlayerId(final String playerSign) {
        return players.stream().filter(p -> p.getPlayerMark().getSign().equals(playerSign)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No such player in game"));
    }

    private boolean isPlayerMove(final Player player) {
        return player.getPlayerMark() != previousPlayer;
    }

    private String generateId() {
        return UUID.randomUUID().toString();
    }


}
