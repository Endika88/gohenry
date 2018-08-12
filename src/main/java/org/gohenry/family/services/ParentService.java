package org.gohenry.family.services;


import org.gohenry.family.entities.Parent;
import org.gohenry.family.exceptions.ParentNotFoundExcepction;
import org.gohenry.family.repo.ParentRepository;
import org.springframework.stereotype.Service;


@Service
public class ParentService {


    private ParentRepository repository;

    public ParentService(ParentRepository repository) {

        this.repository = repository;
    }

    public Parent getParentDetails(Integer id) {

        Parent parent = repository.findById(id);

        if(parent==null){
            throw new ParentNotFoundExcepction();
        }
        return parent;
    }

    public Parent createParent(Parent parent)
    {
        Parent savedParent = repository.save(parent);
        return savedParent;
    }


}
