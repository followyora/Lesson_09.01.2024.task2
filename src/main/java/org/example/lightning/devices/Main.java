package org.example.lightning.devices;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    static SmartLamp sl = new SmartLamp();

    public static void main(String[] args) {
        Map<Class<?>, List<String>> classToFields = new HashMap<>();
        Class<?> clazz = SmartLamp.class;

        while (clazz != null) {
            Field[] fields = clazz.getDeclaredFields();
            classToFields.put(clazz, Arrays.stream(fields).map(Field::getName).toList());
            clazz = clazz.getSuperclass();
        }
        classToFields.forEach((k, v) -> System.out.println(k.getSimpleName() + "\t" + v));

        classToFields.forEach((k, v) -> {
            v.forEach(f -> {
                try {
                    Field field = k.getDeclaredField(f);
                    if (field.getType().equals(String.class)) {
                        field.setAccessible(true);
                        System.out.println(f + "Old value: " + field.get(sl));
                        field.set(sl, "abc");
                        System.out.println(f + "New value: " + field.get(sl));
                        field.setAccessible(false);
                    }
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    throw new RuntimeException(e);
                }

            });

        });
    }
}
