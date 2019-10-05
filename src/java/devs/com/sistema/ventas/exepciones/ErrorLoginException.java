package devs.com.sistema.ventas.exepciones;

public class ErrorLoginException extends UserException{
    public ErrorLoginException(String mensaje){
        super(mensaje);
    }
}
