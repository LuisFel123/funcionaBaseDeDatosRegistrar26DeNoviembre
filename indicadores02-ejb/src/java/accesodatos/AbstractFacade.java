/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package accesodatos;

import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author felip
 */
public abstract class AbstractFacade<T> {

    private Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }
    //metodo de frabricacion de un objeto EntitiManager
    protected abstract EntityManager getEntityManager();

    //insertar registros
    public void create(T entity) {
        getEntityManager().persist(entity);
    }
    //actualizar registros
    public void edit(T entity) {
        getEntityManager().merge(entity);
    }
    //eliminar un registro
    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }
    //recuperacion utilizando la llave primaria
    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }
    
    //recuperacion de todos los registros 
    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }
    //se encuentra por un rango
    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }
    //regresa el numero de registros
    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
    
}
