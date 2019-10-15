package ru.javawebinar.topjava.service.impl;


import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;


public class MealServiceImpl implements MealService {

    private static final Logger log = getLogger(MealServiceImpl.class);

    @Override
    public synchronized  void delete(Long mealId) {
        log.debug("delete " + mealId);
        List<Meal> mealList = MealsUtil.getMeals();
        mealList.removeIf(m->m.getId().equals(mealId));
        int a=0;
    }
}
