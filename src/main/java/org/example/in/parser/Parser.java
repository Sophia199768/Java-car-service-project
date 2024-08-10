package org.example.in.parser;

import org.example.application.ApplicationState;
import org.example.in.parser.command.Commandable;


public interface Parser {
    Commandable parse(String input, ApplicationState state);
}
