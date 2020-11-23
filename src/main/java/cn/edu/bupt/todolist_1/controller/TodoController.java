package cn.edu.bupt.todolist_1.controller;

import cn.edu.bupt.todolist_1.entities.Todo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/todo")
@SessionAttributes("todos")
public class TodoController {

    @GetMapping("/list")
    String list(Model model, @SessionAttribute(required = false) List<Todo> todos){
        if (todos == null) {
            todos = new ArrayList<Todo>();
        }
        model.addAttribute("todos",todos);
        return "todolist";

    }

    @GetMapping("/add")
    String addForm(){
        return "todoadd";
    }

    @PostMapping("/add")
    String add(Model model, @SessionAttribute List<Todo> todos, @RequestParam String content){
        Todo todo = new Todo();
        todo.setContent(content);
        todos.add(todo);
        model.addAttribute("todos",todos);
        return "success";
    }

    @GetMapping("/delete")
    String delete(Model model, @SessionAttribute List<Todo> todos, @RequestParam int todoId){
        todos.remove(todoId);
        model.addAttribute("todos",todos);
        return "success";
    }

    @GetMapping("/edit")
    String editForm(Model model, @SessionAttribute List<Todo> todos, @RequestParam int todoId){
        Todo todo = todos.get(todoId);
        model.addAttribute("todo",todo);
        model.addAttribute("todoId",todoId);
        return "todoedit";
    }

    @PostMapping("/edit")
    String edit(Model model, @SessionAttribute List<Todo> todos, @RequestParam String content,
                @RequestParam int todoId){
        todos.get(todoId).setContent(content);
        model.addAttribute("todos",todos);
        return "success";
    }
}
