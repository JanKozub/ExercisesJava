package lessons.exception;

public class ConstructionExceptions {


    public static class Person {
        private String name;
        private int age;

        public Person(String name, int age) {
            this.age = age;
            this.name = name;
            validate();
        }

        public void validate() {
            if(age <= 0)
                throw new IllegalArgumentException("age <= 0");
            this.age = age;

            if((name == null) || name.isEmpty())
                throw new IllegalArgumentException("name is empty");
        }
    }


    public static void main(String[] args) {
        Person p = null;
        try {
            p = new Person("asad", 10);
        } catch(Exception ex) {
        }
    }

}

