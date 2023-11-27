package ASDR.Parser;

import ASDR.TipoToken;
import ASDR.Token;

import java.beans.Expression;

public class ExprAssign extends Expression {
    final TipoToken name;
    final Expression value;

    ExprAssign(Token name, Expression value) {
        this.name = name;
        this.value = value;
    }
}
