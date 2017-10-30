import java.io.*;

public class Main {

    private final static String MENU = "\nМеню:\n" +
            "1. Вывод всех киноактеров\n" +
            "2. Добавление нового киноактера\n" +
            "3. Удаление киноактера с указанной фамилией\n" +
            "4. Вывод отечественных киноактеров\n" +
            "5. Сортировка киноактеров по возрасту\n" +
            "6. Запись всех киноактеров в файл\n" +
            "7. Считывание списка киноактеров из файла\n" +
            "8. Выход";

    private final static String fileLink = "file.txt";

    public static void main(String[] args) {
        Actor[] actors = new Actor[20]; //создали массив 20 элементов Actor
        actors[0] = new Actor("Ivanov", "Krim", 24, "Russia"); //добавили в массив значения
        actors[1] = new Actor("Ivanov", "Krim", 21, "Russia");
        actors[2] = new Actor("Ivanov", "Krim", 23, "USA");
        actors[3] = new Actor("Ivanov", "Krim", 45, "Russia");
        actors[4] = new Actor("Ivanov", "Krim", 12, "Russia");

        boolean isExit = false;//переменая сигнализирующая о выходе
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));//чтение с консоли
        while (!isExit) { // цикл
            System.out.println(MENU);
            try {
                String s = br.readLine(); //присваеваем строку с консоли в переменную s
                int i = Integer.parseInt(s); // преобразуем строку в число
                switch (i) {
                    case 1:
                        printAllActors(actors);
                        break;
                    case 2:
                        actors = addNewActor(actors);
                        break;
                    case 3:
                        actors = deleteActor(actors);
                        break;
                    case 4:
                        printRussianActors(actors);
                        break;
                    case 5:
                        actors = sortActors(actors);
                        break;
                    case 6:
                        saveToFile(actors);
                        break;
                    case 7:
                        actors = getFromFile();
                        break;
                    case 8:
                        isExit = true; //выход из ваил
                        break;
                    default:
                        System.out.println("Данного пункта в меню нет!");
                        break;
                }
            } catch (IOException e) { //Обработка исключения ввода ввывода
                e.printStackTrace();
            } catch (NumberFormatException nfe) {// Обработка Исключение на преобразования страки в число
                System.out.println("Введите число!");
            }
        }

    }

    private static void printAllActors(Actor[] actors) { //метод 1
        for (int i = 0; i < actors.length; i++) {
            if (actors[i] != null)
                System.out.println(actors[i]);
        }
    }

    private static Actor[] addNewActor(Actor[] actors) throws IOException {
        System.out.println("Введите фамилию, фильм, возраст и страну, раздиляя их пробелом");
        for (int i = 0; i < actors.length; i++) {
            if (actors[i] == null) {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                String s = br.readLine();
                String[] params = s.split(" ");
                actors[i] = new Actor(params[0], params[1], params[2], params[3]);
                break;
            }
        }
        return actors;
    }

    private static Actor[] deleteActor(Actor[] actors) throws IOException {
        System.out.println("Введите фамилию актера которого хотите удалить");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        for (int i = 0; i < actors.length; i++) {
            if (actors[i] != null && actors[i].getSurname().equals(s)) {
                actors[i] = null;
            }
        }
        return actors;
    }

    private static void printRussianActors(Actor[] actors) {
        for (int i = 0; i < actors.length; i++) {
            if (actors[i] != null && "russia".equals(actors[i].getCountry().toLowerCase())) { //toLowerCase - преобразует строку маленький регистр
                System.out.println(actors[i]);
            }
        }
    }

    private static Actor[] sortActors(Actor[] actors) {
        for (int i = 0; i < actors.length; i++) {
            if (actors[i] != null) {
/*Предполагаем, что первый элемент (в каждом
подмножестве элементов) является минимальным */
                int min = actors[i].getAge();
                int min_i = i;
/*В оставшейся части подмножества ищем элемент,
который меньше предположенного минимума*/
                for (int j = i + 1; j < actors.length; j++) {
                    if (actors[j] != null) {
//Если находим, запоминаем его индекс
                        if (actors[j].getAge() < min) {
                            min = actors[j].getAge();
                            min_i = j;
                        }
                    }
                }
/*Если нашелся элемент, меньший, чем на текущей позиции,
меняем их местами*/
                if (i != min_i) {
                    Actor tmp = actors[i];
                    actors[i] = actors[min_i];
                    actors[min_i] = tmp;
                }
            }
        }
        return actors;
    }

    private static void
    saveToFile(Actor[] actors) throws IOException {
        File file = new File(fileLink);
        if (file.exists()) {
            file.createNewFile();
        }
        FileWriter writer = new FileWriter(file);
        for (int i = 0; i < actors.length; i++) {
            if (actors[i] != null) {
                writer.write(actors[i].getSurname() +
                        " " + actors[i].getFilm() +
                        " " + actors[i].getAge() +
                        " " + actors[i].getCountry());
                writer.append('\n');
            }

        }
        writer.close();
    }

    private static Actor[] getFromFile() throws IOException {
        FileInputStream fstream = new FileInputStream(fileLink);
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
        String strLine;
        Actor[] actors = new Actor[20];
        int i = 0;
        while ((strLine = br.readLine()) != null) {
            System.out.println(strLine);
            String[] actorArr = strLine.split(" ");
            actors[i] = new Actor(actorArr[0], actorArr[1], actorArr[2], actorArr[3]);
            i++;
        }
        return actors;
    }
}