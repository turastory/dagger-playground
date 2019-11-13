package base;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public final class CommandRouter {
    private final Map<String, Command> commands;

    @Inject
    CommandRouter(Map<String, Command> commands) {
        this.commands = commands;
    }

    Command.Result route(String input) {
        List<String> splitInput = split(input);
        if (splitInput.isEmpty()) {
            return invalidCommand(input);
        }

        String commandKey = splitInput.get(0);
        Command command = commands.get(commandKey);
        if (command == null) {
            return invalidCommand(input);
        }

        Command.Result result = command.handleInput(splitInput.subList(1, splitInput.size()));
        if (result.getStatus() == Command.Status.INVALID) {
            return invalidCommand(input);
        }

        return result;
    }

    private Command.Result invalidCommand(String input) {
        System.out.println(
                String.format("couldn't understand \"%s\". please try again.", input));
        return Command.Result.invalid();
    }

    // Split on whitespace
    private static List<String> split(String string) {
        return Arrays.asList(string.split(" "));
    }
}