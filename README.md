# dataBase Accounting Employees & Salaries (dBAES)
#### *"No one is forgotten!"*


## Это репозиторий проекта "AES" 
Pet-project № 1
#### Он позволяет работать с  базой данный в компании, включающей в себя сотрудников, данные их карточек трудоустройства, сведения о графике работы, количестве отработанных дней. ПОзволяет делать расчеты заработной платы, бонусов, отсавшихся дней отпуска и их оплаты.

Поставленные задачи:
- [X] Создать БД сотрудников, департаментов, грейдов
- [X] Создать эндпойнты, согласно REST
- [X] Создать функционал DAL
- [ ] Создать калькулятор зарплат
- [ ] Создать калькулятор отпусков

Приложение написано на Java при помощи Spring и сопустствующих библиотек. Пример кода:

```java
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BankEmployeesSalariesApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankEmployeesSalariesApplication.class, args);
    }

}
```

#### ER-диаграмма базы данных для приложения:

![This is ER-diagramme](database_BAES_v1.png)

<!-- #### Примеры запросов к базе данных:

Получаем наименование жанров по названиям фильмов:
```
SELECT f.name,
	   g.name
FROM film AS f
JOIN genre AS g ON g.genre_id=f.genre_id 
```
Получаем названия фильмов с рейтингом:
```
SELECT f.name,
	   r.name
FROM film AS f
JOIN rate AS r ON r.rate_id=f.rate_id
```
Получаем количество лайков у фильмов:
```
SELECT f.name,
	   COUNT(l.user_id)
FROM film AS f
JOIN like AS l ON l.film_id=f.film_id
GROUP BY f.name
```
Получаем фильмы, которые нравятся Васе:
```
SELECT u.name,
	   f.name
FROM film AS f
JOIN like AS l ON l.film_id=f.film_id 
JOIN user AS u ON l.user_id=u.user_id
WHERE u.name = 'Vasya'
GROUP BY user_name
```
Получаем пользователей, которым нравится фильм Дюна:
```
SELECT u.name
FROM user AS u
JOIN like AS l ON l.user_id=f.user_id 
JOIN film AS f ON f.film_id=l.film_id
WHERE f.name = 'Dune'
```
Получаем друзей Васи:
```
SELECT f.name
FROM user AS u
JOIN friendly_status AS fs u.user_id=fs.user_id
JOIN friend AS f ON fs.user_id=f.friend_id
WHERE user_name='Vasya'
``` -->
