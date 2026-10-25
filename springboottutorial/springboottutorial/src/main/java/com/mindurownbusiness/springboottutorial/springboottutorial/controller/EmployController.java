package com.mindurownbusiness.springboottutorial.springboottutorial.controller;

import com.mindurownbusiness.springboottutorial.springboottutorial.dto.EmployDTO;
import com.mindurownbusiness.springboottutorial.springboottutorial.entity.EmployeeEntity;
import com.mindurownbusiness.springboottutorial.springboottutorial.exception.ResourceNotFound;
import com.mindurownbusiness.springboottutorial.springboottutorial.repositary.EmployeeReepository;
import com.mindurownbusiness.springboottutorial.springboottutorial.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
@Validated
@RestController
@RequestMapping(path = "/employees")

public class EmployController {

//    @GetMapping(path = "/getSecretMessage")
//    public String getSuperSecretMessage(){
//        return "Secret message  BSDK";
//    }
//
//    private final EmployeeReepository employeeReepository;

//    public EmployController(EmployeeReepository employeeReepository) {
//        this.employeeReepository = employeeReepository;
//    }

    private final EmployeeService employeeService;

    public EmployController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/{employeeID}")
//    public EmployDTO getEmployById(@PathVariable(name = "employeeID" ) Long Id)
//    public EmployeeEntity getEmployById(@PathVariable(name = "employeeID" ) Long Id)


//     public EmployDTO getEmployById(@PathVariable(name = "employeeID" ) Long Id){
////    return  new EmployDTO(Id,"Meopi", "Meopi@gmail.com",22, LocalDate.of(2026,7,29),true);
//        return employeeService.getEmployById(Id);
//    }

    public ResponseEntity<EmployDTO> getEmployById(@PathVariable(name = "employeeID") Long Id) {
        Optional<EmployDTO> employDTO = employeeService.getEmployById(Id);
        return employDTO
                .map(employDTO1 -> ResponseEntity.ok(employDTO1))
                .orElseThrow(() -> new ResourceNotFound("Employee not found with id:" +Id));
    }




//    @GetMapping(path = "/employees")
//    @GetMapping
//    public String getAllEmployees(@RequestParam(required = false) Integer age,
//                                  @RequestParam(required = false) String sortby){

    /// /        return "Hi age"+ age +sortby;
//    }
    @GetMapping
    public ResponseEntity<List<EmployDTO>> getAllEmployees(@RequestParam(required = false) Integer age,
                                                           @RequestParam(required = false) String sortby) {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }
        //    @PostMapping
//    public String creatnewEmployee(){
//        return " hello from post";
//    }


//    @PostMapping
//    public EmployDTO creatNewEmployee(@RequestBody EmployDTO inputEmplyee){
//
//        inputEmplyee.setId(100L);
//        return inputEmplyee;
//    }


        @PostMapping
        public ResponseEntity<EmployDTO> createNewEmployee(
                @Valid @RequestBody EmployDTO inputEmplyee) {

            EmployDTO saveEmployee =
                    employeeService.createNewEmployee(inputEmplyee);

            return new ResponseEntity<>(saveEmployee, HttpStatus.CREATED);
        }


        @PutMapping(path = "/{employeeID}")
        public ResponseEntity<EmployDTO> updateEmplyeeById(
                @RequestBody EmployDTO employDTO,
                @PathVariable Long employeeID) {

            return ResponseEntity.ok(
                    employeeService.updateEmplyeeById(employeeID, employDTO)
            );
        }


        @DeleteMapping(path = "/{employeeID}")
        public ResponseEntity<Boolean> deleteEmplyeeById(
                @PathVariable Long employeeID) {

            boolean gotDeleted =
                    employeeService.deleteEmplyeeById(employeeID);

            if (gotDeleted)
                return ResponseEntity.ok(true);

            return ResponseEntity.notFound().build();
        }


//    @PatchMapping(path = "/{employeeID}")
//    public ResponseEntity<EmployDTO> updatePartialEmplyeeById(
//            @RequestBody Map<String, Object> update,
//            @PathVariable Long employeeID) {
//
//        EmployDTO employDTO =
//                employeeService.updatePartialEmplyeeById(employeeID, update);
//
//        if (employDTO == null)
//            return ResponseEntity.notFound().build();
//
//        return ResponseEntity.ok(employDTO);
//    }


        @PatchMapping(path = "/{employeeID}")
        public ResponseEntity<EmployDTO> updatePartialEmplyeeById(
                @RequestBody Map<String, Object> update,
                @PathVariable Long employeeID) {

            EmployDTO employDTO =
                    employeeService.updatePartialEmplyeeById(employeeID, update);

            if (employDTO == null)
                return ResponseEntity.notFound().build();

            return ResponseEntity.ok(employDTO);

        }

    }

