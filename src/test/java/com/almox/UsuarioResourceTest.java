package com.almox;

import com.almox.model.entidades.Usuario;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.assertj.core.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

@SpringBootTest
public class UsuarioResourceTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    public void setup(){
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    void userInsertTest(){
        try {

            Usuario user1 = new Usuario();
            user1.setId(1L);
            user1.setLogin("Alexsander");
            Usuario user2 = new Usuario();
            user2.setId(2L);
            user2.setLogin("Patrick");
            Usuario user3 = new Usuario();
            user3.setId(3L);
            user3.setLogin("Leonardo");

            List<Usuario> list = List.of(user1,user2,user3);

            String requestJson = new ObjectMapper().writeValueAsString(list);
            //formatter:off

            mvc.perform(post("/usuarios")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(requestJson)
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isCreated());
            // @formatter:on

        }catch (Exception e){
            fail(e.getClass().getSimpleName() + " -> " + e.getMessage());
        }
    }

    @Test
    void updateLoginExistingTest(){
        try {

            Usuario user1 = new Usuario();
            user1.setId(1L);
            user1.setLogin("Usuario");

            String requestJson = new ObjectMapper().writeValueAsString(user1);
            //formatter:off

            mvc.perform(post("/usuarios/1")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(requestJson)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
            // @formatter:on

        }catch (Exception e) {
            fail(e.getClass().getSimpleName() + " -> " + e.getMessage());
        }
    }

    @Test
    void removeUserTest(){
        try{
            Usuario user1 = new Usuario();
            user1.setId(1L);
            user1.setLogin("Usuario");

            String requestJson = new ObjectMapper().writeValueAsString(user1);
// @formatter:off
            mvc.perform(delete("/usuarios/1")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(requestJson)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());

// @formatter:on
        }catch (Exception e) {
            fail(e.getClass().getSimpleName() + " -> " + e.getMessage());
        }
    }


}
