# An example of GraphQL
In this project you can add, list and delete a player from a team with GraphQL query and mutations.
The project is also an example of usage the [dgs-framework](https://github.com/Netflix/dgs-framework)

## Application Startup
You can directly use docker-compose to start the database (PostgreSQL) and application. 
```sh
docker-compose up -d
```
After that you can connect to api on 8080 port.

## Usage
If you want to use your GraphQL client you can access it with http://localhost:8080/graphql address.

You can also the built-in interface for the usage of GraphQL requests with that url http://localhost:8080/graphiql

## Commands
List all active players.
```sh
query List {
  list {
    id
    name
    surname
    position
  }
}
```
***
Add a player to team.
```sh
mutation Create {
  create(request: {
    name: "Name",
    surname: "Surname",
    position: CENTER
  }) {
    id
    name
    surname
    position
  }
}
```
Available positions for the request :
* POINT_GUARD
* SHOOTING_GUARD
* SMALL_FORWARD
* POWER_FORWARD
* CENTER

***
Delete a player with id.
```sh
mutation Delete {
  delete(id : 1)
}
```