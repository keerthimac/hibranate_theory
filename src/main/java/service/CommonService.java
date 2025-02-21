package service;

import dto.CommonDTO;
import entity.Licence;
import entity.NationalIdentityCard;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repo.LicenceRepository;
import repo.NationalIdRepository;
import util.FactoryConfiguration;

public class CommonService {

    LicenceRepository licenceRepository;
    NationalIdRepository nic;

    public CommonService() {
        this.licenceRepository = new LicenceRepository();
        this.nic = new NationalIdRepository();

    }

    public void saveCommonDetails(CommonDTO commonDTO) {
        Licence licence = new Licence();

        licence.setNumber(commonDTO.getLicenceNumber());
        licence.setBloodGroup(commonDTO.getBloodGroup());

        NationalIdentityCard nationalIdentityCard = new NationalIdentityCard();
        nationalIdentityCard.setNumber(commonDTO.getNicNumber());
        nationalIdentityCard.setName(commonDTO.getName());
        nationalIdentityCard.setAddress(commonDTO.getAddress());

        licence.setNic(nationalIdentityCard);
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
