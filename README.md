# Sales management system
## Course Project
### Project’s Objectives and Learning Goals
This is a group course project with 2 students. You can start on the project from the first day of the course and concepts that must be used will be continually discussed during the duration of the semester. This is a multitier application with ORM (Object Relational Mapping) support.
### Learning goal
* This project will focus on:
  * Creating a presentation tier in Java Swing/JSF
  * Creating a middle tier for the service interface in Spring framework
  * Creating a data tier in Hibernate framework
  
### You will need the following tools:
* Java https://java.com/en/download/
  * Spring https://spring.io/
  * Hibernate https://hibernate.org/
  * Java IDE editor:
    * NetBeans https://netbeans.apache.org/download/index.html (great in handling the jdbc:derby database)
    
### Project Details
The company MA Scene Inc. is a small local boutique store specializing in selling modern style clothing and accessories. The currency is in Canadian dollars. They requested a desktop application to view and sell the items in the store by the sales associates. All the items have a bar code associated with it, stock numbers (quantity of the item available), price, title (name of item), colour, text description, and sizes (if applicable). Before the final purchase, a full order list with prices and a tax amount of 13% is added to the total. The pay now (doesn’t have to really work but a pop up window saying “Purchased” is sufficient). Finally, the order history is stored in the system to be viewed at any time with a timestamp of purchase by sales or administrator.

The backend where the administrative personnel can access the shop allows them to add new items to the store, edit them, and remove them completely. An item that is out of stock (0 items left) or not available for a few days has a textual area to add the reason why it is not available to be purchased, and the reason can be viewed by the sales associate too.

The owners of the company have asked you to develop this in-house store system with the credentials mentioned above. If your team wants to create an additional feature of search items by title or bar code number in the front and backend of the store then it will be a 10% bonus on the final delivered product. **(It is a bonus of 10% on this course project grade.)**

To understand how you will approach this project, please look at the following architecture diagram to view the relationship of the components used in designing and programming such a system.
