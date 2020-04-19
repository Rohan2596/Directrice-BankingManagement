package com.directrice.banking.controller;



import com.directrice.banking.dto.AddressDTO;
import com.directrice.banking.dto.OrganisationDTO;
import com.directrice.banking.dto.UserAccountDTO;
import com.directrice.banking.response.Response;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
public class BankingAccountControllerTest {

    /*
     * NATURAL = Normal User Account.
     * LEGAL = Organisation User Account.
     **/

    @Autowired
    private MockMvc mockMvc;

    private UserAccountDTO userAccountDTO;
    private OrganisationDTO organisationDto;
    private AddressDTO addressDTO;


    //added
    @Test
    public void givenValidUserInfo_NATURAL_WhenAdded_shouldReturnValidResponse() throws Exception {
        this.userAccountDTO=new UserAccountDTO("Rohan","kadam","1/01/1990","Indian","India","Services");

        String userAccountDTO=new Gson().toJson(this.userAccountDTO);
        MvcResult result = this.mockMvc.perform(post("/directrice/banking/user/account")
                .header("token","token")
                .content(userAccountDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(201, result.getResponse().getStatus());
    }
//Natural FirstName
    @Test
    public void givenInValidUserInfo_NATURAL_FirstName_NULL_WhenAdded_shouldReturnValidResponse() throws Exception {
        String firstName=null;
        this.userAccountDTO=new UserAccountDTO(firstName,"kadam","1/01/1990","Indian","India","Services");
        String userAccountDTO=new Gson().toJson(this.userAccountDTO);
        MvcResult result = this.mockMvc.perform(post("/directrice/banking/user/account")
                .header("token","token")
                .content(userAccountDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400,result.getResponse().getStatus());
        assertEquals("FirstName pattern doesn't match.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());
    }
    @Test
    public void givenInValidUserInfo_NATURAL_FirstName_EMPTY_WhenAdded_shouldReturnValidResponse() throws Exception {
        String firstName="";
        this.userAccountDTO=new UserAccountDTO(firstName,"kadam","1/01/1990","Indian","India","Services");
        String userAccountDTO=new Gson().toJson(this.userAccountDTO);
        MvcResult result = this.mockMvc.perform(post("/directrice/banking/user/account")
                .header("token","token")
                .content(userAccountDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400,result.getResponse().getStatus());
        assertEquals("FirstName pattern doesn't match.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());
    }
    @Test
    public void givenInValidUserInfo_NATURAL_FirstName_MIN_WhenAdded_shouldReturnValidResponse() throws Exception {
        String firstName="J";
        this.userAccountDTO=new UserAccountDTO(firstName,"kadam","1/01/1990","Indian","India","Services");
        String userAccountDTO=new Gson().toJson(this.userAccountDTO);
        MvcResult result = this.mockMvc.perform(post("/directrice/banking/user/account")
                .header("token","token")
                .content(userAccountDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400,result.getResponse().getStatus());
        assertEquals("FirstName pattern doesn't match.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());
    }
    @Test
    public void givenInValidUserInfo_NATURAL_FirstName_Max_WhenAdded_shouldReturnValidResponse() throws Exception {
        String firstName="asdfgjsdfdfhfgjghqwewrerytttuuertgfdsfgg";
        this.userAccountDTO=new UserAccountDTO(firstName,"kadam","1/01/1990","Indian","India","Services");
        String userAccountDTO=new Gson().toJson(this.userAccountDTO);
        MvcResult result = this.mockMvc.perform(post("/directrice/banking/user/account")
                .header("token","token")
                .content(userAccountDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400,result.getResponse().getStatus());
        assertEquals("FirstName pattern doesn't match.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());
    }
    @Test
    public void givenInValidUserInfo_NATURAL_FirstName_Pattern_WhenAdded_shouldReturnValidResponse() throws Exception {
        String firstName="Rohan!2";
        this.userAccountDTO=new UserAccountDTO(firstName,"kadam","1/01/1990","Indian","India","Services");
        String userAccountDTO=new Gson().toJson(this.userAccountDTO);
        MvcResult result = this.mockMvc.perform(post("/directrice/banking/user/account")
                .header("token","token")
                .content(userAccountDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400,result.getResponse().getStatus());
        assertEquals("FirstName pattern doesn't match.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());
    }
//LastName

    @Test
    public void givenInValidUserInfo_NATURAL_LastName_NULL_WhenAdded_shouldReturnValidResponse() throws Exception {
        String lastName=null;
        this.userAccountDTO=new UserAccountDTO("Rohan",lastName,"1/01/1990","Indian","India","Services");
        String userAccountDTO=new Gson().toJson(this.userAccountDTO);
        MvcResult result = this.mockMvc.perform(post("/directrice/banking/user/account")
                .header("token","token")
                .content(userAccountDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400,result.getResponse().getStatus());
        assertEquals("LastName pattern doesn't match.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());
    }
    @Test
    public void givenInValidUserInfo_NATURAL_LastName_EMPTY_WhenAdded_shouldReturnValidResponse() throws Exception {
        String lastName="";
        this.userAccountDTO=new UserAccountDTO("Rohan",lastName,"1/01/1990","Indian","India","Services");
        String userAccountDTO=new Gson().toJson(this.userAccountDTO);
        MvcResult result = this.mockMvc.perform(post("/directrice/banking/user/account")
                .header("token","token")
                .content(userAccountDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400,result.getResponse().getStatus());
        assertEquals("LastName pattern doesn't match.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());
    }
    @Test
    public void givenInValidUserInfo_NATURAL_LastName_MIN_WhenAdded_shouldReturnValidResponse() throws Exception {
        String lastName="J";
        this.userAccountDTO=new UserAccountDTO("ROhan",lastName,"1/01/1990","Indian","India","Services");
        String userAccountDTO=new Gson().toJson(this.userAccountDTO);
        MvcResult result = this.mockMvc.perform(post("/directrice/banking/user/account")
                .header("token","token")
                .content(userAccountDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400,result.getResponse().getStatus());
        assertEquals("LastName pattern doesn't match.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());
    }
    @Test
    public void givenInValidUserInfo_NATURAL_LastName_Max_WhenAdded_shouldReturnValidResponse() throws Exception {
        String lastName="asdfgjsdfdfhfgjghqwewrerytttuuertgfdsfgg";
        this.userAccountDTO=new UserAccountDTO("ROhan",lastName,"1/01/1990","Indian","India","Services");
        String userAccountDTO=new Gson().toJson(this.userAccountDTO);
        MvcResult result = this.mockMvc.perform(post("/directrice/banking/user/account")
                .header("token","token")
                .content(userAccountDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400,result.getResponse().getStatus());
        assertEquals("LastName pattern doesn't match.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());
    }
    @Test
    public void givenInValidUserInfo_NATURAL_LastName_Pattern_WhenAdded_shouldReturnValidResponse() throws Exception {
        String lastName="Rohan!2";
        this.userAccountDTO=new UserAccountDTO("ROhan",lastName,"1/01/1990","Indian","India","Services");
        String userAccountDTO=new Gson().toJson(this.userAccountDTO);
        MvcResult result = this.mockMvc.perform(post("/directrice/banking/user/account")
                .header("token","token")
                .content(userAccountDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400,result.getResponse().getStatus());
        assertEquals("LastName pattern doesn't match.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());
    }

    //BirthDay

    @Test
    public void givenInValidUserInfo_NATURAL_BirthDate_NULL_WhenAdded_shouldReturnValidResponse() throws Exception {
        String dob=null;
        this.userAccountDTO=new UserAccountDTO("Rohan","Lastname",dob,"Indian","India","Services");
        String userAccountDTO=new Gson().toJson(this.userAccountDTO);
        MvcResult result = this.mockMvc.perform(post("/directrice/banking/user/account")
                .header("token","token")
                .content(userAccountDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400,result.getResponse().getStatus());
        assertEquals("Birth Date pattern doesn't match.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());
    }
    @Test
    public void givenInValidUserInfo_NATURAL_BirthDate_EMPTY_WhenAdded_shouldReturnValidResponse() throws Exception {
        String dob="";
        this.userAccountDTO=new UserAccountDTO("Rohan","Lastname",dob,"Indian","India","Services");
        String userAccountDTO=new Gson().toJson(this.userAccountDTO);
        MvcResult result = this.mockMvc.perform(post("/directrice/banking/user/account")
                .header("token","token")
                .content(userAccountDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400,result.getResponse().getStatus());
        assertEquals("Birth Date pattern doesn't match.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());
    }

    //Nationality

    @Test
    public void givenInValidUserInfo_NATURAL_Nationality_NULL_WhenAdded_shouldReturnValidResponse() throws Exception {
        String Nationality=null;
        this.userAccountDTO=new UserAccountDTO("Rohan","LastName","1/01/1990",Nationality,"India","Services");
        String userAccountDTO=new Gson().toJson(this.userAccountDTO);
        MvcResult result = this.mockMvc.perform(post("/directrice/banking/user/account")
                .header("token","token")
                .content(userAccountDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400,result.getResponse().getStatus());
        assertEquals("Nationality pattern doesn't match.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());
    }
    @Test
    public void givenInValidUserInfo_NATURAL_Nationality_EMPTY_WhenAdded_shouldReturnValidResponse() throws Exception {
        String Nationality="";
        this.userAccountDTO=new UserAccountDTO("Rohan","lastName","1/01/1990",Nationality,"India","Services");
        String userAccountDTO=new Gson().toJson(this.userAccountDTO);
        MvcResult result = this.mockMvc.perform(post("/directrice/banking/user/account")
                .header("token","token")
                .content(userAccountDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400,result.getResponse().getStatus());
    }
    @Test
    public void givenInValidUserInfo_NATURAL_Nationality_MIN_WhenAdded_shouldReturnValidResponse() throws Exception {
        String Nationality="J";
        this.userAccountDTO=new UserAccountDTO("ROhan","LastName","1/01/1990",Nationality,"India","Services");
        String userAccountDTO=new Gson().toJson(this.userAccountDTO);
        MvcResult result = this.mockMvc.perform(post("/directrice/banking/user/account")
                .header("token","token")
                .content(userAccountDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400,result.getResponse().getStatus());
        assertEquals("Nationality pattern doesn't match.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());
    }
    @Test
    public void givenInValidUserInfo_NATURAL_Nationality_Max_WhenAdded_shouldReturnValidResponse() throws Exception {
        String Nationality="asdfgjsdfdfhfgjghqwewrerytttuuertgfdsfgg";
        this.userAccountDTO=new UserAccountDTO("ROhan","Lastname","1/01/1990",Nationality,"India","Services");
        String userAccountDTO=new Gson().toJson(this.userAccountDTO);
        MvcResult result = this.mockMvc.perform(post("/directrice/banking/user/account")
                .header("token","token")
                .content(userAccountDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400,result.getResponse().getStatus());
        assertEquals("Nationality pattern doesn't match.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());
    }
    @Test
    public void givenInValidUserInfo_NATURAL_Nationality_Pattern_WhenAdded_shouldReturnValidResponse() throws Exception {
        String Nationality="Rohan!2";
        this.userAccountDTO=new UserAccountDTO("ROhan","LastName","1/01/1990",Nationality,"India","Services");
        String userAccountDTO=new Gson().toJson(this.userAccountDTO);
        MvcResult result = this.mockMvc.perform(post("/directrice/banking/user/account")
                .header("token","token")
                .content(userAccountDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400,result.getResponse().getStatus());
        assertEquals("Nationality pattern doesn't match.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());
    }

    //Country Of Residence

    @Test
    public void givenInValidUserInfo_NATURAL_CountryOfResidence_NULL_WhenAdded_shouldReturnValidResponse() throws Exception {
        String CountryOfResidence=null;
        this.userAccountDTO=new UserAccountDTO("Rohan","LastName","1/01/1990","Indian",CountryOfResidence,"Services");
        String userAccountDTO=new Gson().toJson(this.userAccountDTO);
        MvcResult result = this.mockMvc.perform(post("/directrice/banking/user/account")
                .header("token","token")
                .content(userAccountDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400,result.getResponse().getStatus());
        assertEquals("Country Of Residence pattern doesn't match.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());
    }
    @Test
    public void givenInValidUserInfo_NATURAL_CountryOfResidence_EMPTY_WhenAdded_shouldReturnValidResponse() throws Exception {
        String CountryOfResidence="";
        this.userAccountDTO=new UserAccountDTO("Rohan","lastName","1/01/1990","Indian",CountryOfResidence,"Services");
        String userAccountDTO=new Gson().toJson(this.userAccountDTO);
        MvcResult result = this.mockMvc.perform(post("/directrice/banking/user/account")
                .header("token","token")
                .content(userAccountDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400,result.getResponse().getStatus());
        assertEquals("Country Of Residence pattern doesn't match.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());
    }
    @Test
    public void givenInValidUserInfo_NATURAL_CountryOfResidence_MIN_WhenAdded_shouldReturnValidResponse() throws Exception {
        String CountryOfResidence="J";
        this.userAccountDTO=new UserAccountDTO("ROhan","LastName","1/01/1990","Indian",CountryOfResidence,"Services");
        String userAccountDTO=new Gson().toJson(this.userAccountDTO);
        MvcResult result = this.mockMvc.perform(post("/directrice/banking/user/account")
                .header("token","token")
                .content(userAccountDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400,result.getResponse().getStatus());
        assertEquals("Country Of Residence pattern doesn't match.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());
    }
    @Test
    public void givenInValidUserInfo_NATURAL_CountryOfResidence_Max_WhenAdded_shouldReturnValidResponse() throws Exception {
        String CountryOfResidence="asdfgjsdfdfhfgjghqwewrerytttuuertgfdsfggnkzsdfgmtrerytfqwryuiopfcsdvcxcvbvaffdvcvsvcvvvccvefghgtrsdcxvnhjhfgddsfcvghggfdsfvcxsgsdfxgfcdrtrschgugdtydxgfcct";
        this.userAccountDTO=new UserAccountDTO("ROhan","Lastname","1/01/1990","Indian",CountryOfResidence,"Services");
        String userAccountDTO=new Gson().toJson(this.userAccountDTO);
        MvcResult result = this.mockMvc.perform(post("/directrice/banking/user/account")
                .header("token","token")
                .content(userAccountDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400,result.getResponse().getStatus());
        assertEquals("Country Of Residence pattern doesn't match.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());
    }
    @Test
    public void givenInValidUserInfo_NATURAL_CountryOfResidence_Pattern_WhenAdded_shouldReturnValidResponse() throws Exception {
        String CountryOfResidence="Rohan!2";
        this.userAccountDTO=new UserAccountDTO("ROhan","LastName","1/01/1990","Indian",CountryOfResidence,"Services");
        String userAccountDTO=new Gson().toJson(this.userAccountDTO);
        MvcResult result = this.mockMvc.perform(post("/directrice/banking/user/account")
                .header("token","token")
                .content(userAccountDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400,result.getResponse().getStatus());
        assertEquals("Country Of Residence pattern doesn't match.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());
    }

    //Occupation

    @Test
    public void givenInValidUserInfo_NATURAL_Occupation_NULL_WhenAdded_shouldReturnValidResponse() throws Exception {
        String Occupation=null;
        this.userAccountDTO=new UserAccountDTO("Rohan","LastName","1/01/1990","Indian","India",Occupation);
        String userAccountDTO=new Gson().toJson(this.userAccountDTO);
        MvcResult result = this.mockMvc.perform(post("/directrice/banking/user/account")
                .header("token","token")
                .content(userAccountDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400,result.getResponse().getStatus());
        assertEquals("Occupation pattern doesn't match.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());
    }
    @Test
    public void givenInValidUserInfo_NATURAL_Occupation_EMPTY_WhenAdded_shouldReturnValidResponse() throws Exception {
        String Occupation="";
        this.userAccountDTO=new UserAccountDTO("Rohan","lastName","1/01/1990","Indian","India",Occupation);
        String userAccountDTO=new Gson().toJson(this.userAccountDTO);
        MvcResult result = this.mockMvc.perform(post("/directrice/banking/user/account")
                .header("token","token")
                .content(userAccountDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400,result.getResponse().getStatus());
        assertEquals("Occupation pattern doesn't match.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());
    }
    @Test
    public void givenInValidUserInfo_NATURAL_Occupation_MIN_WhenAdded_shouldReturnValidResponse() throws Exception {
        String Occupation="J";
        this.userAccountDTO=new UserAccountDTO("ROhan","LastName","1/01/1990","Indian","India",Occupation);
        String userAccountDTO=new Gson().toJson(this.userAccountDTO);
        MvcResult result = this.mockMvc.perform(post("/directrice/banking/user/account")
                .header("token","token")
                .content(userAccountDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400,result.getResponse().getStatus());
        assertEquals("Occupation pattern doesn't match.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());
    }
    @Test
    public void givenInValidUserInfo_NATURAL_Occupation_Max_WhenAdded_shouldReturnValidResponse() throws Exception {
        String Occupation="trsdcxvnhjhfgddsfcvghggfdsfvcxsgsdfxgfcdrtrschgugdtydxgfcct";
        this.userAccountDTO=new UserAccountDTO("ROhan","Lastname","1/01/1990","Indian","India",Occupation);
        String userAccountDTO=new Gson().toJson(this.userAccountDTO);
        MvcResult result = this.mockMvc.perform(post("/directrice/banking/user/account")
                .header("token","token")
                .content(userAccountDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400,result.getResponse().getStatus());
        assertEquals("Occupation pattern doesn't match.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());
    }
    @Test
    public void givenInValidUserInfo_NATURAL_Occupation_Pattern_WhenAdded_shouldReturnValidResponse() throws Exception {
        String Occupation="Rohan!2";
        this.userAccountDTO=new UserAccountDTO("ROhan","LastName","1/01/1990","Indian","India",Occupation);
        String userAccountDTO=new Gson().toJson(this.userAccountDTO);
        MvcResult result = this.mockMvc.perform(post("/directrice/banking/user/account")
                .header("token","token")
                .content(userAccountDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400,result.getResponse().getStatus());
        assertEquals("Occupation pattern doesn't match.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());
    }

    //EDITED User Account
    @Test
    public void givenValidUserInfo_NATURAL_WhenEdited_shouldReturnValidResponse() throws Exception {
        this.userAccountDTO=new UserAccountDTO("Rohan","kadam","1/01/1990","Indian","India","Services");
        String userAccountDTO=new Gson().toJson(this.userAccountDTO);
        MvcResult result = this.mockMvc.perform(put("/directrice/banking/user/account")
                .header("token","token")
                .header("accountId","accountId")
                .content(userAccountDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(200, result.getResponse().getStatus());
    }
    //Natural FirstName
    @Test
    public void givenInValidUserInfo_NATURAL_FirstName_NULL_WhenEdited_shouldReturnValidResponse() throws Exception {
        String firstName=null;
        this.userAccountDTO=new UserAccountDTO(firstName,"kadam","1/01/1990","Indian","India","Services");
        String userAccountDTO=new Gson().toJson(this.userAccountDTO);
        MvcResult result = this.mockMvc.perform(put("/directrice/banking/user/account")
                .header("token","token")
                .header("accountId","accountId")
                .content(userAccountDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400,result.getResponse().getStatus());
        assertEquals("FirstName pattern doesn't match.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());
    }
    @Test
    public void givenInValidUserInfo_NATURAL_FirstName_EMPTY_WhenEdited_shouldReturnValidResponse() throws Exception {
        String firstName="";
        this.userAccountDTO=new UserAccountDTO(firstName,"kadam","1/01/1990","Indian","India","Services");
        String userAccountDTO=new Gson().toJson(this.userAccountDTO);
        MvcResult result = this.mockMvc.perform(put("/directrice/banking/user/account")
                .header("token","token")
                .header("accountId","accountId")
                .content(userAccountDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400,result.getResponse().getStatus());
        assertEquals("FirstName pattern doesn't match.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());
    }
    @Test
    public void givenInValidUserInfo_NATURAL_FirstName_MIN_WhenEdited_shouldReturnValidResponse() throws Exception {
        String firstName="J";
        this.userAccountDTO=new UserAccountDTO(firstName,"kadam","1/01/1990","Indian","India","Services");
        String userAccountDTO=new Gson().toJson(this.userAccountDTO);
        MvcResult result = this.mockMvc.perform(put("/directrice/banking/user/account")
                .header("token","token")
                .header("accountId","accountId")
                .content(userAccountDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400,result.getResponse().getStatus());
        assertEquals("FirstName pattern doesn't match.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());
    }
    @Test
    public void givenInValidUserInfo_NATURAL_FirstName_Max_WhenEdited_shouldReturnValidResponse() throws Exception {
        String firstName="asdfgjsdfdfhfgjghqwewrerytttuuertgfdsfgg";
        this.userAccountDTO=new UserAccountDTO(firstName,"kadam","1/01/1990","Indian","India","Services");
        String userAccountDTO=new Gson().toJson(this.userAccountDTO);
        MvcResult result = this.mockMvc.perform(put("/directrice/banking/user/account")
                .header("token","token")
                .header("accountId","accountId")
                .content(userAccountDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400,result.getResponse().getStatus());
        assertEquals("FirstName pattern doesn't match.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());
    }
    @Test
    public void givenInValidUserInfo_NATURAL_FirstName_Pattern_WhenEdited_shouldReturnValidResponse() throws Exception {
        String firstName="Rohan!2";
        this.userAccountDTO=new UserAccountDTO(firstName,"kadam","1/01/1990","Indian","India","Services");
        String userAccountDTO=new Gson().toJson(this.userAccountDTO);
        MvcResult result = this.mockMvc.perform(put("/directrice/banking/user/account")
                .header("token","token")
                .header("accountId","accountId")
                .content(userAccountDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400,result.getResponse().getStatus());
        assertEquals("FirstName pattern doesn't match.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());
    }
//LastName

    @Test
    public void givenInValidUserInfo_NATURAL_LastName_NULL_WhenEdited_shouldReturnValidResponse() throws Exception {
        String lastName=null;
        this.userAccountDTO=new UserAccountDTO("Rohan",lastName,"1/01/1990","Indian","India","Services");
        String userAccountDTO=new Gson().toJson(this.userAccountDTO);
        MvcResult result = this.mockMvc.perform(put("/directrice/banking/user/account")
                .header("token","token")
                .header("accountId","accountId")
                .content(userAccountDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400,result.getResponse().getStatus());
        assertEquals("LastName pattern doesn't match.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());
    }
    @Test
    public void givenInValidUserInfo_NATURAL_LastName_EMPTY_WhenEdited_shouldReturnValidResponse() throws Exception {
        String lastName="";
        this.userAccountDTO=new UserAccountDTO("Rohan",lastName,"1/01/1990","Indian","India","Services");
        String userAccountDTO=new Gson().toJson(this.userAccountDTO);
        MvcResult result = this.mockMvc.perform(put("/directrice/banking/user/account")
                .header("token","token")
                .header("accountId","accountId")
                .content(userAccountDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400,result.getResponse().getStatus());
        assertEquals("LastName pattern doesn't match.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());
    }
    @Test
    public void givenInValidUserInfo_NATURAL_LastName_MIN_WhenEdited_shouldReturnValidResponse() throws Exception {
        String lastName="J";
        this.userAccountDTO=new UserAccountDTO("ROhan",lastName,"1/01/1990","Indian","India","Services");
        String userAccountDTO=new Gson().toJson(this.userAccountDTO);
        MvcResult result = this.mockMvc.perform(put("/directrice/banking/user/account")
                .header("token","token")
                .header("accountId","accountId")
                .content(userAccountDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400,result.getResponse().getStatus());
        assertEquals("LastName pattern doesn't match.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());
    }
    @Test
    public void givenInValidUserInfo_NATURAL_LastName_Max_WhenEdited_shouldReturnValidResponse() throws Exception {
        String lastName="asdfgjsdfdfhfgjghqwewrerytttuuertgfdsfgg";
        this.userAccountDTO=new UserAccountDTO("ROhan",lastName,"1/01/1990","Indian","India","Services");
        String userAccountDTO=new Gson().toJson(this.userAccountDTO);
        MvcResult result = this.mockMvc.perform(put("/directrice/banking/user/account")
                .header("token","token")
                .header("accountId","accountId")
                .content(userAccountDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400,result.getResponse().getStatus());
        assertEquals("LastName pattern doesn't match.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());
    }
    @Test
    public void givenInValidUserInfo_NATURAL_LastName_Pattern_WhenEdited_shouldReturnValidResponse() throws Exception {
        String lastName="Rohan!2";
        this.userAccountDTO=new UserAccountDTO("ROhan",lastName,"1/01/1990","Indian","India","Services");
        String userAccountDTO=new Gson().toJson(this.userAccountDTO);
        MvcResult result = this.mockMvc.perform(put("/directrice/banking/user/account")
                .header("token","token")
                .header("accountId","accountId")
                .content(userAccountDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400,result.getResponse().getStatus());
        assertEquals("LastName pattern doesn't match.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());
    }

    //BirthDay

    @Test
    public void givenInValidUserInfo_NATURAL_BirthDate_NULL_WhenEdited_shouldReturnValidResponse() throws Exception {
        String dob=null;
        this.userAccountDTO=new UserAccountDTO("Rohan","Lastname",dob,"Indian","India","Services");
        String userAccountDTO=new Gson().toJson(this.userAccountDTO);
        MvcResult result = this.mockMvc.perform(put("/directrice/banking/user/account")
                .header("token","token")
                .header("accountId","accountId")
                .content(userAccountDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400,result.getResponse().getStatus());
        assertEquals("Birth Date pattern doesn't match.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());
    }
    @Test
    public void givenInValidUserInfo_NATURAL_BirthDate_EMPTY_WhenEdited_shouldReturnValidResponse() throws Exception {
        String dob="";
        this.userAccountDTO=new UserAccountDTO("Rohan","Lastname",dob,"Indian","India","Services");
        String userAccountDTO=new Gson().toJson(this.userAccountDTO);
        MvcResult result = this.mockMvc.perform(put("/directrice/banking/user/account")
                .header("token","token")
                .header("accountId","accountId")
                .content(userAccountDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400,result.getResponse().getStatus());
        assertEquals("Birth Date pattern doesn't match.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());
    }

    //Nationality

    @Test
    public void givenInValidUserInfo_NATURAL_Nationality_NULL_WhenEdited_shouldReturnValidResponse() throws Exception {
        String Nationality=null;
        this.userAccountDTO=new UserAccountDTO("Rohan","LastName","1/01/1990",Nationality,"India","Services");
        String userAccountDTO=new Gson().toJson(this.userAccountDTO);
        MvcResult result = this.mockMvc.perform(put("/directrice/banking/user/account")
                .header("token","token")
                .header("accountId","accountId")
                .content(userAccountDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400,result.getResponse().getStatus());
        assertEquals("Nationality pattern doesn't match.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());
    }
    @Test
    public void givenInValidUserInfo_NATURAL_Nationality_EMPTY_WhenEdited_shouldReturnValidResponse() throws Exception {
        String Nationality="";
        this.userAccountDTO=new UserAccountDTO("Rohan","lastName","1/01/1990",Nationality,"India","Services");
        String userAccountDTO=new Gson().toJson(this.userAccountDTO);
        MvcResult result = this.mockMvc.perform(put("/directrice/banking/user/account")
                .header("token","token")
                .header("accountId","accountId")
                .content(userAccountDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400,result.getResponse().getStatus());
        assertEquals("Nationality pattern doesn't match.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());
    }
    @Test
    public void givenInValidUserInfo_NATURAL_Nationality_MIN_WhenEdited_shouldReturnValidResponse() throws Exception {
        String Nationality="J";
        this.userAccountDTO=new UserAccountDTO("ROhan","LastName","1/01/1990",Nationality,"India","Services");
        String userAccountDTO=new Gson().toJson(this.userAccountDTO);
        MvcResult result = this.mockMvc.perform(put("/directrice/banking/user/account")
                .header("token","token")
                .header("accountId","accountId")
                .content(userAccountDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400,result.getResponse().getStatus());
        assertEquals("Nationality pattern doesn't match.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());
    }
    @Test
    public void givenInValidUserInfo_NATURAL_Nationality_Max_WhenEdited_shouldReturnValidResponse() throws Exception {
        String Nationality="asdfgjsdfdfhfgjghqwewrerytttuuertgfdsfgg";
        this.userAccountDTO=new UserAccountDTO("ROhan","Lastname","1/01/1990",Nationality,"India","Services");
        String userAccountDTO=new Gson().toJson(this.userAccountDTO);
        MvcResult result = this.mockMvc.perform(put("/directrice/banking/user/account")
                .header("token","token")
                .header("accountId","accountId")
                .content(userAccountDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400,result.getResponse().getStatus());
        assertEquals("Nationality pattern doesn't match.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());
    }
    @Test
    public void givenInValidUserInfo_NATURAL_Nationality_Pattern_WhenEdited_shouldReturnValidResponse() throws Exception {
        String Nationality="Rohan!2";
        this.userAccountDTO=new UserAccountDTO("ROhan","LastName","1/01/1990",Nationality,"India","Services");
        String userAccountDTO=new Gson().toJson(this.userAccountDTO);
        MvcResult result = this.mockMvc.perform(put("/directrice/banking/user/account")
                .header("token","token")
                .header("accountId","accountId")
                .content(userAccountDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400,result.getResponse().getStatus());
        assertEquals("Nationality pattern doesn't match.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());
    }

    //Country Of Residence

    @Test
    public void givenInValidUserInfo_NATURAL_CountryOfResidence_NULL_WhenEdited_shouldReturnValidResponse() throws Exception {
        String CountryOfResidence=null;
        this.userAccountDTO=new UserAccountDTO("Rohan","LastName","1/01/1990","Indian",CountryOfResidence,"Services");
        String userAccountDTO=new Gson().toJson(this.userAccountDTO);
        MvcResult result = this.mockMvc.perform(put("/directrice/banking/user/account")
                .header("token","token")
                .header("accountId","accountId")
                .content(userAccountDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400,result.getResponse().getStatus());
        assertEquals("Country Of Residence pattern doesn't match.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());
    }
    @Test
    public void givenInValidUserInfo_NATURAL_CountryOfResidence_EMPTY_WhenEdited_shouldReturnValidResponse() throws Exception {
        String CountryOfResidence="";
        this.userAccountDTO=new UserAccountDTO("Rohan","lastName","1/01/1990","Indian",CountryOfResidence,"Services");
        String userAccountDTO=new Gson().toJson(this.userAccountDTO);
        MvcResult result = this.mockMvc.perform(put("/directrice/banking/user/account")
                .header("token","token")
                .header("accountId","accountId")
                .content(userAccountDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400,result.getResponse().getStatus());
        assertEquals("Country Of Residence pattern doesn't match.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());
    }
    @Test
    public void givenInValidUserInfo_NATURAL_CountryOfResidence_MIN_WhenEdited_shouldReturnValidResponse() throws Exception {
        String CountryOfResidence="J";
        this.userAccountDTO=new UserAccountDTO("ROhan","LastName","1/01/1990","Indian",CountryOfResidence,"Services");
        String userAccountDTO=new Gson().toJson(this.userAccountDTO);
        MvcResult result = this.mockMvc.perform(put("/directrice/banking/user/account")
                .header("token","token")
                .header("accountId","accountId")
                .content(userAccountDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400,result.getResponse().getStatus());
        assertEquals("Country Of Residence pattern doesn't match.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());
    }
    @Test
    public void givenInValidUserInfo_NATURAL_CountryOfResidence_Max_WhenEdited_shouldReturnValidResponse() throws Exception {
        String CountryOfResidence="asdfgjsdfdfhfgjghqwewrerytttuuertgfdsfggnkzsdfgmtrerytfqwryuiopfcsdvcxcvbvaffdvcvsvcvvvccvefghgtrsdcxvnhjhfgddsfcvghggfdsfvcxsgsdfxgfcdrtrschgugdtydxgfcct";
        this.userAccountDTO=new UserAccountDTO("ROhan","Lastname","1/01/1990","Indian",CountryOfResidence,"Services");
        String userAccountDTO=new Gson().toJson(this.userAccountDTO);
        MvcResult result = this.mockMvc.perform(put("/directrice/banking/user/account")
                .header("token","token")
                .header("accountId","accountId")
                .content(userAccountDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400,result.getResponse().getStatus());
        assertEquals("Country Of Residence pattern doesn't match.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());
    }
    @Test
    public void givenInValidUserInfo_NATURAL_CountryOfResidence_Pattern_WhenEdited_shouldReturnValidResponse() throws Exception {
        String CountryOfResidence="Rohan!2";
        this.userAccountDTO=new UserAccountDTO("ROhan","LastName","1/01/1990","Indian",CountryOfResidence,"Services");
        String userAccountDTO=new Gson().toJson(this.userAccountDTO);
        MvcResult result = this.mockMvc.perform(put("/directrice/banking/user/account")
                .header("token","token")
                .header("accountId","accountId")
                .content(userAccountDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400,result.getResponse().getStatus());
        assertEquals("Country Of Residence pattern doesn't match.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());
    }

    //Occupation

    @Test
    public void givenInValidUserInfo_NATURAL_Occupation_NULL_WhenEdited_shouldReturnValidResponse() throws Exception {
        String Occupation=null;
        this.userAccountDTO=new UserAccountDTO("Rohan","LastName","1/01/1990","Indian","India",Occupation);
        String userAccountDTO=new Gson().toJson(this.userAccountDTO);
        MvcResult result = this.mockMvc.perform(put("/directrice/banking/user/account")
                .header("token","token")
                .header("accountId","accountId")
                .content(userAccountDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400,result.getResponse().getStatus());
        assertEquals("Occupation pattern doesn't match.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());
    }
    @Test
    public void givenInValidUserInfo_NATURAL_Occupation_EMPTY_WhenEdited_shouldReturnValidResponse() throws Exception {
        String Occupation="";
        this.userAccountDTO=new UserAccountDTO("Rohan","lastName","1/01/1990","Indian","India",Occupation);
        String userAccountDTO=new Gson().toJson(this.userAccountDTO);
        MvcResult result = this.mockMvc.perform(put("/directrice/banking/user/account")
                .header("token","token")
                .header("accountId","accountId")
                .content(userAccountDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400,result.getResponse().getStatus());
        assertEquals("Occupation pattern doesn't match.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());
    }
    @Test
    public void givenInValidUserInfo_NATURAL_Occupation_MIN_WhenEdited_shouldReturnValidResponse() throws Exception {
        String Occupation="J";
        this.userAccountDTO=new UserAccountDTO("ROhan","LastName","1/01/1990","Indian","India",Occupation);
        String userAccountDTO=new Gson().toJson(this.userAccountDTO);
        MvcResult result = this.mockMvc.perform(put("/directrice/banking/user/account")
                .header("token","token")
                .header("accountId","accountId")
                .content(userAccountDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400,result.getResponse().getStatus());
        assertEquals("Occupation pattern doesn't match.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());
    }
    @Test
    public void givenInValidUserInfo_NATURAL_Occupation_Max_WhenEdited_shouldReturnValidResponse() throws Exception {
        String Occupation="trsdcxvnhjhfgddsfcvghggfdsfvcxsgsdfxgfcdrtrschgugdtydxgfcct";
        this.userAccountDTO=new UserAccountDTO("ROhan","Lastname","1/01/1990","Indian","India",Occupation);
        String userAccountDTO=new Gson().toJson(this.userAccountDTO);
        MvcResult result = this.mockMvc.perform(put("/directrice/banking/user/account")
                .header("token","token")
                .header("accountId","accountId")
                .content(userAccountDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400,result.getResponse().getStatus());
        assertEquals("Occupation pattern doesn't match.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());
    }
    @Test
    public void givenInValidUserInfo_NATURAL_Occupation_Pattern_WhenEdited_shouldReturnValidResponse() throws Exception {
        String Occupation="Rohan!2";
        this.userAccountDTO=new UserAccountDTO("ROhan","LastName","1/01/1990","Indian","India",Occupation);
        String userAccountDTO=new Gson().toJson(this.userAccountDTO);
        MvcResult result = this.mockMvc.perform(put("/directrice/banking/user/account")
                .header("token","token")
                .header("accountId","accountId")
                .content(userAccountDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400,result.getResponse().getStatus());
        assertEquals("Occupation pattern doesn't match.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());
    }

    //Getting
    @Test
    public void givenValidUserToken_NATURAL_WhenGetting_shouldReturnValidResponse() throws Exception {

        MvcResult result = this.mockMvc.perform(get("/directrice/banking/user/"+"accountId")
                .header("token","token")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(200, result.getResponse().getStatus());
    }

    @Test
    public void givenInValidUserToken_NATURAL_WhenGetting_shouldReturnValidResponse() throws Exception {

        MvcResult result = this.mockMvc.perform(get("/directrice/banking/user/")
                .header("token","token")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(404, result.getResponse().getStatus());
    }
    @Test
    public void givenValid_NATURAL_WhenGetting_shouldReturnValidResponse() throws Exception {

        MvcResult result = this.mockMvc.perform(get("/directrice/banking/user/accounts")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(200, result.getResponse().getStatus());
    }

    ///Organisation Account Controller.
    //ADDED
    @Test
    public void givenValidOrganisationDto_LEGAL_WhenAdded_shouldReturnValidResponse() throws Exception {
        this.addressDTO=new AddressDTO("Marine Drive,Mumbai","Mumbai","Mumbai","Maharashtra","India","400025");

        this.organisationDto=new OrganisationDTO("Directrice Pvt LTD","INMHMU4120","www.directrice.com","Business",this.addressDTO,"12/12/2019");
        String organisationDto=new Gson().toJson(this.organisationDto);
        MvcResult result = this.mockMvc.perform(post("/directrice/banking/organisation/account")
                .header("token","token")
                .content(organisationDto)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(201,result.getResponse().getStatus());
    }
    //Organisation name
    @Test
    public void givenInValidOrganisationDTO_LEGAL_Name_NULL_WhenAdded_shouldReturnValidResponse() throws Exception {
        this.addressDTO=new AddressDTO("Marine Drive,Mumbai","Mumbai","Mumbai","Maharashtra","India","400025");

        String name=null;
        this.organisationDto=new OrganisationDTO(name,"INMHMU4120","www.directrice.com","Business",this.addressDTO,"12/12/2019");
        String organisationDto=new Gson().toJson(this.organisationDto);
        MvcResult result = this.mockMvc.perform(post("/directrice/banking/organisation/account")
                .header("token","token")
                .content(organisationDto)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400,result.getResponse().getStatus());
        assertEquals("Organisation Name cannot be empty.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());
    }

    @Test
    public void givenInValidOrganisationDTO_LEGAL_Name_Empty_WhenAdded_shouldReturnValidResponse() throws Exception {
        this.addressDTO=new AddressDTO("Marine Drive,Mumbai","Mumbai","Mumbai","Maharashtra","India","400025");
        String name="";
        this.organisationDto=new OrganisationDTO(name,"INMHMU4120","www.directrice.com","Business",this.addressDTO,"12/12/2019");
        String organisationDto=new Gson().toJson(this.organisationDto);
        MvcResult result = this.mockMvc.perform(post("/directrice/banking/organisation/account")
                .header("token","token")
                .content(organisationDto)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400,result.getResponse().getStatus());
        assertEquals("Organisation Name cannot be empty.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());
    }


    //Organisation LicenceNumber
    @Test
    public void givenInValidOrganisationDTO_LEGAL_licenseNumber_NULL_WhenAdded_shouldReturnValidResponse() throws Exception {
        this.addressDTO=new AddressDTO("Marine Drive,Mumbai","Mumbai","Mumbai","Maharashtra","India","400025");

        String licenseNumber=null;
        this.organisationDto=new OrganisationDTO("Directrice",licenseNumber,"www.directrice.com","Business",this.addressDTO,"12/12/2019");
        String organisationDto=new Gson().toJson(this.organisationDto);
        MvcResult result = this.mockMvc.perform(post("/directrice/banking/organisation/account")
                .header("token","token")
                .content(organisationDto)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400,result.getResponse().getStatus());
        assertEquals("Organisation licenseNumber cannot be empty.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());
    }

    @Test
    public void givenInValidOrganisationDTO_LEGAL_licenseNumber_EMpty_WhenAdded_shouldReturnValidResponse() throws Exception {
        this.addressDTO=new AddressDTO("Marine Drive,Mumbai","Mumbai","Mumbai","Maharashtra","India","400025");
        String licenseNumber="";
        this.organisationDto=new OrganisationDTO("Directrice",licenseNumber,"www.directrice.com","Business",this.addressDTO,"12/12/2019");
        String organisationDto=new Gson().toJson(this.organisationDto);
        MvcResult result = this.mockMvc.perform(post("/directrice/banking/organisation/account")
                .header("token","token")
                .content(organisationDto)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400,result.getResponse().getStatus());
        assertEquals("Organisation licenseNumber length should be greater than 4.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());
    }

    @Test
    public void givenInValidOrganisationDTO_LEGAL_licenseNumber_Length_WhenAdded_shouldReturnValidResponse() throws Exception {
        this.addressDTO=new AddressDTO("Marine Drive,Mumbai","Mumbai","Mumbai","Maharashtra","India","400025");
        String licenseNumber="123";
        this.organisationDto=new OrganisationDTO("Directrice",licenseNumber,"www.directrice.com","Business",this.addressDTO,"12/12/2019");
        String organisationDto=new Gson().toJson(this.organisationDto);
        MvcResult result = this.mockMvc.perform(post("/directrice/banking/organisation/account")
                .header("token","token")
                .content(organisationDto)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400,result.getResponse().getStatus());
        assertEquals("Organisation licenseNumber length should be greater than 4.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());
    }
    //Organisation Type
    @Test
    public void givenInValidOrganisationDTO_LEGAL_type_NULL_WhenAdded_shouldReturnValidResponse() throws Exception {
        this.addressDTO=new AddressDTO("Marine Drive,Mumbai","Mumbai","Mumbai","Maharashtra","India","400025");

        String type=null;
        this.organisationDto=new OrganisationDTO("Directrice","INMH4256","www.directrice.com",type,this.addressDTO,"12/12/2019");
        String organisationDto=new Gson().toJson(this.organisationDto);
        MvcResult result = this.mockMvc.perform(post("/directrice/banking/organisation/account")
                .header("token","token")
                .content(organisationDto)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400,result.getResponse().getStatus());
        assertEquals("Organisation type cannot be empty.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());
    }

    @Test
    public void givenInValidOrganisationDTO_LEGAL_type_EMpty_WhenAdded_shouldReturnValidResponse() throws Exception {
        this.addressDTO=new AddressDTO("Marine Drive,Mumbai","Mumbai","Mumbai","Maharashtra","India","400025");
        String type="";
        this.organisationDto=new OrganisationDTO("Directrice","INMH4256","www.directrice.com",type,this.addressDTO,"12/12/2019");
        String organisationDto=new Gson().toJson(this.organisationDto);
        MvcResult result = this.mockMvc.perform(post("/directrice/banking/organisation/account")
                .header("token","token")
                .content(organisationDto)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400,result.getResponse().getStatus());
        assertEquals("Organisation type length should be greater than 2.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());
    }

    @Test
    public void givenInValidOrganisationDTO_LEGAL_type_Length_WhenAdded_shouldReturnValidResponse() throws Exception {
        this.addressDTO=new AddressDTO("Marine Drive,Mumbai","Mumbai","Mumbai","Maharashtra","India","400025");
        String type="1";
        this.organisationDto=new OrganisationDTO("Directrice","INMH4256","www.directrice.com",type,this.addressDTO,"12/12/2019");
        String organisationDto=new Gson().toJson(this.organisationDto);
        MvcResult result = this.mockMvc.perform(post("/directrice/banking/organisation/account")
                .header("token","token")
                .content(organisationDto)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400,result.getResponse().getStatus());
        assertEquals("Organisation type length should be greater than 2.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());
    }
    //Organisation registration Date.
    @Test
    public void givenInValidOrganisationDTO_LEGAL_registrationDate_NULL_WhenAdded_shouldReturnValidResponse() throws Exception {
        this.addressDTO=new AddressDTO("Marine Drive,Mumbai","Mumbai","Mumbai","Maharashtra","India","400025");

        String registrationDate=null;
        this.organisationDto=new OrganisationDTO("Directrice","INMH4256","www.directrice.com","PVT LTD",this.addressDTO,registrationDate);
        String organisationDto=new Gson().toJson(this.organisationDto);
        MvcResult result = this.mockMvc.perform(post("/directrice/banking/organisation/account")
                .header("token","token")
                .content(organisationDto)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400,result.getResponse().getStatus());
        assertEquals("Organisation registrationDate cannot be empty.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());
    }

    @Test
    public void givenInValidOrganisationDTO_LEGAL_registrationDate_EMpty_WhenAdded_shouldReturnValidResponse() throws Exception {
        this.addressDTO=new AddressDTO("Marine Drive,Mumbai","Mumbai","Mumbai","Maharashtra","India","400025");
        String registrationDate="";
        this.organisationDto=new OrganisationDTO("Directrice","INMH4256","www.directrice.com","llc",this.addressDTO,registrationDate);
        String organisationDto=new Gson().toJson(this.organisationDto);
        MvcResult result = this.mockMvc.perform(post("/directrice/banking/organisation/account")
                .header("token","token")
                .content(organisationDto)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400,result.getResponse().getStatus());
        assertEquals("Organisation registrationDate cannot be empty.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());
    }
//EDITED
    @Test
    public void givenValidOrganisationDto_LEGAL_WhenEdited_shouldReturnValidResponse() throws Exception {
        this.addressDTO=new AddressDTO("Marine Drive,Mumbai","Mumbai","Mumbai","Maharashtra","India","400025");

        this.organisationDto=new OrganisationDTO("Directrice Pvt LTD","INMHMU4120","www.directrice.com","Business",this.addressDTO,"12/12/2019");
        String organisationDto=new Gson().toJson(this.organisationDto);
        MvcResult result = this.mockMvc.perform(put("/directrice/banking/organisation/account")
                .header("token","token")
                .header("accountId","accountId")
                .content(organisationDto)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(200,result.getResponse().getStatus());
    }

    //Organisation name
    @Test
    public void givenInValidOrganisationDTO_LEGAL_Name_NULL_WhenEdited_shouldReturnValidResponse() throws Exception {
        this.addressDTO=new AddressDTO("Marine Drive,Mumbai","Mumbai","Mumbai","Maharashtra","India","400025");

        String name=null;
        this.organisationDto=new OrganisationDTO(name,"INMHMU4120","www.directrice.com","Business",this.addressDTO,"12/12/2019");
        String organisationDto=new Gson().toJson(this.organisationDto);
        MvcResult result = this.mockMvc.perform(put("/directrice/banking/organisation/account")
                .header("token","token")
                .header("accountId","accountId")
                .content(organisationDto)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400,result.getResponse().getStatus());
        assertEquals("Organisation Name cannot be empty.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());
    }

    @Test
    public void givenInValidOrganisationDTO_LEGAL_Name_Empty_WhenEdited_shouldReturnValidResponse() throws Exception {
        this.addressDTO=new AddressDTO("Marine Drive,Mumbai","Mumbai","Mumbai","Maharashtra","India","400025");
        String name="";
        this.organisationDto=new OrganisationDTO(name,"INMHMU4120","www.directrice.com","Business",this.addressDTO,"12/12/2019");
        String organisationDto=new Gson().toJson(this.organisationDto);
        MvcResult result = this.mockMvc.perform(put("/directrice/banking/organisation/account")
                .header("token","token")
                .header("accountId","accountId")
                .content(organisationDto)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400,result.getResponse().getStatus());
        assertEquals("Organisation Name cannot be empty.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());
    }


    //Organisation LicenceNumber
    @Test
    public void givenInValidOrganisationDTO_LEGAL_licenseNumber_NULL_WhenEdited_shouldReturnValidResponse() throws Exception {
        this.addressDTO=new AddressDTO("Marine Drive,Mumbai","Mumbai","Mumbai","Maharashtra","India","400025");

        String licenseNumber=null;
        this.organisationDto=new OrganisationDTO("Directrice",licenseNumber,"www.directrice.com","Business",this.addressDTO,"12/12/2019");
        String organisationDto=new Gson().toJson(this.organisationDto);
        MvcResult result = this.mockMvc.perform(put("/directrice/banking/organisation/account")
                .header("token","token")
                .header("accountId","accountId")
                .content(organisationDto)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400,result.getResponse().getStatus());
        assertEquals("Organisation licenseNumber cannot be empty.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());
    }

    @Test
    public void givenInValidOrganisationDTO_LEGAL_licenseNumber_EMpty_WhenEdited_shouldReturnValidResponse() throws Exception {
        this.addressDTO=new AddressDTO("Marine Drive,Mumbai","Mumbai","Mumbai","Maharashtra","India","400025");
        String licenseNumber="";
        this.organisationDto=new OrganisationDTO("Directrice",licenseNumber,"www.directrice.com","Business",this.addressDTO,"12/12/2019");
        String organisationDto=new Gson().toJson(this.organisationDto);
        MvcResult result = this.mockMvc.perform(put("/directrice/banking/organisation/account")
                .header("token","token")
                .header("accountId","accountId")
                .content(organisationDto)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400,result.getResponse().getStatus());
        assertEquals("Organisation licenseNumber length should be greater than 4.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());
    }

    @Test
    public void givenInValidOrganisationDTO_LEGAL_licenseNumber_Length_WhenEdited_shouldReturnValidResponse() throws Exception {
        this.addressDTO=new AddressDTO("Marine Drive,Mumbai","Mumbai","Mumbai","Maharashtra","India","400025");
        String licenseNumber="123";
        this.organisationDto=new OrganisationDTO("Directrice",licenseNumber,"www.directrice.com","Business",this.addressDTO,"12/12/2019");
        String organisationDto=new Gson().toJson(this.organisationDto);
        MvcResult result = this.mockMvc.perform(put("/directrice/banking/organisation/account")
                .header("token","token")
                .header("accountId","accountId")
                .content(organisationDto)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400,result.getResponse().getStatus());
        assertEquals("Organisation licenseNumber length should be greater than 4.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());
    }
    //Organisation Type
    @Test
    public void givenInValidOrganisationDTO_LEGAL_type_NULL_WhenEdited_shouldReturnValidResponse() throws Exception {
        this.addressDTO=new AddressDTO("Marine Drive,Mumbai","Mumbai","Mumbai","Maharashtra","India","400025");

        String type=null;
        this.organisationDto=new OrganisationDTO("Directrice","INMH4256","www.directrice.com",type,this.addressDTO,"12/12/2019");
        String organisationDto=new Gson().toJson(this.organisationDto);
        MvcResult result = this.mockMvc.perform(put("/directrice/banking/organisation/account")
                .header("token","token")
                .header("accountId","accountId")
                .content(organisationDto)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400,result.getResponse().getStatus());
        assertEquals("Organisation type cannot be empty.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());
    }

    @Test
    public void givenInValidOrganisationDTO_LEGAL_type_EMpty_WhenEdited_shouldReturnValidResponse() throws Exception {
        this.addressDTO=new AddressDTO("Marine Drive,Mumbai","Mumbai","Mumbai","Maharashtra","India","400025");
        String type="";
        this.organisationDto=new OrganisationDTO("Directrice","INMH4256","www.directrice.com",type,this.addressDTO,"12/12/2019");
        String organisationDto=new Gson().toJson(this.organisationDto);
        MvcResult result = this.mockMvc.perform(put("/directrice/banking/organisation/account")
                .header("token","token")
                .header("accountId","accountId")
                .content(organisationDto)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400,result.getResponse().getStatus());
        assertEquals("Organisation type length should be greater than 2.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());
    }

    @Test
    public void givenInValidOrganisationDTO_LEGAL_type_Length_WhenEdited_shouldReturnValidResponse() throws Exception {
        this.addressDTO=new AddressDTO("Marine Drive,Mumbai","Mumbai","Mumbai","Maharashtra","India","400025");
        String type="1";
        this.organisationDto=new OrganisationDTO("Directrice","INMH4256","www.directrice.com",type,this.addressDTO,"12/12/2019");
        String organisationDto=new Gson().toJson(this.organisationDto);
        MvcResult result = this.mockMvc.perform(put("/directrice/banking/organisation/account")
                .header("token","token")
                .header("accountId","accountId")
                .content(organisationDto)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400,result.getResponse().getStatus());
        assertEquals("Organisation type length should be greater than 2.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());
    }
    //Organisation registration Date.
    @Test
    public void givenInValidOrganisationDTO_LEGAL_registrationDate_NULL_WhenEdited_shouldReturnValidResponse() throws Exception {
        this.addressDTO=new AddressDTO("Marine Drive,Mumbai","Mumbai","Mumbai","Maharashtra","India","400025");

        String registrationDate=null;
        this.organisationDto=new OrganisationDTO("Directrice","INMH4256","www.directrice.com","PVT LTD",this.addressDTO,registrationDate);
        String organisationDto=new Gson().toJson(this.organisationDto);
        MvcResult result = this.mockMvc.perform(put("/directrice/banking/organisation/account")
                .header("token","token")
                .header("accountId","accountId")
                .content(organisationDto)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400,result.getResponse().getStatus());
        assertEquals("Organisation registrationDate cannot be empty.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());
    }

    @Test
    public void givenInValidOrganisationDTO_LEGAL_registrationDate_EMpty_WhenEdited_shouldReturnValidResponse() throws Exception {
        this.addressDTO=new AddressDTO("Marine Drive,Mumbai","Mumbai","Mumbai","Maharashtra","India","400025");
        String registrationDate="";
        this.organisationDto=new OrganisationDTO("Directrice","INMH4256","www.directrice.com","llc",this.addressDTO,registrationDate);
        String organisationDto=new Gson().toJson(this.organisationDto);
        MvcResult result = this.mockMvc.perform(put("/directrice/banking/organisation/account")
                .header("token","token")
                .header("accountId","accountId")
                .content(organisationDto)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400,result.getResponse().getStatus());
        assertEquals("Organisation registrationDate cannot be empty.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());
    }


    //Get Organistaion
    @Test
    public void givenValidTokenAndAccountId_LEGAL_WhenGetting_shouldReturnValidResponse() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/directrice/banking/organisation/456mhin")
                .header("token","token")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(200,result.getResponse().getStatus());
    }
    
}
