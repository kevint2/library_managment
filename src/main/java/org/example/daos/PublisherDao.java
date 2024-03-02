package org.example.daos;

import org.example.entity.Publisher;
import org.example.exception.GenericException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class PublisherDao extends GenericDao<Publisher,Long>{
private Session session;
public PublisherDao(Session session){
    super(session , Publisher.class);
    this.session=session;
}
public Publisher create(Publisher publisher){
    if (publisher.getId()!=null){
        throw GenericException.idNotNull();
    }else {
       return super.create(publisher);
    }
}
public Publisher update(Publisher publisher){
    if (publisher.getId()==null && findById(publisher.getId())==null){
        throw GenericException.idShouldNotBeNull();
    }else {
       return super.update(publisher);
    }
}
public void delete(Long id){
    Publisher publisher = findById(id);
    if (publisher!=null){
        super.delete(publisher);
    }

}
}
