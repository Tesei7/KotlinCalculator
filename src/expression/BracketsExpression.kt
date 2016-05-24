package expression

/**
 * Expression rounded by brackets
 *
 * Created by Ilya on 24.05.2016.
 */
class BracketsExpression(val expression: Expression) : Expression {
    override fun getValue(): Double = expression.getValue()
    override fun toString(): String = "(" + expression.toString() + ")"
}
