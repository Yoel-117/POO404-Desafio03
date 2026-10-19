package controller;

import dao.EstudianteDAO;
import models.EstudianteBean;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/estudiantes")
public class EstudianteController extends HttpServlet {
    private final EstudianteDAO dao=new EstudianteDAO();
    protected void doGet(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException { try{req.setAttribute("estudiantes",dao.listar());req.getRequestDispatcher("/registroEstudiante.jsp").forward(req,resp);}catch(Exception e){error(req,resp,e);} }
    protected void doPost(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException { req.setCharacterEncoding("UTF-8"); try{String carnet=req.getParameter("carnet"),nombre=req.getParameter("nombreEstudiante"),carrera=req.getParameter("carrera"),tel=req.getParameter("telefono"); if(carnet==null||carnet.trim().isEmpty()||nombre==null||nombre.trim().isEmpty()||carrera==null||carrera.trim().isEmpty())throw new IllegalArgumentException("Carnet, nombre y carrera son obligatorios."); if(tel!=null&&!tel.trim().isEmpty()&&!tel.matches("\\d{8,9}"))throw new IllegalArgumentException("El teléfono debe contener entre 8 y 9 dígitos."); EstudianteBean e=new EstudianteBean();e.setCarnet(carnet.trim());e.setNombreEstudiante(nombre.trim());e.setCarrera(carrera.trim());e.setTelefono(tel==null?null:tel.trim());dao.insertar(e);resp.sendRedirect(req.getContextPath()+"/estudiantes?ok=estudiante");}catch(Exception e){req.setAttribute("error","No se pudo registrar el estudiante. Verifica que el carnet no esté repetido. "+e.getMessage());doGet(req,resp);} }
    private void error(HttpServletRequest req,HttpServletResponse resp,Exception e)throws ServletException,IOException{req.setAttribute("error",e.getMessage());req.getRequestDispatcher("/error.jsp").forward(req,resp);}
}
