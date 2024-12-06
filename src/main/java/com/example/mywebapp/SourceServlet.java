package main.java.com.example.mywebapp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;


@WebServlet("/source")
public class SourceServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String type = request.getParameter("type");
        String year = request.getParameter("year");

        if(type.equals("add")){
            String name = request.getParameter("name");
            String money2018 = request.getParameter("money2018");
            String source = request.getParameter("source");
            String money2019 = request.getParameter("money2019");
            String money2020 = request.getParameter("money2020");
            String money2021 = request.getParameter("money2021");
            String money2022 = request.getParameter("money2022");
            String rank2018 = request.getParameter("rank2018");
            String rank2019 = request.getParameter("rank2019");
            String rank2020 = request.getParameter("rank2020");
            String rank2021 = request.getParameter("rank2021");
            String rank2022 = request.getParameter("rank2022");
            String chineseName = request.getParameter("chineseName");
            String country = request.getParameter("country");
            String food = request.getParameter("food");
            Random random = new Random();
            int pid = random.nextInt(900000) + 100000;
            person person = new person();
            person.setPID(pid);
            person.setChineseName(chineseName);
            person.setCountry(country);
            person.setFood(food);
            person.setEnglishName(name);
            person.setSource(source);
            personDAO.savePerson(person);
            System.out.println(person.getPID());
            resultDAO.saveResult(new result(pid,Integer.parseInt(rank2018),money2018,2018));
            resultDAO.saveResult(new result(pid,Integer.parseInt(rank2019),money2019,2019));
            resultDAO.saveResult(new result(pid,Integer.parseInt(rank2020),money2020,2020));
            resultDAO.saveResult(new result(pid,Integer.parseInt(rank2021),money2021,2021));
            resultDAO.saveResult(new result(pid,Integer.parseInt(rank2022),money2022,2022));
            wholesourceDAO.saveSource(new wholesource(source,
                    Integer.parseInt(money2018),
                    Integer.parseInt(money2019),
                    Integer.parseInt(money2020),
                    Integer.parseInt(money2021),
                    Integer.parseInt(money2022)
            ));
            response.sendRedirect("source.jsp?year="+year);
        }else if(type.equals("update")){
//            String pid = request.getParameter("pid");
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String type = request.getParameter("type");
        if(type.equals("del")){
            String source = request.getParameter("source");
            String year = request.getParameter("year");
            wholesourceDAO.deleteSourceByYear(source,Integer.parseInt(year));

        }
        response.sendRedirect("source.jsp");
    }
}
