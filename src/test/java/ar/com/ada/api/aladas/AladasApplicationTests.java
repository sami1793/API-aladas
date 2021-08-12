package ar.com.ada.api.aladas;

import static org.junit.jupiter.api.Assertions.*;


import java.math.BigDecimal;

import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ar.com.ada.api.aladas.entities.Aeropuerto;
import ar.com.ada.api.aladas.entities.Usuario;
import ar.com.ada.api.aladas.entities.Vuelo;
import ar.com.ada.api.aladas.entities.Vuelo.EstadoVueloEnum;
import ar.com.ada.api.aladas.security.Crypto;
import ar.com.ada.api.aladas.services.AeropuertoService;
import ar.com.ada.api.aladas.services.VueloService;
import ar.com.ada.api.aladas.services.VueloService.ValidacionVueloDataEnum;

@SpringBootTest
class AladasApplicationTests {

	@Autowired
	VueloService vueloServices;

	@Autowired
	AeropuertoService aeropuertoServices;

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

	@Test
	void aeropuertoTestValidarCodigoIATAOK(){

		String codigoIATAOk1="EZE";
		String codigoIATAOk2="AEP";
		String codigoIATAOk3="SA ";

		Aeropuerto aeropuerto1 = new Aeropuerto();
		Aeropuerto aeropuerto2 = new Aeropuerto();
		Aeropuerto aeropuerto3 = new Aeropuerto();

		//forma equals
		assertEquals(3, codigoIATAOk1.length());

		//forma true
		assertTrue(codigoIATAOk1.length()==3);

		aeropuerto1.setCodigoIATA(codigoIATAOk1);
		aeropuerto2.setCodigoIATA(codigoIATAOk2);
		aeropuerto3.setCodigoIATA(codigoIATAOk3);

		//usando el metodo del services
		assertTrue(aeropuertoServices.validarCodigoIATA(aeropuerto1));

		//Uso un IATA que esta mal por eso pongo FALSE
		assertFalse(aeropuertoServices.validarCodigoIATA(aeropuerto3));
	}

	
	@Test
	void aeropuertoTestValidarCodigoIATANoOK(){

	}

	@Test
	void vueloVerificarValidacionAeropuertoOrigenDestino(){

		//funciona mal verificar el id 	que se crea
		Vuelo vuelo = new Vuelo(); 
		String aeropuertoOrigen = "EZE";
		String aeropuertoDestino="NQN";

		
		vuelo.setAeropuertoOrigen(aeropuertoServices.buscarPorCodigoIATA(aeropuertoOrigen).getAeropuertoId());
		vuelo.setAeropuertoDestino(aeropuertoServices.buscarPorCodigoIATA(aeropuertoDestino).getAeropuertoId());

		assertTrue(vueloServices.validarAeropuertoOrigenDiffDestino(vuelo));
		//si es difernte origen y destino
		//otras cosas que se me ocurran
	}

	@Test
	void vueloChequearQueLosPendientesNoTenganVuelosViejos(){
		//limpiar vuelos viejos?
		//que no se liste un vuelo que ya salio

		//queremos validar que cuando hagamos un método que traiga los vuelos actuales
		//para hacer resrvas, no haya vuelos en el pasado
	}

	@Test
	void vueloVerificarCapacidadMinima(){

	}

	@Test
	void vueloVerificarCapacidadMaxima(){

	}


	@Test
	void aeropuertoTestBuscarCodigoIATA(){

	}

	@Test
	void vueloValidarVueloMismoDestino(){
		Vuelo vuelo = new Vuelo();
		vuelo.setPrecio(new BigDecimal(100));
		vuelo.setEstadoVueloId(EstadoVueloEnum.CREADO);
		vuelo.setAeropuertoDestino(116);
		vuelo.setAeropuertoOrigen(116);

		assertEquals(ValidacionVueloDataEnum.ERROR_AEROPUERTOS_IGUALES,vueloServices.validar(vuelo));
	}

	@Test
	void testearEncriptacion(){
		String contraseñaImaginaria = "pitufosasesinos";
		String contraseñaImaginariaEncriptada= Crypto.encrypt(contraseñaImaginaria, "palabra");

		String contraseñaImaginariaDesencriptada=Crypto.decrypt(contraseñaImaginariaEncriptada, "palabra");

		//assertTrue(contraseñaImaginaria.equals(contraseñaImaginariaDesencriptada));
		assertEquals(contraseñaImaginaria, contraseñaImaginariaDesencriptada);
	}
	@Test
	void testearContraseña(){
		Usuario usuario= new Usuario();
		//esto es lo que va en la base de datos
		usuario.setUsername("jonr@gmail.com");
		usuario.setPassword("SJsPgk5Z3cYrWtrRNeON5A==");
		usuario.setEmail("jonr@gmail.com");

		assertFalse(!usuario.getPassword().equals(Crypto.encrypt("jon123", usuario.getUsername())));

	}

}
