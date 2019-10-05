package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
        );
        System.out.println("getFilteredWithExceeded\n" + getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000)+"\n");
        System.out.println("getFilteredWithExceededOn\n" + getFilteredWithExceededOn(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000)+"\n");
        System.out.println("getFilteredWithExceededStreamOptional\n" + getFilteredWithExceededStreamOptional(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000)+"\n");
        System.out.println("getFilteredWithExceededStreamOptional2\n" + getFilteredWithExceededStreamOptional2(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000)+"\n");
    }

    public static List<UserMealWithExceed> getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, List<UserMeal>> dayMealMap = new HashMap<>();
        for (UserMeal meal : mealList) {
            LocalDate localDate = meal.getDateTime().toLocalDate();
            List<UserMeal> userMealListDialy = dayMealMap.getOrDefault(localDate, new ArrayList<UserMeal>());
            userMealListDialy.add(meal);
            dayMealMap.put(localDate, userMealListDialy);
        }

        List<UserMealWithExceed> userMealWithExceedList = new ArrayList<>();
        for (Map.Entry<LocalDate, List<UserMeal>> dayMealEntry : dayMealMap.entrySet()) {
            List<UserMeal> userMealListDialy = dayMealEntry.getValue();
            for (UserMeal userMeal : userMealListDialy) {
                LocalTime lt = userMeal.getDateTime().toLocalTime();
                if (TimeUtil.isBetween(lt, startTime, endTime)) {
                    boolean exceed = caloriesCouter(userMealListDialy) > caloriesPerDay;
                    userMealWithExceedList.add(newUserMealWithExceed(userMeal, exceed));
                }
            }
        }
        return userMealWithExceedList;
    }

    private static int caloriesCouter(List<UserMeal> userMealListDialy) {
        int count = 0;
        for (UserMeal meal : userMealListDialy) {
            count += meal.getCalories();
        }
        return count;
    }

    private static UserMealWithExceed newUserMealWithExceed(UserMeal userMeal, boolean exceed){
        return new UserMealWithExceed(userMeal.getDateTime(),userMeal.getDescription(),userMeal.getCalories(),exceed);
    }

    public static List<UserMealWithExceed> getFilteredWithExceededOn(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> dayCaloriesMap = new HashMap<>();
        for (UserMeal meal : mealList) {
            LocalDate localDate = meal.getDateTime().toLocalDate();
            int caloriesSum = dayCaloriesMap.getOrDefault(localDate, 0) + meal.getCalories();
            dayCaloriesMap.put(localDate, caloriesSum);
        }

        List<UserMealWithExceed> userMealWithExceedList = new ArrayList<>();
        for (UserMeal meal : mealList) {
            LocalTime lt = meal.getDateTime().toLocalTime();
            LocalDate localDate = meal.getDateTime().toLocalDate();
            if (TimeUtil.isBetween(lt, startTime, endTime)) {
                boolean exceed = dayCaloriesMap.get(localDate) > caloriesPerDay;
                userMealWithExceedList.add(newUserMealWithExceed(meal, exceed));
            }
        }
        return userMealWithExceedList;
    }


    public static List<UserMealWithExceed> getFilteredWithExceededStreamOptional(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> dayCaloriesMap = new HashMap<>();
        mealList
                .forEach(m -> {
                    LocalDate ld = m.getDateTime().toLocalDate();
                    dayCaloriesMap.merge(ld, m.getCalories(), Integer::sum);
                });

        List<UserMealWithExceed> userMealWithExceedList = new ArrayList<>();
        mealList.stream()
                .filter(m -> TimeUtil.isBetween(m.getDateTime().toLocalTime(), startTime, endTime))
                .forEach(m -> {
                    LocalDate ld = m.getDateTime().toLocalDate();
                    boolean exceed = dayCaloriesMap.getOrDefault(ld, 0) > caloriesPerDay;
                    userMealWithExceedList.add(newUserMealWithExceed(m, exceed));
                });
        return userMealWithExceedList;
    }


    public static List<UserMealWithExceed> getFilteredWithExceededStreamOptional2(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {


        Map<LocalDate, Integer> dayCaloriesMap = mealList.stream()
                .collect(Collectors.groupingBy(m -> m.getDateTime().toLocalDate(),
                        Collectors.summingInt(UserMeal::getCalories)
                        )
                );


        List<UserMealWithExceed> userMealWithExceedList = mealList.stream()
                .filter(m -> TimeUtil.isBetween(m.getDateTime().toLocalTime(), startTime, endTime))
                .map(m-> newUserMealWithExceed(m,dayCaloriesMap.get(m.getDateTime().toLocalDate())>caloriesPerDay))
                .collect(Collectors.toList());

        return userMealWithExceedList;
    }


}
