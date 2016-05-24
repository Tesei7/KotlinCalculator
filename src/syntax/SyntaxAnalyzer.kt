package syntax

import expression.*
import lexical.Token
import lexical.TokenType

/**
 * Expressions syntax analyzer
 *
 * Created by Ilya on 20.05.2016.
 */
class SyntaxAnalyzer(val tokens: List<Token>) {
    val shuntingYard = ShuntingYard(tokens)
    val stack: MutableList<Expression> = arrayListOf()

    fun analyze(): Expression {
        val buffer = shuntingYard.createBuffer()
        return createExpressionsTree(buffer)
    }

    private fun createExpressionsTree(buffer: List<Token>): Expression {
        for (token in buffer) {
            if (token.isValue()) {
                stack.add(Value(token.value.toDouble()))
            } else {
                createExpression(token.type)
            }
        }
        if (stack.size > 1) {
            throw Exception("Incomplete expression")
        }
        return stack.first()
    }

    private fun createExpression(type: TokenType) {
        val lastOperands = getOperands(type)
        val expression = when (type) {
            TokenType.PLUS -> Sum(lastOperands[0], lastOperands[1])
            TokenType.MINUS -> Subtraction(lastOperands[0], lastOperands[1])
            TokenType.MUL -> Multiplication(lastOperands[0], lastOperands[1])
            TokenType.DEV -> Division(lastOperands[0], lastOperands[1])
            TokenType.RIGHT_PARENTHESIS -> BracketsExpression(lastOperands[0])
            else -> throw Exception("Unknown token")
        }
        stack.removeAll(lastOperands)
        stack.add(expression)
    }

    private fun getOperands(type: TokenType): MutableList<Expression> {
        val operandsNumber = if (type == TokenType.RIGHT_PARENTHESIS) 1 else 2
        if (stack.size < operandsNumber) {
            throw Exception("Incomplete expression")
        }
        return stack.subList(stack.size - operandsNumber, stack.size)
    }

}