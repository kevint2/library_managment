package org.example.daos;

import org.example.entity.Author;
import org.example.exception.GenericException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

public class AuthorDao extends GenericDao<Author,Long>{
    private Session session;

    public AuthorDao(Session session){
        super(session , Author.class);
        this.session=session;
    }
    public Author create(Author author){
        if (author.getId()!=null){
          throw   GenericException.idNotNull();
        }else {
            return super.create(author);
        }

    }
    public Author update(Author author){
        if (author.getId() == null ){
            throw GenericException.idShouldNotBeNull();
        }else {
            return super.update(author);
        }
    }
    public void deleteById(Long id){
        Author author = super.findById(id);
        if (author!=null){
            super.delete(author);
        }
    }


}
