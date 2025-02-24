package api;

import com.google.gson.Gson;
import dto.NicDto;
import entity.NicEntity;
import service.NicService;
import util.exception.CustomException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet(urlPatterns = "/nic")
public class NicApi extends HttpServlet {
    private NicService nicService;

    public NicApi(){
        this.nicService = new NicService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getHeader("Content-Type").equals("application/json")){
            BufferedReader reader = req.getReader();
            NicDto nicDto = new Gson().fromJson(reader, NicDto.class);
            try {
                nicService.saveNic(nicDto);
                resp.setStatus(HttpServletResponse.SC_CREATED);
                resp.getWriter().write("recived");
            } catch (CustomException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
