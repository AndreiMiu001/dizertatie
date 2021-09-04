package view;

import common.ElectionBean;
import common.UserBean;
import common.ObjectToJson;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ViewElectionsServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate();
        request.setAttribute("response", "Called doPost from view servlet");
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute("user");
        if (user == null) {
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
        ViewElectionsImpl viewImpl = new ViewElectionsImpl();
        ArrayList<ElectionBean> electionsArray = viewImpl.getAllElections();
        ObjectToJson<ArrayList<ElectionBean>> jsonConverter = new ObjectToJson<>();
        String electionsArrayJson = jsonConverter.convert(electionsArray);
        request.setAttribute("electionsArrayJson", electionsArrayJson);
        request.getRequestDispatcher("/viewElections.jsp").forward(request, response);
    }
}
