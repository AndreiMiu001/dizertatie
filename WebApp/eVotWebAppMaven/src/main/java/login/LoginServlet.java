/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import common.Category;
import common.ObjectToJson;
import common.Pair;
import common.UserBean;
import insert.InsertDataImpl;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Andrei
 */
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UserBean user = new UserBean(username, password);
        LoginImpl userLogin = new LoginImpl();
        if (userLogin.checkIfUserExists(user)) {
            session.setAttribute("user", user);
            ArrayList<Pair<Integer, String>> countyArr = userLogin.getCounties();
            ArrayList<Pair<Integer, String>> cityArr = userLogin.getCities();
            session.setAttribute("countyArr", countyArr);
            session.setAttribute("cityArr", cityArr);
            ObjectToJson<ArrayList<Pair<Integer, String>>> converter = new ObjectToJson<>();
            String countyJson = converter.convert(countyArr);
            String cityJson = converter.convert(cityArr);
            session.setAttribute("countyJson", countyJson);
            session.setAttribute("cityJson", cityJson);
            InsertDataImpl insertDataImpl = new InsertDataImpl();
            ArrayList<Category> electionCategoryArray = insertDataImpl.getElectionCategories();
            Collections.reverse(electionCategoryArray);
            session.setAttribute("listCategory", electionCategoryArray);
            request.getRequestDispatcher("/mainPage.jsp").forward(request, response);
        } else {
            request.setAttribute("response", "Username or password are invalid ! Please try again.");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }
}
