package com.biletxservice;


import com.biletx.controller.VehicleController;
import com.biletx.enums.VehicleStatus;
import com.biletx.enums.VehicleType;
import com.biletx.request.RouteRequest;
import com.biletx.response.VehicleResponse;
import com.biletx.service.VehicleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(VehicleController.class)
@ContextConfiguration(classes = {VehicleController.class, VehicleService.class})
public class VehicleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VehicleService vehicleService;
    @Autowired
    private ObjectMapper mapper = new ObjectMapper();


    @Test
    void it_should_get_vehicles_by_route() throws Exception {

        // given
        Mockito.when(vehicleService.getAllByRoute(getRouteRequest())).thenReturn(getAllRouteResponses());

        // when
        ResultActions resultActions = mockMvc.perform(get("/vehicles/route"));

        // then

        //// @formatter:off
        System.out.println("bak-----------" + resultActions.andReturn().getResponse().getContentAsString());
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].status").value("ACTIVE"))
                .andExpect(jsonPath("$[0].no").value("18-DLL-745"))
                .andExpect(jsonPath("$[0].vehicleType").value("CAR"))
                .andExpect(jsonPath("$[0].fromWhere").value("Çanakkale"))
                .andExpect(jsonPath("$[0].whereTo").value("İstanbul"))
                .andExpect(jsonPath("$[0].ridership").value(45))
                .andExpect(jsonPath("$[0].departureTime").value("17-09-2022"))
                .andExpect(jsonPath("$[0].departureClock").value("13:23"))
                .andExpect(jsonPath("$[0].clockOfArrival").value("15:30"))
                .andExpect(jsonPath("$[0].emptySeat").value(45))
                .andExpect(jsonPath("$[0].price").value(350));


        // @formatter:on


    }

    private List<VehicleResponse> getAllRouteResponses() {
        return List.of(getVehicleResponse(1));
    }


    private RouteRequest getRouteRequest() {
        return new RouteRequest("Çanakkale", "İstanbul");

    }


    private VehicleResponse getVehicleResponse(int id) {
        return new VehicleResponse(id,
                VehicleStatus.ACTIVE,
                "18-DLL-745",
                VehicleType.CAR,
                "Çanakkale",
                "İstanbul",
                45,
                "17-09-2022",
                "13:23", "15:30", 45, 350);
    }


}
