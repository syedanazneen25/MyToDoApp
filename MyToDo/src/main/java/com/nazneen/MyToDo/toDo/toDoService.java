package com.nazneen.MyToDo.toDo;

import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;


@Service
public class toDoService {

    private static List<toDo> toDos = new ArrayList<>();
    private static int todosCount = 0;

    static {
        toDos.add(new toDo(
                ++todosCount,
                "Nazneen",
                "Finish Spring Boot ToDo App",
                "Create controller service repository and JSP views",
                LocalDate.now().plusMonths(1),
                false
        ));

        toDos.add(new toDo(
                ++todosCount,
                "Nazneen",
                "Fix JSP Rendering Issue",
                "Move JSP to webapp and configure view resolver",
                LocalDate.now().plusMonths(3),
                true
        ));

        toDos.add(new toDo(
                ++todosCount,
                "Nazneen",
                "Prepare Interview Notes",
                "Revise authentication authorization and Spring Security basics",
                LocalDate.now().plusMonths(4),
                false
        ));
    }

    public static toDo findById(int id) {
        Predicate<? super toDo> predicate = toDo -> toDo.getId()== id;
        toDo toDo = toDos.stream().filter(predicate).findFirst().get();
        return toDo;
    }

    public static void updateToDo(@Valid toDo toDo) {
        deleteTodoById(toDo.getId());
        toDos.add(toDo);
    }

    public List<toDo> findByUsername(String userName){
        Predicate<? super toDo> predicate = toDo -> toDo.getUserName().equalsIgnoreCase(userName);
        return toDos.stream().filter(predicate).toList();
    }

    public static void AddToDos(String userName, String taskName, String taskDescription, LocalDate dueDate, boolean done){
        toDo toDo = new toDo(++todosCount, userName, taskName, taskDescription, dueDate, done);
        toDos.add(toDo);

    }

    public static void deleteTodoById(int id) {
            Predicate<? super toDo> predicate = toDo -> toDo.getId()== id;
            toDos.removeIf(predicate);
    }
}
