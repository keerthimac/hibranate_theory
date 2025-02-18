package repo;

import entity.StudentEntity;
import org.hibernate.Session;

import java.io.Serializable;

public class StudentRepository {

    public StudentEntity save(StudentEntity studentEntity, Session session){
        Serializable save = session.save(studentEntity);
        if(save != null){
            int id = (int) save;
            studentEntity.setId(id);
            return studentEntity;
        }
        return null;
    }
}
