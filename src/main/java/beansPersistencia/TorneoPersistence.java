package beansPersistencia;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Asynchronous;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import entidades.Torneo;

@Stateless
@LocalBean
@Asynchronous
public class TorneoPersistence {

	@PersistenceContext
	EntityManager em;

	@Resource
	UserTransaction utx;

    public TorneoPersistence() {}

    public boolean save(Torneo t) {
    	try {
			try {
				utx.begin();
				em.persist(t);
				utx.commit();
			} finally {
				em.close();
			}
			return true;
		} catch (Exception e) {
			try {
				utx.rollback();
			} catch (IllegalStateException e1) {
				e1.printStackTrace();
			} catch (SecurityException e1) {
				e1.printStackTrace();
			} catch (SystemException e1) {
				e1.printStackTrace();
			}
			return false;
		}
    }

    public boolean remove(int id) {
    	try {
			try {
				utx.begin();
				em.remove(em.find(Torneo.class, id));
				utx.commit();
			} finally {
				em.close();
			}
			return true;
		} catch (Exception e) {
			try {
				utx.rollback();
			} catch (IllegalStateException e1) {
				e1.printStackTrace();
			} catch (SecurityException e1) {
				e1.printStackTrace();
			} catch (SystemException e1) {
				e1.printStackTrace();
			}
			return false;
		}
    }

    public Torneo getTorneo(int id) {
    	return em.find(Torneo.class,id);
    }

    public List<Torneo> getAllTorneos() {
    	return em.createNamedQuery("Torneo.findAll", Torneo.class).getResultList();
    }

}
