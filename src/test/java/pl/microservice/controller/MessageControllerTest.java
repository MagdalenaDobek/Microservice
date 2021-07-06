package pl.microservice.controller;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.microservice.model.MessageService;
import pl.microservice.model.Message;


import javax.annotation.Resource;
import java.util.List;
import java.util.Random;
import java.util.UUID;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class MessageControllerTest {

    TestDataLoader dataLoader;

    @Resource
    MessageService service;


    @Autowired
    private MockMvc mockMvc;


    @Test
    void createMessage() throws Exception {
        String content = "Donec placerat odio id ex interdum, ut viverra urna tristique.";
        String body = "{\"content\":\"" + content + "\"}";

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/message")
                .content(body)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        //verify if 201 status (CREATED) is returned
        assertEquals(HttpStatus.CREATED.value(), response.getStatus());

        //verify message body
        assertTrue(response.getContentAsString().contains(content));
    }

    @Test
    void readAllMessages() throws Exception {
        //execute
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/message")
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        //verify if 200 status (OK) is returned
        assertEquals(HttpStatus.OK.value(), response.getStatus());

        //verify message body
        assertTrue(response.getContentAsString().contains("id\":"));
        assertTrue(response.getContentAsString().contains("content\":"));

    }

    @Test
    void readMessage() throws Exception {
        //prepare data
        dataLoader = new TestDataLoader();

        Random rand = new Random();
        int random = rand.nextInt(20);

        List<Message> list = service.findAll();
        UUID id = list.get(random).getId();
        String content = list.get(random).getContent();

        //execute
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/message/" + id)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();


        //verify if 200 status (OK) is returned
        assertEquals(HttpStatus.OK.value(), response.getStatus());

        //verify message body
        String body = "{\"id\":\"" + id + "\",\"content\":\""+ content + "\"}";
        assertEquals(body, response.getContentAsString());

    }

    @Test
    void updateMessage() throws Exception {
        //prepare data
        dataLoader = new TestDataLoader();

        Random rand = new Random();
        int random = rand.nextInt(20);

        List<Message> list = service.findAll();
        UUID id = list.get(random).getId();
        String newContent = "Nunc dapibus enim eget eros venenatis ultricies.";
        String body = "{\"content\":\"" + newContent + "\"}";

        //execute
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/message/" + id)
                .content(body)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();



        //verify if 200 status (OK) is returned
        assertEquals(HttpStatus.OK.value(), response.getStatus());

        //verify message body
        body = "{\"id\":\"" + id + "\",\"content\":\""+ newContent + "\"}";
        assertEquals(body, response.getContentAsString());


    }

    @Test
    void getRandom() throws Exception {
        dataLoader = new TestDataLoader();

        int random = (int) Math.floor(Math.random() * 20 + 1);


        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/message/random/" + random)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        //verify if 200 status (OK) is returned
        assertEquals(HttpStatus.OK.value(), response.getStatus());

        //verify message body
        assertTrue(response.getContentAsString().contains("id\":"));
        assertTrue(response.getContentAsString().contains("content\":"));
    }
}