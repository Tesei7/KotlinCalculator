package syntax

import lexical.Token

/**
 * Realization of shunting yard algorithm for expressions calculation
 *
 * Created by Ilya on 20.05.2016.
 */
class ShuntingYard(val input: List<Token>) {
    var buffer: MutableList<Token> = mutableListOf()
    var operations: MutableList<Token> = mutableListOf()

    fun createBuffer() : List<Token> {
        for (token in input) {
            if (token.isValue()) {
                buffer.add(token)
            } else if (token.isOperation()) {
                while (token.isLessThanOrEqual(getOperationsTop())){
                    popOperation()
                }
                operations.add(token)
            } else if (token.isLeftBrace()) {
                operations.add(token)
            } else if (token.isRightBrace()) {
                var op = getOperationsTop()
                while (op != null && !op.isLeftBrace()) {
                    popOperation()
                    op = getOperationsTop()
                }
                if (op != null) {
                    operations.remove(operations.last())
                }
            }
        }
        while(!operations.isEmpty()){
            popOperation()
        }
        return buffer
    }

    private fun popOperation() {
        buffer.add(operations.last())
        operations.remove(operations.last())
    }

    private fun getOperationsTop(): Token? {
        return if (operations.isEmpty()) null else operations.last()
    }
}