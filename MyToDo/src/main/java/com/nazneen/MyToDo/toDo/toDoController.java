package com.nazneen.MyToDo.toDo;

import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
public class toDoController {

    private final toDoService toDoServices;

    public toDoController(toDoService toDoServices) {
        this.toDoServices = toDoServices;
    }

    private String getLoggedInUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    @RequestMapping("list-todos")
    public String listAllToDoS(ModelMap model){
        String username = getLoggedInUsername();
        List<toDo> toDos = toDoServices.findByUsername(username);
        model.addAttribute("toDos", toDos);
        return "todo";
    }

    @RequestMapping(value = "add-todos", method = RequestMethod.GET)
    public String showNewTodoPage(ModelMap model) {
        String username = getLoggedInUsername();
        toDo todo = new toDo(0, username, "", "", LocalDate.now().plusMonths(1), false);
        model.put("toDo", todo);
        return "todo-functions";
    }

    @RequestMapping(value = "add-todos", method = RequestMethod.POST)
    public String addToDoS(@Valid @ModelAttribute("toDo") toDo toDo,
                           BindingResult result,
                           ModelMap model){
        if (result.hasErrors()){
            return "todo-functions";
        }

        String username = getLoggedInUsername();
        toDoService.AddToDos(username, toDo.getTaskName(), toDo.getTaskDescription(), toDo.getDueDate(), false);
        return "redirect:list-todos";
    }

    @RequestMapping("delete-todo")
    public String deleteToDoS(@RequestParam int id){
        toDoService.deleteTodoById(id);
        return "redirect:list-todos";
    }

    @RequestMapping(value="update-todo", method = RequestMethod.GET)
    public String showUpdateTodoPage(@RequestParam int id, ModelMap model) {
        toDo todo = toDoService.findById(id);
        model.addAttribute("toDo", todo);
        return "todo-functions";
    }

    @RequestMapping(value = "update-todo", method = RequestMethod.POST)
    public String updateToDoS(@Valid @ModelAttribute("toDo") toDo toDo,
                              BindingResult result){
        if (result.hasErrors()){
            return "todo-functions";
        }

        String username = getLoggedInUsername();
        toDo.setUserName(username);
        toDoService.updateToDo(toDo);
        return "redirect:list-todos";
    }
}
