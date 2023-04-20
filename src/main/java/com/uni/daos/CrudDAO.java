package com.uni.daos;

import java.util.List;

public interface CrudDAO<Entity> {

    //Create
    Entity save(Entity entity, String devId);

    //Read
    List<Entity> findAll(String devId);

    //Update
    void update(Entity entity, String devId);

    //Delete



}
