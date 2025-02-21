package api;

import com.google.gson.Gson;
import dto.StudentDTO;
import org.hibernate.Session;
import org.hibernate.Transaction;
import entity.StudentEntity;
import service.StudentService;
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
    private StudentService studentService;
    public StudentAPI(){
        //constructor through Dependency Injection - but inefficient way to do this.
        this.studentService = new StudentService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //This Api Layer do the catching the Http request and convert into manageable java object
        //This is the only responsibility that this layer has according to solid principles
        //Also do Exception handling
        if(req.getHeader("Content-Type").equals("application/json")){
            BufferedReader reader = req.getReader();
            StudentDTO studentDTO = new Gson().fromJson(reader, StudentDTO.class);
            StudentDTO savedStudent = studentService.saveStudent(studentDTO);
            if(savedStudent != null){
                resp.setStatus(HttpServletResponse.SC_CREATED);
                resp.getWriter().write(new Gson().toJson(savedStudent));
            }else{
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().write("Something went wrong");
            }
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getHeader("Content-Type").equals("application/json")){
            BufferedReader reader = req.getReader();
            StudentDTO studentDTO = new Gson().fromJson(reader, StudentDTO.class);
            StudentDTO updatedStudent = studentService.updateStudent(studentDTO);
            if(updatedStudent != null){
                resp.setStatus(HttpServletResponse.SC_ACCEPTED);
                resp.getWriter().write(new Gson().toJson(updatedStudent));
                resp.getWriter().flush();
            }else{
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().write("Something went wrong");
            }
        }
    }
}
