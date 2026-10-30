> **Archived.** This repository is no longer maintained
> and is kept read-only. It may be removed in the future.

# Atomikos with Spring Boot JTA with Postgresql
Spring Boot Atomikos JTA PoC with Postgresql for managing Distributed Transactions over multiple databases.

## DB Structure: 

#### Customer
* DB Name : customer
* DB Schema : public
* DB Tables : customer

#### Account
* DB Name : account
* DB Schema : public
* DB Tables : account

## Creating DBs with docker-compose:
Go to the directory /docker and run `docker-compose up`
