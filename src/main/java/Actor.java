public class Actor {
    private String surname;
    private String film;
    private int age;
    private String country;

    Actor(String surname, String film, int age, String country) {
        this.surname = surname;
        this.film = film;
        this.age = age;
        this.country = country;
    }

    Actor(String surname, String film, String age, String country) {
        this.surname = surname;
        this.film = film;
        this.age = Integer.parseInt(age);
        this.country = country;
    }

    public String getSurname() {
        return surname;
    }

    public String getFilm() {
        return film;
    }

    public int getAge() {
        return age;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public String toString() {
        return "Фамилия: " + surname + ", Фильм: " +film + ", Возраст: " + age + ", Страна: " + country ;
    }
}