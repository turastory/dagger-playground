package base;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;

/**
 * Logic to process some user input.
 */
public interface Command {
    /**
     * Process the rest of the command's words and do something.
     */
    Result handleInput(@NotNull List<String> input);

    class Result {
        private final Status status;
        private final Optional<CommandRouter> nestedCommandRouter;

        public static Result enterNestedCommandSet(CommandRouter commandRouter) {
            return new Result(Status.HANDLED, Optional.of(commandRouter));
        }

        public static Result invalid() {
            return new Result(Status.INVALID, Optional.empty());
        }

        public static Result inputCompleted() {
            return new Result(Status.INPUT_COMPLETED, Optional.empty());
        }

        public static Result handled() {
            return new Result(Status.HANDLED, Optional.empty());
        }

        public Result(Status status, Optional<CommandRouter> nestedCommandRouter) {
            this.status = status;
            this.nestedCommandRouter = nestedCommandRouter;
        }

        public Status getStatus() {
            return status;
        }

        public Optional<CommandRouter> getNestedCommandRouter() {
            return nestedCommandRouter;
        }
    }

    enum Status {
        INVALID,
        HANDLED,
        INPUT_COMPLETED
    }
}