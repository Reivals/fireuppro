package com.karkowski.client.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.Valid;

public class MoveDTO   {
  @JsonProperty("player")
  private PlayerDTO player = null;

  @JsonProperty("position")
  private String position = null;

  public MoveDTO player(PlayerDTO player) {
    this.player = player;
    return this;
  }


  @Valid
  public PlayerDTO getPlayer() {
    return player;
  }

  public void setPlayer(PlayerDTO player) {
    this.player = player;
  }

  public MoveDTO position(String position) {
    this.position = position;
    return this;
  }

  public String getPosition() {
    return position;
  }

  public void setPosition(String position) {
    this.position = position;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MoveDTO moveDTO = (MoveDTO) o;
    return Objects.equals(this.player, moveDTO.player) &&
            Objects.equals(this.position, moveDTO.position);
  }

  @Override
  public int hashCode() {
    return Objects.hash(player, position);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MoveDTO {\n");

    sb.append("    player: ").append(toIndentedString(player)).append("\n");
    sb.append("    position: ").append(toIndentedString(position)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

