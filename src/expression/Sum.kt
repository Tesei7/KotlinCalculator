package expression

/**
 * Sum expression
 *
 * Created by Ilya on 20.05.2016.
 */

class Sum(val expression1: Expression, val expression2: Expression) : Expression {
    override fun getValue(): Double = expression1.getValue() + expression2.getValue();
}
