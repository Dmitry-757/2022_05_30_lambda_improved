package org.dng;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * 1. Реализовать класс автомобиль, который содержит поля:
 * a. Марка автомобиля  (компания и модель, например, toyta ipsum)
 * b. Стоимость автомобиля  (руб)
 * c. Мощность автомобиля (л.с.)
 * 2. Добавить в класс все необходимые методы.
 * 3. Создать список автомобилей, заполнить его автомобилями с произвольными данными.
 * 4. Вывести данные об автомобилях на экран.
 * 5. Сделать выборку автомобилей ценой более 1000000 руб. Вывести данные на экран.
 * 6. Отсортировать список автомобилей по цене и мощности (автомобили с одной ценой сортировались по мощности).
 * Вывести результат на экран.
 * 7. Найти автомобиль с указанной стоимостью
 * 8. Случилась инфляция, необходимо увеличить стоимость всех авто на 20%.
 * 9. Случилось снижение инфляции, необходимо снизить стоимость всех авто на 20%.
 */

public class Main {

    public static void main(String[] args) {
        List<Car> carList = new ArrayList<>();
        // gosNumber,  power,  productionYear,  loadCapacity,  passengerSeats
        carList.add(new Car("toyota tundra", 5170000, 250));
        carList.add(new Car("ford explorer", 5170000, 240));
        carList.add(new Car("uaz hunter", 3170000, 110));
        carList.add(new Car("kia sportage", 3700000, 200));
        carList.add(new Car("lada granta", 980000, 90));

        //3. Создать список автомобилей, заполнить его автомобилями с произвольными данными.
        System.out.println("\n3. Создать список автомобилей, заполнить его автомобилями с произвольными данными");
        System.out.println("4. Вывести данные об автомобилях на экран");
        Consumer<Car> print = System.out::println; //завернем метод println в интерфейс Consumer
        carList.forEach(print);

        System.out.println("5. Сделать выборку автомобилей ценой более 1000000 руб. Вывести данные на экран");
        carList.stream()
                .filter(c -> c.getCost() > 1000_000)
                .forEach(print);
        System.out.println();

        System.out.println("""
                 6. Отсортировать список автомобилей по цене и мощности (автомобили с одной ценой сортировались по мощности).
                    Вывести результат на экран.
                """);
        carList.stream()
//                .sorted()//use native comparator which is defined in class Car
                .sorted(new Comparator<Car>() { //let`s use anonymous class
                    @Override
                    public int compare(Car o1, Car o2) {
                        if (o1.getCost() == o2.getCost())
                            return o1.getPower() - o2.getPower();
                        return o1.getCost() - o2.getCost();
                    }
                })
                .forEach(print);

        System.out.println();
        System.out.println("7. Найти автомобиль с указанной стоимостью");
        int wantedCostMin = 1000_000, wantedCostMax = 4000_000;
        System.out.println("Пусть стоимость будет между " + wantedCostMin + " и " + wantedCostMax);
        Predicate<Car> carPredicate = (a) -> (wantedCostMin <= a.getCost() && a.getCost() <= wantedCostMax);
        carList.stream()
                .filter(carPredicate)
                .forEach(print);


//        SearchableI carSearch = new SearchableI() {
//            @Override
//            public Car searchByCost(List<Car> list, int minCost, int maxCost) {
//                return list.stream()
//                        .filter(a -> (minCost <= a.getCost() && a.getCost() <= maxCost))
//                        .findFirst()
//                        .get();
//            }
//        };

        System.out.println("\nAnother method. Using of functional interface and reference to method");
        SearchableI carWithNeededCost = Main::searchByCost;
        carWithNeededCost.searchByCost(carList,3_000_000, 4_000_000)
                .forEach(System.out::println);

        System.out.println();
        System.out.println(" 8. Случилась инфляция, необходимо увеличить стоимость всех авто на 20%");
        System.out.println("list of cars before price rising");
        carList.forEach(print);
        carList.forEach(c -> c.changeCost(20));
        System.out.println("\nlist of cars after price rising");
        carList.forEach(print);

        System.out.println("\n 9. Случилось снижение инфляции, необходимо снизить стоимость всех авто на 20%");
        System.out.println("Cost of car before rising price by 20% and price after decreasing by 20% is not equal! \n" +
                "Because in second case the base from which the percentage is calculated is higher!");
        carList.forEach(c -> c.changeCost(-20));
        System.out.println("\nlist of cars after price falling");
        carList.forEach(print);

    }


    public static List<Car> searchByCost(List<Car> list, int minCost, int maxCost) {
        return list.stream()
                .filter(a -> (minCost <= a.getCost() && a.getCost() <= maxCost))
                .toList();
    }

}
