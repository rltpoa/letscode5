package ada.tech.alunos.exception;

import lombok.Getter;

@Getter
public class PerseveranceException extends Exception{
    private int httpStatus;
    public PerseveranceException(int status, String msg){
        super(msg);
        this.httpStatus = status;
    };

}
