package pl.karkowski.server.converter;

import io.swagger.model.BoardDTO;
import pl.karkowski.server.model.Board;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Michal on 13.05.2021
 */
public class BoardConverter {

    public static BoardDTO convertBoardToBoardDTO(final Board board) {
        final BoardDTO boardDTO = new BoardDTO();
        boardDTO.setId(board.getBoardId());
        final List<String> collect = Stream.of(board.getBoard())
                .collect(Collectors.toUnmodifiableList());
        boardDTO.setBoard(collect);
        return boardDTO;
    }
}
