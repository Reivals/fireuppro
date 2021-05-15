package pl.karkowski.server.controller;

import io.swagger.api.BoardsApi;
import io.swagger.model.BoardDTO;
import io.swagger.model.GameDTO;
import io.swagger.model.MoveDTO;
import io.swagger.model.PlayerDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import pl.karkowski.server.service.BoardService;

import javax.validation.Valid;

/**
 * @author Michal on 12.05.2021
 * Board controller generated via openApi 3.0.
 */
@Controller
public class BoardController implements BoardsApi {

    private final BoardService boardService;

    public BoardController(final BoardService boardService) {
        this.boardService = boardService;
    }

    @Override
    public ResponseEntity<GameDTO> checkIfGameEnded(final String boardId) {
        return ResponseEntity.ok(this.boardService.getGameStatus(boardId));
    }

    @Override
    public ResponseEntity<BoardDTO> createBoard() {
        return ResponseEntity.ok(this.boardService.createBoard());
    }

    @Override
    public ResponseEntity<BoardDTO> getBoard(final String boardId) {
        return ResponseEntity.ok(this.boardService.getBoard(boardId));
    }

    @Override
    public ResponseEntity<PlayerDTO> joinToBoard(final String boardId) {
        return ResponseEntity.ok(this.boardService.joinToBoard(boardId));
    }

    @Override
    public ResponseEntity<BoardDTO> makeMove(@Valid MoveDTO move, String boardId) {
        return ResponseEntity.ok(this.boardService.makeMoveOnBoard(move, boardId));
    }
}
