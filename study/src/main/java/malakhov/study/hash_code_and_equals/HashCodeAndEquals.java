package malakhov.study.hash_code_and_equals;

public class HashCodeAndEquals {
    public class Student {
        private int id;
        private String name;
        private int age;

        public Student(int id, String name, int age) {
            this.id = id;
            this.name = name;
            this.age = age;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (obj instanceof Student) { //warning
                Student temp = (Student) obj;
                return this.id == temp.id && name.equals(temp.name) && this.age == temp.age;
            } else
                return false;
        }

        /*@Override
        public int hashCode() {
            return (int) (31 * id + age + ((name == null) ? 0 : name.hashCode()));
        }*/
        @Override
        public int hashCode() {
            int result = 17;
            result = 31 * result + id;
            result = 31 * result + (name == null ? 0 : name.hashCode());
            result = 31 * result + age;
            return result;
        }

        //@Override
        //        public boolean equals(Object o) {
        //            if (this == o) return true;
        //            if (!(o instanceof Student)) return false;
        //            Student student = (Student) o;
        //            return id == student.id && age == student.age && Objects.equals(name, student.name);
        //        }
        //
        //        @Override
        //        public int hashCode() {
        //            int result = id;
        //            result = 31 * result + (name != null ? name.hashCode() : 0);
        //            result = 31 * result + age;
        //            return result;
        //        }

        @Override
        public String toString() {
            return getClass().getName() + "@name" + name
                    + " id:" + id + " age:" + age;
        }

    }
}
