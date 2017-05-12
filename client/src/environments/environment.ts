import { AppConstant } from './../app/app.constant';
// The file contents for the current environment will overwrite these during build.
// The build system defaults to the dev environment which uses `environment.ts`, but if you do
// `ng build --env=prod` then `environment.prod.ts` will be used instead.
// The list of which env maps to which file can be found in `angular-cli.json`.

const UseDevResourceServer:boolean = false;

export const environment = {
  production: false,
  loginUrl: (UseDevResourceServer ? AppConstant.RESOURCE_SERVER_DEV : AppConstant.RESOURCE_SERVER) + "/login",
  resourceUrl: (UseDevResourceServer ? AppConstant.RESOURCE_SERVER_DEV : AppConstant.RESOURCE_SERVER) + "/rest",
  assetsUrl: "/assets"
};
