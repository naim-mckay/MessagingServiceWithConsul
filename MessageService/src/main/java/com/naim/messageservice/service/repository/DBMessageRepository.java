package com.naim.messageservice.service.repository;

import com.naim.messageservice.model.Message;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DBMessageRepository extends CrudRepository<Message,String> {

}