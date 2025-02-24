package service;

import dto.NicDto;
import entity.NicEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.modelmapper.ModelMapper;
import repo.NicRepo;
import util.FactoryConfiguration;
import util.exception.CustomException;

public class NicService {
    NicRepo nicRepo;

    public NicService(){
        this.nicRepo = new NicRepo();
    }

    public void saveNic(NicDto nicDto) throws CustomException {
        NicEntity mapedEntity = new ModelMapper().map(nicDto, NicEntity.class);
        Session session = null;
        Transaction transaction = null;

        try {
            session = FactoryConfiguration.getInstance().getSession();
            transaction = session.beginTransaction();
            NicEntity save = nicRepo.save(session, mapedEntity);
            transaction.commit();
            return;

        }catch (Exception ex) {
            if(transaction != null) transaction.rollback();
            ex.printStackTrace();
            throw new CustomException("Something Went Wrong", ex);
        }finally {
            if(session != null && session.isOpen()) session.close();
        }

    }
}
