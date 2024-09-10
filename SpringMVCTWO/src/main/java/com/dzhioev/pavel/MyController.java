package com.dzhioev.pavel;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/")
public class MyController {
    @RequestMapping("/")
    public String showFirstView() {
        return "first-view";
    }

    @RequestMapping("/askDetails")
    public String askEmployeeDetails(Model model) {
        model.addAttribute("employee", new Employee());
        return "ask-emp-details-view";
    }


    @RequestMapping("/showDetails")
    public String showEmpDetails(@Valid @ModelAttribute("employee") Employee emp, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "ask-emp-details-view";
        } else {
            String name = emp.getName();
            emp.setName("Mr" + name);
            String surname = emp.getSurname();
            emp.setSurname(surname + "!");
            int salary = emp.getSalary();
            emp.setSalary(salary * 10);


            return "show-emp-details-view";
        }
    }
}
