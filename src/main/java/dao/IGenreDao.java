package dao;

import java.util.List;

import metier.entities.Genre;

public interface IGenreDao {
    public Genre save(Genre genre);
    public Genre getGenre(Long id);
    public Genre updateGenre(Genre genre);
    public void deleteGenre(Long id);
    public List<Genre> getAllGenres();
}
