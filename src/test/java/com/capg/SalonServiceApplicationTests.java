package com.capg;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.capg.dto.SalonServicedto;
import com.capg.entity.SalonService;
import com.capg.exception.SalonServiceNotFoundException;
import com.capg.exception.ServiceAlreadyExistsException;
import com.capg.repository.ISalonRepository;
import com.capg.service.ISalonService;
import com.capg.service.SalonServiceImp;

@SpringBootTest
class SalonServiceApplicationTests {
	@Mock
	ISalonRepository salonRepository;
	@Autowired
	SalonServiceImp salonserviceimp;
	
	@InjectMocks
	ISalonService salon = new SalonServiceImp();
	SalonService s1,s2,s3;
	
	public static SalonService demo(){
		SalonService s = new SalonService();
		s.setServiceId(1);
		s.setServiceName("Haircut");
		s.setServicePrice("200");
		s.setServiceDuration("60");
		s.setDiscount(10);
		return s;
	}
	@BeforeEach
    public void init() {
        s1 = new SalonService(1, " Haircut","200","60",10);
        s2 = new SalonService(2, " Hairspa","200","60",10);
        s3 = new SalonService(3, " Haircut","200","60",10);
        
	}
        

	@Test
	void validSalonServiceAddition() throws ServiceAlreadyExistsException{
		SalonServicedto salonDTO = SalonServicedto.entityToDTO(SalonServiceApplicationTests.demo());
		Mockito.when(salonRepository.findById(salonDTO.getServiceId())).thenReturn(Optional.empty());
		Assertions.assertEquals(salon.addService(SalonService.DTOToentity(salonDTO)), SalonService.DTOToentity(salonDTO));
	}
	/*
	 @Test
	    public void validtUpdate() throws SalonServiceNotFoundException {

		 when(salonRepository.existsById(s1.getServiceId())).thenReturn(true);
		 when(salonRepository.findById(s1.getServiceId())).thenReturn(Optional.ofNullable(s1));
		 when(salonRepository.save(s1)).thenReturn(s1);
		 assertEquals(s1.getDiscount(),salonserviceimp.updateService(s1.getServiceId(),s1).getDiscount());
	 } */
	 
}