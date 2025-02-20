package service;

import dto.CommonDTO;
import entity.Licence;
import entity.NationalIdentityCard;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repo.LicenceRepository;
import util.FactoryConfiguration;

public class CommonService {

    private static LicenceRepository licenceRepository;
    public CommonService() {
        this.licenceRepository = new LicenceRepository();
    }

    public static void saveCommonDetails(CommonDTO commonDTO) {
        Licence licence = new Licence();
        licence.setBloodGroup(commonDTO.getBloodGroup());

        NationalIdentityCard nationalIdentityCard = new NationalIdentityCard();
        nationalIdentityCard.setNumber(commonDTO.getNicNumber());
        nationalIdentityCard.setName(commonDTO.getName());
        nationalIdentityCard.setAddress(commonDTO.getAddress());

        nationalIdentityCard.setNumber(commonDTO.getLicenceNumber());
        licence.setNic(nationalIdentityCard);


        Session session =null;
        Transaction transaction = null;

        try{
            session = FactoryConfiguration.getInstance().getSession();
            transaction = session.beginTransaction();
            Licence save = licenceRepository.save(session, licence);
        }catch (Exception ex){
            if(transaction != null) transaction.rollback();
            ex.printStackTrace();
        }finally {
            if(session != null && session.isOpen()) session.close();
        }



    }
}
