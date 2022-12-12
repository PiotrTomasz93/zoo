package com.tomaszpiotr.zoo;

import com.tomaszpiotr.zoo.model.Animal;
import com.tomaszpiotr.zoo.model.Elephant;
import com.tomaszpiotr.zoo.model.Lion;
import com.tomaszpiotr.zoo.model.Zone;
import com.tomaszpiotr.zoo.service.AnimalService;
import com.tomaszpiotr.zoo.service.ZoneService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ZoneServiceTests {

    @LocalServerPort
    private int randomServerPort;
    private TestRestTemplate restTemplate = new TestRestTemplate();

    @Autowired
    ZoneService zoneService;
    @Autowired
    AnimalService animalService;


    @Autowired
    private WebApplicationContext webApplicationContext;

    String getAddressWithRandomPort(){
        return "http://localhost:"+ randomServerPort;
    }


    @Test
    void shouldReturnMinus1WhenMapIsEmpty(){
        Map<Zone, Integer> zoneFoodMap = new HashMap<>();
        int result = zoneService.getMaxFood(zoneFoodMap);
        assertEquals(-1, result);
    }

    @Test
    void shouldReturnCorrectValueWhenMapContainsOneEntry(){
        Map<Zone, Integer> zoneFoodMap = new HashMap<>();

        zoneFoodMap.put(new Zone(1, "test1"), 14);

        int result = zoneService.getMaxFood(zoneFoodMap);
        assertEquals(14, result);
    }

    @Test
    void shouldReturnCorrectValueWhenMapContainsMoreThenOneEntries(){
        Map<Zone, Integer> zoneFoodMap = new HashMap<>();

        zoneFoodMap.put(new Zone(1, "test1"), -1);
        zoneFoodMap.put(new Zone(2, "test2"), 12);
        zoneFoodMap.put(new Zone(3, "test3"), 5);

        int result = zoneService.getMaxFood(zoneFoodMap);
        assertEquals(12, result);
    }

    @Test
    void shouldReturnMessageWhenDatabaseIsEmpty(){
        String message = zoneService.getZonesWithMaxFoodNeeded();

        assertEquals("There are no animals in database", message);
    }

    @Test
    void shouldReturnCorrectMessageWhenDatabaseIsNotEmpty() throws Exception{
        //given
//        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        Zone zone1 = new Zone(1,"zone1");
        Zone zone2 = new Zone(2,"zone2");

        zoneService.save(zone1);
        zoneService.save(zone2);


        Animal animal1 = new Elephant();
        animal1.setZone(zone1);

        Animal animal2 = new Lion();
        animal2.setZone(zone2);

        Animal animal3 = new Lion();
        animal3.setZone(zone2);

        animalService.save(animal1);
        animalService.save(animal2);
        animalService.save(animal3);


        //then
        String raport = zoneService.getZonesWithMaxFoodNeeded();
        assertEquals("Zones with max amount of food needed (22 units): [Zone{zoneId=2, name='zone2'}]", raport);

//        String zone1Payload = "{\"name\":\"zone1\"}";
//        String zone2Payload = "{\"name\":\"zone2\"}";
//
//        String animal1Payload = "{\"name\":\"animal1\",\"zone\":{\"zone_id\":1}}";
//        String animal2Payload = "{\"name\":\"animal2\",\"zone\":{\"zone_id\":2}}";
//        String animal3Payload = "{\"name\":\"animal3\",\"zone\":{\"zone_id\":2}}";

//        mockMvc.perform(MockMvcRequestBuilders
//                .post("/zone/zone")
//                .content(zone1Payload)
//                .contentType(MediaType.APPLICATION_JSON));
//
//        mockMvc.perform(MockMvcRequestBuilders
//                .post("/zone/zone")
//                .content(zone2Payload)
//                .contentType(MediaType.APPLICATION_JSON));



//        mockMvc.perform(MockMvcRequestBuilders
//                .post("/animal/elephant")
//                .content(animal1Payload)
//                .contentType(MediaType.APPLICATION_JSON));
//
//        mockMvc.perform(MockMvcRequestBuilders
//                .post("/animal/lion")
//                .content(animal2Payload)
//                .contentType(MediaType.APPLICATION_JSON));
//
//        mockMvc.perform(MockMvcRequestBuilders
//                .post("/animal/lion")
//                .content(animal3Payload)
//                .contentType(MediaType.APPLICATION_JSON));



    }
}
