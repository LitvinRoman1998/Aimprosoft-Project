Сопроводительный текст для развёртывания приложения:
1. C помощью MySQL импортировать базу данных;
2. Открыть проект(aimprosoft) при помощи Intellij IDEA;
3. Открыть класс ConnectionFactory и заменить параметр password на ваш пароль MySQL
property.setProperty("password","12345678"(поле, которое необходимо заменить));
4. Запустить проект при помощи Apache tomcat(apache-tomcat-8.5.37 использовался при разработке) и пройти по адресу http://localhost:8080/departments.
