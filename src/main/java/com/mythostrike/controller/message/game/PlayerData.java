package com.mythostrike.controller.message.game;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.mythostrike.model.game.player.Champion;
import com.mythostrike.model.game.player.Player;
import com.mythostrike.model.lobby.Identity;

public record PlayerData(String username, int cardCount, boolean isAlive,
                         Champion champion, int maxHp, int currentHp, Identity identity) {
    public PlayerData(Player player) {
        this(player.getUsername(), player.getHandCards().size(), player.isAlive(),
            player.getChampion(), player.getMaxHp(), player.getCurrentHp(), player.getIdentity());
    }

    @JsonGetter("identity")
    private String getIdentityString() {
        if (identity.isIncognito()) {
            return Identity.NONE.toString();
        } else {
            return identity.toString();
        }
    }
}