package api;

import com.google.gson.Gson;
import dto.StudentDTO;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repo.StudentEntity;
import util.FactoryConfiguration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet(urlPatterns = "/student")
public class StudentAPI extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getHeader("Content-Type").equals("application/json")){
            BufferedReader reader = req.getReader();
            StudentDTO studentDTO = new Gson().fromJson(reader, StudentDTO.class);

            StudentEntity studentEntity = new StudentEntity();
            studentEntity.setId(studentDTO.getId());
            studentEntity.setName(studentDTO.getName());
            studentEntity.setAge((studentDTO.getAge()));
            studentEntity.setAddress(studentDTO.getAddress());

            try{
                Session session = FactoryConfiguration.getInstance().getSession();
                Transaction transaction = session.beginTransaction();
                session.save(studentEntity);
                transaction.commit();
                resp.getWriter().print("done");
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }
}
