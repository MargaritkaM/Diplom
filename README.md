# Дипломный проект

## План автоматизации и отчетные документы:
* [План автоматизации](https://github.com/MargaritkaM/Diplom/blob/master/Plan.md)
* [Отчётные документы по итогам автоматизированного тестирования](https://github.com/MargaritkaM/Diplom/blob/master/Report.md)
* [Отчётные документы по итогам автоматизации](https://github.com/MargaritkaM/Diplom/blob/master/Summary.md)


# Файлы и директории:

- Файлы docker-compose.yml, application.properties находятся в корне проекта
- Jar файл и gate-simulator находятся в папке artifacts
- Код тестов находится в папке src/test/java/test/

# Описание процедуры запуска автотестов:

1. Склонировать [проект](https://github.com/MargaritkaM/Diplom.git)

2. Параметры [application.properties](https://github.com/MargaritkaM/Diplom/blob/master/application.properties)

3. Параметры [docker-compose.yml](https://github.com/MargaritkaM/Diplom/blob/master/docker-compose.yml)
4. Склонировать проект на удаленную машину для запуска контейнера
 
 `адрес - 89.223.70.43`
 
5. Поднять контейнеры postgresql, Node.js:
 
 ` директория Diplom5-Diplom`
 
 ` команда docker-compose up`
 
6. Запустить jar файл через команду в терминале IDEA:

 ` java "-Dspring.datasource.url=jdbc:postgresql://89.223.70.43:5432/app" -jar artifacts/aqa-shop.jar`
 
7. Запустить тесты используя зеленый треугольник в IDEA либо через команду в терминале:

 ` gradlew clean test -Ddb.url=jdbc:postgresql://89.223.70.43:5432/app -Dapp.url=http://localhost:8080`
 
8. Остановить контейнеры через команду в терминале:

 ` docker-compose down`
