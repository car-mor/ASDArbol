package mx.ipn.escom.k.parser.ASDR.Parser;
import ASDR.TipoToken;
import ASDR.Token;
import mx.ipn.escom.k.parser.ASDR.Parser;
import java.beans.Expression;

public class ExprAssign extends Expression {
    
    final TipoToken name;
    final Expression value;

    ExprAssign(Token name, Expression value) {
        this.name = name;
        this.value = value;
    }
}
