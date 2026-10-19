package controller;

import dao.CategoriaDAO;
import dao.LibroDAO;
import models.LibroBean;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/libros")
public class LibroController extends HttpServlet {
    private final LibroDAO libroDAO = new LibroDAO();
    private final CategoriaDAO categoriaDAO = new CategoriaDAO();
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try { req.setAttribute("categorias", categoriaDAO.listar()); req.setAttribute("libros", libroDAO.listar()); req.getRequestDispatcher("/registroLibro.jsp").forward(req, resp); }
        catch (Exception e) { req.setAttribute("error", "No se pudieron cargar los libros: " + e.getMessage()); req.getRequestDispatcher("/error.jsp").forward(req, resp); }
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        try {
            String titulo=req.getParameter("titulo"), autor=req.getParameter("autor"); int cantidad=Integer.parseInt(req.getParameter("cantidadDisponible"));
            if(titulo==null||titulo.trim().isEmpty()||autor==null||autor.trim().isEmpty()||cantidad<0) throw new IllegalArgumentException("Título, autor y una cantidad válida son obligatorios.");
            LibroBean l=new LibroBean(); l.setTitulo(titulo.trim()); l.setAutor(autor.trim()); l.setIsbn(vacioANull(req.getParameter("isbn"))); l.setIdCategoria(Integer.parseInt(req.getParameter("idCategoria"))); l.setCantidadDisponible(cantidad); libroDAO.insertar(l); resp.sendRedirect(req.getContextPath()+"/libros?ok=libro");
        } catch(Exception e) { req.setAttribute("error", "No se pudo registrar el libro. Verifica que el ISBN no esté repetido. " + e.getMessage()); doGet(req,resp); }
    }
    private String vacioANull(String v){return v==null||v.trim().isEmpty()?null:v.trim();}
}
