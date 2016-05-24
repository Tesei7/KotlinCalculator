package lexical

/**
 * Lexical exception
 *
 * Created by Ilya on 20.05.2016.
 */
class LexicalException(message: String?, val offset : Int) : Exception(message)
