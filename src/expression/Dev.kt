package expression

/**
 * Created by Ilya on 20.05.2016.
 */
class Dev(val expression1: Expression, val expression2: Expression) : Expression {
    override fun getValue(): Double {
      return if (expression2.getValue().equals(0.0)){
          throw Exception("/ by 0");
      } else {
          expression1.getValue()/expression2.getValue();
      }
    }
}