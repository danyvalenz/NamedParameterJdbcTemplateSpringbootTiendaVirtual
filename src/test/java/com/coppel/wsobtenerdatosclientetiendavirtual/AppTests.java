package com.coppel.wsobtenerdatosclientetiendavirtual;

import org.junit.jupiter.api.Test;

class AppTests {

	@Test
	void contextLoads() {
	}

	/**
	 * Se comenta la batería de pruebas unitarias
	 * y se deja en comentario para futuras referencias
	 * 
	 * @Autowired
	 *            private TestRestTemplate restTemplate;
	 * 
	 * @Test
	 *       public void getFacturasStatusCode() throws Exception {
	 *       ResponseEntity<ApiResponseDTO> response = restTemplate.getForEntity(
	 *       new URL("http://localhost:" + port + "/api/v1/facturas").toString(),
	 *       ApiResponseDTO.class);
	 * 
	 *       assertEquals(200, response.getBody().getMeta().getStatusCode());
	 *       }
	 * 
	 * @Test
	 *       public void getFacturasPorIdNombreFactura() throws Exception {
	 *       ResponseEntity<ApiResponseDTO> response = restTemplate.getForEntity(
	 *       new URL("http://localhost:" + port + "/api/v1/facturas/1").toString(),
	 *       ApiResponseDTO.class);
	 * 
	 *       FacturasDTO facturasDTO = new
	 *       ModelMapper().map(response.getBody().getData(), FacturasDTO.class);
	 *       assertEquals("Paimi Arizmendi", facturasDTO.getNombreCliente());
	 *       }
	 * 
	 * @Test
	 *       public void getFacturasPorIdImportes() throws Exception {
	 *       ResponseEntity<ApiResponseDTO> response = restTemplate.getForEntity(
	 *       new URL("http://localhost:" + port + "/api/v1/facturas/2").toString(),
	 *       ApiResponseDTO.class);
	 * 
	 *       FacturasDTO facturasDTO = new
	 *       ModelMapper().map(response.getBody().getData(), FacturasDTO.class);
	 *       List<FacturasDetalleDTO> conceptos =
	 *       facturasDTO.getFacturaDetalleList();
	 *       BigDecimal importeFactura = facturasDTO.getMonto();
	 *       BigDecimal importeConceptos = new BigDecimal(0);
	 *       BigDecimal importeDetalle = new BigDecimal(0);
	 *       for (FacturasDetalleDTO facturaDetalle : conceptos) {
	 *       importeDetalle = facturaDetalle.getPrecioUnitario().multiply(new
	 *       BigDecimal(facturaDetalle.getCantidad()));
	 *       importeConceptos = importeConceptos.add(importeDetalle);
	 *       }
	 *       assertEquals(importeFactura, importeConceptos);
	 *       }
	 */
}
