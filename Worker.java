package lab4;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.*;
import java.util.Set;

public class Worker {

    @Size(min = 4, message = "ID must be more than 3 character")
    @Pattern(regexp = "[a-z]+_[1-9]+|[a-z]+_[1-9][0-9]+", message = "Id new worker must consist of letter LowerCase, than symbol '_' and number (for example, 'worker_1' is correct id)")
    private String id;

    @Size(min = 4, message = "Surname must be more than 3character")
    @Pattern(regexp = "[A-Z][a-z]+", message = "Surname must consists only from letter, and first letter must be UpperCase")
    private String Surname;

    @Size(min = 3, message = "Name must be more than 2 character")
    @Pattern(regexp = "[A-Z][a-z]+", message = "Name must consists only from letter, and first letter must be UpperCase")
    private String Name;

    @Size(min = 6, message = "Lastname must be more than 5 character")
    @Pattern(regexp = "[A-Z][a-z]+", message = "Lastname must consists only from letter, and first letter must be UpperCase")
    private String Lastname;

    @Min(value = 18, message = "Age cannot be less than 18")
    @Max(value = 60, message = "Age cannot be more than 60")
    private int age;

    @Min(value = 500, message = "Salary must be more than 500")
    @Max(value = 20000, message = "Salary cannot be more than 20000")
    private int salary;


   @Min(value = 1, message = "Experience must be more or equal 1 year")
    private int experience;


    public String getId() {
        return id;
    }

    public String getSurname() {
        return Surname;
    }

    public String getName() {
        return Name;
    }

    public String getLastname() {
        return Lastname;
    }

    public int getAge() {
        return age;
    }

    public int getSalary() {
        return salary;
    }

    public int getExperience() {
        return experience;
    }

    public static class Builder{

        private Worker person;

        public Builder(){
            person = new Worker();
        }

        public Builder setId(String id){
            person.id = id;
            return this;
        }

        public Builder setSurname(String Surname){
            person.Surname = Surname;
            return this;
        }

        public Builder setName(String name){

            person.Name = name;
            return this;
        }

        public Builder setLastname(String lastname){
            person.Lastname = lastname;
            return this;
        }

        public Builder setAge(int age){
            person.age = age;
            return this;
        }

        public Builder setSalary(int salary){
            person.salary = salary;
            return this;
        }

        public Builder setExperience(int experience){
            person.experience = experience;
            return this;
        }

        public Worker build(){
            validate(person);
            return person;
        }
    }

    private static void validate(Worker worker) throws IllegalArgumentException {

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();


        Set<ConstraintViolation<Worker>> check = validator.validate(worker);

        StringBuilder sb = new StringBuilder();

        for(ConstraintViolation<Worker> i : check){
            sb.append("Error value "+i.getInvalidValue() + " because " + i.getMessage());
            sb.append("\n");
        }

        if(sb.length() > 0){
            throw new IllegalArgumentException(sb.toString());
        }
    }

    public static void main(String args[]){
        try {
            Worker worker = new Worker.Builder()
                    .setId("worker_1")
                    .setSurname("Petrenko")
                    .setName("Petro")
                    .setLastname("Petrovych")
                    .setAge(19)
                    .setSalary(900)
                    .setExperience(7)
                    .build();
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }

}
