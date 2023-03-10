# Отчётные документы по итогам тестирования

## Краткое описание

В рамках тестирования была проверена:

- работа web-формы по покупке тура по карте и оформления в кредит через веб-браузер Google Chrome
- взаимодействие приложения с банковским сервисом (симулятором Payment Gate и Credit Gate)
- сохранение информации в БД(PostgreSQL)
- обработка ответов сервиса в виде сообщений об статусе операции для клиента
- появление ошибок в случае некорректного заполнения формы

В написании тест-кейсов мною были использованы различные методики (обычные тесты и параметризированные) умышлено, для получения максимальной тренировки. 

## Количество тест-кейсов

`Всего` 75 тестов

`Успешные` 61 (81%)

`Неуспешные` 14 (19%)

![image](https://user-images.githubusercontent.com/106307755/214855082-41cf1844-0115-411f-b9b1-41e33abb3a84.png)
![image](https://user-images.githubusercontent.com/106307755/215082926-7bee8c27-6706-4386-937e-e3e73709703f.png)


## Список зарегистрированных дефектов

1.  [Возникает сообщение об успешной оплате в Payment Gate при фактическом статусе по операции "DECLINED"](https://github.com/MargaritkaM/Diplom/issues/1)
2.  [Возникает сообщение об успешной оплате в Credit Gate при фактическом статусе по операции "DECLINED"](https://github.com/MargaritkaM/Diplom/issues/2)
3.  [Возникает сообщение об ошибке в Paymont Gate при указание нетестируемого номера карты](https://github.com/MargaritkaM/Diplom/issues/3)
4.  [Возникает сообщение об ошибке в Credit Gate при указание нетестируемого номера карты](https://github.com/MargaritkaM/Diplom/issues/4)
5.  [Успешная операция при указании месяца действия карты "00" в Paymont Gate](https://github.com/MargaritkaM/Diplom/issues/5)
6.  [Успешная операция при указании месяца действия карты "00" в Credit Gate](https://github.com/MargaritkaM/Diplom/issues/6)
7.  [Условия заполнения поля "Владелец" не установлены, при вводе невалидных значений операции проходят в Paymont Gate](https://github.com/MargaritkaM/Diplom/issues/7)
8.  [Условия заполнения поля "Владелец" не установлены, при вводе невалидных значений операции проходят в Credit Gate](https://github.com/MargaritkaM/Diplom/issues/8)
9.  [Возникает сообщение об ошибке в поле "Владелец" при пустом поле "CVC/CVV" в Paymont Gate](https://github.com/MargaritkaM/Diplom/issues/9)
10. [Возникает сообщение об ошибке в поле "Владелец" при пустом поле "CVC/CVV" в Credit Gate](https://github.com/MargaritkaM/Diplom/issues/10)

## Общие рекомендации:

- Доработать приложение в части устранения дефектов
- Разработать подробные требования в документации
