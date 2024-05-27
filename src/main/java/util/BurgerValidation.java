package util;

import exceptions.BurgerException;
import org.springframework.http.HttpStatus;

public class BurgerValidation {
    public static void checkName(String name){
        if (name==null || name.isEmpty()){
            throw new BurgerException("name is null or empty! name:"+name, HttpStatus.BAD_REQUEST);
        }
    }
}
