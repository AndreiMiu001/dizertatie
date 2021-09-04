package insert;

import common.Category;
import common.UserBean;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
        session.setAttribute("listCategory", electionCategoryArray);        
        request.getRequestDispatcher("/createElection.jsp").forward(request, response);
    }
}
