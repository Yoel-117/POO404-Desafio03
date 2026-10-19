package controller;

import dao.*;
import models.PrestamoBean;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Date;

@WebServlet("/prestamos")
public class PrestamoController extends HttpServlet {
    private final PrestamoDAO prestamoDAO=new PrestamoDAO(); private final EstudianteDAO estudianteDAO=new EstudianteDAO(); private final LibroDAO libroDAO=new LibroDAO();
    protected void doGet(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException { try{req.setAttribute("estudiantes",estudianteDAO.listar());req.setAttribute("libros",libroDAO.listar());req.setAttribute("prestamos",prestamoDAO.listar());req.getRequestDispatcher("/registroPrestamo.jsp").forward(req,resp);}catch(Exception e){error(req,resp,e);} }
    protected void doPost(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException { req.setCharacterEncoding("UTF-8"); try{String accion=req.getParameter("accion"); if("devolver".equals(accion)){prestamoDAO.devolver(Integer.parseInt(req.getParameter("idPrestamo")));resp.sendRedirect(req.getContextPath()+"/prestamos?ok=devolucion");return;} PrestamoBean p=new PrestamoBean();p.setIdEstudiante(Integer.parseInt(req.getParameter("idEstudiante")));p.setIdLibro(Integer.parseInt(req.getParameter("idLibro")));p.setFechaPrestamo(Date.valueOf(req.getParameter("fechaPrestamo")));p.setFechaDevolucion(Date.valueOf(req.getParameter("fechaDevolucion")));if(p.getFechaDevolucion().before(p.getFechaPrestamo()))throw new IllegalArgumentException("La fecha de devolución no puede ser anterior a la fecha del préstamo.");prestamoDAO.insertar(p);req.setAttribute("confirmacion",p);req.getRequestDispatcher("/confirmacion.jsp").forward(req,resp);}catch(Exception e){req.setAttribute("error","No se pudo procesar la operación: "+e.getMessage());doGet(req,resp);} }
    private void error(HttpServletRequest req,HttpServletResponse resp,Exception e)throws ServletException,IOException{req.setAttribute("error",e.getMessage());req.getRequestDispatcher("/error.jsp").forward(req,resp);}
}
