package com.example.demo.utils.validation;

import java.util.regex.Pattern;

public class ValidationUtils {

    public static boolean isNumeric(String numericParameter){
        return Pattern.matches("/d", numericParameter);
    }

    public static boolean isText(String text){
        return Pattern.matches("[а-яА-яa-zA-Z]", text);
    }

}
