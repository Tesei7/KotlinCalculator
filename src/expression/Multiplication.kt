package expression

/**
 * Multiplication expression
 *
 * Created by Ilya on 20.05.2016.
 */
class Multiplication(val expression1: Expression, val expression2: Expression) : Expression {
    override fun getValue(): Double = expression1.getValue() * expression2.getValue();
}
