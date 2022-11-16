package ru.ripgor.crud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.ripgor.crud.model.User;
import ru.ripgor.crud.service.UserService;

@Controller
@RequestMapping("/users")
public class UsersController {

    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    // Метод для отображения списка пользователей
    @GetMapping()
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());

        return "users/index";
    }

    // Метод для отображения информации об отдельном пользователе.
    // Принимает id, на основе которого в представлении генерируется динамическая ссылка для каждого пользователя
    @GetMapping("/{id}")
    public String showUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.showUser(id));

        return "users/show";
    }

    // Метод для создания пользователя. Ссылка находится в index.html
    // Аннотация @ModelAttribute позволяет сократить код, ведь она берет на себя три действия:
    // -создание объекта
    // -инициализация значений в поля этого объекта из HTML-формы
    // -передача готового объекта в model
    @GetMapping("/new")
    public String getViewForCreateUsers(@ModelAttribute("user") User user) {

        return "users/new";
    }

    // Метод, который вызывается после newUser.
    // Перенаправляет на /users с добавлением нового пользователя в БД
    @PostMapping()
    public String createUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);

        return "redirect:/users";
    }

    // Метод обновления пользователя. Ссылка находится в show.html
    // Ссылка на представление генерируется динамически с пом-ю id
    // Передаем в model параметры отдельного человека для удобства изменений,
    // так мы будем видеть прошлые значения.

    // После работы метода вызывается метод update
    @GetMapping("/{id}/edit")
    public String getViewForEditUser(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userService.showUser(id));

        return "users/edit";
    }

    // Простое перенаправление с изменением БД, аналогичное методу create
    @PatchMapping("/{id}")
    public String updateUser(@ModelAttribute("user") User user, @PathVariable("id") int id) {
        userService.updateUser(id, user);

        return "redirect:/users";
    }

    // Ссылка на удаление находится в show.html
    // При нажатии происходит изменение БД и redirect на основную, уже измененную, страницу
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userService.deleteUser(id);

        return "redirect:/users";
    }

}