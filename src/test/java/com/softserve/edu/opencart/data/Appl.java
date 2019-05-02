//package com.softserve.edu;
package com.softserve.edu.opencart.data;

import com.softserve.edu.opencart.data.User;

public class Appl {

    public static void main(String[] args) {
        // 1. Use Classic Constructor
//        User user = new User("1_firstname", "1_lastname", "1_email",
//                "1_telephone", "1_fax", "1_company",
//                "1_address1", "1_address2", "1_city",
//                "1_postcode", "1_country", "1_region",
//                "1_password", true);
//        System.out.println("firstname = " + user.getFirstname());
        //
        // 2. Use Default Constructor and Setters
//        User user = new User();
//        user.setFirstname("2_firstname");
//        user.setLastname("2_lastname");
//        user.setEmail("2_email");
//        user.setTelephone("2_telephone");
//        user.setFax("2_fax");
//        user.setCompany("2_company");
//        user.setAddress1("2_address1");
//        user.setAddress2("2_address2");
//        user.setCity("2_city");
//        user.setPostcode("2_postcode");
//        user.setCountry("2_country");
//        user.setRegion("2_region");
//        user.setPassword("2_password");
//        user.setSubscribe(true);
//        System.out.println("firstname = " + user.getFirstname());
        //
        // 3. Add Fluent Interface 
//        User user = new User()
//            .setFirstname("3_firstname")
//            .setLastname("3_lastname")
//            .setEmail("3_email")
//            .setTelephone("3_telephone")
//            .setFax("3_fax")
//            .setCompany("3_company")
//            .setAddress1("3_address1")
//            .setAddress2("3_address2")
//            .setCity("3_city")
//            .setPostcode("3_postcode")
//            .setCountry("3_country")
//            .setRegion("3_region")
//            .setPassword("3_password")
//            .setSubscribe(true);
//        System.out.println("firstname = " + user.getFirstname());
        //
        // 4. Add Static Factory 
//        User user = User.get()
//                .setFirstname("4_firstname")
//                .setLastname("4_lastname")
//                .setEmail("4_email")
//                .setTelephone("4_telephone")
//                .setFax("4_fax")
//                .setCompany("4_company")
//                .setAddress1("4_address1")
//                .setAddress2("4_address2")
//                .setCity("4_city")
//                .setPostcode("4_postcode")
//                .setCountry("4_country")
//                .setRegion("4_region")
//                .setPassword("4_password")
//                .setSubscribe(true);
//        System.out.println("firstname = " + user.getFirstname());
        //
        // 5. Add Builder by Interfaces
//        User user = User.get()
//                .setFirstname("5_firstname")
//                .setLastname("5_lastname")
//                .setEmail("5_email")
//                .setTelephone("5_telephone")
//                .setAddress1("5_address1")
//                .setCity("5_city")
//                .setPostcode("5_postcode")
//                .setCountry("5_country")
//                .setRegion("5_region")
//                .setPassword("5_password")
//                .setSubscribe(true)
//                .setFax("5_fax")
//                .setCompany("5_company")
//                .setAddress2("5_address2")
//                .build();
//        System.out.println("firstname = " + user.getFirstname());
//        // Code ...
//        System.out.println("firstname = " + user.setFirstname("abcd")); // Destroy Object
//        // Code ...
//        System.out.println("firstname = " + user.getFirstname());
        //
        // 6. Add Dependency Inversion
//        IUser user = User.get()
//                .setFirstname("6_firstname")
//                .setLastname("6_lastname")
//                .setEmail("6_email")
//                .setTelephone("6_telephone")
//                .setAddress1("6_address1")
//                .setCity("6_city")
//                .setPostcode("6_postcode")
//                .setCountry("6_country")
//                .setRegion("6_region")
//                .setPassword("6_password")
//                .setSubscribe(true)
//                .setFax("6_fax")
//                .setCompany("6_company")
//                .setAddress2("6_address2")
//                .build();
        //System.out.println("firstname = " + user.setFirstname("abcd")); // Compile Error
        //System.out.println("firstname = " + ((User) user).setFirstname("1234")); // (User) - Code Smell
        // Code ...
//        System.out.println("firstname = " + user.getFirstname());
        //
        // 7. Add Singleton
        // 8. Repository 
        IUser user = UserRepository.get().customer();
        System.out.println("firstname = " + user.getFirstname());
    }
}
