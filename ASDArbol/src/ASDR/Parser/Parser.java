package mx.ipn.escom.k.parser.ASDR.Parser;
import ASDR.TipoToken;
import ASDR.Token;
import java.util.List;
import mx.ipn.escom.k.parser.exception.ParserException;

public class Parser{

    private int i = 0;
    private boolean hayErrores = false;
    private Token preanalisis;
    private final List<Token> tokens;


    public Parser(List<Token> tokens){
        this.tokens = tokens;
        preanalisis = this.tokens.get(i);
    }

    @Override
    public boolean parse() {
        Q();

        if(preanalisis.tipo == TipoToken.EOF && !hayErrores){
            System.out.println("Consulta correcta");
            return  true;
        }else {
            System.out.println("Se encontraron errores");
        }
        return false;
    }

    // Q -> select D from T
    private void Q(){
        match(TipoToken.SELECT);
        D();
f
        match(TipoToken.FROM);
        T();
    }

    // D -> distinct P | P
    private void D(){
        if(hayErrores)
            return;

        if(preanalisis.tipo == TipoToken.DISTINCT){
            match(TipoToken.DISTINCT);
            P();
        }
        else if (preanalisis.tipo == TipoToken.ASTERISCO
                || preanalisis.tipo == TipoToken.IDENTIFICADOR) {
            P();
        }
        else{
            hayErrores = true;
            System.out.println("Se esperaba 'distinct' o '*' o 'identificador'");
        }
    }

    // P -> * | A
    private void P(){
        if(hayErrores)
            return;

        if(preanalisis.tipo == TipoToken.ASTERISCO){
            match(TipoToken.ASTERISCO);
        }
        else if(preanalisis.tipo == TipoToken.IDENTIFICADOR){
            A();
        }
        else{
            hayErrores = true;
            System.out.println("Se esperaba '*' or 'identificador'");
        }
    }

    // A -> A2 A1
    private void A(){
        if(hayErrores)
            return;

        A2();
        A1();
    }

    // A2 -> id A3
    private void A2(){
        if(hayErrores)
            return;

        if(preanalisis.tipo == TipoToken.IDENTIFICADOR){
            match(TipoToken.IDENTIFICADOR);
            A3();
        }
        else{
            hayErrores = true;
            System.out.println("Se esperaba un 'identificador'");
        }
    }

    // A1 -> ,A | Ɛ
    private void A1(){
        if(hayErrores)
            return;

        if(preanalisis.tipo == TipoToken.COMA){
            match(TipoToken.COMA);
            A();
        }
    }

    // A3 -> . id | Ɛ
    private void A3(){
        if(hayErrores)
            return;

        if(preanalisis.tipo == TipoToken.PUNTO){
            match(TipoToken.PUNTO);
            match(TipoToken.IDENTIFICADOR);
        }
    }

    // T -> T2 T1
    private void T() {
        //System.out.println("T -> T2 T1 "+tokens.get(i));
        if(hayErrores)
            return;
        T2();
        T1();

    }


    // T1 -> , T | Ɛ
    private void T1() {
        //System.out.println("T1 -> , T | Ɛ " +tokens.get(i));
        if(hayErrores){
            return;}
        //System.out.println(" " +preanalisis.tipo);
        if(preanalisis.tipo == TipoToken.COMA) {
            match(TipoToken.COMA);
            T();
        }
        else if(preanalisis.tipo!=TipoToken.EOF){
            hayErrores = true;
            System.out.println("Se esperaba una ','");
        }
    }



    // T2 -> id T3
    private void T2() {
        //System.out.println("T2 -> id T3 "+tokens.get(i));
        if(hayErrores)
            return;

        if(preanalisis.tipo == TipoToken.IDENTIFICADOR) {
            match(TipoToken.IDENTIFICADOR);
            T3();
        }
        else {
            hayErrores = true;
            System.out.println("Se esperaba un 'identificador'");
        }

    }

    //T3 -> ,id | Ɛ
    private void T3() {
        //System.out.println("T3 -> id | Ɛ "+tokens.get(i));
        if(hayErrores)
            return;
        if(preanalisis.tipo == TipoToken.COMA) {
            match(TipoToken.COMA);
            match(TipoToken.IDENTIFICADOR);
        }
    }

    private void match(TipoToken tt){
        if(preanalisis.tipo == tt){
            i++;
            preanalisis = tokens.get(i);
        }
        else{
            hayErrores = true;
            System.out.println("Error encontrado");
        }

    }

}
