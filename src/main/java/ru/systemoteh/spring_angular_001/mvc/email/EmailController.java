package ru.systemoteh.spring_angular_001.mvc.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@Controller
public class EmailController {

    @Autowired
    EmailService emailService;

    @RequestMapping(value = "/email/send", method = RequestMethod.POST)
    public ModelAndView email(@ModelAttribute("emailModel") EmailModel emailModel) {
        System.out.println("EmailController email is called");
        Map<String, Object> model = new HashMap<>();
        model.put(emailService.FROM, "systemoteh.ru@gmail.com");
        model.put(emailService.SUBJECT, "Hello from " + emailModel.getName() + "!");
        model.put(emailService.TO, emailModel.getEmail());
        model.put(emailService.CCC_LIST, new ArrayList<>());
        model.put(emailService.BCC_LIST, new ArrayList<>());
        model.put("userName", "JavaUser");
        model.put("urlsystemoteh", "systemoteh.ru@gmail.com");
        model.put("message", emailModel.getMessage());
        boolean result = emailService.sendEmail("registered.vm", model);
        //use redirect or you will send email after every refresh page.
        return new ModelAndView("redirect:/email.html", "resultSending", result);
    }

}