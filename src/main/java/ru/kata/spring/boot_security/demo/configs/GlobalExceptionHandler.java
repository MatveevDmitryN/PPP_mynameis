//package ru.kata.spring.boot_security.demo.configs;
//
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//
//@ControllerAdvice
//public class GlobalExceptionHandler {
//
//    // Этот метод будет вызываться при возникновении любого исключения в контроллерах
//    @ExceptionHandler(Exception.class)
//    public String handleException(Exception e, Model model) {
//        model.addAttribute("errorMessage", e.getMessage());  // Добавляем сообщение об ошибке в модель
//        return "error";  // Отображаем страницу ошибки
//    }
//}
