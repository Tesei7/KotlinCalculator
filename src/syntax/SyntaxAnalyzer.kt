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
        if(stack.size<2){
            throw Exception("Incomplete expression")
        }
        val e1 = stack.last()
        val e2 = stack[stack.size - 2]
        val expression = when (type) {
            TokenType.PLUS -> Sum(e1, e2)
            TokenType.MINUS -> Subtraction(e2, e1)
            TokenType.MUL -> Multiplication(e1, e2)
            TokenType.DEV -> Division(e2, e1)
            else -> throw Exception("Unknown token")
        }
        stack.remove(e1)
        stack.remove(e2)
        stack.add(expression)
    }

}