package beansNegocio;

import java.util.List;
import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import beansPersistencia.TorneoPersistence;
import entidades.Torneo;

@Stateless
@LocalBean
@Asynchronous
public class TorneoBusiness {

	@EJB TorneoPersistence torneoPersistence;

    public TorneoBusiness() {}

    public boolean save(Torneo t) {
    	return torneoPersistence.save(t);
    }

    public boolean remove(int id) {
    	return torneoPersistence.remove(id);
    }

    public Torneo getTorneo(int id) {
    	return torneoPersistence.getTorneo(id);
    }

    public List<Torneo> getAllTorneos() {
    	return torneoPersistence.getAllTorneos();
    }

}
