package com.directrice.banking.controller;

import com.directrice.banking.dto.*;
import com.google.gson.Gson;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
public class BankingControllerTest {


    /*
    * NATURAL = Normal User Account.
    * LEGAL = Organisation User Account.
    **/

    @Autowired
    private MockMvc mockMvc;

    private AddressDTO addressDTO;
    private UserAccountDTO userAccountDTO;
    private OrganisationDTO organisationDTO;
    private LegalPersonDTO legalPersonDTO;

    @BeforeEach
    void setUp() {
          this.addressDTO=new AddressDTO("Fort,Marnie Drive","Mumbai","Mumbai","Maharashtra","India","4000123");
          this.userAccountDTO=new UserAccountDTO("John I","Doe",this.addressDTO,"1 Jan 1990","Indian","India","Services");
          this.legalPersonDTO=new LegalPersonDTO("John I","Doe","789456130","","johnDoe1@gmail.com",this.addressDTO,"Indian","India","CEO");
          this.organisationDTO=new OrganisationDTO("Business","Directrice pvt ltd",this.addressDTO,"QWERTY456789",this.legalPersonDTO);
    }


    //Natural User Account
    @Test
    public void givenValidUserInfo_NATURAL_WhenAdded_shouldReturnValidResponse() throws Exception {

       String userAccountDTO=new Gson().toJson(this.userAccountDTO);
        MvcResult result = this.mockMvc.perform(post("/directrice/banking/user")
                .header("token","token")
                .content(userAccountDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        Assertions.assertEquals(201, result.getResponse().getStatus());
    }

    @Test
    public void givenValidUserInfo_NATURAL_WhenEdited_shouldReturnValidResponse() throws Exception {

        String userAccountDTO=new Gson().toJson(this.userAccountDTO);
        MvcResult result = this.mockMvc.perform(put("/directrice/banking/user")
                .header("token","token")
                .content(userAccountDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        Assertions.assertEquals(200, result.getResponse().getStatus());
    }

    @Test
    public void givenValidUserToken_NATURAL_WhenGetting_shouldReturnValidResponse() throws Exception {

        MvcResult result = this.mockMvc.perform(get("/directrice/banking/user/"+"accountId")
                .header("token","token")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        Assertions.assertEquals(200, result.getResponse().getStatus());
    }

    @Test
    public void givenValid_NATURAL_WhenGetting_shouldReturnValidResponse() throws Exception {

        MvcResult result = this.mockMvc.perform(get("/directrice/banking/users")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        Assertions.assertEquals(200, result.getResponse().getStatus());
    }



    //Legal Account
    @Test
    public void givenValidOrganisationInfo_LEGAL_WhenAdded_shouldReturnValidResponse() throws Exception {
        String organisationDTO=new Gson().toJson(this.organisationDTO);
        MvcResult result = this.mockMvc.perform(post("/directrice/banking/organisation")
                .header("token","token")
                .content(organisationDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        Assertions.assertEquals(201, result.getResponse().getStatus());
    }

    @Test
    public void givenValidOrganisationInfo_LEGAL_WhenEdited_shouldReturnValidResponse() throws Exception {
        String organisationDTO=new Gson().toJson(this.organisationDTO);
        MvcResult result = this.mockMvc.perform(put("/directrice/banking/organisation")
                .header("token","token")
                .content(organisationDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        Assertions.assertEquals(200, result.getResponse().getStatus());
    }

    @Test
    public void givenValidUserToken_LEGAL_WhenGetting_shouldReturnValidResponse() throws Exception {

        MvcResult result = this.mockMvc.perform(get("/directrice/banking/organisation/"+"accountId")
                .header("token","token")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        Assertions.assertEquals(200, result.getResponse().getStatus());
    }

    @Test
    public void givenValid_LEGAL_WhenGetting_shouldReturnValidResponse() throws Exception {

        MvcResult result = this.mockMvc.perform(get("/directrice/banking/organisations")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        Assertions.assertEquals(200, result.getResponse().getStatus());
    }

}
