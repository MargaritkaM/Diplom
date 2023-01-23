# Файлы и директории:

- Файлы docker-compose-posgreSQL.yml, application.properties находятся в корне проекта
- Jar файл и gate-simulator находятся в папке artifacts
- Код тестов находится в папке src/test/java/ru/netology

# Описание шагов запуска теста, приводящий к ошибке:

1. Склонировать [проект](https://github.com/MargaritkaM/Diplom.git)

2. В application.properties прописано:

>spring.credit-gate.url=http://89.223.70.43:9999/credit
>
>spring.payment-gate.url=http://89.223.70.43:9999/payment
>
>spring.datasource.url=jdbc:postgresql://89.223.70.43:5432/app
>
>spring.datasource.username=margo
>
>spring.datasource.password=queenMargo

3. Склонировать проект на удаленную машину для запуска контейнера

4. Поднять контейнеры postgresql, Node.js в корне проекта склонированного проекта на удаленной машине:

>docker-compose up

5. Запустить jar файл через команду в терминале IDEA:

>java "-Dspring.datasource.url=jdbc:postgresql://89.223.70.43:5432/app" -jar artifacts/aqa-shop.jar

6. Запустить первый автотест "buyHappyPathApproved" используя зеленый треугольник в IDEA либо через команду в терминале:

>gradlew clean test -Ddb.url=jdbc:postgresql://89.223.70.43:5432/app -Dapp.url=http://localhost:8080

7. Появилась ошибка:

>The authentication type 10 is not supported. Check that you have configured the pg_hba.conf file to include the client's IP address or subnet, and that it is using an authentication scheme supported by the driver.
org.postgresql.util.PSQLException: The authentication type 10 is not supported. Check that you have configured the pg_hba.conf file to include the client's IP address or subnet, and that it is using an authentication scheme supported by the driver.

8. Отчет об ошибке можно посмотреть build/reports/tests/test/classes/test.BuyTest.html

9. Остановить контейнеры через команду в терминале:

>docker-compose down
