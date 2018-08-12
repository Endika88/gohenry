package org.gohenry.family.web;


import org.gohenry.family.entities.Parent;
import org.gohenry.family.exceptions.ParentNotFoundExcepction;
import org.gohenry.family.services.ParentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class GoHenryController {


    @Autowired
    private ParentService parentService;

    @GetMapping("parent/{id}")
    private Parent getParent(@PathVariable Integer id){
        return parentService.getParentDetails(id);
    }

    @RequestMapping (value = "/parents", method = RequestMethod.POST)
    public ResponseEntity createParent(@RequestBody Parent parent){
        Parent parentResponse =  parentService.createParent(parent);
        if(parentResponse!=null){
            return new ResponseEntity<Parent>(parentResponse,HttpStatus.CREATED);
        }else{
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }



    }

    @PutMapping("/parents/{id}")
    public Parent update(@PathVariable String id, @RequestBody Parent parent){
        int parentId = Integer.parseInt(id);
        Parent dbParent = parentService.getParentDetails(parentId);
        dbParent.setName(parent.getName());
        dbParent.setAge(parent.getAge());
        dbParent.setSurname(parent.getSurname());
        return parentService.createParent(dbParent);

    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private void parentNotFoundHandler (ParentNotFoundExcepction ex){}
}
