import java.util.Random;
import java.util.Scanner;

/**
 * Created by d405 on 26.04.17.
 */
class CheckAddition {
    final Random rand = new Random();
    private int counter = 0;
    private int rightAnswersCounter = 0;
    private int maxInteger = 98; //ограничение для рандомного числа(чтоб не больше 99)
    private int firstRandomNumber;
    private int secondRandomNumber;
    private Scanner sc = new Scanner(System.in);
    final int keyToRightAnswer = 111;


    private void generateRandomNumbers() {
        firstRandomNumber = (rand.nextInt(maxInteger) + 1); // Генерация 1-го числа, прибавил 1, чтоб слагаемые начинались с единицы
        secondRandomNumber = (rand.nextInt(maxInteger) + 1); // Генерация 2-го числа, прибавил 1, чтоб слагаемые начинались с единицы
        if (firstRandomNumber + secondRandomNumber >= 100) { //ограничение на сумму не более 99
            generateRandomNumbers();
        }
    }

    private void tryToSolve() {
        System.out.print(firstRandomNumber + " + " + secondRandomNumber + " = ");
        int inputAnswer = sc.nextInt();

        int answer = firstRandomNumber + secondRandomNumber; //костыль на обход "constant expression required"
        if (answer == inputAnswer) {
            inputAnswer = keyToRightAnswer;
        }

        switch (inputAnswer) {
            case 999: //выход из бесконечного цикла
                System.out.println("Тренировка окончена. Решенных примеров: " + rightAnswersCounter);
                System.out.println("Всего попыток: " + counter);
                break;
            case 111: //костыль на константу
                System.out.println("Верно!");
                rightAnswersCounter++;
                counter++;
                generateNewTask();
                break;
            default:
                counter++;
                System.out.println("Неправильно! Попробуй еще раз!");
                tryToSolve();
                break;
        }
    }

    public void generateNewTask() {
        generateRandomNumbers();
        tryToSolve();
    }
}
