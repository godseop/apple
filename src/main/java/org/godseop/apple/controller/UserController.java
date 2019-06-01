package org.godseop.apple.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/user")
public class UserController {

    @GetMapping(value="/list")
    public String viewUserListPage() {
        return "user/userList";
    }

}
