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

2. В application.properties прописано:

> spring.credit-gate.url=http://89.223.70.43:9999/credit
>
> spring.payment-gate.url=http://89.223.70.43:9999/payment
>
> spring.datasource.url=jdbc:postgresql://89.223.70.43:5432/app
>
> spring.datasource.username=user
>
> spring.datasource.password=pass

3. В docker-compose.yml прописано:
` version: '3.7'`

`   services:`
`    postgres:`
`      image: postgres:12.0-alpine`
`      restart: always`
`      environment:`
`       - POSTGRES_USER=user`
`       - POSTGRES_PASSWORD=pass`
`       - POSTGRES_DB=app`
`      ports:`
`       - '5432:5432'`
`    node-app:`
`      build: ./artifacts/gate-simulator`
`      image: latest`
`      container_name: node`
`     ports:`
`       - '9999:9999'~`



3. Склонировать проект на удаленную машину для запуска контейнера

> адрес - 89.223.70.43

4. Поднять контейнеры postgresql, Node.js в корне проекта склонированного проекта на удаленной машине:

> директория Diplom5-Diplom 

> команда docker-compose up

5. Запустить jar файл через команду в терминале IDEA:

> java "-Dspring.datasource.url=jdbc:postgresql://89.223.70.43:5432/app" -jar artifacts/aqa-shop.jar

6. Запустить тесты используя зеленый треугольник в IDEA либо через команду в терминале:

> gradlew clean test -Ddb.url=jdbc:postgresql://89.223.70.43:5432/app -Dapp.url=http://localhost:8080

7. Остановить контейнеры через команду в терминале:

>docker-compose down
