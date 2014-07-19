package daos;

import java.util.List;

import javax.persistence.EntityManager;

import models.BaseModel;

import org.hibernate.Criteria;
import org.hibernate.Session;

import play.db.jpa.JPA;

public class BaseModelDAO
{
	public static final BaseModelDAO INSTANCE = new BaseModelDAO();
	
	protected BaseModelDAO() { }
	
    /**
     * Save an entity. Throws an error if an entity with the given id already exists
     * @param entity
     * @return
     */
    public void persist(BaseModel model) {
        em().persist(model);
    }


    /**
     * If no entity with the given id exists in the DB, a new entity is stored. If there is already
     * an entity with the given id, the entity is updated
     * @param entity
     * @param <T>
     * @return
     */
    public <T extends BaseModel> T merge(T entity) {
        return em().merge(entity);
    }

    /**
     * Get an entity of the given type using the id
     * @param id
     * @param entityClazz
     * @param <T>
     * @return
     */
    public <T extends BaseModel> T findEntity(int id, Class<T> entityClazz) {
        return em().find(entityClazz, id);
    }

    /**
     * Get the entity manager
     * @return
     */
    protected EntityManager em() {
        return JPA.em();
    }
}
