package com.mindurownbusiness.springboottutorial.springboottutorial.service;

import ch.qos.logback.core.boolex.EvaluationException;
import com.mindurownbusiness.springboottutorial.springboottutorial.dto.EmployDTO;
import com.mindurownbusiness.springboottutorial.springboottutorial.entity.EmployeeEntity;
import com.mindurownbusiness.springboottutorial.springboottutorial.repositary.EmployeeReepository;
import org.modelmapper.ModelMapper;
//import org.springframework.data.util.ReflectionUtils;
import org.springframework.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeReepository employeeReepository;
    private final ModelMapper modelMapper;

    public EmployeeService(EmployeeReepository employeeReepository, ModelMapper modelMapper) {
        this.employeeReepository = employeeReepository;
        this.modelMapper = modelMapper;
    }

//    public EmployDTO getEmployById(Long Id) {
//        EmployeeEntity employeeEntity = employeeReepository.findById(Id).orElse(null);
//
//       return modelMapper.map( employeeEntity,  EmployDTO.class);
//    }

    public Optional<EmployDTO> getEmployById(Long Id) {
//        Optional<EmployeeEntity> employeeEntity = employeeReepository.findById(id);
//        return employeeEntity.map(employeeEntity1 -> modelMapper.map(employeeEntity1, EmployDTO.class));
//
        return employeeReepository.findById(Id).map(employeeEntity ->  modelMapper.map(employeeEntity, EmployDTO.class));
    }

    public List<EmployDTO> getAllEmployees() {
       List<EmployeeEntity> employeeEntities = employeeReepository.findAll();
      return employeeEntities
               .stream()
               .map(employeeEntity -> modelMapper.map(employeeEntity, EmployDTO.class) )
               .collect(Collectors.toList());
    }

    public EmployDTO createNewEmployee(EmployDTO inputEmplyee) {
        EmployeeEntity toSaveentity = modelMapper.map(inputEmplyee, EmployeeEntity.class);
       EmployeeEntity SavedEmployeeEntity = employeeReepository.save(toSaveentity);
           return modelMapper.map(SavedEmployeeEntity,EmployDTO.class);
    }

    public EmployDTO updateEmplyeeById(Long employeeID, EmployDTO employDTO) {
        EmployeeEntity employeeEntity =modelMapper.map(employDTO, EmployeeEntity.class);
        employeeEntity.setId(employeeID);
        EmployeeEntity savedEmployeeEntity = employeeReepository.save(employeeEntity);
        return  modelMapper.map(savedEmployeeEntity, EmployDTO.class);
    }

    public boolean isExistByEmployeeId(Long employeeID){
        return employeeReepository.existsById(employeeID);
    }

    public boolean deleteEmplyeeById(Long employeeID) {
        boolean exists =isExistByEmployeeId(employeeID);
        if (!exists)
            return false;
        employeeReepository.deleteById(employeeID);
        return true;
    }

    public EmployDTO updatePartialEmplyeeById(Long employeeID, Map<String, Object> update) {
        boolean exists =isExistByEmployeeId(employeeID);
        if (!exists)
            return null;
       EmployeeEntity employeeEntity = employeeReepository.findById(employeeID).get();
     update.forEach((field, value) -> {
         Field fieldToBeUpdate = ReflectionUtils.findField(EmployeeEntity.class, field);
         if (fieldToBeUpdate!=null) {
             fieldToBeUpdate.setAccessible(true);
             ReflectionUtils.setField(fieldToBeUpdate, employeeEntity, value);
         }
         });
//     return ModelMapper.map(employeeReepository.save(employeeEntity), EmployDTO.class);
        return modelMapper.map(employeeReepository.save(employeeEntity), EmployDTO.class);
    }

//    public EmployDTO updatePartialEmplyeeById(Long employeeID, Map<String, Object> update) {
//
//        boolean exists = isExistByEmployeeId(employeeID);
//        if (!exists) {
//            return null;
//        }
//
//        EmployeeEntity employeeEntity = employeeReepository.findById(employeeID).get();
//
//        update.forEach((field, value) -> {
//            Field fieldToBeUpdate = ReflectionUtils.findField(EmployeeEntity.class, field);
//
//            if (fieldToBeUpdate != null) {
//                fieldToBeUpdate.setAccessible(true);
//                ReflectionUtils.setField(fieldToBeUpdate, employeeEntity, value);
//            }
//        });
//
//        EmployeeEntity savedEmployee = employeeReepository.save(employeeEntity);
//
//        return modelMapper.map(savedEmployee, EmployDTO.class);
//    }
}
