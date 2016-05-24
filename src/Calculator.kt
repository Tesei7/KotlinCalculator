import expression.Expression
import lexical.LexicalAnalyzer
import syntax.SyntaxAnalyzer

/**
 * Expressions calculator
 *
 * Created by Ilya on 20.05.2016.
 */

class Calculator(val expression: String) {
    val lexicalAnalyzer: LexicalAnalyzer = LexicalAnalyzer(expression)

    /**
     * @return root expression
     */
    fun calculate(): Expression{
        val list = lexicalAnalyzer.analyze()
        return SyntaxAnalyzer(list).analyze()
    }
}