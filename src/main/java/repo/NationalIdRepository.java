package repo;

import entity.NationalIdentityCard;
import org.hibernate.Session;

public class NationalIdRepository {
    public NationalIdentityCard save(Session session, NationalIdentityCard nic) {
        session.persist(nic);
        return nic;
    }
}
