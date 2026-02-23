package io.github.mohitkasliwal51_hub;

public class StringUtils {

        public static String greet(String name) {
        return "Hello " + name + "!";
    }
    
    public static String reverse(String str) {
        return new StringBuilder(str).reverse().toString();
    }
}