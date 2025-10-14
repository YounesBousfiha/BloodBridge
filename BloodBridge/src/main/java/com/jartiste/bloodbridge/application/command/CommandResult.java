package com.jartiste.bloodbridge.application.command;

public class CommandResult {

    private final String path;
    private final boolean redirect;

    private CommandResult(String view, boolean redirect) {
        this.path = view;
        this.redirect = redirect;
    }

    public String getPath() {
        return this.path;
    }

    public boolean isRedirect() {
        return this.redirect;
    }

    public static CommandResult forward(String path) {
        return new CommandResult(path, false);
    }

    public static CommandResult redirect(String url) {
        return new CommandResult(url, true);
    }
}
