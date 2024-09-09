package br.com.sistema.controle.financas.pessoais.handler;

public class ServiceException extends RuntimeException{
    public ServiceException(String massage, Throwable cause){
        super(massage, cause);
    }
}
