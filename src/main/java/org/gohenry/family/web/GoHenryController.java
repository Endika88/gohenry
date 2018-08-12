package org.gohenry.family.web;


import org.gohenry.family.entities.Parent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GoHenryController {


    @GetMapping("parent/{id}")
    private Parent getParent(@PathVariable Integer id){
        return null;
    }
}
