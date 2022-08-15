package com.etiya.northwind.businessTests;

import com.etiya.northwind.business.abstracts.ProductService;
import com.etiya.northwind.business.abstracts.StudentService;
import com.etiya.northwind.business.concretes.CategoryManager;
import com.etiya.northwind.business.concretes.SupplierManager;
import com.etiya.northwind.business.requests.categories.CreateCategoryRequest;
import com.etiya.northwind.business.responses.categories.CategoryListResponse;
import com.etiya.northwind.business.responses.categories.ReadCategoryResponse;
import com.etiya.northwind.core.utilities.mapping.ModelMapperManager;
import com.etiya.northwind.core.utilities.results.DataResult;
import com.etiya.northwind.core.utilities.results.Result;
import com.etiya.northwind.core.utilities.results.SuccessDataResult;
import com.etiya.northwind.dataAccess.CategoryRepository;
import com.etiya.northwind.entities.concretes.Category;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.quality.Strictness;
import org.modelmapper.ModelMapper;


import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ExtendWith(MockitoExtension.class)
public class SupplierServiceTest {

    @Mock
    CategoryRepository categoryRepository;

    @Mock
    ModelMapperManager modelMapperManager;

    @Mock
    ModelMapper modelMapper;

    @InjectMocks
    CategoryManager categoryManager;


    @Test
    public void save() {

        Category category = new Category(1, "deneme", new ArrayList<>());//gelen veri kontrol edilmiyor.

        CreateCategoryRequest createCategoryRequest = new CreateCategoryRequest(1, "deneme");

        when(modelMapperManager.forRequest()).thenReturn(modelMapper);
        when(modelMapperManager.forRequest().map(createCategoryRequest, Category.class)).thenReturn(category);
        when(categoryRepository.save(category)).thenReturn(category);

        Result result = this.categoryManager.add(createCategoryRequest);
        boolean expected = true;

        boolean actual = result.isSuccess();


        Assertions.assertEquals(expected, actual);


    }


  //  @Test
//    @Override
//    public void testFindById() {
//        EmployeeEntity employeeEntity = employeeRepository.findById(1L).get();
//        //Hamit Test adında kayıt var mı yok mu
//        assertEquals("Hamit Test", employeeEntity.getFirstName());
//    }


   /* @Test
    public void getAll(){
        //given
        Category category1 = new Category(1, "deneme1", new ArrayList<>());
        Category category2 = new Category(2, "deneme2", new ArrayList<>());
        List<Category> categories = new ArrayList<>();
        categories.add(category1);
        categories.add(category2);



        CategoryListResponse categoryListResponse1=new CategoryListResponse(1,"deneme1");
        CategoryListResponse categoryListResponse2=new CategoryListResponse(2,"deneme2");
        List<CategoryListResponse> response = new ArrayList<>();
        response.add(categoryListResponse1);
        response.add(categoryListResponse2);

       when(categoryRepository.findAll()).thenReturn(categories);
        when(modelMapperManager.forResponse()).thenReturn(modelMapper);
        when(categories.stream().map(category ->  this.modelMapperManager.forResponse().
                map(category,CategoryListResponse.class)).collect(Collectors.toList())).thenReturn(response);
        boolean expected = true;

        boolean actual = this.categoryManager.getAll().isSuccess();

     //  List<CategoryListResponse> actual = dataResult.getData();

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(actual, response),
                () -> assertEquals(actual.size(), response.size())
        );
        Assertions.assertEquals(expected,actual);

    }


    //LIST
//    @Test
//    @Override
//    public void testList() {
//        List<EmployeeEntity> list = employeeRepository.findAll();
//        //eğer Sıfırdan büyükse liste vardır
//        assertThat(list).size().isGreaterThan(0);
//    }

*/

    @Test
    public void get(){
        //given
        Category category = new Category();
        category.setCategoryId(1);
        category.setCategoryName("deneme");
        category.setProducts(new ArrayList<>());

        ReadCategoryResponse readCategoryResponse=new ReadCategoryResponse(1,"deneme", new ArrayList<>());

        when(categoryRepository.findById(category.getCategoryId())).thenReturn(Optional.of(category));
        when(modelMapperManager.forResponse()).thenReturn(modelMapper);
        when(modelMapperManager.forResponse().map(category, ReadCategoryResponse.class)).thenReturn(readCategoryResponse);

        //when
      /*  Category category1 = this.categoryRepository.findById(category.getCategoryId()).get();
        ReadCategoryResponse actual = new ReadCategoryResponse();
        actual.setCategoryId(category1.getCategoryId());
        actual.setCategoryName(category1.getCategoryName());
        actual.setProducts(new ArrayList<>());*/
      //  DataResult dataResult=categoryManager.getById(1);
      //  Category actual=dataResult.getData();

        Category actual= this.categoryRepository.findById(category.getCategoryId()).get();

        //then
        assertAll(

                () -> assertEquals(actual.getCategoryName(), readCategoryResponse.getCategoryName()),
                () -> assertEquals(actual.getCategoryId(), readCategoryResponse.getCategoryId())
        );

    }

    /*
    @Test
    public void update(){
        //given
        Customer customer = Customer.builder()
                .id(1L)
                .identityNumber("11111111116")
                .firstName("First Name")
                .lastName("Last Name")
                .salary(new BigDecimal("6000"))
                .phoneNumber("5555555555")
                .build();

        CustomerDto customerDto = CustomerDto.builder()
                .id(1L)
                .identityNumber("11111111116")
                .firstName("First Name")
                .lastName("Last Name")
                .salary(new BigDecimal("6000"))
                .phoneNumber("5555555555")
                .build();


        when(customerMapper.mapFromCustomerDtoToCustomer(customerDto)).thenReturn(customer);
        when(customerRepository.save(customer)).thenReturn(customer);
        when(customerMapper.mapFromCustomerToCustomerDto(customer)).thenReturn(customerDto);

        //when
        CustomerDto actual = this.customerService.save(customerDto);

        //then
        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(actual.getFirstName(), customerDto.getFirstName()),
                () -> assertEquals(actual, customerDto),
                () -> assertEquals(actual.getId(), customerDto.getId())
        );

    }
    */




    @Test
    public void getAll() {
        List<Category> categories = new ArrayList<>();
        Category category = new Category();
        category.setCategoryId(1);
        category.setCategoryName("jefsdjx");
        categories.add(category);

        when(modelMapperManager.forResponse()).thenReturn(modelMapper);
        Mockito.when(categoryRepository.findAll()).thenReturn(categories);
        DataResult<List<CategoryListResponse>> responses = categoryManager.getAll();

        Assertions.assertEquals(1, responses.getData().size());
        Assertions.assertEquals(category, responses.getData().get(0));
    }

    @Test
    void getByIdTest_whenCustomerIdExists_shouldReturnCustomerWithThatId() {
        Category category = new Category(1,"deneme",new ArrayList<>());
        when(modelMapperManager.forResponse()).thenReturn(modelMapper);
        when(categoryRepository.findById(category.getCategoryId())).thenReturn(Optional.of(category));
        ReadCategoryResponse readCategoryResponse = this.modelMapperManager.forResponse().map(category, ReadCategoryResponse.class);
        DataResult<ReadCategoryResponse> expected = new SuccessDataResult<>(readCategoryResponse);

        DataResult<ReadCategoryResponse> actual = categoryManager.getById(category.getCategoryId());

        Assertions.assertEquals(expected, actual);
    }

}

//LIST
//    @Test
//    @Override
//    public void testList() {
//        List<EmployeeEntity> list = employeeRepository.findAll();
//        //eğer Sıfırdan büyükse liste vardır
//        assertThat(list).size().isGreaterThan(0);
//    }


