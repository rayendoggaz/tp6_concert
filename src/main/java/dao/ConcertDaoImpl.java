package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import metier.entities.Concert;
import util.JPAutil;

public class ConcertDaoImpl implements IConcertDao {

	private EntityManager entityManager = JPAutil.getEntityManager("TP5_concert");

	@Override
	public Concert save(Concert c) {
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		entityManager.persist(c);
		tx.commit();
		return c;
	}

	@Override
	public List<Concert> concertsParMC(String mc) {
		List<Concert> concerts = entityManager
				.createQuery("SELECT c FROM Concert c WHERE c.nomConcert LIKE :mc", Concert.class)
				.setParameter("mc", "%" + mc + "%").getResultList();
		return concerts;
	}

	@Override
	public Concert getConcert(Long id) {
		return entityManager.find(Concert.class, id);
	}

	@Override
	public Concert updateConcert(Concert c) {
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		c = entityManager.merge(c);
		tx.commit();
		return c;
	}

	@Override
	public void deleteConcert(Long id) {
		Concert concert = entityManager.find(Concert.class, id);
		entityManager.getTransaction().begin();
		entityManager.remove(concert);
		entityManager.getTransaction().commit();
	}
}
