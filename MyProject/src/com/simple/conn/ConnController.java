package com.simple.conn;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/say")
public class ConnController {
    @RequestMapping("/test")
    public String test(Model model){
        model.addAttribute("name","neo4j");
        model.addAttribute("str","aaa");
        return "test";
    }
    @RequestMapping("/sel")
    public String show(Model model){
        DAO dd=new DAO();
        model.addAttribute("jsonstr",dd.QueryAll());
        return "sel";
    }

    @RequestMapping("/mdf")
    public String modify(Model model){
        model.addAttribute("message","No Result!");
        return "mdf";
    }

    @RequestMapping("/create")
    public String create(Model model){
        DAO dd=new DAO();
        model.addAttribute("message",dd.CreateOne());
        return "mdf";
    }

    @RequestMapping("/delete")
    public String delete(Model model){
        DAO dd=new DAO();
        model.addAttribute("message",dd.DeleteOne());
        return "mdf";
    }

    @RequestMapping("/update")
    public String update(Model model){
        DAO dd=new DAO();
        model.addAttribute("message",dd.UpdateOne());
        return "mdf";
    }

}
