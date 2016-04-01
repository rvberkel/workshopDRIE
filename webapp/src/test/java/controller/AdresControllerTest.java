package controller;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

public class AdresControllerTest {

	@Test
	public void testShowArtikelForm() throws Exception {
		AdresController controller = new AdresController();
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
		mockMvc.perform(MockMvcRequestBuilders.get("/showCreateAdresForm")).andExpect(MockMvcResultMatchers.view().name("adres"));
	}

}
