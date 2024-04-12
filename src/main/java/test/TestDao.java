package test;

import java.util.List;

import dao.ConcertDaoImpl;
import metier.entities.Concert; // Update the import to use the Concert class

public class TestDao {
    public static void main(String[] args) {
        ConcertDaoImpl cdao = new ConcertDaoImpl();
        Concert concert = cdao.save(new Concert("F TOUR", 390.99, null));
        System.out.println(concert);

        List<Concert> concerts = cdao.concertsParMC("SBS");
        for (Concert c : concerts) {
			System.out.println(c);
		}
    }
}