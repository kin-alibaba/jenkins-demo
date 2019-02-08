package com.example.kin.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.ArrayList;

@Controller
public class DemoController {

    @Autowired
    private ItemRepository repository;

    @RequestMapping("/")
    public String index(Model model) {
        ArrayList<Item> List = (ArrayList<Item>) repository.findAll();
        model.addAttribute("newitem", new Item());
        model.addAttribute("items", new ListViewModel(List));
        return "index";
    }

    @RequestMapping("/add")
    public String Todo(@ModelAttribute Item requestItem) {
        Item item = new Item(requestItem.getCategory(), requestItem.getName());
        repository.save(item);
        return "redirect:/";
    }

    @RequestMapping("/update")
    public String updateitem(@ModelAttribute ListViewModel requestItems) {
        for (Item requestItem : requestItems.getList() ) {
             Item item = new Item(requestItem.getCategory(), requestItem.getName());
             item.setComplete(requestItem.isComplete());
             item.setId(requestItem.getId());
             repository.save(item);
        }
        return "redirect:/";
    }
}
