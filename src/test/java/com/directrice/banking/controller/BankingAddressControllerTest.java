package com.directrice.banking.controller;

import com.directrice.banking.dto.AddressDTO;
import com.directrice.banking.dto.UserAccountDTO;
import com.directrice.banking.response.Response;
import com.directrice.banking.service.BankingAccountServiceImpl;
import com.directrice.banking.service.BankingAddressServiceImpl;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
public class BankingAddressControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BankingAddressServiceImpl bankingAddressService;

    private AddressDTO addressDTO;


    //Address Adding
    @Test
    void givenValidAddressDTO_WhenAdded_ShouldReturnValidResponse() throws Exception {
        this.addressDTO=new AddressDTO("Marine Drive,Mumbai","Mumbai","Mumbai","Maharashtra","India","400025");

        String addressDTO=new Gson().toJson(this.addressDTO);
        MvcResult result = this.mockMvc.perform(post("/directrice/banking/address")
                .header("token","token")
                .header("accountNumber","accountNumber")
                .content(addressDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(201, result.getResponse().getStatus());
    }
    //Address
    @Test
    void givenInValidAddressDTO_Address_Null_WhenAdded_ShouldReturnValidResponse() throws Exception {
        String address=null;
        this.addressDTO=new AddressDTO(address,"Mumbai","Mumbai","Maharashtra","India","400025");

        String addressDTO=new Gson().toJson(this.addressDTO);
        MvcResult result = this.mockMvc.perform(post("/directrice/banking/address")
                .header("token","token")
                .header("accountNumber","accountNumber")
                .content(addressDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400, result.getResponse().getStatus());
        assertEquals("Address pattern doesn't match.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());

    }

    @Test
    void givenInValidAddressDTO_Address_Empty_WhenAdded_ShouldReturnValidResponse() throws Exception {
        String address="";
        this.addressDTO=new AddressDTO(address,"Mumbai","Mumbai","Maharashtra","India","400025");

        String addressDTO=new Gson().toJson(this.addressDTO);
        MvcResult result = this.mockMvc.perform(post("/directrice/banking/address")
                .header("token","token")
                .header("accountNumber","accountNumber")
                .content(addressDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400, result.getResponse().getStatus());
        assertEquals("Address pattern doesn't match.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());

    }
    @Test
    void givenInValidAddressDTO_Address_Min_WhenAdded_ShouldReturnValidResponse() throws Exception {
        String address="m";
        this.addressDTO=new AddressDTO(address,"Mumbai","Mumbai","Maharashtra","India","400025");

        String addressDTO=new Gson().toJson(this.addressDTO);
        MvcResult result = this.mockMvc.perform(post("/directrice/banking/address")
                .header("token","token")
                .header("accountNumber","accountNumber")
                .content(addressDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400, result.getResponse().getStatus());
        assertEquals("Address pattern doesn't match.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());

    }
    @Test
    void givenInValidAddressDTO_Address_Max_WhenAdded_ShouldReturnValidResponse() throws Exception {
        String address="asdadadadsadsa sfmdmnb uibubdfgslk uibefndf kdspoji oindflndjkbhvpojfdisuigfnjknkjubuuwerwoijfodsfsbdfsfbsibsfhsdbfsdbfsfbsdhfbdsbfjhdsbuihfdsfdsifgi bjgufdsbfjbsfdsf  uiguisfaspiuteruighibchjv nbgigiv  b";
        this.addressDTO=new AddressDTO(address,"Mumbai","Mumbai","Maharashtra","India","400025");

        String addressDTO=new Gson().toJson(this.addressDTO);
        MvcResult result = this.mockMvc.perform(post("/directrice/banking/address")
                .header("token","token")
                .header("accountNumber","accountNumber")
                .content(addressDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400, result.getResponse().getStatus());
        assertEquals("Address pattern doesn't match.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());

    }
    @Test
    void givenInValidAddressDTO_Address_Pattern_WhenAdded_ShouldReturnValidResponse() throws Exception {
        String address="#Mareiner";
        this.addressDTO=new AddressDTO(address,"Mumbai","Mumbai","Maharashtra","India","400025");

        String addressDTO=new Gson().toJson(this.addressDTO);
        MvcResult result = this.mockMvc.perform(post("/directrice/banking/address")
                .header("token","token")
                .header("accountNumber","accountNumber")
                .content(addressDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400, result.getResponse().getStatus());
        assertEquals("Address pattern doesn't match.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());

    }

    //town

    @Test
    void givenInValidAddressDTO_Town_Null_WhenAdded_ShouldReturnValidResponse() throws Exception {
        String Town=null;
        this.addressDTO=new AddressDTO("Marine Drive,Mumbai",Town,"Mumbai","Maharashtra","India","400025");

        String addressDTO=new Gson().toJson(this.addressDTO);
        MvcResult result = this.mockMvc.perform(post("/directrice/banking/address")
                .header("token","token")
                .header("accountNumber","accountNumber")
                .content(addressDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400, result.getResponse().getStatus());
        assertEquals("Town pattern doesn't match.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());

    }

    @Test
    void givenInValidAddressDTO_Town_Empty_WhenAdded_ShouldReturnValidResponse() throws Exception {
        String Town="";
        this.addressDTO=new AddressDTO("Marine Drive,Mumbai",Town,"Mumbai","Maharashtra","India","400025");

        String addressDTO=new Gson().toJson(this.addressDTO);
        MvcResult result = this.mockMvc.perform(post("/directrice/banking/address")
                .header("token","token")
                .header("accountNumber","accountNumber")
                .content(addressDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400, result.getResponse().getStatus());
        assertEquals("Town pattern doesn't match.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());

    }
    @Test
    void givenInValidAddressDTO_Town_Min_WhenAdded_ShouldReturnValidResponse() throws Exception {
        String Town="m";
        this.addressDTO=new AddressDTO("Marine Drive,Mumbai",Town,"Mumbai","Maharashtra","India","400025");

        String addressDTO=new Gson().toJson(this.addressDTO);
        MvcResult result = this.mockMvc.perform(post("/directrice/banking/address")
                .header("token","token")
                .header("accountNumber","accountNumber")
                .content(addressDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400, result.getResponse().getStatus());
        assertEquals("Town pattern doesn't match.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());

    }
    @Test
    void givenInValidAddressDTO_Town_Max_WhenAdded_ShouldReturnValidResponse() throws Exception {
        String Town="asdadadadsadsa sfmdmnb uibubdfgslk uibefndf kdspoji oindflndjkbhvpojfdisuigfnjknkjubuuwerwoijfodsfsbdfsfbsibsfhsdbfsdbfsfbsdhfbdsbfjhdsbuihfdsfdsifgi bjgufdsbfjbsfdsf  uiguisfaspiuteruighibchjv nbgigiv  b";
        this.addressDTO=new AddressDTO("Marine Drive,Mumbai",Town,"Mumbai","Maharashtra","India","400025");

        String addressDTO=new Gson().toJson(this.addressDTO);
        MvcResult result = this.mockMvc.perform(post("/directrice/banking/address")
                .header("token","token")
                .header("accountNumber","accountNumber")
                .content(addressDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400, result.getResponse().getStatus());
        assertEquals("Town pattern doesn't match.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());

    }
    @Test
    void givenInValidAddressDTO_Town_Pattern_WhenAdded_ShouldReturnValidResponse() throws Exception {
        String Town="#Mareiner";
        this.addressDTO=new AddressDTO("Marine Drive,Mumbai",Town,"Mumbai","Maharashtra","India","400025");

        String addressDTO=new Gson().toJson(this.addressDTO);
        MvcResult result = this.mockMvc.perform(post("/directrice/banking/address")
                .header("token","token")
                .header("accountNumber","accountNumber")
                .content(addressDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400, result.getResponse().getStatus());
        assertEquals("Town pattern doesn't match.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());

    }

    //district


    @Test
    void givenInValidAddressDTO_District_Null_WhenAdded_ShouldReturnValidResponse() throws Exception {
        String District=null;
        this.addressDTO=new AddressDTO("Marine Drive,Mumbai","Mumbai",District,"Maharashtra","India","400025");

        String addressDTO=new Gson().toJson(this.addressDTO);
        MvcResult result = this.mockMvc.perform(post("/directrice/banking/address")
                .header("token","token")
                .header("accountNumber","accountNumber")
                .content(addressDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400, result.getResponse().getStatus());
        assertEquals("District pattern doesn't match.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());

    }

    @Test
    void givenInValidAddressDTO_District_Empty_WhenAdded_ShouldReturnValidResponse() throws Exception {
        String District="";
        this.addressDTO=new AddressDTO("Marine Drive,Mumbai","Mumbai",District,"Maharashtra","India","400025");

        String addressDTO=new Gson().toJson(this.addressDTO);
        MvcResult result = this.mockMvc.perform(post("/directrice/banking/address")
                .header("token","token")
                .header("accountNumber","accountNumber")
                .content(addressDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400, result.getResponse().getStatus());
        assertEquals("District pattern doesn't match.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());

    }
    @Test
    void givenInValidAddressDTO_District_Min_WhenAdded_ShouldReturnValidResponse() throws Exception {
        String District="m";
        this.addressDTO=new AddressDTO("Marine Drive,Mumbai","Mumbai",District,"Maharashtra","India","400025");

        String addressDTO=new Gson().toJson(this.addressDTO);
        MvcResult result = this.mockMvc.perform(post("/directrice/banking/address")
                .header("token","token")
                .header("accountNumber","accountNumber")
                .content(addressDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400, result.getResponse().getStatus());
        assertEquals("District pattern doesn't match.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());

    }
    @Test
    void givenInValidAddressDTO_District_Max_WhenAdded_ShouldReturnValidResponse() throws Exception {
        String District="asdadadadsadsa sfmdmnb uibubdfgslk uibefndf kdspoji oindflndjkbhvpojfdisuigfnjknkjubuuwerwoijfodsfsbdfsfbsibsfhsdbfsdbfsfbsdhfbdsbfjhdsbuihfdsfdsifgi bjgufdsbfjbsfdsf  uiguisfaspiuteruighibchjv nbgigiv  b";
        this.addressDTO=new AddressDTO("Marine Drive,Mumbai","Mumbai",District,"Maharashtra","India","400025");

        String addressDTO=new Gson().toJson(this.addressDTO);
        MvcResult result = this.mockMvc.perform(post("/directrice/banking/address")
                .header("token","token")
                .header("accountNumber","accountNumber")
                .content(addressDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400, result.getResponse().getStatus());
        assertEquals("District pattern doesn't match.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());

    }
    @Test
    void givenInValidAddressDTO_District_Pattern_WhenAdded_ShouldReturnValidResponse() throws Exception {
        String District="#Mareiner";
        this.addressDTO=new AddressDTO("Marine Drive,Mumbai","Mumbai",District,"Maharashtra","India","400025");

        String addressDTO=new Gson().toJson(this.addressDTO);
        MvcResult result = this.mockMvc.perform(post("/directrice/banking/address")
                .header("token","token")
                .header("accountNumber","accountNumber")
                .content(addressDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400, result.getResponse().getStatus());
        assertEquals("District pattern doesn't match.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());

    }

    //State


    @Test
    void givenInValidAddressDTO_State_Null_WhenAdded_ShouldReturnValidResponse() throws Exception {
        String State=null;
        this.addressDTO=new AddressDTO("Marine Drive,Mumbai","Mumbai","Mumbai",State,"India","400025");

        String addressDTO=new Gson().toJson(this.addressDTO);
        MvcResult result = this.mockMvc.perform(post("/directrice/banking/address")
                .header("token","token")
                .header("accountNumber","accountNumber")
                .content(addressDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400, result.getResponse().getStatus());
        assertEquals("State pattern doesn't match.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());

    }

    @Test
    void givenInValidAddressDTO_State_Empty_WhenAdded_ShouldReturnValidResponse() throws Exception {
        String State="";
        this.addressDTO=new AddressDTO("Marine Drive,Mumbai","Mumbai","Mumbai",State,"India","400025");

        String addressDTO=new Gson().toJson(this.addressDTO);
        MvcResult result = this.mockMvc.perform(post("/directrice/banking/address")
                .header("token","token")
                .header("accountNumber","accountNumber")
                .content(addressDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400, result.getResponse().getStatus());
        assertEquals("State pattern doesn't match.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());

    }
    @Test
    void givenInValidAddressDTO_State_Min_WhenAdded_ShouldReturnValidResponse() throws Exception {
        String State="m";
        this.addressDTO=new AddressDTO("Marine Drive,Mumbai","Mumbai","Mumbai",State,"India","400025");

        String addressDTO=new Gson().toJson(this.addressDTO);
        MvcResult result = this.mockMvc.perform(post("/directrice/banking/address")
                .header("token","token")
                .header("accountNumber","accountNumber")
                .content(addressDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400, result.getResponse().getStatus());
        assertEquals("State pattern doesn't match.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());

    }
    @Test
    void givenInValidAddressDTO_State_Max_WhenAdded_ShouldReturnValidResponse() throws Exception {
        String State="asdadadadsadsa sfmdmnb uibubdfgslk uibefndf kdspoji oindflndjkbhvpojfdisuigfnjknkjubuuwerwoijfodsfsbdfsfbsibsfhsdbfsdbfsfbsdhfbdsbfjhdsbuihfdsfdsifgi bjgufdsbfjbsfdsf  uiguisfaspiuteruighibchjv nbgigiv  b";
        this.addressDTO=new AddressDTO("Marine Drive,Mumbai","Mumbai","Mumbai",State,"India","400025");

        String addressDTO=new Gson().toJson(this.addressDTO);
        MvcResult result = this.mockMvc.perform(post("/directrice/banking/address")
                .header("token","token")
                .header("accountNumber","accountNumber")
                .content(addressDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400, result.getResponse().getStatus());
        assertEquals("State pattern doesn't match.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());

    }
    @Test
    void givenInValidAddressDTO_State_Pattern_WhenAdded_ShouldReturnValidResponse() throws Exception {
        String State="#Mareiner";
        this.addressDTO=new AddressDTO("Marine Drive,Mumbai","Mumbai","Mumbai",State,"India","400025");

        String addressDTO=new Gson().toJson(this.addressDTO);
        MvcResult result = this.mockMvc.perform(post("/directrice/banking/address")
                .header("token","token")
                .header("accountNumber","accountNumber")
                .content(addressDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400, result.getResponse().getStatus());
        assertEquals("State pattern doesn't match.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());

    }
    //country

    @Test
    void givenInValidAddressDTO_Country_Null_WhenAdded_ShouldReturnValidResponse() throws Exception {
        String Country=null;
        this.addressDTO=new AddressDTO("Marine Drive,Mumbai","Mumbai","Mumbai","Maharashtra",Country,"400025");

        String addressDTO=new Gson().toJson(this.addressDTO);
        MvcResult result = this.mockMvc.perform(post("/directrice/banking/address")
                .header("token","token")
                .header("accountNumber","accountNumber")
                .content(addressDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400, result.getResponse().getStatus());
        assertEquals("Country pattern doesn't match.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());

    }

    @Test
    void givenInValidAddressDTO_Country_Empty_WhenAdded_ShouldReturnValidResponse() throws Exception {
        String Country="";
        this.addressDTO=new AddressDTO("Marine Drive,Mumbai","Mumbai","Mumbai","Maharashtra",Country,"400025");

        String addressDTO=new Gson().toJson(this.addressDTO);
        MvcResult result = this.mockMvc.perform(post("/directrice/banking/address")
                .header("token","token")
                .header("accountNumber","accountNumber")
                .content(addressDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400, result.getResponse().getStatus());
        assertEquals("Country pattern doesn't match.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());

    }
    @Test
    void givenInValidAddressDTO_Country_Min_WhenAdded_ShouldReturnValidResponse() throws Exception {
        String Country="m";
        this.addressDTO=new AddressDTO("Marine Drive,Mumbai","Mumbai","Mumbai","Maharashtra",Country,"400025");

        String addressDTO=new Gson().toJson(this.addressDTO);
        MvcResult result = this.mockMvc.perform(post("/directrice/banking/address")
                .header("token","token")
                .header("accountNumber","accountNumber")
                .content(addressDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400, result.getResponse().getStatus());
        assertEquals("Country pattern doesn't match.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());

    }
    @Test
    void givenInValidAddressDTO_Country_Max_WhenAdded_ShouldReturnValidResponse() throws Exception {
        String Country="asdadadadsadsa sfmdmnb uibubdfgslk uibefndf kdspoji oindflndjkbhvpojfdisuigfnjknkjubuuwerwoijfodsfsbdfsfbsibsfhsdbfsdbfsfbsdhfbdsbfjhdsbuihfdsfdsifgi bjgufdsbfjbsfdsf  uiguisfaspiuteruighibchjv nbgigiv  b";
        this.addressDTO=new AddressDTO("Marine Drive,Mumbai","Mumbai","Mumbai","Maharashtra",Country,"400025");

        String addressDTO=new Gson().toJson(this.addressDTO);
        MvcResult result = this.mockMvc.perform(post("/directrice/banking/address")
                .header("token","token")
                .header("accountNumber","accountNumber")
                .content(addressDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400, result.getResponse().getStatus());
        assertEquals("Country pattern doesn't match.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());

    }
    @Test
    void givenInValidAddressDTO_Country_Pattern_WhenAdded_ShouldReturnValidResponse() throws Exception {
        String Country="#Mareiner";
        this.addressDTO=new AddressDTO("Marine Drive,Mumbai","Mumbai","Mumbai","Maharashtra",Country,"400025");

        String addressDTO=new Gson().toJson(this.addressDTO);
        MvcResult result = this.mockMvc.perform(post("/directrice/banking/address")
                .header("token","token")
                .header("accountNumber","accountNumber")
                .content(addressDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400, result.getResponse().getStatus());
        assertEquals("Country pattern doesn't match.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());

    }

    //postalCode

    @Test
    void givenInValidAddressDTO_PostalCode_Null_WhenAdded_ShouldReturnValidResponse() throws Exception {
        String PostalCode=null;
        this.addressDTO=new AddressDTO("Marine Drive,Mumbai","Mumbai","Mumbai","Maharashtra","India",PostalCode);

        String addressDTO=new Gson().toJson(this.addressDTO);
        MvcResult result = this.mockMvc.perform(post("/directrice/banking/address")
                .header("token","token")
                .header("accountNumber","accountNumber")
                .content(addressDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400, result.getResponse().getStatus());
        assertEquals("Postal Code pattern doesn't match.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());

    }

    @Test
    void givenInValidAddressDTO_PostalCode_Empty_WhenAdded_ShouldReturnValidResponse() throws Exception {
        String PostalCode="";
        this.addressDTO=new AddressDTO("Marine Drive,Mumbai","Mumbai","Mumbai","Maharashtra","India",PostalCode);

        String addressDTO=new Gson().toJson(this.addressDTO);
        MvcResult result = this.mockMvc.perform(post("/directrice/banking/address")
                .header("token","token")
                .header("accountNumber","accountNumber")
                .content(addressDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400, result.getResponse().getStatus());
        assertEquals("Postal Code pattern doesn't match.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());

    }
    @Test
    void givenInValidAddressDTO_PostalCode_Min_WhenAdded_ShouldReturnValidResponse() throws Exception {
        String PostalCode="4";
        this.addressDTO=new AddressDTO("Marine Drive,Mumbai","Mumbai","Mumbai","Maharashtra","India",PostalCode);

        String addressDTO=new Gson().toJson(this.addressDTO);
        MvcResult result = this.mockMvc.perform(post("/directrice/banking/address")
                .header("token","token")
                .header("accountNumber","accountNumber")
                .content(addressDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400, result.getResponse().getStatus());
        assertEquals("Postal Code pattern doesn't match.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());

    }
    @Test
    void givenInValidAddressDTO_PostalCode_Max_WhenAdded_ShouldReturnValidResponse() throws Exception {
        String PostalCode="400021545";
        this.addressDTO=new AddressDTO("Marine Drive,Mumbai","Mumbai","Mumbai","Maharashtra","India",PostalCode);

        String addressDTO=new Gson().toJson(this.addressDTO);
        MvcResult result = this.mockMvc.perform(post("/directrice/banking/address")
                .header("token","token")
                .header("accountNumber","accountNumber")
                .content(addressDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400, result.getResponse().getStatus());
        assertEquals("Postal Code pattern doesn't match.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());

    }
    @Test
    void givenInValidAddressDTO_PostalCode_Pattern_WhenAdded_ShouldReturnValidResponse() throws Exception {
        String PostalCode="#400021545w";
        this.addressDTO=new AddressDTO("Marine Drive,Mumbai","Mumbai","Mumbai","Maharashtra","India",PostalCode);

        String addressDTO=new Gson().toJson(this.addressDTO);
        MvcResult result = this.mockMvc.perform(post("/directrice/banking/address")
                .header("token","token")
                .header("accountNumber","accountNumber")
                .content(addressDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400, result.getResponse().getStatus());
        assertEquals("Postal Code pattern doesn't match.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());

    }

    //Updating Address DTO
    @Test
    void givenValidAddressDTO_WhenEdited_ShouldResturnValidResponse() throws Exception {
        this.addressDTO=new AddressDTO("Marine Drive,Mumbai-78","Mumbai","Mumbai","Maharashtra","India","400025");

        String addressDTO=new Gson().toJson(this.addressDTO);
        MvcResult result = this.mockMvc.perform(put("/directrice/banking/address")
                .header("token","token")
                .header("accountNumber","accountNumber")
                .content(addressDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(201, result.getResponse().getStatus());
    }

    //Address
    @Test
    void givenInValidAddressDTO_Address_Null_WhenEdited_ShouldReturnValidResponse() throws Exception {
        String address=null;
        this.addressDTO=new AddressDTO(address,"Mumbai","Mumbai","Maharashtra","India","400025");

        String addressDTO=new Gson().toJson(this.addressDTO);
        MvcResult result = this.mockMvc.perform(put("/directrice/banking/address")
                .header("token","token")
                .header("accountNumber","accountNumber")
                .content(addressDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400, result.getResponse().getStatus());
        assertEquals("Address pattern doesn't match.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());

    }

    @Test
    void givenInValidAddressDTO_Address_Empty_WhenEdited_ShouldReturnValidResponse() throws Exception {
        String address="";
        this.addressDTO=new AddressDTO(address,"Mumbai","Mumbai","Maharashtra","India","400025");

        String addressDTO=new Gson().toJson(this.addressDTO);
        MvcResult result = this.mockMvc.perform(put("/directrice/banking/address")
                .header("token","token")
                .header("accountNumber","accountNumber")
                .content(addressDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400, result.getResponse().getStatus());
        assertEquals("Address pattern doesn't match.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());

    }
    @Test
    void givenInValidAddressDTO_Address_Min_WhenEdited_ShouldReturnValidResponse() throws Exception {
        String address="m";
        this.addressDTO=new AddressDTO(address,"Mumbai","Mumbai","Maharashtra","India","400025");

        String addressDTO=new Gson().toJson(this.addressDTO);
        MvcResult result = this.mockMvc.perform(put("/directrice/banking/address")
                .header("token","token")
                .header("accountNumber","accountNumber")
                .content(addressDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400, result.getResponse().getStatus());
        assertEquals("Address pattern doesn't match.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());

    }
    @Test
    void givenInValidAddressDTO_Address_Max_WhenEdited_ShouldReturnValidResponse() throws Exception {
        String address="asdadadadsadsa sfmdmnb uibubdfgslk uibefndf kdspoji oindflndjkbhvpojfdisuigfnjknkjubuuwerwoijfodsfsbdfsfbsibsfhsdbfsdbfsfbsdhfbdsbfjhdsbuihfdsfdsifgi bjgufdsbfjbsfdsf  uiguisfaspiuteruighibchjv nbgigiv  b";
        this.addressDTO=new AddressDTO(address,"Mumbai","Mumbai","Maharashtra","India","400025");

        String addressDTO=new Gson().toJson(this.addressDTO);
        MvcResult result = this.mockMvc.perform(put("/directrice/banking/address")
                .header("token","token")
                .header("accountNumber","accountNumber")
                .content(addressDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400, result.getResponse().getStatus());
        assertEquals("Address pattern doesn't match.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());

    }
    @Test
    void givenInValidAddressDTO_Address_Pattern_WhenEdited_ShouldReturnValidResponse() throws Exception {
        String address="#Mareiner";
        this.addressDTO=new AddressDTO(address,"Mumbai","Mumbai","Maharashtra","India","400025");

        String addressDTO=new Gson().toJson(this.addressDTO);
        MvcResult result = this.mockMvc.perform(put("/directrice/banking/address")
                .header("token","token")
                .header("accountNumber","accountNumber")
                .content(addressDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400, result.getResponse().getStatus());
        assertEquals("Address pattern doesn't match.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());

    }

    //town

    @Test
    void givenInValidAddressDTO_Town_Null_WhenEdited_ShouldReturnValidResponse() throws Exception {
        String Town=null;
        this.addressDTO=new AddressDTO("Marine Drive,Mumbai",Town,"Mumbai","Maharashtra","India","400025");

        String addressDTO=new Gson().toJson(this.addressDTO);
        MvcResult result = this.mockMvc.perform(put("/directrice/banking/address")
                .header("token","token")
                .header("accountNumber","accountNumber")
                .content(addressDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400, result.getResponse().getStatus());
        assertEquals("Town pattern doesn't match.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());

    }

    @Test
    void givenInValidAddressDTO_Town_Empty_WhenEdited_ShouldReturnValidResponse() throws Exception {
        String Town="";
        this.addressDTO=new AddressDTO("Marine Drive,Mumbai",Town,"Mumbai","Maharashtra","India","400025");

        String addressDTO=new Gson().toJson(this.addressDTO);
        MvcResult result = this.mockMvc.perform(put("/directrice/banking/address")
                .header("token","token")
                .header("accountNumber","accountNumber")
                .content(addressDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400, result.getResponse().getStatus());
        assertEquals("Town pattern doesn't match.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());

    }
    @Test
    void givenInValidAddressDTO_Town_Min_WhenEdited_ShouldReturnValidResponse() throws Exception {
        String Town="m";
        this.addressDTO=new AddressDTO("Marine Drive,Mumbai",Town,"Mumbai","Maharashtra","India","400025");

        String addressDTO=new Gson().toJson(this.addressDTO);
        MvcResult result = this.mockMvc.perform(put("/directrice/banking/address")
                .header("token","token")
                .header("accountNumber","accountNumber")
                .content(addressDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400, result.getResponse().getStatus());
        assertEquals("Town pattern doesn't match.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());

    }
    @Test
    void givenInValidAddressDTO_Town_Max_WhenEdited_ShouldReturnValidResponse() throws Exception {
        String Town="asdadadadsadsa sfmdmnb uibubdfgslk uibefndf kdspoji oindflndjkbhvpojfdisuigfnjknkjubuuwerwoijfodsfsbdfsfbsibsfhsdbfsdbfsfbsdhfbdsbfjhdsbuihfdsfdsifgi bjgufdsbfjbsfdsf  uiguisfaspiuteruighibchjv nbgigiv  b";
        this.addressDTO=new AddressDTO("Marine Drive,Mumbai",Town,"Mumbai","Maharashtra","India","400025");

        String addressDTO=new Gson().toJson(this.addressDTO);
        MvcResult result = this.mockMvc.perform(put("/directrice/banking/address")
                .header("token","token")
                .header("accountNumber","accountNumber")
                .content(addressDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400, result.getResponse().getStatus());
        assertEquals("Town pattern doesn't match.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());

    }
    @Test
    void givenInValidAddressDTO_Town_Pattern_WhenEdited_ShouldReturnValidResponse() throws Exception {
        String Town="#Mareiner";
        this.addressDTO=new AddressDTO("Marine Drive,Mumbai",Town,"Mumbai","Maharashtra","India","400025");

        String addressDTO=new Gson().toJson(this.addressDTO);
        MvcResult result = this.mockMvc.perform(put("/directrice/banking/address")
                .header("token","token")
                .header("accountNumber","accountNumber")
                .content(addressDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400, result.getResponse().getStatus());
        assertEquals("Town pattern doesn't match.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());

    }

    //district


    @Test
    void givenInValidAddressDTO_District_Null_WhenEdited_ShouldReturnValidResponse() throws Exception {
        String District=null;
        this.addressDTO=new AddressDTO("Marine Drive,Mumbai","Mumbai",District,"Maharashtra","India","400025");

        String addressDTO=new Gson().toJson(this.addressDTO);
        MvcResult result = this.mockMvc.perform(put("/directrice/banking/address")
                .header("token","token")
                .header("accountNumber","accountNumber")
                .content(addressDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400, result.getResponse().getStatus());
        assertEquals("District pattern doesn't match.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());

    }

    @Test
    void givenInValidAddressDTO_District_Empty_WhenEdited_ShouldReturnValidResponse() throws Exception {
        String District="";
        this.addressDTO=new AddressDTO("Marine Drive,Mumbai","Mumbai",District,"Maharashtra","India","400025");

        String addressDTO=new Gson().toJson(this.addressDTO);
        MvcResult result = this.mockMvc.perform(put("/directrice/banking/address")
                .header("token","token")
                .header("accountNumber","accountNumber")
                .content(addressDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400, result.getResponse().getStatus());
        assertEquals("District pattern doesn't match.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());

    }
    @Test
    void givenInValidAddressDTO_District_Min_WhenEdited_ShouldReturnValidResponse() throws Exception {
        String District="m";
        this.addressDTO=new AddressDTO("Marine Drive,Mumbai","Mumbai",District,"Maharashtra","India","400025");

        String addressDTO=new Gson().toJson(this.addressDTO);
        MvcResult result = this.mockMvc.perform(put("/directrice/banking/address")
                .header("token","token")
                .header("accountNumber","accountNumber")
                .content(addressDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400, result.getResponse().getStatus());
        assertEquals("District pattern doesn't match.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());

    }
    @Test
    void givenInValidAddressDTO_District_Max_WhenEdited_ShouldReturnValidResponse() throws Exception {
        String District="asdadadadsadsa sfmdmnb uibubdfgslk uibefndf kdspoji oindflndjkbhvpojfdisuigfnjknkjubuuwerwoijfodsfsbdfsfbsibsfhsdbfsdbfsfbsdhfbdsbfjhdsbuihfdsfdsifgi bjgufdsbfjbsfdsf  uiguisfaspiuteruighibchjv nbgigiv  b";
        this.addressDTO=new AddressDTO("Marine Drive,Mumbai","Mumbai",District,"Maharashtra","India","400025");

        String addressDTO=new Gson().toJson(this.addressDTO);
        MvcResult result = this.mockMvc.perform(put("/directrice/banking/address")
                .header("token","token")
                .header("accountNumber","accountNumber")
                .content(addressDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400, result.getResponse().getStatus());
        assertEquals("District pattern doesn't match.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());

    }
    @Test
    void givenInValidAddressDTO_District_Pattern_WhenEdited_ShouldReturnValidResponse() throws Exception {
        String District="#Mareiner";
        this.addressDTO=new AddressDTO("Marine Drive,Mumbai","Mumbai",District,"Maharashtra","India","400025");

        String addressDTO=new Gson().toJson(this.addressDTO);
        MvcResult result = this.mockMvc.perform(put("/directrice/banking/address")
                .header("token","token")
                .header("accountNumber","accountNumber")
                .content(addressDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400, result.getResponse().getStatus());
        assertEquals("District pattern doesn't match.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());

    }

    //State


    @Test
    void givenInValidAddressDTO_State_Null_WhenEdited_ShouldReturnValidResponse() throws Exception {
        String State=null;
        this.addressDTO=new AddressDTO("Marine Drive,Mumbai","Mumbai","Mumbai",State,"India","400025");

        String addressDTO=new Gson().toJson(this.addressDTO);
        MvcResult result = this.mockMvc.perform(put("/directrice/banking/address")
                .header("token","token")
                .header("accountNumber","accountNumber")
                .content(addressDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400, result.getResponse().getStatus());
        assertEquals("State pattern doesn't match.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());

    }

    @Test
    void givenInValidAddressDTO_State_Empty_WhenEdited_ShouldReturnValidResponse() throws Exception {
        String State="";
        this.addressDTO=new AddressDTO("Marine Drive,Mumbai","Mumbai","Mumbai",State,"India","400025");

        String addressDTO=new Gson().toJson(this.addressDTO);
        MvcResult result = this.mockMvc.perform(put("/directrice/banking/address")
                .header("token","token")
                .header("accountNumber","accountNumber")
                .content(addressDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400, result.getResponse().getStatus());
        assertEquals("State pattern doesn't match.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());

    }
    @Test
    void givenInValidAddressDTO_State_Min_WhenEdited_ShouldReturnValidResponse() throws Exception {
        String State="m";
        this.addressDTO=new AddressDTO("Marine Drive,Mumbai","Mumbai","Mumbai",State,"India","400025");

        String addressDTO=new Gson().toJson(this.addressDTO);
        MvcResult result = this.mockMvc.perform(put("/directrice/banking/address")
                .header("token","token")
                .header("accountNumber","accountNumber")
                .content(addressDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400, result.getResponse().getStatus());
        assertEquals("State pattern doesn't match.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());

    }
    @Test
    void givenInValidAddressDTO_State_Max_WhenEdited_ShouldReturnValidResponse() throws Exception {
        String State="asdadadadsadsa sfmdmnb uibubdfgslk uibefndf kdspoji oindflndjkbhvpojfdisuigfnjknkjubuuwerwoijfodsfsbdfsfbsibsfhsdbfsdbfsfbsdhfbdsbfjhdsbuihfdsfdsifgi bjgufdsbfjbsfdsf  uiguisfaspiuteruighibchjv nbgigiv  b";
        this.addressDTO=new AddressDTO("Marine Drive,Mumbai","Mumbai","Mumbai",State,"India","400025");

        String addressDTO=new Gson().toJson(this.addressDTO);
        MvcResult result = this.mockMvc.perform(put("/directrice/banking/address")
                .header("token","token")
                .header("accountNumber","accountNumber")
                .content(addressDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400, result.getResponse().getStatus());
        assertEquals("State pattern doesn't match.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());

    }
    @Test
    void givenInValidAddressDTO_State_Pattern_WhenEdited_ShouldReturnValidResponse() throws Exception {
        String State="#Mareiner";
        this.addressDTO=new AddressDTO("Marine Drive,Mumbai","Mumbai","Mumbai",State,"India","400025");

        String addressDTO=new Gson().toJson(this.addressDTO);
        MvcResult result = this.mockMvc.perform(put("/directrice/banking/address")
                .header("token","token")
                .header("accountNumber","accountNumber")
                .content(addressDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400, result.getResponse().getStatus());
        assertEquals("State pattern doesn't match.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());

    }
    //country

    @Test
    void givenInValidAddressDTO_Country_Null_WhenEdited_ShouldReturnValidResponse() throws Exception {
        String Country=null;
        this.addressDTO=new AddressDTO("Marine Drive,Mumbai","Mumbai","Mumbai","Maharashtra",Country,"400025");

        String addressDTO=new Gson().toJson(this.addressDTO);
        MvcResult result = this.mockMvc.perform(put("/directrice/banking/address")
                .header("token","token")
                .header("accountNumber","accountNumber")
                .content(addressDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400, result.getResponse().getStatus());
        assertEquals("Country pattern doesn't match.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());

    }

    @Test
    void givenInValidAddressDTO_Country_Empty_WhenEdited_ShouldReturnValidResponse() throws Exception {
        String Country="";
        this.addressDTO=new AddressDTO("Marine Drive,Mumbai","Mumbai","Mumbai","Maharashtra",Country,"400025");

        String addressDTO=new Gson().toJson(this.addressDTO);
        MvcResult result = this.mockMvc.perform(put("/directrice/banking/address")
                .header("token","token")
                .header("accountNumber","accountNumber")
                .content(addressDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400, result.getResponse().getStatus());
        assertEquals("Country pattern doesn't match.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());

    }
    @Test
    void givenInValidAddressDTO_Country_Min_WhenEdited_ShouldReturnValidResponse() throws Exception {
        String Country="m";
        this.addressDTO=new AddressDTO("Marine Drive,Mumbai","Mumbai","Mumbai","Maharashtra",Country,"400025");

        String addressDTO=new Gson().toJson(this.addressDTO);
        MvcResult result = this.mockMvc.perform(put("/directrice/banking/address")
                .header("token","token")
                .header("accountNumber","accountNumber")
                .content(addressDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400, result.getResponse().getStatus());
        assertEquals("Country pattern doesn't match.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());

    }
    @Test
    void givenInValidAddressDTO_Country_Max_WhenEdited_ShouldReturnValidResponse() throws Exception {
        String Country="asdadadadsadsa sfmdmnb uibubdfgslk uibefndf kdspoji oindflndjkbhvpojfdisuigfnjknkjubuuwerwoijfodsfsbdfsfbsibsfhsdbfsdbfsfbsdhfbdsbfjhdsbuihfdsfdsifgi bjgufdsbfjbsfdsf  uiguisfaspiuteruighibchjv nbgigiv  b";
        this.addressDTO=new AddressDTO("Marine Drive,Mumbai","Mumbai","Mumbai","Maharashtra",Country,"400025");

        String addressDTO=new Gson().toJson(this.addressDTO);
        MvcResult result = this.mockMvc.perform(put("/directrice/banking/address")
                .header("token","token")
                .header("accountNumber","accountNumber")
                .content(addressDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400, result.getResponse().getStatus());
        assertEquals("Country pattern doesn't match.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());

    }
    @Test
    void givenInValidAddressDTO_Country_Pattern_WhenEdited_ShouldReturnValidResponse() throws Exception {
        String Country="#Mareiner";
        this.addressDTO=new AddressDTO("Marine Drive,Mumbai","Mumbai","Mumbai","Maharashtra",Country,"400025");

        String addressDTO=new Gson().toJson(this.addressDTO);
        MvcResult result = this.mockMvc.perform(put("/directrice/banking/address")
                .header("token","token")
                .header("accountNumber","accountNumber")
                .content(addressDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400, result.getResponse().getStatus());
        assertEquals("Country pattern doesn't match.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());

    }

    //postalCode

    @Test
    void givenInValidAddressDTO_PostalCode_Null_WhenEdited_ShouldReturnValidResponse() throws Exception {
        String PostalCode=null;
        this.addressDTO=new AddressDTO("Marine Drive,Mumbai","Mumbai","Mumbai","Maharashtra","India",PostalCode);

        String addressDTO=new Gson().toJson(this.addressDTO);
        MvcResult result = this.mockMvc.perform(put("/directrice/banking/address")
                .header("token","token")
                .header("accountNumber","accountNumber")
                .content(addressDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400, result.getResponse().getStatus());
        assertEquals("Postal Code pattern doesn't match.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());

    }

    @Test
    void givenInValidAddressDTO_PostalCode_Empty_WhenEdited_ShouldReturnValidResponse() throws Exception {
        String PostalCode="";
        this.addressDTO=new AddressDTO("Marine Drive,Mumbai","Mumbai","Mumbai","Maharashtra","India",PostalCode);

        String addressDTO=new Gson().toJson(this.addressDTO);
        MvcResult result = this.mockMvc.perform(put("/directrice/banking/address")
                .header("token","token")
                .header("accountNumber","accountNumber")
                .content(addressDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400, result.getResponse().getStatus());
        assertEquals("Postal Code pattern doesn't match.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());

    }
    @Test
    void givenInValidAddressDTO_PostalCode_Min_WhenEdited_ShouldReturnValidResponse() throws Exception {
        String PostalCode="4";
        this.addressDTO=new AddressDTO("Marine Drive,Mumbai","Mumbai","Mumbai","Maharashtra","India",PostalCode);

        String addressDTO=new Gson().toJson(this.addressDTO);
        MvcResult result = this.mockMvc.perform(put("/directrice/banking/address")
                .header("token","token")
                .header("accountNumber","accountNumber")
                .content(addressDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400, result.getResponse().getStatus());
        assertEquals("Postal Code pattern doesn't match.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());

    }
    @Test
    void givenInValidAddressDTO_PostalCode_Max_WhenEdited_ShouldReturnValidResponse() throws Exception {
        String PostalCode="400021545";
        this.addressDTO=new AddressDTO("Marine Drive,Mumbai","Mumbai","Mumbai","Maharashtra","India",PostalCode);

        String addressDTO=new Gson().toJson(this.addressDTO);
        MvcResult result = this.mockMvc.perform(put("/directrice/banking/address")
                .header("token","token")
                .header("accountNumber","accountNumber")
                .content(addressDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400, result.getResponse().getStatus());
        assertEquals("Postal Code pattern doesn't match.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());

    }
    @Test
    void givenInValidAddressDTO_PostalCode_Pattern_WhenEdited_ShouldReturnValidResponse() throws Exception {
        String PostalCode="#400021545w";
        this.addressDTO=new AddressDTO("Marine Drive,Mumbai","Mumbai","Mumbai","Maharashtra","India",PostalCode);

        String addressDTO=new Gson().toJson(this.addressDTO);
        MvcResult result = this.mockMvc.perform(put("/directrice/banking/address")
                .header("token","token")
                .header("accountNumber","accountNumber")
                .content(addressDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(400, result.getResponse().getStatus());
        assertEquals("Postal Code pattern doesn't match.", new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getData());

    }




    //Getting Address of User.
    @Test
    void givenValidToken_ValidaccountNumber_WhenGettingUser_ShouldReturnValidResponse() throws Exception {
              MvcResult result = this.mockMvc.perform(get("/directrice/banking/address")
                .header("token","token")
                .header("accountNumber","accountNumber")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(200, result.getResponse().getStatus());

    }

}
