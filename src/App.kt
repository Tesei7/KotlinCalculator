/**
 * Application main function
 *
 * Created by Ilya on 20.05.2016.
 */
fun main(args: Array<String>) {
    println("Enter expression: ")
    val line = readLine();

    try {
        if (line == null) {
            println("Enter expression");
            return;
        }
        val result = Calculator(line).calculate();
        println("$line = $result");
    } catch(t: Throwable) {
        println(t.message);
    }
}