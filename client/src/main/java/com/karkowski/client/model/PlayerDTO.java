package com.karkowski.client.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;


public class PlayerDTO   {
  /**
   * sign
   */
  public enum PlayerSignEnum {
    X("X"),

    O("O");

    private String value;

    PlayerSignEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static PlayerSignEnum fromValue(String text) {
      for (PlayerSignEnum b : PlayerSignEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("playerSign")
  private PlayerSignEnum playerSign = null;

  public PlayerDTO playerSign(PlayerSignEnum playerSign) {
    this.playerSign = playerSign;
    return this;
  }
  public PlayerSignEnum getPlayerSign() {
    return playerSign;
  }

  public void setPlayerSign(PlayerSignEnum playerSign) {
    this.playerSign = playerSign;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PlayerDTO playerDTO = (PlayerDTO) o;
    return Objects.equals(this.playerSign, playerDTO.playerSign);
  }

  @Override
  public int hashCode() {
    return Objects.hash(playerSign);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PlayerDTO {\n");

    sb.append("    playerSign: ").append(toIndentedString(playerSign)).append("\n");
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

