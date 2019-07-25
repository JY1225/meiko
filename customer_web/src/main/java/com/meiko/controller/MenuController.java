package com.meiko.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageInfo;
import com.meiko.domain.Menu;
import com.meiko.service.IMenuService;

@Controller
@RequestMapping("menu")
public class MenuController {
    @Autowired
    private IMenuService service;
    @RequestMapping("/findAll")
    public  String findAll(@RequestParam(name = "page",required = true,defaultValue = "1")Integer page,
                           @RequestParam(name = "pageSize",required = true,defaultValue = "4")Integer pageSize,
                           Model model)
    {
        List<Menu> menuList= service.findAll(page,pageSize);
        PageInfo<Menu> pageInfo=new PageInfo<Menu>(menuList);
        model.addAttribute("pageInfo",pageInfo);
        return "menu-list";
    }
    @RequestMapping("/save")
    public String save(Menu menu){
        service.save(menu);
        return "redirect:/menu/findAll";
    }
   /* @RequestMapping("/findOneById")
    public ModelAndView findOneById(String id){
        ModelAndView modelAndView=new ModelAndView();
       Menu menu= service.findOneById(id);
       modelAndView.addObject("menu",menu);
       modelAndView.setViewName("menu-show");
        return modelAndView;
    }*/
   /* @RequestMapping("/savemenuRoles")
    public String saveRoles(String id){
        return "menu-add"
    }*/
}
