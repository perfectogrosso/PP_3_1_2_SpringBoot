package mvc.springbootapp2.dao;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import mvc.springbootapp2.model.User;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class UserDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public List<User> index() {
        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u", User.class);
        return query.getResultList();
    }

    @Transactional
    public User show(int id) {
        return entityManager.find(User.class, id);
    }

    @Transactional
    public void create(User user){
        entityManager.persist(user);
    }

    @Transactional
    public void update(int id, User updatedUser){
        User user = entityManager.find(User.class, id);
        user.setName(updatedUser.getName());
        user.setAge(updatedUser.getAge());
    }

    @Transactional
    public void delete(int id){
        entityManager.remove(entityManager.find(User.class, id));
    }
}
