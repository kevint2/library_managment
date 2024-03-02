package org.example.daos;

import org.example.entity.Book;
import org.example.exception.GenericException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class BookDao extends GenericDao<Book,Long>{
    private Session session ;

    public BookDao(Session session){
        super(session , Book.class);
        this.session =session;
    }
    public Book create(Book book){
        if (book.getId() != null){
            throw GenericException.idNotNull();
        }else {
          return   super.create(book);
        }
    }
    public Book update (Book book){
        if (book.getId() == null && findById(book.getId())==null){
            throw GenericException.idShouldNotBeNull();
        }else {
         return    super.update(book);
        }
    }
    public void delete(Long id){
        Book book =findById(id);
        if (book != null){
            super.delete(book);
        }
    }
    public List<Book>getBookId(String title){
        String query = "select book  from Book book where  book.title=:title";
        Query<Book>findByTitleQuery = session.createQuery(query, Book.class);
        findByTitleQuery.setParameter("title","%".concat(title).concat("%"));
        return findByTitleQuery.getResultList();
    }
    public List<Book>findByAuthor(String name){
        String query = "select author.books from Author as author where upper(concat(author.firstname,'',author.lastname)) = upper (:name )";
        Query<Book>findByAuthorQuery=session.createQuery(query, Book.class);
        findByAuthorQuery.setParameter("name","%".concat(name).concat("%"));
        return findByAuthorQuery.getResultList();
    }
}
