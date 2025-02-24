package repo;

import entity.NicEntity;
import org.hibernate.Session;

public class NicRepo {
    public NicEntity save(Session session, NicEntity nic) {
        session.persist(nic);
        return nic;
    }
}
