package base

interface SingleArgCommand : Command {
    fun handleArg(argument: String): Command.Result

    override fun handleInput(input: MutableList<String>): Command.Result {
        return if (input.size != 1) {
            Command.Result.invalid()
        } else {
            handleArg(input[0])
        }
    }
}