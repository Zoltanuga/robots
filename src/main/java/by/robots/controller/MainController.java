package by.robots.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/")
public class MainController {
    @RequestMapping(method = RequestMethod.GET)
    public String getIndexPage() {
        return "TaskManagerPage";
    }

}
