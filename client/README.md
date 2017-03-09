# Front-End (Client) Server
This project was generated with [angular-cli](https://github.com/angular/angular-cli) version 1.0.0-beta.22-1. It has since been updated to version 1.0.0-beta.32.3. _TODO: List some of the frameworks used._
## Table of Contents
Coming Soon

## Development Notes
This section contains notes and references that can be useful during development of the front-end server.

### Development Server
Run `ng serve` for a dev server. Navigate to `http://localhost:4200/`. The app will automatically reload if you change any of the source files.

### Components
Components typically contain a view (html), controller (typescript), and style (scss).

#### Creating New Components
* In this project, all of the components are located in the `src/app/components` directory, and each component is part of a module. The module is then added under the imports in `AppModule`. It is recommended to follow these conventions when creating new components for this particular project.

* It is NOT recommended to use `ng generate component component-name` to generate new components, as it doesn't follow project conventions. To be specific, it will create the component in the `src/app` directory and add the component directly as an import in `AppModule`.

### Dialogs

#### Creating New Dialogs
* Need to add it to @NgModule.entryComponents.

## Deployment Notes
...

### Build
Run `ng build` to build the project. The build artifacts will be stored in the `dist/` directory. Use the `-prod` flag for a production build.

### Deploying to Github Pages
Run `ng github-pages:deploy` to deploy to Github Pages.
