package com.route.trains.command;

abstract class AbstractCommand implements Command {
    private final String commandLine;

    public AbstractCommand(final String commandLine) {
        this.commandLine = commandLine;
    }

    protected final String getCommandLine() {
        return commandLine;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (commandLine == null ? 0 : commandLine.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AbstractCommand other = (AbstractCommand) obj;
        if (commandLine == null) {
            return other.commandLine == null;
        }
        return commandLine.equals(other.commandLine);
    }

}
