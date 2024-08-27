package malakhov.study.polimorphism.instanseof;

public class PatternMatchingForInstanceOf {
    public static void main(String[] args) {
        PatternMatchingForInstanceOf patternMatchingForInstanceOf = new PatternMatchingForInstanceOf();
        patternMatchingForInstanceOf.run();
    }

    public void run() {
        BattleCruiser battleCruiser = new BattleCruiser();
        SpaceShip.expectedTimeOfArrivalOld(battleCruiser);
    }

    //Проблема: много кода с instanceof в котором очень легко допустить ошибку и мы получим ClassCastException.
    //1. Много кода
    //2. Нет стабильности, потому что легко допустить ошибку и передать не правильный объект в метод

    //Решение:
    //1. Java 16 Pattern Matching for instanceof
    interface SpaceShip {
        static double expectedTimeOfArrivalOld(SpaceShip ship) {

            //В старом виде в условие у нас есть только предикат
            if (ship instanceof BattleCruiser) {
                BattleCruiser battleCruiser = (BattleCruiser) ship;
                //...

            } else if (ship instanceof Cargo) {
                Cargo cargo = (Cargo) ship;
                //...

            }

            return 0;
        }

        static double expectedTimeOfArrivalUpd(SpaceShip ship) {

            //В новом виде у нас добавился "pattern variable"
            //Теперь у нас происходит 2 действия:
            //1. ship instanceof BattleCruiser (если true) ->
            //2. Создание локальной переменной battleCruiser

            //Здесь невозможно допустить ошибку ClassCastException.
            if (ship instanceof BattleCruiser battleCruiser) {
                //...

            } else if (ship instanceof Cargo cargo) {
                //...

            }

            return 0;
        }
    }

    class BattleCruiser implements SpaceShip {

    }

    class Cargo implements SpaceShip {

    }
}
