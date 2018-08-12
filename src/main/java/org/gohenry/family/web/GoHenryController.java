package org.gohenry.family.web;


import org.gohenry.family.entities.Parent;
import org.gohenry.family.services.ParentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GoHenryController {


    @Autowired
    private ParentService parentService;

    @GetMapping("parent/{id}")
    private Parent getParent(@PathVariable Integer id){
        return parentService.getParentDetails(id);
    }
}
