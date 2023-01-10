package com.example.minkiApi.exception;

// serialVersionUID를 정의해주지 안흥ㄴ 경우 나타나는 warnning을 체크하지 않음
@SuppressWarnings("serial")
public class FileException  extends RuntimeException{

    public FileException(String message){
        super(message);
    }

    public FileException(String message, Throwable cause){
        super(message, cause);
    }
}
