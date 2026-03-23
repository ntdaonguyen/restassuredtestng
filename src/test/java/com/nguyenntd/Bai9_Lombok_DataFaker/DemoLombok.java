package com.nguyenntd.Bai9_Lombok_DataFaker;

import com.nguyenntd.model.RegisterUserPOJO_Lombok;
import net.datafaker.Faker;
import org.testng.annotations.Test;

import java.util.Locale;

public class DemoLombok {
    @Test
    public void testLombok() {
        RegisterUserPOJO_Lombok registerUserPOJOLombok1 = new RegisterUserPOJO_Lombok();
        registerUserPOJOLombok1.getUserStatus();
        RegisterUserPOJO_Lombok registerUserPOJOLombok2 = new RegisterUserPOJO_Lombok(
                "",
                "",
                "",
                "",
                "",
                "",
                1
        );
    }

    @Test
    public void testFaker() {
        Faker faker = new Faker(new Locale("vi"));
        System.out.println(faker.color().name());
        System.out.println(faker.color().hex());

        String computer = faker.computer().windows();
        String name = faker.name().fullName();
        String fullAddress = faker.address().fullAddress();
        String email = faker.internet().emailAddress();

        System.out.println(computer);
        System.out.println(name);
        System.out.println(fullAddress);
        System.out.println(email);
    }
}
