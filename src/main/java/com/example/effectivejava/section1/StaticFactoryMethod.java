package com.example.effectivejava.section1;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

// 정적 팩토리 메서드 데모
public class StaticFactoryMethod {

    public static void main(String[] args) {
    	
        // 1. 이름을 가진 정적 팩토리 메서드
        Person student = Person.createStudent("Alice", 20);
        Person teacher = Person.createTeacher("Bob", "Math");

        System.out.println(student);
        System.out.println(teacher);

        // 2. 객체 재사용 (캐싱)
        LocalDate date1 = LocalDateCache.of(2025, 1, 20);
        LocalDate date2 = LocalDateCache.of(2025, 1, 20);

        System.out.println(date1 == date2); // true (캐싱된 객체)

        // 3. 하위 타입 반환
        Animal cat = Animal.create("cat", "Kitty");
        Animal dog = Animal.create("dog", "Rover");

        System.out.println(cat.speak());
        System.out.println(dog.speak());
    }
}

// 1. 이름을 가진 정적 팩토리 메서드
class Person {
    private final String name;
    private final String role;
    private final int age;

    private Person(String name, String role, int age) {
        this.name = name;
        this.role = role;
        this.age = age;
    }

    public static Person createStudent(String name, int age) {
        return new Person(name, "Student", age);
    }

    public static Person createTeacher(String name, String subject) {
        return new Person(name, "Teacher (" + subject + ")", 0);
    }

    @Override
    public String toString() {
        return "Person{name='" + name + "', role='" + role + "', age=" + age + "}";
    }
}

// 2. 객체 재사용 (캐싱)
class LocalDateCache {
    private static final Map<String, LocalDate> CACHE = new HashMap<>();

    public static LocalDate of(int year, int month, int dayOfMonth) {
        String key = year + "-" + month + "-" + dayOfMonth;
        return CACHE.computeIfAbsent(key, k -> LocalDate.of(year, month, dayOfMonth));
    }
}

// 3. 하위 타입 반환
abstract class Animal {
    protected final String name;

    protected Animal(String name) {
        this.name = name;
    }

    public abstract String speak();

    public static Animal create(String type, String name) {
        switch (type.toLowerCase()) {
            case "cat":
                return new Cat(name);
            case "dog":
                return new Dog(name);
            default:
                throw new IllegalArgumentException("Unknown animal type: " + type);
        }
    }
}

class Cat extends Animal {
    public Cat(String name) {
        super(name);
    }

    @Override
    public String speak() {
        return name + " says: Meow!";
    }
}

class Dog extends Animal {
    public Dog(String name) {
        super(name);
    }

    @Override
    public String speak() {
        return name + " says: Woof!";
    }
}
