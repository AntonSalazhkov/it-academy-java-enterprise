package by.it.academy.shop.util.annotation;

import javax.annotation.processing.*;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

/**
 * Анализатор аннотации SetUUID.
 * Проверяет наличие аннотации по полученному классу.
 * Проверяет наличие поля Id в сущности.
 * В случае нахождение поля Id и если оно равно null, то устанавливает ему случайно сгенерированный UUID.
 */

@SupportedAnnotationTypes("SetUUID")
public class SetUUIDAnnotationAnalyzer {

    public static void parse(Object object) {
        Optional<SetUUID> annotation = Optional.ofNullable(object.getClass().getAnnotation(SetUUID.class));

        if (annotation.isPresent()) {
            Field[] fields = object.getClass().getDeclaredFields();

            Optional<String> id = Arrays.stream(fields)
                    .map(Field::getName)
                    .filter(name -> name.equals("id"))
                    .findFirst();

            if (id.isPresent()) {
                try {
                    Field fieldId = object.getClass().getDeclaredField("id");
                    fieldId.setAccessible(true);

                    if (Objects.isNull(fieldId.get(object))) {
                        UUID uuid = UUID.randomUUID();
                        fieldId.set(object, uuid + "");
                    }
                } catch (IllegalAccessException | NoSuchFieldException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
