package web;

import java.io.IOException;
import java.util.List;

import dao.ConcertDaoImpl;
import dao.GenreDaoImpl;
import dao.IConcertDao;
import dao.IGenreDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import metier.entities.Concert;
import metier.entities.Genre;

@WebServlet(name = "cs", urlPatterns = { "/controleur", "*.do" })
public class ControleurServlet extends HttpServlet {
    IConcertDao metier;
    IGenreDao metierGenre;

    @Override
    public void init() throws ServletException {
        metier = new ConcertDaoImpl();
        metierGenre = new GenreDaoImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getServletPath();
        if (path.equals("/index.do")) {
            request.getRequestDispatcher("concerts.jsp").forward(request, response);     
        } 
        
        else if (path.equals("/chercher.do")) {
            String motCle = request.getParameter("motCle");
            ConcertModele model = new ConcertModele();
            model.setMotCle(motCle);
            List<Concert> concerts = metier.concertsParMC(motCle);
            model.setConcerts(concerts);
            request.setAttribute("model", model);
            request.getRequestDispatcher("concerts.jsp").forward(request, response);
        } 
        
        else if (path.equals("/saisie.do")) {
        	List<Genre> genres = metierGenre.getAllGenres();
            GenreModel genreModel = new GenreModel();
            genreModel.setGenres(genres);
            request.setAttribute("genreModel", genreModel);

            request.getRequestDispatcher("saisieConcert.jsp").forward(request, response);
        } 
        
        else if (path.equals("/save.do") && request.getMethod().equals("POST")) {
        	String nomConcert = request.getParameter("nomConcert");
            double prix = Double.parseDouble(request.getParameter("prix"));
            Long genreId = Long.parseLong(request.getParameter("genre"));
            Genre genre = metierGenre.getGenre(genreId);
            Concert concert = metier.save(new Concert(nomConcert, prix, genre));
            request.setAttribute("concert", concert);
            response.sendRedirect("chercher.do?motCle=");
        } 
        
        else if (path.equals("/supprimer.do")) {
            Long id = Long.parseLong(request.getParameter("id"));
            metier.deleteConcert(id);
            response.sendRedirect("chercher.do?motCle=");
        } 
        
        else if (path.equals("/editer.do")) {
        	Long id = Long.parseLong(request.getParameter("id"));
            Concert concert = metier.getConcert(id);
            request.setAttribute("concert", concert);
            
            List<Genre> genres = metierGenre.getAllGenres();
            GenreModel genreModel = new GenreModel();
            genreModel.setGenres(genres);
            request.setAttribute("genreModel", genreModel);
            
            request.getRequestDispatcher("editerConcert.jsp").forward(request, response);
        } 
        
        else if (path.equals("/update.do")) {
        	Long id = Long.parseLong(request.getParameter("id"));
            String nomConcert = request.getParameter("nom");
            double prix = Double.parseDouble(request.getParameter("prix"));
            Long genreId = Long.parseLong(request.getParameter("genre"));
            Concert concert = new Concert();
            int idInt = id.intValue();
            concert.setIdConcert(idInt);
            concert.setNomConcert(nomConcert);
            concert.setPrix(prix);
            Genre genre = metierGenre.getGenre(genreId);
            concert.setGenre(genre);
            metier.updateConcert(concert);
            response.sendRedirect("chercher.do?motCle=");
            request.getRequestDispatcher("confirmation.jsp").forward(request, response);
        }
        
        else {
            response.sendError(jakarta.servlet.http.HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
