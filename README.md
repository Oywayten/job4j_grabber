# job4j_grabber
В этом репозитории проект - **Агрегатор вакансий**.

Подключены maven, jacoco, checkstyle.

Приложение должно собирается в jar.

### Описание.

Система запускается по расписанию. Период запуска указан в настройках - app.properties. 

Первый сайт будет [career.habr.com](https://career.habr.com/ "переход на сайт career.habr.com"). В нем есть раздел /vacancies. Программа считывает все вакансии относящиеся к Java и записывает их в базу.

Доступ к интерфейсу через REST API.


### Расширение.

1. Можно добавлять новые сайты без изменения кода.

2. Можно делать параллельный парсинг сайтов.
