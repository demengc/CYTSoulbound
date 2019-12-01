package com.demeng7215.cytsoulbound;

/**
 * Retrieve messages from the config easily.
 */
public enum Message {

    INVALID_USAGE(CYTSoulbound.getPlugin().getSettings().getString("invalid-usage")),
    NOT_ONLINE(CYTSoulbound.getPlugin().getSettings().getString("not-online")),
    NO_PERMISSION(CYTSoulbound.getPlugin().getSettings().getString("no-permission")),
    EMPTY_HAND(CYTSoulbound.getPlugin().getSettings().getString("nothing-in-target-hand")),
    NOT_SOULBOUND(CYTSoulbound.getPlugin().getSettings().getString("not-soulbound-item")),
    SUCCESSFULLY_ADDED(CYTSoulbound.getPlugin().getSettings().getString("soulbound-added")),
    SUCCESSFULLY_REMOVED(CYTSoulbound.getPlugin().getSettings().getString("soulbound-removed")),
    ALREADY_SOULBOUND(CYTSoulbound.getPlugin().getSettings().getString("soulbound-already-exists-on-item"));

    private final String text;

    Message(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
