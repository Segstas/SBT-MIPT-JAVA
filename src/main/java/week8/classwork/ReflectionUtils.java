package week8.classwork;

import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static java.util.stream.Collectors.toList;

public class ReflectionUtils {

    public static List<Field> collectionFieldName(Object object) {
        return Arrays.asList(object.getClass().getDeclaredFields());
    }

    public static List<Method> collectionMethodName(Object object) {
        return Arrays.asList(object.getClass().getDeclaredMethods());
    }

    public static List<String> collectionMemberName(List<? extends Member> memberList, Predicate<String> stringPredicate) {
        return memberList.stream()
                .map(Member::getName)
                .filter(stringPredicate)
                .collect(toList());
    }

    public static void main(String[] args) {
        Person person1 = new Person(8, "asdasd");
        List<String> methodNameList = collectionMemberName(collectionMethodName(person1), o -> o.length() > 5);
        for (String arg : methodNameList) {
            System.out.println(arg);
        }
        List<String> fieldNameList = collectionMemberName(collectionFieldName(person1), o -> o.length() > 5);
        for (String arg : fieldNameList) {
            System.out.println(arg);
        }
    }
}

