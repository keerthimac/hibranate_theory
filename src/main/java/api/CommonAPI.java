package api;

import com.google.gson.Gson;
import dto.CommonDTO;
import service.CommonService;
import util.FactoryConfiguration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet(urlPatterns = "/common")
public class CommonAPI extends HttpServlet {
    private CommonService commonService;
    public CommonAPI() {
        this.commonService = new CommonService();
        FactoryConfiguration.getInstance();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getHeader("Content-Type").equals("application/json")){
            BufferedReader reader = req.getReader();
            CommonDTO commonDTO = new Gson().fromJson(reader, CommonDTO.class);
            commonService.saveCommonDetails(commonDTO);
            resp.getWriter().write("done");
        }
    }
}
