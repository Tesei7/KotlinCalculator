/**
 * Application main function
 *
 * Created by Ilya on 20.05.2016.
 */
fun main(args: Array<String>) {
    println("Enter expression: ")
    val line = readLine();

    try {
        if (line == null || line.isEmpty()) {
            println("Expression is empty");
            return;
        }
        val expression = Calculator(line).calculate();
        println("$expression = ${expression.getValue()}");
    } catch(t: Throwable) {
        println(t.message);
    }
}