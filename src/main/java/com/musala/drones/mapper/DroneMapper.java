package com.musala.drones.mapper;

import com.musala.drones.entity.Drone;
import com.musala.drones.vm.request.DroneRequest;
import com.musala.drones.vm.response.DroneResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DroneMapper {

	Drone toEntityModel(DroneRequest droneRequest);

	DroneResponse toViewModel(Drone drone);

	List<DroneResponse> toViewModel(List<Drone> drones);
}
