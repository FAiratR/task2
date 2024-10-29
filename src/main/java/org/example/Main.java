package org.example;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {

        // Реализуйте удаление из листа всех дубликатов
        List<Integer> lstInt = java.util.List.of(5,2,10,9,4,3,10,1,13);
        System.out.println("1. До удаления дубликатов: " + lstInt.toString());
        System.out.println("1. После удаления дубликатов: " + deleteDuplicate(lstInt).toString());
        List<String> lstStr = java.util.List.of("a","b","c","d","e","a","b","c","d");
        System.out.println("1. До удаления дубликатов: " + lstStr);
        System.out.println("1. После удаления дубликатов: " + deleteDuplicate(lstStr).toString());

        // Найдите в списке целых чисел 3-е наибольшее число (пример: 5 2 10 9 4 3 10 1 13 => 10)
        System.out.println("2. 3-е наибольшее число: " + getMax(lstInt));

        // Найдите в списке целых чисел 3-е наибольшее «уникальное» число (пример: 5 2 10 9 4 3 10 1 13 => 9,
        // в отличие от прошлой задачи здесь разные 10 считает за одно число)
        System.out.println("3. 3-е наибольшее «уникальное» число: " + getMaxUnic(lstInt));

        // Имеется список объектов типа Сотрудник (имя, возраст, должность), необходимо получить список
        // имен 3 самых старших сотрудников с должностью «Инженер», в порядке убывания возраста
        List<Employee> lstEmployee = java.util.List.of(new Employee("Иван",24,"Инженер"),
                new Employee("Федор",34,"Инженер"),
                new Employee("Петр",44,"Инженер"),
                new Employee("Саша",33,"Инженер"),
                new Employee("Маша",35,"Инженер"),
                new Employee("Даша",28,"Инженер"),
                new Employee("Игорь",47,"Начальник"));

        System.out.println("4. 3 самых старших сотрудника с должностью «Инженер», в порядке убывания возраста: " + getSeniorEmployees(lstEmployee).toString());

        // Имеется список объектов типа Сотрудник (имя, возраст, должность), посчитайте средний возраст
        // сотрудников с должностью «Инженер»
        System.out.println("5. средний возраст: " + getAverageAge(lstEmployee));

        // Найдите в списке слов самое длинное
        List<String> lstWords = java.util.List.of("Найдите", "в" ,"списке", "самоедлинное", "слов", "самое", "длинное");
        System.out.println("6. в списке слов самое длинное: " + getLongWord(lstWords));

        // Имеется строка с набором слов в нижнем регистре, разделенных пробелом. Постройте хеш-мапы, в
        // которой будут хранится пары: слово - сколько раз оно встречается во входной строке
        String str = "имеется строка с набором слов в нижнем регистре имеется строка";
        System.out.println("7. Строка для подсчета: " + str);
        System.out.println("7. слово - сколько раз оно встречается во входной строке: " + getWordsCount(str).toString());

        // Отпечатайте в консоль строки из списка в порядке увеличения длины слова, если слова имеют
        // одинаковую длины, то должен быть сохранен алфавитный порядок
        System.out.println("8. строки из списка в порядке увеличения: " + getWordsInLength(str));

        // Имеется массив строк, в каждой из которых лежит набор из 5 строк, разделенных пробелом, найдите
        // среди всех слов самое длинное, если таких слов несколько, получите любое из них
        String[] strings = {"первая1 строка1 разделенная1 пробелом1 один",
                "вторая2 строка2 разделенная2самаядлинная пробелом2 два",
                "третья3 строка3 разделенная3самаядлинная пробелом3 три3"};
        System.out.println("9. среди всех слов самое длинное: " + getLongWord(strings));
    }

    // Реализуйте удаление из листа всех дубликатов
    public static List<?> deleteDuplicate(List<?> inList) {
        return inList.stream().distinct().toList();
    }

    //  Найдите в списке целых чисел 3-е наибольшее число (пример: 5 2 10 9 4 3 10 1 13 => 10)
    public static Integer getMax(List<Integer> lstIn) {
        return lstIn.stream().sorted(Comparator.reverseOrder()).toList().get(2);
    }
    public static Integer getMaxUnic(List<Integer> lstIn) {
        return lstIn.stream().distinct().sorted(Comparator.reverseOrder()).toList().get(2);
    }

    // Имеется список объектов типа Сотрудник (имя, возраст, должность), необходимо получить список
    // имен 3 самых старших сотрудников с должностью «Инженер», в порядке убывания возраста
    public static List<Employee> getSeniorEmployees(List<Employee> lstIn) {
        return lstIn.stream().filter(x -> x.getPosition() == "Инженер").sorted(Comparator.comparing(Employee::getAge).reversed()).limit(3).toList();
    }

    // Имеется список объектов типа Сотрудник (имя, возраст, должность), посчитайте средний возраст
    // сотрудников с должностью «Инженер»
    public static Double getAverageAge(List<Employee> lstIn) {
        return lstIn.stream().filter(x -> x.getPosition() == "Инженер").collect(Collectors.averagingDouble(Employee::getAge));
    }

    // Найдите в списке слов самое длинное
    public static String getLongWord(List<String> lstIn) {
        return lstIn.stream().max(Comparator.comparingInt(String::length)).get();
    }

    // Имеется строка с набором слов в нижнем регистре, разделенных пробелом. Постройте хеш-мапы, в
    // которой будут хранится пары: слово - сколько раз оно встречается во входной строке
    public static Map<String, Long> getWordsCount(String strIn) {
        //return Arrays.stream(strIn.split(" ")).toList().stream().collect(()-> new HashMap<String,Integer>(), (a,b)->{if (a.containsKey(b)) a.put(b,a.get(b)+1); else a.put(b,1);}, (a,b)->a.putAll(b));
        return Arrays.stream(strIn.split(" ")).collect(Collectors.groupingBy(String::valueOf,Collectors.counting()));
    }

    // Отпечатайте в консоль строки из списка в порядке увеличения длины слова, если слова имеют
    // одинаковую длины, то должен быть сохранен алфавитный порядок
    public static List<String> getWordsInLength(String strIn) {
        return Arrays.stream(strIn.split(" ")).toList().stream().sorted(Comparator.naturalOrder()).sorted(Comparator.comparingInt(String::length)).toList();
    }

    // Имеется массив строк, в каждой из которых лежит набор из 5 строк, разделенных пробелом, найдите
    // среди всех слов самое длинное, если таких слов несколько, получите любое из них
    public static String getLongWord(String[] inStrArr) {
        //return Arrays.stream(Arrays.stream(inStrArr).collect(Collectors.joining(" ")).split(" ")).max(Comparator.comparingInt(String::length)).get();
        return Arrays.stream(inStrArr).flatMap(s->Stream.of(s.split(" "))).max(Comparator.comparingInt(String::length)).get();
    }
}