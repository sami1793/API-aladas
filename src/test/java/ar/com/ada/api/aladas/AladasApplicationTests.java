package ar.com.ada.api.aladas;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ar.com.ada.api.aladas.entities.Vuelo;
import ar.com.ada.api.aladas.services.VueloServices;

@SpringBootTest
class AladasApplicationTests {

	/*@Test
	void contextLoads() {
	}*/
	@Autowired
	VueloServices vueloServices;

	@Test
	void vueloTestPrecioNegativo(){

		Vuelo vueloPrecioNegativo = new Vuelo();
		vueloPrecioNegativo.setPrecio(new BigDecimal(-100));
		//ASSERT: afirmar
		//assertTrue: esperamos que sea verdadero
		//assertFalse: esperamos que sea falso
		assertFalse(vueloServices.validarPrecio(vueloPrecioNegativo));//si es verdadero el test es correcto

	}

	@Test
	void vueloTestPrecioOk(){
		Vuelo vueloPrecioOk = new Vuelo();
		vueloPrecioOk.setPrecio(new BigDecimal(100));

		assertTrue(vueloServices.validarPrecio(vueloPrecioOk));
	}

}
