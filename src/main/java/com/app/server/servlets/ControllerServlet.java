package com.app.server.servlets;

import com.app.server.model.OneRes;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "controllerServlet", value = "/controller")
public class ControllerServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            int x = Integer.parseInt(req.getParameter("x"));
            float y = Float.parseFloat(req.getParameter("y"));
            float r = Float.parseFloat(req.getParameter("r"));
            new OneRes(x, y, r);
            RequestDispatcher dispatcher;
            if (Math.abs(x) <= 4 && y > -3 && y < 3 && r > 2 && r < 5) {
                dispatcher = getServletContext().getRequestDispatcher("/one_res");
            } else {
                dispatcher = getServletContext().getRequestDispatcher("/incorrect-data");
            }
            dispatcher.forward(req, resp);
        } catch (Exception exception) {
            resp.getWriter().println(exception.getMessage());
        }
    }
}