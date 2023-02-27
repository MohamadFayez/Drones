package com.musala.drones.service.impl;

import com.musala.drones.entity.Drone;
import com.musala.drones.entity.Medication;
import com.musala.drones.repository.DroneRepository;
import com.musala.drones.type.Model;
import com.musala.drones.type.State;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class DroneServiceImplTest {

    @InjectMocks
    DroneSeriviceImpl droneServiceImpl;
    @Mock
    DroneRepository droneRepository;

    @Test
    void testGetAvailabeDrones() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));


        when(droneRepository.findAllByState(State.IDLE)).thenReturn(testDrones());

        List<Drone> availableDrones = droneServiceImpl.getAvailabeDrones();
        assertEquals(4, availableDrones.size());
        verify(droneRepository, times(1)).findAllByState(State.IDLE);
    }

    public List<Drone> testDrones() {
        List<Medication> medications = new ArrayList<Medication>();
        Medication medication1 = new Medication("ALG_10MG", "Algoo", 140.2, new byte[]{});
        Medication medication2 = new Medication("SED_1000", "Sedoo", 200.2, new byte[]{});
        Medication medication3 = new Medication("MEG_20", "Megrano", 101.2, new byte[]{});

        List<Drone> drones = new ArrayList<Drone>();
        Drone drone1 = new Drone("SN-24527X", Model.MIDDLEWEIGHT, 1200.0, 10.95, State.IDLE, null, new Date(), new Date());
        Drone drone2 = new Drone("SN-202T7G", Model.LIGHTWEIGHT, 1500.0, 1002.4, State.DELIVERED, medications, new Date(), new Date());
        Drone drone3 = new Drone("SN-2092R4", Model.CRUISERWEIGHT, 2000.0, 0.98, State.LOADING, new ArrayList<Medication>(), new Date(), new Date());
        Drone drone4 = new Drone("SN-20827T", Model.HEAVYWEIGHT, 2000.0, 40.98, State.LOADED, medications, new Date(), new Date());
        drones.add(drone1);
        drones.add(drone2);
        drones.add(drone3);
        drones.add(drone4);
        return drones;
    }

}
