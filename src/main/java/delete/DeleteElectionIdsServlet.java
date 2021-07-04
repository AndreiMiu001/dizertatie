/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package delete;

import common.UserBean;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Andrei
 */
public class DeleteElectionIdsServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute("user");
        if (user == null) {
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
        String[] idsStr = request.getParameterValues("idCheckbox");
        for (String idStr : idsStr) {
            int id = Integer.parseInt(idStr);
            DeleteElectionIdImpl deleteElection = new DeleteElectionIdImpl();
            if (deleteElection.deleteId(id)) {
                System.out.println("Could not delete election with id:" + id);
            }
        }
        request.getRequestDispatcher("/deleteElection.jsp").forward(request, response);
    }
}
