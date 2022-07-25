package by.it.academy.mailapi.controllers;

import by.it.academy.mailapi.controllers.mail.MailController;
import by.it.academy.mailapi.dtos.mail.requests.MailRequest;
import by.it.academy.mailapi.dtos.mail.responses.MailResponse;
import by.it.academy.mailapi.services.mail.MailService;
import com.google.gson.Gson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@DisplayName("Mail controller test")
@WebMvcTest(MailController.class)
public class MailControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private MailService mailService;
    @Autowired
    private Gson gson;

    @Test
    @DisplayName("Send message test")
    void sendMessageTest() throws Exception {
        MailRequest mailRequest = new MailRequest("1234@gmail.com", "Hello", "Hello");
        MailResponse mailResponse = new MailResponse(true);

        when(mailService.dispatchMessage(mailRequest)).thenReturn(true);
        when(mailService.dispatchStatus(mailResponse)).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.post("/mail")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .content(gson.toJson(mailRequest)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        assertTrue(mailService.dispatchMessage(mailRequest));
    }
}
