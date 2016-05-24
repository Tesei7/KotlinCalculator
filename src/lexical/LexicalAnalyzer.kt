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

    private fun getNextToken(): Token {
        try {
            val c = expression[offset];

            val isDigit = "".plus(c).matches("\\d".toRegex())
            return if (isDigit) {
                buffer = "".plus(c)
                val offsetOfValue = offset
                while (getNextChar() != null && "".plus(getNextChar()).matches("\\d|\\.".toRegex())) {
                    buffer = buffer.plus(getNextChar())
                    offset++
                }
                Token(TokenType.VALUE, buffer, offsetOfValue)
            } else {
                when (c) {
                    '(' -> Token(TokenType.LEFT_BRACE, offset)
                    ')' -> Token(TokenType.RIGHT_BRACE, offset)
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

    private fun getNextChar(): Char? {
        return if (offset == expression.length - 1) null else expression[offset + 1];
    }

}