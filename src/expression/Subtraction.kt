package expression

/**
 * Subtraction expression
 *
 * Created by Ilya on 20.05.2016.
 */

class Subtraction(val expression1: Expression, val expression2: Expression) : Expression {
    override fun getValue(): Double = expression1.getValue() - expression2.getValue()
    override fun toString(): String = expression1.toString() + "-" + expression2.toString()
}
