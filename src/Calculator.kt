import lexical.LexicalAnalyzer
import syntax.SyntaxAnalyzer

/**
 * Created by Ilya on 20.05.2016.
 */

class Calculator(val expression: String) {
    val lexicalAnalyzer: LexicalAnalyzer = LexicalAnalyzer(expression)


    fun calculate(): Double{
        val list = lexicalAnalyzer.analyze()
        return SyntaxAnalyzer(list).analyze().getValue()
    }
}