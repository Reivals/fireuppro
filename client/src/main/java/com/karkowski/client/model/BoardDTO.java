package com.karkowski.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * BoardDTO
 */

public class BoardDTO   {
  @JsonProperty("board")
  @Valid
  private List<String> board = null;

  @JsonProperty("id")
  private String id = null;

  public BoardDTO board(List<String> board) {
    this.board = board;
    return this;
  }

  public BoardDTO addBoardItem(String boardItem) {
    if (this.board == null) {
      this.board = new ArrayList<String>();
    }
    this.board.add(boardItem);
    return this;
  }



  public List<String> getBoard() {
    return board;
  }

  public void setBoard(List<String> board) {
    this.board = board;
  }

  public BoardDTO id(String id) {
    this.id = id;
    return this;
  }


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BoardDTO boardDTO = (BoardDTO) o;
    return Objects.equals(this.board, boardDTO.board) &&
        Objects.equals(this.id, boardDTO.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(board, id);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BoardDTO {\n");

    sb.append("    board: ").append(toIndentedString(board)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

