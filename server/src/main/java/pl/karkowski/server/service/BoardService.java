package pl.karkowski.server.service;

import io.swagger.model.BoardDTO;
import io.swagger.model.GameDTO;
import io.swagger.model.MoveDTO;
import io.swagger.model.PlayerDTO;
import org.springframework.stereotype.Service;
import pl.karkowski.server.converter.BoardConverter;
import pl.karkowski.server.model.Board;
import pl.karkowski.server.model.Judge;
import pl.karkowski.server.model.Player;
import pl.karkowski.server.model.PlayerMark;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;

/**
 * @author Michal on 12.05.2021
 * Service handling interaction with board controller.
 */
@Service
public class BoardService {
    private final Collection<Board> boards;

    public BoardService() {
        this.boards = new HashSet<>();
    }

    public BoardDTO createBoard() {
        final Board board = new Board();
        boards.add(board);
        return BoardConverter.convertBoardToBoardDTO(board);
    }

    public BoardDTO getBoard(final String boardId) {
        final Board board = findBoardById(boardId);
        return BoardConverter.convertBoardToBoardDTO(board);
    }

    public BoardDTO makeMoveOnBoard(final MoveDTO move, final String boardId) {
        final Board board = this.findBoardById(boardId);
        GameDTO.StatusEnum gameStatus = getGameStatus(boardId).getStatus();
        if(GameDTO.StatusEnum.RUNNING != gameStatus) {
            throw new IllegalStateException("Game is already won by " + gameStatus);
        }
        board.makeMove(move.getPosition(), move.getPlayer().getPlayerSign().toString());
        return BoardConverter.convertBoardToBoardDTO(board);
    }

    public PlayerDTO joinToBoard(final String boardId) {
        final Board boardById = findBoardById(boardId);
        final PlayerMark playerMark = boardById.assignSign();
        final PlayerDTO playerDTO = new PlayerDTO();
        playerDTO.setPlayerSign(PlayerDTO.PlayerSignEnum.valueOf(playerMark.toString()));
        return playerDTO;
    }

    private Board findBoardById(final String boardId) {
        return boards.stream().filter(b -> b.getBoardId().equals(boardId)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No such board present in game"));
    }

    public GameDTO getGameStatus(final String boardId) {
        final GameDTO gameResult = new GameDTO();
        final Board board = findBoardById(boardId);
        final Optional<Player> wonPlayer = getWonPlayer(board);
        if (wonPlayer.isPresent()) {
            gameResult.setStatus(GameDTO.StatusEnum.valueOf(wonPlayer.get().getPlayerMark().name()));
        } else if (board.areAllFieldsFilled()) {
            gameResult.setStatus(GameDTO.StatusEnum.DRAW);
        } else {
            gameResult.setStatus(GameDTO.StatusEnum.RUNNING);
        }
        return gameResult;
    }

    public Optional<Player> getWonPlayer(final Board board) {
        return board.getPlayers()
                .stream()
                .filter(p -> Judge.checkIfWin(p.getPositions()))
                .findFirst();
    }


}
