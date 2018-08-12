package org.gohenry.family.web;


import org.gohenry.family.entities.Parent;
import org.gohenry.family.exceptions.ParentNotFoundExcepction;
import org.gohenry.family.services.ParentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class GoHenryController {


    @Autowired
    private ParentService parentService;

    @GetMapping("parent/{id}")
    private Parent getParent(@PathVariable Integer id){
        return parentService.getParentDetails(id);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private void parentNotFoundHandler (ParentNotFoundExcepction ex){}
}
