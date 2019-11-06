package base

interface SingleArgCommand : Command {
    fun handleArg(argument: String): Command.Status

    override fun handleInput(input: MutableList<String>): Command.Status {
        return if (input.size != 1) {
            Command.Status.INVALID
        } else {
            handleArg(input[0])
        }
    }
}