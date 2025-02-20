package repo;

import entity.Licence;
import org.hibernate.Session;

public class LicenceRepository {
    public Licence save(Session session, Licence licence){
            session.persist(licence);
            return licence;
    }
}
