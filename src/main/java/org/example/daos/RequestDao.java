package org.example.daos;

import org.example.entity.Request;
import org.example.exception.GenericException;
import org.example.static_data.Role;
import org.example.static_data.Status;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.time.LocalDate;
import java.util.List;

public class RequestDao extends GenericDao<Request, Long> {
    private Session session;

    public RequestDao(Session session) {
        super(session, Request.class);
        this.session = session;
    }

    public Request create(Request request) {
        if (request.getId() != null) {
            throw GenericException.idNotNull();
        } else {
            request.setStatus(Status.CREATED);
            request.setCreatetAt(LocalDate.now());
            request.setReturnedAt(LocalDate.now().plusDays(30));
            request.getBooks().forEach(book -> {
                        if (book.getQuantity() == 0) {
                            throw GenericException.bookNotAvailable(book.getTitle());
                        } else {
                            book.setQuantity(book.getQuantity() - 1);
                            session.merge(book);
                        }
                    }
            );
            return super.create(request);
        }
    }

    public List<Request> getAllRequestByUsername(String username) {
        String query = "select request from Request request where request.user.username =:username and user.role=:role ";
        Query<Request> getAllRequestByUsernameQuery = session.createQuery(query, Request.class);
        getAllRequestByUsernameQuery.setParameter("username", username);
        getAllRequestByUsernameQuery.setParameter("role", Role.CLIENT);
        return getAllRequestByUsernameQuery.getResultList();
    }

    public String closeRequest(Long id) {
        Request request = findById(id);
        if (request != null) {
            request.setStatus(Status.COMPLETED);
            request.setReturnedAt(LocalDate.now());
            request.getBooks().forEach(book -> {
                book.setQuantity(book.getQuantity() + 1);
                session.merge(book);

            });
            super.update(request);
            return "Request has been completed";
        }else {
            throw GenericException.requestIdDoesNotExists(id);
        }
    }
    public String cancelRequest(Long id) {
        Request request = findById(id);
        if (request != null) {
            request.setStatus(Status.CANCELLED);
            request.getBooks().forEach(book -> {
                book.setQuantity(book.getQuantity() + 1);
                session.merge(book);

            });
            super.update(request);
            return "Request has been cancelled";
        }else {
            return "Request not found";
        }
    }
}
