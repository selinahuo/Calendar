# Design Decisions

We have designed our calendar application with the clean architecture in mind. We will organize our design decisions by the layers within the clean architecture. We will also refer to UMLDiagrams to supplement our explanations.

## Entities
* The entities package contains all of our entity classes. See UML diagram #1.
* Each entity class represents a core entity of our calendar app and the business logic of the application are contained in these classes.
* Since our entity classes are the highest-level, to satisfy the Dependency Inversion Principle they do not depend on the rest of the codebase.

## Use Cases
* For each entity we have an entity manager responsible for application specific interaction with each entity. This supports the Single Responsibility Principle as each entity may be responsible to different actors.
* Since most entities rely on events, other managers use the dependency injection pattern to interact with an instance of the EventManager. This supports the Single Responsibility Principle as all operations relating to events are contained in one class. If an actor decides to change how events are created, the other managers will not need to change. See UML diagram #2.
* The entity manager classes only interact with entities through entity repository interfaces. See UML diagram #3. This allowed us to delay the decision for the implementation of persistent storage. When we switched from CSV files to serial files at the end of phase 1, the use case classes did not need to react as all repository implementations implemented repository interfaces. This supports a number of SOLID principles:
	* SRP - use case classes are only responsible to application logic actors and not data storage implementation actors
	* OCP & LSP - repository implementations can be substituted allowing us to change implementations without having to modify old implementations, but by simply adding new classes that implement existing interfaces.
	* DIP - application logic classes and repository implementations both rely on the higher level repository abstraction.
* Since many entities are reliant on events we need to maintain persistent state when events are deleted so we used the observer design pattern. The EventManager acts as the observable class notifying other managers implementing observer interfaces to respond to event deletion. See UML diagram #4.
* Finally in the use case layer we used the facade design pattern to provide a simpler interface for all the entity managers. This means our controller/presenter classes only need to interact with the UseCaseManager. See UML diagram #5.

## Interface Adapters
* In the controller package, we have three types of classes: adapters, controllers, and view models.
* Adapters are responsible for converting data to forms most convenient for use cases and view classes (our GUI).
* Our Controller class acts as the controller and presenter. As a controller: it uses adapters to convert requests and forward requests to the UseCaseManager. As a presenter it takes the UseCaseManager’s response and uses the adapters to convert complex responses to view models. See UML diagram #6.
* View models are returned to views so that view classes may remain humble objects responsible for simply printing our formatted strings representing entities.
* Our repository implementation classes (dataaccess package) live in this layer and their interfaces are discussed in the use case section. We used the factory design pattern to abstract repository construction so that the repository implementation can be changed by adding a new repository class and modifying the factory. See UML diagram #8. This pattern contributes to the repository interfaces in the use case layer to support the SRP, OCP, LSP, and DIP.

## Frameworks and Drivers
* Our GUI composed of the views package live in this layer.
* Our view classes are responsible for printing output and accepting user input. A number of classes support them. See UML diagram #7.
	* LocalStorage is a class for session specific data, such as the currently signed in user and their “clipboard”, a feature described in the features list.
	* A controller instance is injected so each view may forward their inputs to the controller
	* View models can also be injected to display complex information. They allow the view classes to be humble objects responsible for displaying formatted entity strings.
* Our serial files, allow persistent data storage and are the outermost layer of storage. The repository implementation classes interact with these files.

## Misc
* When we run our app from Main we instantiate a CalendarApp and the majority of the instantiation of managers, controllers, etc. is in the CalendarApp class.
* We made this decision in response to feedback from phase 1. The CalendarApp class simply instantiates the concrete classes without unecessarily using factories or builder design pattern (which was overkill).
* This class is dependent on many classes, so we have made it an irresponsible class which allows us to easily change CalendarApp. Our program remains flexible since we can easily modify CalendarApp to switch out classes of our program, while also avoiding execessive complexity associated with the unecessary factories and interfaces we used in phase 1.