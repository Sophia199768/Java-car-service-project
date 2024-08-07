package org.example.in.parser.strategy;

import org.example.in.parser.command.Commandable;

public interface Strategy {
    Commandable parse(String[] tokens);
}
