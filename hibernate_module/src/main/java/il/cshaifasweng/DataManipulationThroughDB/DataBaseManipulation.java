package il.cshaifasweng.DataManipulationThroughDB;
import org.hibernate.Session;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
public class DataBaseManipulation<T> implements DAO<T>{
    static Session session;
    public void intiate(){
        session=factory.openSession();
    }
    @Override
    public T get(int id,Class<T> type) {
//       Session session=factory.;
       session.beginTransaction();
       T object=session.get(type,id);
        session.getTransaction().commit();
        return object;
    }
    @Override
    public T getLastAdded(Class<T> Type) {
//        Session session=factory.getCurrentSession();
        session.beginTransaction();
        String hql ="FROM "+Type.getName()+" e ORDER BY e.id DESC";
        TypedQuery<T> query = session.createQuery(hql, Type).setMaxResults(1);
        return query.getSingleResult();
    }
    @Override
    public List<T> getAll(Class<T> type) {
//        Session session=factory.getCurrentSession();
        session.beginTransaction();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(type);
        query.from(type);
        List<T> entities=session.createQuery(query).getResultList();
        session.getTransaction().commit();


        return entities;
    }

    @Override
    public void save(T t,Class<T> type) {
//        Session session=factory.getCurrentSession();
        session.beginTransaction();
        session.save(t);
        session.flush();
        session.getTransaction().commit();

    }

    @Override
    public void update(T t) {
//        Session session=factory.getCurrentSession();
        session.beginTransaction();
        session.update(t);
        session.flush();
        session.getTransaction().commit();

    }
    @Override
    public void delete(T t,Class<T> type) {
//        Session session=factory.getCurrentSession();
        session.beginTransaction();
        session.delete(t);
        session.flush();
        session.getTransaction().commit();

    }
}
