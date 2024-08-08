package org.example;

import org.example.application.ApplicationState;
import org.example.application.factory.ParserFactory;
import org.example.in.parser.Parser;
import org.example.in.parser.command.Commandable;
import org.example.service.service.AuditService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Parser parser = ParserFactory.createParser();
        ApplicationState state = new ApplicationState(new AuditService());

        System.out.println("register or login");


        while (true) {
            String command = scanner.nextLine();

            if (command.equals("exit")) break;

            Commandable comm = parser.parse(command, state);

            if (comm == null) continue;

            try {
                comm.execute(state);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
