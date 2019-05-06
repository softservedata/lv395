package com.softserve.edu.opencart.data;

public final class UserRepository {
    private static volatile UserRepository instance = null;
    
    private UserRepository() {
    }

    public static UserRepository get() {
        if (instance == null) {
            synchronized (UserRepository.class) {
                if (instance == null) {
                    instance = new UserRepository();
                }
            }
        }
        return instance;
    }
    
    public IUser defaultUser() {
        return customer();
    }
    
    //public IUser admin() {}
    
    public IUser customer() {
        return User.get()
                .setFirstname("8_firstname")
                .setLastname("8_lastname")
                .setEmail("8_email")
                .setTelephone("8_telephone")
                .setAddress1("8_address1")
                .setCity("8_city")
                .setPostcode("8_postcode")
                .setCountry("8_country")
                .setRegion("8_region")
                .setPassword("8_password")
                .setSubscribe(true)
                .setFax("8_fax")
                .setCompany("8_company")
                .setAddress2("8_address2")
                .build();
    }

    public IUser yaroslav() {
        return User.get()
                .setFirstname("8_firstname")
                .setLastname("8_lastname")
                .setEmail("garasym@gmail.com")
                .setTelephone("8_telephone")
                .setAddress1("8_address1")
                .setCity("8_city")
                .setPostcode("8_postcode")
                .setCountry("8_country")
                .setRegion("8_region")
                .setPassword("qwerty")
                .setSubscribe(true)
                .setFax("8_fax")
                .setCompany("8_company")
                .setAddress2("8_address2")
                .build();
    }

    public IUser johnWick() {
        return User.get()
                .setFirstname("John")
                .setLastname("Wick")
                .setEmail("john.wick.test@ukr.net")
                .setTelephone("+380123456789")
                .setAddress1("")
                .setCity("RaccoonCity")
                .setPostcode("12345")
                .setCountry("USA")
                .setRegion("Texas")
                .setPassword("qwerty")
                .setSubscribe(true)
                .setFax("123456789")
                .setCompany("Mafia")
                .setAddress2("address")
                .build();
    }

    public IUser userWithIncorrectCredentials(){
        return User.get()
                .setFirstname("Not_John")
                .setLastname("Not_Wick")
                .setEmail("not.john.wick.test@ukr.net")
                .setTelephone("8_telephone")
                .setAddress1("8_address1")
                .setCity("8_city")
                .setPostcode("8_postcode")
                .setCountry("8_country")
                .setRegion("8_region")
                .setPassword("notqwerty")
                .setSubscribe(true)
                .setFax("8_fax")
                .setCompany("8_company")
                .setAddress2("8_address2")
                .build();
    }

    // TODO
   //public List<IUser> fromExcel() {}
   //public List<IUser> fromDB() { create class, read, max 5-7 lines}
}
