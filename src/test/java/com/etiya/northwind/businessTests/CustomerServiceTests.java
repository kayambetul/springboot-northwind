package com.etiya.northwind.businessTests;

import com.etiya.northwind.business.concretes.CustomerManager;
import com.etiya.northwind.business.responses.customers.CustomerListResponse;
import com.etiya.northwind.business.responses.customers.ReadCustomerResponse;
import com.etiya.northwind.core.utilities.mapping.ModelMapperManager;
import com.etiya.northwind.core.utilities.results.DataResult;
import com.etiya.northwind.core.utilities.results.SuccessDataResult;
import com.etiya.northwind.dataAccess.CustomerRepository;
import com.etiya.northwind.entities.concretes.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTests {
    @Mock
    ModelMapperManager modelMapperManager;

    @Mock
    ModelMapper modelMapper;

    @Mock
    CustomerRepository customerRepository;

    @InjectMocks
    CustomerManager customerManager;

    @Test
    void getById() {
        Customer customer = new Customer("aaaaa","dsd","klşlli",new City(),new ArrayList<>(),new Country());
       // when(modelMapperManager.forResponse()).thenReturn(modelMapper);
        when(customerRepository.findById(customer.getCustomerId())).thenReturn(Optional.of(customer));

        ReadCustomerResponse readCustomerResponse = this.modelMapperManager.forResponse().map(customer, ReadCustomerResponse.class);

        DataResult<ReadCustomerResponse> expected = new SuccessDataResult<>(readCustomerResponse);
        expected.getData().getCustomerId();

        DataResult<ReadCustomerResponse> actual = customerManager.getById(customer.getCustomerId());
        actual.getData().getCustomerId();



        Assertions.assertEquals(expected.getData().getCustomerId(), actual.getData().getCustomerId());
    }
}
