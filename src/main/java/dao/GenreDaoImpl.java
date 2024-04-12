package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import metier.entities.Genre;
import util.JPAutil;

public class GenreDaoImpl implements IGenreDao {
    private EntityManager entityManager = JPAutil.getEntityManager("TP5_concert");

    @Override
    public Genre save(Genre genre) {
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        entityManager.persist(genre);
        tx.commit();
        return genre;
    }

    @Override
    public Genre getGenre(Long id) {
        return entityManager.find(Genre.class, id);
    }

    @Override
    public Genre updateGenre(Genre genre) {
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        entityManager.merge(genre);
        tx.commit();
        return genre;
    }

    @Override
    public void deleteGenre(Long id) {
        Genre genre = entityManager.find(Genre.class, id);
        entityManager.getTransaction().begin();
        entityManager.remove(genre);
        entityManager.getTransaction().commit();
    }

    @Override
    public List<Genre> getAllGenres() {
        List<Genre> genres = entityManager.createQuery("select g from Genre g", Genre.class).getResultList();
        return genres;
    }
}
