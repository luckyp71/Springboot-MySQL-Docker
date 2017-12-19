# Springboot-MySQL-Docker

test jenkins webhooks..

This repository is just an example on how to dockerize Spring Boot application with an embedded tomcat server. The app could perform CRUD operations using particular HTTP methods i.e. GET, POST, PUT, and DELETE.

<h3>Prerequisites</h3>
<p>1. Spring Tool Suite </p>
<p>2. Spring Boot</p>
<p>3. Maven</p>
<p>4. Tomcat Embedded</p>
<p>5. Docker Toolbox</p>
<p>6. java:8 Image</p>
<p>7. mysql:latest Image</p>

<p>You could either run the app in localhost or in docker default IP (192.168.99.100).
But since this repository aims to run an image of springboot project, you should choose the docker instead.
</p>
<p>
The following steps are essential to get this project running:
</p>
<p>1. Pull this project from docker hub:</p>
<p>$sudo docker pull luckyp71/simpleboot</p>

<p>2. Pull mysql image from your docker toolbox:</p> 
<p>$sudo docker pull mysql</p>

<p>3. Set the mysql root password and named the container as database:</p>
<p>$sudo docker run --name database -e MYSQL_ROOT_PASSWORD=my-secret-pw -d mysql</p>

<p>4. Enter mysql with your user credential:</p>
<p>$sudo dokcer exec -it daabase mysql -uroot -p</p>

<p>5. Create database called springbootdb and table called employee with table structure as follows:</p>

<table>
  <thead>
  <tr>
  <th>Field</th>
  <th>Type</th>
  <th>Null</th>
  <th>Key</th>
  <th>Default</th>
  </tr>
  </thead>
  
  <tbody>
  <tr>
  <td>id</td>
  <td>int</td>
  <td>NO</td>
  <td>Primary Key</td>
  <td>NULL</td>
  </tr>
  <tr>
  <td>fname</td>
  <td>VARCHAR(100)</td>
  <td>NO</td>
  <td></td>
  <td>NULL</td>
  </tr>
  <tr>
  <td>lname</td>
  <td>VARCHAR(100)</td>
  <td>NO</td>
  <td></td>
  <td>NULL</td>
  </tr>
  <tr>
  <td>gender</td>
  <td>VARCHAR(10)</td>
  <td>NO</td>
  <td></td>
  <td>NULL</td>
  </tr>
  <tr>
  </tbody>
  </table>
  
  <p>
6. Run this project image and link with mysql inside docker container which we already named it as database:
docker run --rm -p 8090:8090 --name simpleboot --link database:mysql luckyp71/simpleboot
</p>
<p>
7. Now you could access the resources through REST URLs. Below are the list of URLs:</p>
<p> a. Return employees data with json format (HTTP Method-> GET): http://196.168.99.100:8090/app/getEmployees</p> 
<p> b. Return an employee object with json format (HTTP Method-> GET): http://196.168.99.100:8090/app/getEmployee?id=?</p>
<p> c. Add employee (HTTP Method-> POST): http://196.168.99.100:8090/app/addEmployee?id=?&fname=?&lname=?&gender=?</p>
<p> d. Update employee (HTTP Method-> PUT): http://196.168.99.100:8090/app/updateEmployee?id=?&fname=?&lname=?&gender=?</p>
<p> e. Delete employee (HTTP Method-> DELETE): http://196.168.99.100:8090/app/deleteEmployee?id=?</p>
