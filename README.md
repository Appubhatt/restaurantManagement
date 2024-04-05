<span align="center">
<h1>Restaurant management</h1>
</span>

## Description:
- This is a web based restaurant management app in which there are two types of users: Admin, Restaurant.
- Admin will have the credentials to log in and Restaurant have to  register themselves to access the application.

## Role:- Admin
- Admin can manage the cities, areas of city, food category and subcategory.
- They can also manage the restaurant, offers on the product and complains from the Restaurants.

## Role:- Restaurant
- Restaurants will only be from the area and city which is created by Admin.
- Restaurant will only use the category and subcategory which is declared by Admin.
- Restaurant will create Their own food products.
- Restaurant will create offers on food products.
- If restaurant is facing any difficulties they can send their complains to Admin to solve it.

## Technology Used:
- For front-end development Html, Css and JavaScript is used.
- For back-end development Spring boot application is used.
- Database is manage by postgres.

## Database Requirement:
- Please create one database in postgres and update the database name and password inside application.properties file in of the folder.
- Note: if using any other database please add the dependancy to pom.xml file and update the spring.jpa.properties.hibernate.dialect in application.properties. Don't forget to give url, username and password of your database.
