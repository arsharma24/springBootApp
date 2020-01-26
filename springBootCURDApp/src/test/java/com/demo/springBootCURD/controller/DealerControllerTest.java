package com.demo.springBootCURD.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.demo.springBootCURD.domain.Dealer;
import com.demo.springBootCURD.exception.BadRequestException;
import com.demo.springBootCURD.exception.DealerNotFoundException;
import com.demo.springBootCURD.service.DealerService;
import com.demo.springBootCURD.utils.TestUtils;

@RunWith(MockitoJUnitRunner.class)
public class DealerControllerTest {
	
	private MockMvc mockMvc;
	
	@Mock
	DealerService dealerService;
	
	@InjectMocks
	DealerController dealerController;
	
	@Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(dealerController).build();
    }
	
	@Test
    public void add_should_return_one_while_adding_new_dealer() throws Exception {
        String dealerJson = "{\n" +
                "\"dealerId\": \"XABCA002589\",\n" + "\"dealerName\": \"XABCA\",\n" +
                " \"organisation\":{\n" + "\t\"orgId\":\"OR002\",\n" +
                "\t\"orgName\": \"ORABCA\"\n" + "}\n" + " }";

        when(dealerService.add(any(Dealer.class))).thenReturn(1);

        mockMvc.perform(post("/dealerManagement/app/dealers/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(dealerJson))
                .andDo(print())
                .andExpect(status().isCreated());

        ArgumentCaptor<Dealer> varArgs = ArgumentCaptor.forClass(Dealer.class);
        verify(dealerService,times(1)).add(varArgs.capture());

        assertEquals("XABCA002589", varArgs.getValue().getDealerId());
        assertEquals("XABCA", varArgs.getValue().getDealerName());
        assertEquals("OR002", varArgs.getValue().getOrganisation().getOrgId());

        verifyNoMoreInteractions(dealerService);
    }
	
	@Test
    public void add_should_return_bad_request_status_code_while_adding_new_dealer_with_blank_dealer_name() throws Exception {

        String dealerJson = "{\n" + " \t \"dealerId\": \"XABCA002589\",\n" + "\"dealerName\": \"\",\n" +
                "\"organisation\":{\n" + "\t\"orgId\":\"OR002\",\n" + "\t\"orgName\": \"ORABCA\"\n" +
                "}\n" + " }";

        when(dealerService.add(any(Dealer.class))).thenThrow(new BadRequestException("Bad Request"));

        mockMvc.perform(post("/dealerManagement/app/dealers/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(dealerJson))
                .andDo(print())
                .andExpect(status().isBadRequest());

        ArgumentCaptor<Dealer> varArgs = ArgumentCaptor.forClass(Dealer.class);
        verify(dealerService,times(1)).add(varArgs.capture());

        assertEquals("XABCA002589", varArgs.getValue().getDealerId());
        assertEquals("", varArgs.getValue().getDealerName());
        assertEquals("OR002", varArgs.getValue().getOrganisation().getOrgId());

        verifyNoMoreInteractions(dealerService);
    }
	
	@Test
    public void add_should_return_bad_request_status_code_while_adding_new_dealer_with_duplicate_dealer() throws Exception{

        String dealerJson = "{\n" +
                "\"dealerId\": \"XABUS12345\",\n" + "\"dealerName\": \"XABUS\",\n" +
                " \"organisation\":{\n" + "\t\"orgId\":\"OR123\",\n" +
                "\t\"orgName\": \"OR_ABUS\"\n" + "}\n" + " }";

        when(dealerService.add(any(Dealer.class))).thenThrow(new BadRequestException("Bad Request"));

        mockMvc.perform(post("/dealerManagement/app/dealers/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(dealerJson))
                .andDo(print())
                .andExpect(status().isBadRequest());

        ArgumentCaptor<Dealer> varArgs = ArgumentCaptor.forClass(Dealer.class);
        verify(dealerService,times(1)).add(varArgs.capture());

        assertEquals("XABUS12345", varArgs.getValue().getDealerId());
        assertEquals("XABUS", varArgs.getValue().getDealerName());
        assertEquals("OR123", varArgs.getValue().getOrganisation().getOrgId());

        verifyNoMoreInteractions(dealerService);

    }
	
	@Test
    public void add_should_return_bad_request_status_code_while_adding_new_dealer_when_organisation_is_missing() throws Exception {

        String dealerJson = "{\n" +
                "\"dealerId\": \"XABCA002589\",\n" + "\"dealerName\": \"XABCA\",\n" +
                " \"organisation\":{\n" + "\t\"orgId\": \"\",\n" +
                "\t\"orgName\": \"ORABCA\"\n" + "}\n" + " }";

        when(dealerService.add(any(Dealer.class))).thenThrow(new BadRequestException("Bad Request"));

        mockMvc.perform(post("/dealerManagement/app/dealers/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(dealerJson))
                .andDo(print())
                .andExpect(status().isBadRequest());

        ArgumentCaptor<Dealer> varArgs = ArgumentCaptor.forClass(Dealer.class);
        verify(dealerService,times(1)).add(varArgs.capture());

        assertEquals("XABCA002589", varArgs.getValue().getDealerId());
        assertEquals("XABCA", varArgs.getValue().getDealerName());
        assertEquals("", varArgs.getValue().getOrganisation().getOrgId());

        verifyNoMoreInteractions(dealerService);
    }
	
	@Test
    public void add_should_return_resource_not_found_status_code_while_adding_new_dealer_when_providing_wrong_url() throws Exception {

        String dealerJson = "{\n" +
                "\"dealerId\": \"XABCA002589\",\n" + "\"dealerName\": \"XABCA\",\n" +
                " \"organisation\":{\n" + "\t\"orgId\":\"OR002\",\n" +
                "\t\"orgName\": \"ORABCA\"\n" + "}\n" + " }";

        mockMvc.perform(post("/app/dealers/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(dealerJson))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
	
	@Test
    public void findById_should_return_status_code_not_found_missing_dealer() throws Exception {

        when(dealerService.findDealerById(anyString())).thenThrow(DealerNotFoundException.class);

        mockMvc.perform(get("/dealerManagement/app/dealers/dealer/{dealerId}", "XABGB12345")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        ArgumentCaptor<String> varArgs = ArgumentCaptor.forClass(String.class);
        verify(dealerService,times(1)).findDealerById(varArgs.capture());

        assertEquals("XABGB12345", (String)varArgs.getValue());

        verifyNoMoreInteractions(dealerService);
    }

    @Test
    public void findById_should_return_dealer_details_for_provided_dealer_id() throws Exception{

        when(dealerService.findDealerById(anyString())).thenReturn(TestUtils.getDealer());

        mockMvc.perform(get("/dealerManagement/app/dealers/dealer/{dealerId}", "XABUS12345")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(jsonPath("$.dealerId", is("XABUS12345")))
                .andExpect(jsonPath("$.dealerName", is("XABUS")));


        ArgumentCaptor<String> varArgs = ArgumentCaptor.forClass(String.class);
        verify(dealerService,times(1)).findDealerById(varArgs.capture());

        assertEquals("XABUS12345", (String)varArgs.getValue());

        verifyNoMoreInteractions(dealerService);
    }
}
