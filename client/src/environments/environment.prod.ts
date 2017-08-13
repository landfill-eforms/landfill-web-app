import { AppConstant } from './../app/app.constant';

export const environment = {
  production: false,
  loginUrl: AppConstant.RESOURCE_SERVER + "/login",
  resourceUrl: AppConstant.RESOURCE_SERVER + "/rest",
  assetsUrl: "./assets"
};