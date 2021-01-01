/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

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
            request.getRequestDispatcher("/mainPage.jsp").forward(request, response);
        } else {
            request.setAttribute("response", "Username or password are invalid ! Please try again.");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }
}
