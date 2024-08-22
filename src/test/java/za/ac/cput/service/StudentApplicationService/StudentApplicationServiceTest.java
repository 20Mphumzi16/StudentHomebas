package za.ac.cput.service.StudentApplicationService;
/*
 * Author : Sabelo Kama (219149364)
 * Date : 24 July 2024
 * */

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.*;
import za.ac.cput.factory.ContactFactory;
import za.ac.cput.factory.DocumentFactory;
import za.ac.cput.factory.StudentApplicationFactory;
import za.ac.cput.service.studentApplication.StudentApplicationService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.class)
class StudentApplicationServiceTest {

    @Autowired
    StudentApplicationService service;

    Address address1, address2;
    byte[] photo1, photo2;
    Document document1, document2;
    List<Document> photoList1, photoList2;
    Name landLordName1, landLordName2, studentName1, studentName2;
    Contact contact1, contact2;
    Landlord landlordA, landlordB;
    Property property1, property2;
    Student student1, student2;
    LocalDate dateApplied;
    AcademicDetails records1, records2;
    StudentApplication studentApplication1, studentApplication2;


    @BeforeEach
    void setUp() {
        dateApplied = LocalDate.of(2023,8,23);

        photo1 = new byte[0];
        photo2 = new byte[0];

        document1 = DocumentFactory.buildDocument(79L
                ,"ID",photo1
                , LocalDateTime.of(LocalDate.of(2024,3,24)
                        , LocalTime.of(14,22)));
        document2 = DocumentFactory.buildDocument(80L
                ,"Proof of Address",photo2
                , LocalDateTime.of(LocalDate.of(2023,6,9)
                        , LocalTime.of(9,47)));

        photoList1 = new ArrayList<>();
        photoList1.add(document1);

        photoList2 = new ArrayList<>();
        photoList2.add(document2);

        address1 = new Address.AddressBuilder().setStreet("47 Lucy Drive")
                .setSuburb("Parow").setCity("Cape Town").setPostalCode("7490").buildAddress();
        address2 = new Address.AddressBuilder().setStreet("11 Olympia Crescent")
                .setSuburb("Delville Park").setCity("George").setPostalCode("6529").buildAddress();

        landLordName1 = new Name.NameBuilder().setFirstName("Mihlai").setLastName("Tukuza").build();
        landLordName2 = new Name.NameBuilder().setFirstName("Emily").setLastName("Thorne").build();

        studentName1 = new Name.NameBuilder().setFirstName("Mila").setLastName("Juices").build();
        studentName2 = new Name.NameBuilder().setFirstName("Sandy").setLastName("Downing").build();

        contact1 = ContactFactory.createContact("0786549009", "mizot24@gmail.com", address1);
        contact2 = ContactFactory.createContact("0715468926", "emily6410@gmail.com", address2);

        records1 = new AcademicDetails.Builder().setAcademicDetailsID("234")
                .setInstituteName("UWC").setProgramOfStudy("Computer Sciences")
                .setYearOfStudy(2023).build();
        records2 = new AcademicDetails.Builder().setAcademicDetailsID("1689")
                .setInstituteName("CPUT").setProgramOfStudy("Information Technology")
                .setYearOfStudy(2021).build();


        landlordA = new Landlord.LandlordBuilder().setName(landLordName1).setUserId(45673L)
                .setDateOfBirth(LocalDate.of(1986,8,13)).setNumOfPropertiesOwned(3)
                .setGender("Male").setPassword("hsfs2637!").setDocuments(photoList1)
                .setContact(contact1).buildLandlord();

        landlordB = new Landlord.LandlordBuilder().setName(landLordName2).setUserId(45696L)
                .setDateOfBirth(LocalDate.of(1994,3,16)).setNumOfPropertiesOwned(5)
                .setGender("Female").setPassword("Emily145Tho!!").setDocuments(photoList2)
                .setContact(contact2).buildLandlord();

        property1 =new Property.Builder()
                .setPropertyName("1st Village").setAddress(address1).setLandlord(landlordA)
                .setPictures(photoList1).setNumberOfRooms(3).setPrice(3750.50).build();

        property2 = new Property.Builder()
                .setPropertyName("More Takers").setAddress(address1).setLandlord(landlordB)
                .setPictures(photoList2).setNumberOfRooms(2).setPrice(4000.50).build();

        student1 = new Student.StudentBuilder().setUserId(377L).setName(studentName1)
                .setGender("Male").setDateOfBirth(LocalDate.of(2000,11,4))
                .setDocuments(photoList1).setPassword("kjrgj6523./").setContact(contact1)
                .setAcademicDetails(records1).build();
        student2 = new Student.StudentBuilder().setName(studentName1)
                .setGender("Female").setDateOfBirth(LocalDate.of(2003,9,25))
                .setDocuments(photoList2).setPassword("kgtugkjd123!").setContact(contact2)
                .setAcademicDetails(records2).build();

        studentApplication1 = StudentApplicationFactory.studentApplication(LocalDate.now(),
                Application.Status.Pending.toString(), student1, property1, property1.getPrice());

        studentApplication2 = StudentApplicationFactory.studentApplication(dateApplied,
                Application.Status.Accepted.toString(), student2, property2, property2.getPrice());
    }

    @Test
    @Order(1)
    void save() {
        StudentApplication savedApp1 = service.save(studentApplication1);
        StudentApplication savedApp2 = service.save(studentApplication2);
        assertNotNull(savedApp1);
        assertNotNull(savedApp2);
    }

    @Test
    @Order(2)
    void read() {
        StudentApplication readApp1 = service.read(studentApplication1.getAppNo());
        StudentApplication readApp2 = service.read(studentApplication2.getAppNo());
        assertNotNull(readApp1);
        assertNotNull(readApp2);
    }

    @Test
    @Order(3)
    void update() {
        StudentApplication statusUpdate = new StudentApplication.Builder().copy(studentApplication1)
                .setStatus(Application.Status.Accepted).build();
        StudentApplication update = service.update(statusUpdate);
        assertNotNull(update);
    }

    @Test
    @Order(4)
    void deleteById() {
        boolean delete = service.deleteById(studentApplication2.getAppNo());
        assertTrue(delete);
    }

    @Test
    @Order(5)
    void getAll() {
        List<StudentApplication> allRecords = service.getAll();
        System.out.println(allRecords.toString());
        assertNotNull(allRecords);
    }
}