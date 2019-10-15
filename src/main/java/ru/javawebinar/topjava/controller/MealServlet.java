package ru.javawebinar.topjava.controller;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.service.impl.MealServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static org.slf4j.LoggerFactory.getLogger;
import static ru.javawebinar.topjava.util.MealsUtil.*;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);

    private MealService mealService = new MealServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action!=null && !action.isEmpty()) {
            switch (action) {
                case "delete":
                    Long mealId = Long.parseLong(request.getParameter("mealId"));
                    mealService.delete(mealId);
                    break;
            }
        }


        List<MealTo> mealToList = getFiltered(getMeals(), LocalTime.MIN, LocalTime.MAX, getDefaultCaloriesPerDay());
        request.setAttribute("mealToList", mealToList);

        log.debug("forward to meal.jsp");
        request.getRequestDispatcher("/meals.jsp").forward(request, response);
    }

}
