package service;

import dto.CommonDTO;
import entity.Licence;
import entity.NicEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repo.LicenceRepository;
import repo.NicRepo;
import util.FactoryConfiguration;

public class CommonService {

    LicenceRepository licenceRepository;
    NicRepo nic;

    public CommonService() {
        this.licenceRepository = new LicenceRepository();
        this.nic = new NicRepo();

    }

    public void saveCommonDetails(CommonDTO commonDTO) {
        Licence licence = new Licence();

        licence.setNumber(commonDTO.getLicenceNumber());
        licence.setBloodGroup(commonDTO.getBloodGroup());

        NicEntity nicEntity = new NicEntity();
        nicEntity.setNumber(commonDTO.getNicNumber());
        nicEntity.setName(commonDTO.getName());
        nicEntity.setAddress(commonDTO.getAddress());

        licence.setNic(nicEntity);
        //nationalIdentityCard.setLicence(licence);

        Session session =null;
        Transaction transaction = null;

        try{
            session = FactoryConfiguration.getInstance().getSession();
            transaction = session.beginTransaction();
            //NationalIdentityCard save = nic.save(session, nationalIdentityCard);
            Licence savedLicence = licenceRepository.save(session,licence);
            if(savedLicence != null){
                transaction.commit();
            }
        }catch (Exception ex){
            if(transaction != null) transaction.rollback();
            ex.printStackTrace();
        }finally {
            if(session != null && session.isOpen()) session.close();
        }



    }
}
