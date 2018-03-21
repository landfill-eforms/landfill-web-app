import { AppConstant } from './../app/app.constant';
// The file contents for the current environment will overwrite these during build.
// The build system defaults to the dev environment which uses `environment.ts`, but if you do
// `ng build --env=prod` then `environment.prod.ts` will be used instead.
// The list of which env maps to which file can be found in `angular-cli.json`.

// Change this to true to test with local server (ie. Eclipse).
const UseLocalResourceServer: boolean = true;

const ResourceServerUrl: string = "http://landfill-dev-server.us-west-1.elasticbeanstalk.com/";
const LocalResourceServerUrl: string = "http://localhost:8080";


export const environment = {
  production: false,
  loginUrl: (UseLocalResourceServer ? LocalResourceServerUrl : ResourceServerUrl) + "/login",
  resourceUrl: (UseLocalResourceServer ? LocalResourceServerUrl : ResourceServerUrl) + "/rest",
  assetsUrl: "/assets"
};
