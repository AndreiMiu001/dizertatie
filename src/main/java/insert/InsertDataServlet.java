/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insert;

import common.Category;
import common.ObjectToJson;
import common.Pair;
import common.UserBean;
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
public class InsertDataServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute("user");
        if (user == null) {
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
        InsertDataImpl insertDataImpl = new InsertDataImpl();
        ArrayList<Category> electionCategoryArray = insertDataImpl.getElectionCategories();
        Collections.reverse(electionCategoryArray);
        request.setAttribute("listCategory", electionCategoryArray);
                ArrayList<Pair<Integer, String>> cityArr;
                ArrayList<Pair<Integer, String>> countyArr;

        Object cityObj = session.getAttribute("cityArr");
        Object countyObj = session.getAttribute("countyArr");
        if (cityObj != null) {
            cityArr = (ArrayList<Pair<Integer, String>>)cityObj;
        } else {
            cityArr = insertDataImpl.getCities();
        }
        if (countyObj != null) {
            countyArr = (ArrayList<Pair<Integer, String>>)countyObj;
        } else {
            countyArr = insertDataImpl.getCounties();
        }
        ObjectToJson<ArrayList<Pair<Integer, String>>> converter = new ObjectToJson<>(); 
        String countyJson = converter.convert(countyArr);
        String cityJson = converter.convert(cityArr);
        session.setAttribute("cityArr", cityArr);
        session.setAttribute("countyArr", countyArr);
        request.setAttribute("countyJson", countyJson);
        request.setAttribute("cityJson", cityJson);
        request.getRequestDispatcher("/createElection.jsp").forward(request, response);
    }
}
