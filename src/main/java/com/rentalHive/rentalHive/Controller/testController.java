package com.rentalHive.rentalHive.Controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class testController {

    @GetMapping("/")
    public String gethome()
    {
        return "this is a home page from the spring application";
    }
    @GetMapping("/person")
    public  person getPerson()
    {
        person p = new person();
        p.setAge(12);
        p.setName("rayan");
        return p;
    }


}

class person
{
    private  String name;
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}