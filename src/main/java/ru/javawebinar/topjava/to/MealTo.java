package ru.javawebinar.topjava.to;

import java.time.LocalDateTime;

public class MealTo {
    private final Integer id;

    private final LocalDateTime dateTime;

    private final String description;

    private final int calories;

    private final boolean excess;


    public MealTo(Integer id, LocalDateTime dateTime, String description, int calories, boolean excess) {
        this.id = id;
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.excess = excess;
    }



    public Integer getId() {
        return id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public boolean isExcess() {
        return excess;
    }

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
