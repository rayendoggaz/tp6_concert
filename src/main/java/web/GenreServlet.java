package web;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.GenreDaoImpl;
import dao.IGenreDao;
import metier.entities.Genre;

@WebServlet(name = "genreServ", urlPatterns = { "/genres", "/saisieGenre", "/saveGenre", "/supprimerGenre",
        "/editerGenre", "/updateGenre" })
public class GenreServlet extends HttpServlet {
    private IGenreDao metier;

    @Override
    public void init() throws ServletException {
        metier = new GenreDaoImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getServletPath();
        System.out.println("PATH " + path);
        if (path.equals("/genres")) {
            GenreModel model = new GenreModel();
            List<Genre> genres = metier.getAllGenres();
            model.setGenres(genres);
            request.setAttribute("model", model);
            request.getRequestDispatcher("genres.jsp").forward(request, response);
        } else if (path.equals("/saisieGenre")) {
            request.getRequestDispatcher("saisieGenre.jsp").forward(request, response);
        } else if (path.equals("/saveGenre") && request.getMethod().equals("POST")) {
            Date dateCreation = new Date();
            String nom = request.getParameter("nom");
            String pattern = "yyyy-MM-dd";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            try {
                dateCreation = simpleDateFormat.parse(request.getParameter("dateCreation"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Genre genre = metier.save(new Genre(nom, dateCreation));
            request.setAttribute("genre", genre);
            response.sendRedirect("genres");
        } else if (path.equals("/supprimerGenre")) {
            Long id = Long.parseLong(request.getParameter("id"));
            metier.deleteGenre(id);
            response.sendRedirect("genres");
        } else if (path.equals("/editerGenre")) {
            Long id = Long.parseLong(request.getParameter("id"));
            Genre genre = metier.getGenre(id);
            request.setAttribute("genre", genre);
            request.getRequestDispatcher("editerGenre.jsp").forward(request, response);
        } else if (path.equals("/updateGenre")) {
            Date dateCreation = new Date();
            Long id = Long.parseLong(request.getParameter("id"));
            String nom = request.getParameter("nom");
            Genre genre = new Genre();
            genre.setIdGenre(id);
            genre.setNomGenre(nom);
            String pattern = "yyyy-MM-dd";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            try {
                dateCreation = simpleDateFormat.parse(request.getParameter("dateCreation"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            genre.setDateCreation(dateCreation);
            metier.updateGenre(genre);
            response.sendRedirect("genres");
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
