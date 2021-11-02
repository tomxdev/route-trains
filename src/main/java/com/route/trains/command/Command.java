package com.route.trains.command;

import com.route.trains.Commuter;

public interface Command {
    void execute(Commuter commuter);
}
