package aula01;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PaisTeste {
	Pais pais, copia;
	static int id = 0;
	
	@Before
	public void setUp() throws Exception {
		System.out.println("setup");
		pais = new Pais(1, "Brasil", 222, 8516000);
		copia = new Pais(1, "Brasil", 222, 8516000);
		System.out.println(pais);
		System.out.println(copia);
		System.out.println(id);
	}
	
	@Test
	public void test03Excluir() {
		System.out.println("excluir");
		copia.setId(-1);
		copia.setNome(null);
		copia.setPopulacao(-1);
		copia.setArea(-1);
		pais.excluir(pais.getId());
		pais.carregar(pais);
		assertEquals("testa exclusao", pais, copia);
	}
	@Test
	public void testCriar() {
		System.out.println("criar");
		
		pais = pais.criar(pais);
		id = pais.getId();
		System.out.println(id);
		copia.setId(id);
		assertEquals("testa criacao", pais, copia);
	}
	
	@Test
	public void testCarregar() {
		System.out.println("carregar");
		//para funcionar o cliente 1 deve ter sido carregado no banco por fora
		Pais fixture = new Pais(16, "Brasil", 222, 8516000);
		Pais novo = new Pais();
		novo.setId(16);
		novo = pais.carregar(novo);
		System.out.println(novo.getId());
		assertEquals("testa inclusao", novo, fixture);
	}
	
	
	
	
}
