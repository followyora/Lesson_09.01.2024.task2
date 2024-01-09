package org.example.lightning.devices;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<Class<?>, List<String>> classToFields = new HashMap<>();
        Class<?> clazz = SmartLamp.class;

        while (clazz != null) {
            Field[] fields = clazz.getDeclaredFields();
            classToFields.put(clazz, Arrays.stream(fields).map(Field::getName).toList());
            clazz = clazz.getSuperclass();
        }
        classToFields.forEach((k, v) -> System.out.println(k.getSimpleName() + "\t" + v));
    }

}
