package com.app.server.servlets;

import com.app.server.model.OneRes;
import com.app.server.model.ResultsBean;
import jakarta.inject.Inject;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet(name = "oneResServlet", value = "/one_res")
public class AreaCheckServlet extends HttpServlet {

    @Inject
    private ResultsBean resultsBean;

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            int x = Integer.parseInt(req.getParameter("x"));
            float y = Float.parseFloat(req.getParameter("y"));
            float r = Float.parseFloat(req.getParameter("r"));
            RequestDispatcher dispatcher;
            if (Math.abs(x) <= 4 && y > -3 && y < 3 && r > 2 && r < 5) {
//                ResultsBean resultsBean = (ResultsBean) req.getSession().getAttribute("results");
                if (resultsBean == null) resultsBean = new ResultsBean();

                OneRes oneRes = new OneRes(x, y, r);
                oneRes.setInside();
                resultsBean.getResults().add(oneRes);
                req.getSession().setAttribute("results", resultsBean);
                req.getSession().setAttribute("check", oneRes);
                dispatcher = req.getRequestDispatcher("/result");
            } else {
                dispatcher = getServletContext().getRequestDispatcher("/incorrect-data");
            }
            dispatcher.forward(req, resp);
        } catch (Exception exception) {
            resp.getWriter().println(exception.getMessage());
        }
    }
}