package lexical

import java.util.*

/**
 * Expressions lexical analyzer
 *
 * Created by Ilya on 20.05.2016.
 */

class LexicalAnalyzer(var expression: String) {

    val tokens: MutableList<Token> = ArrayList()
    var offset: Int = 0
    var buffer: String = ""

    /**
     * @return list of tokens
     */
    fun analyze(): List<Token> {
        expression = expression.replace("\\s+".toRegex(), "")
        while (offset < expression.length) tokens.add(getNextToken());
        return tokens
    }

    fun Char.isDigit() = "".plus(this).matches("\\d".toRegex())
    fun Char.isDot() = "".plus(this).matches("\\.".toRegex())

    private fun getNextToken(): Token {
        try {
            val c = expression[offset];

            return if (c.isDigit()) {
                readFloatLiteral(c)
            } else {
                when (c) {
                    '(' -> Token(TokenType.LEFT_PARENTHESIS, offset)
                    ')' -> Token(TokenType.RIGHT_PARENTHESIS, offset)
                    '+' -> Token(TokenType.PLUS, offset)
                    '-' -> Token(TokenType.MINUS, offset)
                    '*' -> Token(TokenType.MUL, offset)
                    '/' -> Token(TokenType.DEV, offset)
                    else -> throw LexicalException("Illegal character", offset)
                }
            }
        } finally {
            offset++
        }
    }

    private fun readFloatLiteral(c: Char): Token {
        buffer = "".plus(c)
        val offsetOfValue = offset
        var wasDot = false
        while (isNextCharIsPartOfFloatLiteral(wasDot)) {
            val char = getNextChar()
            buffer = buffer.plus(char)
            if (char != null && char.isDot()) wasDot = true
            offset++
        }
        return Token(TokenType.VALUE, buffer, offsetOfValue)
    }

    private fun isNextCharIsPartOfFloatLiteral(wasDot: Boolean): Boolean {
        val nextChar = getNextChar()
        return if (nextChar == null) {
            false
        } else
            if (wasDot) nextChar.isDigit()
            else nextChar.isDigit() || nextChar.isDot()

    }

    private fun getNextChar(): Char? {
        return if (offset == expression.length - 1) null else expression[offset + 1];
    }

}