package base;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Logic to process some user input.
 */
public interface Command {
    /**
     * Process the rest of the command's words and do something.
     */
    Status handleInput(@NotNull List<String> input);

    enum Status {
        INVALID,
        HANDLED
    }
}