package com.lfm.test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

class Main {
    private String name = "dsfads";
    public Main(){
        System.out.println("constructor");
    }
    public static void main (String [] args) {
        try {
            Main main = Main.class.getDeclaredConstructor().newInstance();
            Field[] declaredFields = Main.class.getDeclaredFields();
            for (Field field:declaredFields){
                System.out.println(field.getName()+":"+field.get(main));
            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}