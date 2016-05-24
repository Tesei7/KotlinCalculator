package lexical

/**
 * Lexical token
 *
 * Created by Ilya on 20.05.2016.
 */
class Token(val type: TokenType, val value: String, val offset: Int) {
    constructor(type: TokenType, offset: Int) : this(type, "", offset)

    fun isValue(): Boolean = type == TokenType.VALUE
    fun isOperation(): Boolean = type == TokenType.PLUS || type == TokenType.MINUS || type == TokenType.MUL || type == TokenType.DEV
    fun isLeftParenthesis(): Boolean = type == TokenType.LEFT_PARENTHESIS
    fun isRightParenthesis(): Boolean = type == TokenType.RIGHT_PARENTHESIS
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
            TokenType.LEFT_PARENTHESIS -> 0
            TokenType.RIGHT_PARENTHESIS -> 3
            else -> 0
        }
    }

    override fun toString(): String {
        return when(type){
            TokenType.PLUS -> "+"
            TokenType.MINUS -> "-"
            TokenType.MUL -> "*"
            TokenType.DEV -> "/"
            TokenType.LEFT_PARENTHESIS -> "("
            TokenType.RIGHT_PARENTHESIS -> ")"
            TokenType.VALUE -> value
        }
    }
}