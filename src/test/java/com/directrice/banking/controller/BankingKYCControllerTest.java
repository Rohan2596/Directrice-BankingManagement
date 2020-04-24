package com.directrice.banking.controller;

import com.directrice.banking.service.AccountKYCServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;

@SpringBootTest
@AutoConfigureMockMvc
public class BankingKYCControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    AccountKYCServiceImpl accountKYCService;

    //Upload KYC file image

    @Test
    public void givenValidTokenAndaccountNumber_UPLOAD_IMAGE_whenAdded_shouldReturnResponse() throws Exception {
        MockMultipartFile imageFile=new MockMultipartFile("file","1.png","image/png","Some Data:".getBytes());
        MvcResult result = this.mockMvc.perform(multipart("/directrice/banking/kyc/upload")
                .file(imageFile)
                .header("token", "token")
                .header("accountNumber","accountNumber"))
                .andReturn();
        assertEquals(200,result.getResponse().getStatus());
    }

    @Test
    public void givenValidTokenAndaccountNumber_UPLOAD_WrongFormatIMAGE_whenAdded_shouldReturnResponse() throws Exception {
        MockMultipartFile imageFile=new MockMultipartFile("file","1.svg","image/svg","Some Data:".getBytes());
        MvcResult result = this.mockMvc.perform(multipart("/directrice/banking/kyc/upload")
                .file(imageFile)
                .header("token", "token")
                .header("accountNumber","accountNumber"))
                .andReturn();
        assertEquals(400,result.getResponse().getStatus());
    }
    @Test
    public void givenValidTokenAndaccountNumber_UPLOAD_EmptyIMAGE_whenAdded_shouldReturnResponse() throws Exception {
        MockMultipartFile imageFile=new MockMultipartFile("file","","","Some Data:".getBytes());
        MvcResult result = this.mockMvc.perform(multipart("/directrice/banking/kyc/upload")
                .file(imageFile)
                .header("token", "token")
                .header("accountNumber","accountNumber"))
                .andReturn();
        assertEquals(400,result.getResponse().getStatus());
    }
}
