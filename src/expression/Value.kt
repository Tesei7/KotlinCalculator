package expression

/**
 * Created by Ilya on 20.05.2016.
 */
class Value(val v : Double) : Expression{
    override fun getValue(): Double = v;
}