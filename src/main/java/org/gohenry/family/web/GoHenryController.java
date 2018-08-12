package org.gohenry.family.web;


import org.gohenry.family.entities.Children;
import org.gohenry.family.entities.Parent;
import org.gohenry.family.exceptions.ChildrenNotFoundExcepction;
import org.gohenry.family.exceptions.ParentNotFoundExcepction;
import org.gohenry.family.services.ChildrenService;
import org.gohenry.family.services.ParentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class GoHenryController {


    @Autowired
    private ParentService parentService;

    @Autowired
    private ChildrenService childrenService;

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

    @PutMapping("/children/{id}")
    public Children updateChildren(@PathVariable String id, @RequestBody Children children){
        int childrenId = Integer.parseInt(id);
        Children dbChildren = childrenService.getChildrenDetails(childrenId);
        dbChildren.setName(children.getName());
        dbChildren.setAge(children.getAge());
        dbChildren.setSurname(children.getSurname());
        return childrenService.createChildren(dbChildren);

    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private void parentNotFoundHandler (ParentNotFoundExcepction ex){}

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private void childrenNotFoundHandler (ChildrenNotFoundExcepction ex){}
}
