package servlet;

import freemarker.TemplateProvider;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = {"/infoShareAcademy"})
public class HomeworkServlet extends HttpServlet {

    private static final String TEMPLATE_NAME = "homework";
    @Inject
    private TemplateProvider templateProvider;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("charset=UTF-8");
        PrintWriter writer = resp.getWriter();

        Map<String, Object> model = new HashMap<>();
        model.put("student", "Waldek Wódczak\njjdd5-Zajavka \n " + LocalDateTime.now());
        Template template = templateProvider.getTemplate(getServletContext(), TEMPLATE_NAME);

        try {
            template.process(model, resp.getWriter());
        } catch (TemplateException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("charset=UTF-8");
        PrintWriter param = resp.getWriter();


        List<String> paramList = Collections.list(req.getParameterNames());
        for (String name : paramList) {
            String[] values = req.getParameterValues(name);
            for (int i = 0; i < values.length; i++) {
                param.println(name +" - "+ values[i]);

            }

        }


    }
}
