package com.karkowski.client;

import com.karkowski.client.http.RequestMakerHelper;
import com.karkowski.client.http.TicTacToeHttpClient;
import com.karkowski.client.io.IOController;
import com.karkowski.client.model.BoardDTO;
import com.karkowski.client.model.GameDTO;
import com.karkowski.client.model.MoveDTO;
import com.karkowski.client.model.PlayerDTO;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.List;

/**
 * @author Michal on 12.05.2021
 */

public class Client {
    private static final String REFRESH_SIGN = "R";
    private PlayerDTO player;
    private BoardDTO board;

    public void play() {
        try {
            this.initGame();
            this.playGame();
        } catch (final Exception exc) {
            System.out.println(exc.getMessage());
        }
    }

    private void initGame() throws IOException, InterruptedException, IllegalStateException {
        System.out.println("Connect to game (1) or create new one (2)?:");
        final String choose = IOController.getLine();
        if (choose.equals("2")) {
            try {
                board = TicTacToeHttpClient.makeRequestGetBody(RequestMakerHelper.createBoardRequest(), BoardDTO.class);
                System.out.println("BoardId: " + board.getId());
            } catch (final Exception exception) {
                throw new IllegalStateException("Error during game: " + exception.getMessage());
            }
        } else {
            System.out.println("BoardId:");
            final String boardId = IOController.getKeyboardInput();
            try {
                board = TicTacToeHttpClient.makeRequestGetBody(RequestMakerHelper.createGetBoardRequest(boardId), BoardDTO.class);
            } catch (final Exception exception) {
                throw new IllegalStateException("Error during game:" + exception.getMessage());
            }
        }
        player = TicTacToeHttpClient.makeRequestGetBody(RequestMakerHelper.createJoinTableRequest(board.getId()), PlayerDTO.class);
        IOController.drawBoard(constructPrintableBoard());
    }

    private void playGame() throws IOException, InterruptedException {
        GameDTO.StatusEnum gameStatus;
        do {
            String position = REFRESH_SIGN;
            while(REFRESH_SIGN.equals(position)) {
                System.out.println("Position (to refresh board press R):");
                position = IOController.getKeyboardInput();
                board = TicTacToeHttpClient.makeRequestGetBody(RequestMakerHelper.createGetBoardRequest(board.getId()), BoardDTO.class);
                IOController.drawBoard(constructPrintableBoard());
            }
            HttpResponse<BoardDTO> nextMoveRespone = null;
            try {
                nextMoveRespone = TicTacToeHttpClient.makeRequest(RequestMakerHelper.createMakeMoveRequest(board.getId(),
                        createMoveDTO(position)), BoardDTO.class);
            } catch (final Exception exc) {
                System.out.println(exc.getMessage());
            }

            if (nextMoveRespone != null) {
                board = nextMoveRespone.body();
                IOController.drawBoard(constructPrintableBoard());
            }
            gameStatus = TicTacToeHttpClient.makeRequestGetBody(
                    RequestMakerHelper.createCheckGameStatusRequest(board.getId()), GameDTO.class)
                    .getStatus();
        } while (gameStatus == GameDTO.StatusEnum.RUNNING);
    }

    private char[][] constructPrintableBoard() {
        final List<String> elements = board.getBoard();
        return new char[][]{{getElement(elements, 0), '|', getElement(elements, 1), '|', getElement(elements, 2)},
                {'-', '+', '-', '+', '-'},
                {getElement(elements, 3), '|', getElement(elements, 4), '|', getElement(elements, 5)},
                {'-', '+', '-', '+', '-'},
                {getElement(elements, 6), '|', getElement(elements, 7), '|', getElement(elements, 8)}};
    }

    private char getElement(List<String> elements, int position) {
        return elements.get(position).charAt(0);
    }

    private MoveDTO createMoveDTO(final String position) {
        final MoveDTO moveDTO = new MoveDTO();
        moveDTO.setPlayer(player);
        moveDTO.setPosition(position);
        return moveDTO;
    }

}
