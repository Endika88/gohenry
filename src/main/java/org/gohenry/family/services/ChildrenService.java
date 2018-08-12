package org.gohenry.family.services;



import org.gohenry.family.entities.Children;
import org.gohenry.family.exceptions.ChildrenNotFoundExcepction;
import org.gohenry.family.repo.ChildrenRepository;

import org.springframework.stereotype.Service;


@Service
public class ChildrenService {


    private ChildrenRepository repository;

    public ChildrenService(ChildrenRepository repository) {

        this.repository = repository;
    }


    public Children getChildrenDetails(Integer id) {

        Children children = repository.findById(id);

        if(children==null){
            throw new ChildrenNotFoundExcepction();
        }
        return children;
    }

    public Children createChildren(Children children)
    {
        Children savedChildren = repository.save(children);
        return savedChildren;
    }
}
