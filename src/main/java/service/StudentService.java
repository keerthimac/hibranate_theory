package service;

import dto.StudentDTO;
import entity.StudentEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repo.StudentRepository;
import util.FactoryConfiguration;

public class StudentService {

    private StudentRepository studentRepository;

    public StudentService() {
        this.studentRepository = new StudentRepository();
    }

    public StudentDTO saveStudent(StudentDTO studentDTO){
        //in this layer we implement business logics in the application.
        // We do convert, validate, calculations or preparations of the data on the manageable java object handover from the api layer.
        // In this method we do convert the dto object into the entity object
        StudentEntity studentEntity = new StudentEntity();
        //studentEntity.setId(studentDTO.getId());
        studentEntity.setName(studentDTO.getName());
        studentEntity.setAge((studentDTO.getAge()));
        studentEntity.setAddress(studentDTO.getAddress());
        Transaction transaction = null;
        Session session = null;
        try{
            //Get the session from the FactoryConfiguration
            session = FactoryConfiguration.getInstance().getSession();
            //Beginning of Transaction
            transaction = session.beginTransaction();
            StudentEntity saved = studentRepository.save(studentEntity, session);
            if(saved != null){
                transaction.commit();
                studentDTO.setId(saved.getId());
                return studentDTO;
            }
        }catch (Exception ex){
            if(transaction != null) transaction.rollback();
            ex.printStackTrace();
        }finally {
            if(session != null) session.close();
        }
        return null;
    }

}
