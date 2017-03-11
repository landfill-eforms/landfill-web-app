# Landfill e-Forms Web Application
This project is very much still a work in progress.
## Table of Contents
* [Prerequisites](https://github.com/landfill-eforms/landfill-web-app/blob/development/README.md#prerequisites)
  * [Front-End Server](https://github.com/landfill-eforms/landfill-web-app/blob/development/README.md#front-end-server)
  * [Back-End Server](https://github.com/landfill-eforms/landfill-web-app/blob/development/README.md#back-end-server)
  * [Database Management](https://github.com/landfill-eforms/landfill-web-app/blob/development/README.md#database-management)
* [Environment Setup](https://github.com/landfill-eforms/landfill-web-app/blob/development/README.md#environment-setup)
  * [Front-End Server](https://github.com/landfill-eforms/landfill-web-app/blob/development/README.md#front-end-server-1)
  * [Back-End Server](https://github.com/landfill-eforms/landfill-web-app/blob/development/README.md#back-end-server-1)
  * [Database Management](https://github.com/landfill-eforms/landfill-web-app/blob/development/README.md#database-management-1)
* [Launching Server Instances](https://github.com/landfill-eforms/landfill-web-app/blob/development/README.md#launching-server-instances)
  * [Front-End Server](https://github.com/landfill-eforms/landfill-web-app/blob/development/README.md#front-end-server-2)
  * [Back-End Server](https://github.com/landfill-eforms/landfill-web-app/blob/development/README.md#back-end-server-2)

## Prerequisites
This section lists the prerequisites for each component of the web application separately. You will only need the prereqs for the component(s) that you want to work on or run.
### Front-End Server
* Currently, Node 4 or higher together with npm 3 or higher is required. However, Node 6.9 or higher may be required in future versions of the project, and is thus recommended. Download both Node and npm from [here](https://nodejs.org/en/download/). For Linux users, Node can be installed via [package manager](https://nodejs.org/en/download/package-manager/), and npm can be similarly installed.
* Angular-CLI (installation instructions [below](https://github.com/landfill-eforms/landfill-web-app/blob/development/README.md#installing-angular-cli)).
* Visual Studio Code, or another TypeScript editor. Download VS Code from [here](https://code.visualstudio.com/).

### Back-End Server
* Eclipse, or another Java IDE.
* Gradle Buildship plugin for Eclipse, or the equivalent if using a different IDE. Installation instructions can be found [here](http://download.eclipse.org/buildship/updates/e46/releases/2.x/2.0.0.v20170111-1029/).

### Database Management
* Microsoft SQL Server Management Studio. Download from [here](https://docs.microsoft.com/en-us/sql/ssms/download-sql-server-management-studio-ssms).

## Environment Setup
This section will help you set up the development environments for the first time. Each component will require its respective [prerequisites](https://github.com/landfill-eforms/landfill-web-app/blob/development/README.md#prerequisites) to be installed.
### Front-End Server
The following instructions assumes that you will be using Visual Studio Code as the TypeScript editor.
#### Selecting the Project Folder
1. Start Visual Studio Code.
2. Go to File->Open Folder... and select the `landfill-web-app/client` folder.

After this step, you should see the contents of the `landfill-web-app/client` folder in the Explorer pane.
#### Installing Angular-CLI
If your machine does not have Angular-CLI installed, you will need to install it. You can skip this step if Angular-CLI is already installed.

1. Open the Visual Studio Code built in console by pressing `CTRL+~`.
2. Run the command `npm install -g angular-cli` to install Angular-CLI.

Angular-CLI may take a few miniutes to download.
#### Installing npm Dependencies
1. Open the Visual Studio Code built in console by pressing `CTRL+~`, if its not already opened.
2. Make sure that the current working directory is the `landfill-web-app/client` folder.
3. Run the command `npm install` to install the project dependencies.

The dependencies may take a few miniutes to download.
### Back-End Server
1. Start Eclipse.
2. Import `landfill-web-app/server` as Gradle Project.
3. If Gradle has not automatically downloaded the project dependencies, right click on the 'server' project folder and click Gradle->Refresh Gradle Project.
4. Add the Microsoft SQL Server JDBC driver `sqljdbc42.jar` from the `server/lib` directory to the project's build path.

### Database Management
Coming soon...
## Launching Server Instances
This sections describes the process of launching a local instance of a server for testing, etc.
### Front-End Server
To launch the front-end server from within Visual Studio Code:

1. Open the Visual Studio Code built in console by pressing `CTRL+~`.
2. Make sure that the current working directory is the `landfill-web-app/client` folder.
3. Run the command `ng serve` to start the front-end server.

Note front-end server is set to listen on port 4200, so make sure that no other processes are listening on the same port number. The home page can then be accessed at <http://localhost:4200/>.
### Back-End Server

Note back-end server is set to listen on port 8080, so make sure that no other processes are listening on the same port number.
