package com.kosa.resq.controller.car;

import com.kosa.resq.domain.dto.car.*;
import com.kosa.resq.domain.dto.common.MemDTO;
import com.kosa.resq.domain.vo.car.*;
import com.kosa.resq.service.AddressService;
import com.kosa.resq.service.car.CarUserService;
import lombok.Getter;
import oracle.jdbc.proxy.annotation.Post;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/car_rez/*")
public class CarUserController {

    @Autowired
    private CarUserService service;

    @PostMapping("/rezSave")
    public ResponseEntity<CarRezDTO2> carRezInfoSave(@RequestBody CarRezDTO carRezDTO){

        System.out.println(carRezDTO);
        CarRezDTO2 result=service.carRezInfoSave(carRezDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

//    @GetMapping("/availableCars")
//    public List<CarDetailDTO2> availableCarGetAll(){
//        return service.carGetAll();
//    }
    @GetMapping("/availableCars/{start_at}/{return_at}")
    public List<AvailableCarResponseVO> availableCarGetAll(@PathVariable Date start_at, @PathVariable Date return_at){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        System.out.println("start_at:"+start_at.getClass().getName());
        System.out.println("return_at:"+return_at);
        return service.carGetAll2(start_at,return_at);
    }

    @GetMapping("/carDetail/{car_code}")
    public CarDetailDTO2 carDetailGetOne(@PathVariable String car_code){
        return service.carGetOne(car_code);
    }

    @GetMapping("/locations/{car_rez_code}")
    public List<CarLocDTO> carLocInfoGetAll(@PathVariable String car_rez_code){
        return service.carLocInfoGetAll(car_rez_code);
    }
//    @GetMapping("/rezList/{mem_code}")
//    public List<CarRezInfoResponseVO> carRezGetAll(@PathVariable String mem_code){
//        return service.carRezGetAll(mem_code);
//    }
    @GetMapping("/rezList/{mem_code}/{rez_status}")
    public List<CarRezInfoResponseVO> confirmedCarRezGetAll(@PathVariable String mem_code,@PathVariable String rez_status){
        return service.filterCarRezGetAll(mem_code,rez_status);
    }

    @GetMapping("/searchCarList")
    public List<CarNameCodeResponseVO> searchCarGetAll(){
        return service.searchCarGetAll();
    }

    @GetMapping("/carRezDetail/{car_rez_code}")
    public CarRezResponseVO carRezDetailGetOne(@PathVariable String car_rez_code){
        return service.carRezDetailGetOne(car_rez_code);
    }

    @DeleteMapping("/carRezDetail/{car_rez_code}")
    public ResponseEntity<String> carRezDelete(@PathVariable String car_rez_code){
        service.carRezDelete(car_rez_code);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/carRezDetail")
    public ResponseEntity<CarRezDTO2> carRezInfoUpdate(@RequestBody CarRezDTO carRezDTO){
//        CarRezDTO2 result=service.getCarRezDTO2(carRezDTO);
        CarRezDTO2 success=service.carRezInfoUpdate(carRezDTO);
        return ResponseEntity.ok(success);
    }

    @PostMapping("/operation")
    public ResponseEntity<OperationDTO> operationInfoSave(@RequestBody OperationDTO operationDTO){
        System.out.println("controller: "+operationDTO);
        service.operationInfoSave(operationDTO);
        return ResponseEntity.ok(operationDTO);
    }
    @GetMapping("/findRoute/{receipt_loc}/{return_loc}/{dest_loc}")
    public RouteDTO routes(
            @PathVariable String receipt_loc,@PathVariable String return_loc, @PathVariable String dest_loc
    ){
        RouteDTO routeDTO = new RouteDTO(
                AddressService.findGeoPoint(receipt_loc),AddressService.findGeoPoint(return_loc),AddressService.findGeoPoint(dest_loc)
        );
//        AddressService.findGeoPoint(receipt_loc);
//        AddressService.findGeoPoint(return_loc);
//        AddressService.findGeoPoint(dest_loc);

        return routeDTO;
    }
}
