package ru.javawebinar.topjava.model;

import java.time.LocalDateTime;

public class MealTo {
    private final LocalDateTime dateTime;

    private final String description;

    private final int calories;

//    private final Supplier<Boolean> excess;
//    private final AtomicBoolean excess;
    private final boolean excess;


    public MealTo(LocalDateTime dateTime, String description, int calories, boolean excess) {
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.excess = excess;
    }



//    public Boolean getExcess() {
//        return excess.get();
//    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UserMealWithExceed{");
        sb.append("dateTime=").append(dateTime);
        sb.append(", description='").append(description).append('\'');
        sb.append(", calories=").append(calories);
        sb.append(", exceed=").append(excess);
        sb.append('}');
        return sb.toString();
    }
}
