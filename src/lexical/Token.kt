package lexical

/**
 * Created by Ilya on 20.05.2016.
 */
class Token(val type: TokenType, val value: String, val offset: Int) {
    constructor(type: TokenType, offset: Int) : this(type, "", offset)

    fun isValue(): Boolean = type == TokenType.VALUE
    fun isOperation(): Boolean = type == TokenType.PLUS || type == TokenType.MINUS || type == TokenType.MUL || type == TokenType.DEV
    fun isLeftBrace(): Boolean = type == TokenType.LEFT_BRACE
    fun isRightBrace(): Boolean = type == TokenType.RIGHT_BRACE
    fun isLessThanOrEqual(t: Token?): Boolean {
        return if (t == null)
            false
        else
            this.getPriority().compareTo(t.getPriority()) < 0
    }

    fun getPriority(): Int {
        return when (type) {
            TokenType.PLUS -> 1
            TokenType.MINUS -> 1
            TokenType.MUL -> 2
            TokenType.DEV -> 2
            else -> 0
        }
    }
}