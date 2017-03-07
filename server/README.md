# Back-End Server
This is the back-end server of the Landfill e-Forms web application. It is built on a number of Java frameworks, including Spring, Hibernate, and AspectJ.
## Table of Contents
Coming Soon

## Development Notes
This section contains notes and references that can be useful during development of the back-end server.
### Persistence
Database access and mapping is mostly handled by Hibernate 5. The configuration class is `DataSourceConfig.java`, which is in the `org.lacitysan.landfill.server.config` package. The other classes related to persistence are located in the `org.lacitysan.landfill.server.persistence` package and any sub-packages. 
#### Configuration
* Handled by `DataSourceConfig.java`, located in the `org.lacitysan.landfill.server.config` package.
* Contains a list of persistence entity classes.

#### Entity Classes
* When a new persistence entity class is created, it must be added into the `DataSourceConfig.java` configuration file. Likewise, when an existing persistence entity class is deleted, it must be removed from the configuration file.
